package board.board.controller;

import java.util.List;
import java.net.URLEncoder;
import java.io.File;

import javax.servlet.http.HttpServletResponse;

//import board.common.FileUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.configurationprocessor.metadata.ItemHint.ValueProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.io.FileUtils;


import board.board.dto.BoardDto;
import board.board.dto.BoardFileDto;
import board.board.service.BoardService;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//스프링 MVC컨트롤러 : 클라이언트가 요청을 하면 @controller에 진입한다
//컨트롤러는 요청에 대한 작업을 수행하고 뷰쪽으로 데이터를 전달한다

@Controller  //@컨트롤러 를 이용해 클래스를 생성한다
public class BoardController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
		
		
		@Autowired
        private BoardService boardService;

        @RequestMapping("/board/openBoardList.do")  //@RequestMapping로 ()안은 뷰의 경로를 설정하는 거다
        public ModelAndView openBoardList() throws Exception {
        	log.debug("openBoardList");
        	
            
        	//ModelAndView는 데이터랑 뷰를 동시에 선언 ㄱㄴ()안은 뷰의 이름
        	ModelAndView mv = new ModelAndView("/board/boardList");

            List<BoardDto> list = boardService.selectBoardList();
            mv.addObject("list", list);  //addObject로 데이터 추가(key, value)
           

            return mv;
        }
        
        @RequestMapping("/board/openBoardWrite.do")
        public String openBoardWrite() throws Exception{
        	return "/board/boardWrite";
        }
        @RequestMapping("/board/insertBoard.do")
        public String insertBoard(BoardDto board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception{
        	boardService.insertBoard(board, multipartHttpServletRequest);  //public String insertBoard(BoardDto board, MultipartHttpServletRequest multipartHttpServletRequest) 에 multipartHttpServletRequest가 파라미터로 추가됨
        	return "redirect:/board/openBoardList.do";  //이는 ServletRequest를 상속받아 구현된 인터페이스로 업로드 된 파일을 처리하기 위해 여러 메소드를 제공한다
        }
        
        @RequestMapping("/board/openBoardDetail.do")
        public ModelAndView openBoardDetail(@RequestParam ("boardIdx") int boardIdx) throws Exception{
            ModelAndView mv = new ModelAndView("/board/boardDetail");
            //int i=10/0;

            BoardDto board = boardService.selectBoardDetail(boardIdx);
            mv.addObject("board", board);

            return mv;
        }
        
        @RequestMapping("/board/updateBoard.do")
        public String updateBoard(BoardDto board) throws Exception{
        	boardService.updateBoard(board);
        	return "redirect:/board/openBoardList.do";
        }
        
        @RequestMapping("/board/deleteBoard.do")
        public String deleteBoard(int boardIdx) throws Exception{
        	boardService.deleteBoard(boardIdx);
        	return "redirect:/board/openBoardList.do";
        }
        @RequestMapping("/board/downloadBoardFile.do")
        public void downloadBoardFile(@RequestParam ("idx") int idx, @RequestParam ("boardIdx") int boardIdx, HttpServletResponse response) throws Exception{
        	BoardFileDto boardFile = boardService.selectBoardFileInformation(idx, boardIdx);
        	if(ObjectUtils.isEmpty(boardFile)==false) {
        		String fileName = boardFile.getOriginalFileName();
        		
        		byte[] files = FileUtils.readFileToByteArray(new File(boardFile.getStoredFilePath()));
        		
        		response.setContentType("application/octet-stream");
        		response.setContentLength(files.length);
        		response.setHeader("Content-Disposition", "attachment; fileName=\""+ URLEncoder.encode(fileName,"UTF-8")+"\";");
        		//response.setHeader("Content-Transfer-Encoding", "binary");
        		
        		response.getOutputStream().write(files);
        		response.getOutputStream().flush();
        		response.getOutputStream().close();
        	}
        }
        
        
}