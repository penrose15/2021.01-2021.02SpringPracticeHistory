// BoardServiceImpl.java
package board.board.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import board.board.dto.BoardDto;
import board.board.dto.BoardFileDto;
import board.board.mapper.BoardMapper;
import board.common.FileUtils;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;
    
    @Autowired
	private FileUtils fileUtils;

    @Override
    public List<BoardDto> selectBoardList() throws Exception {
        return boardMapper.selectBoardList();
    }

	@Override
	public void insertBoard(BoardDto board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		boardMapper.insertBoard(board);  //게시물 등록한다
		List<BoardFileDto> list = fileUtils.parseFileInfo(board.getBoardIdx(), multipartHttpServletRequest);  //Fileutils라는 클래스를 이용해서 업로드된 파일을 서버에 저장하고 파일의 정보를 가져옴
		if(CollectionUtils.isEmpty(list)==false) {
			boardMapper.insertBoardFileList(list);
		}
		
		if(ObjectUtils.isEmpty(multipartHttpServletRequest)==false) {
			Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
			String name;
			while(iterator.hasNext()) {
				name = iterator.next();
				log.debug("file tag name : "+ name);
				List<MultipartFile> list1 = multipartHttpServletRequest.getFiles(name);
				for(MultipartFile multipartFile : list1) {
					log.debug("start file imformation");
					log.debug("file name : "+multipartFile.getOriginalFilename());
					log.debug("file size : "+multipartFile.getSize());
					log.debug("file content type :"+multipartFile.getContentType());
					log.debug("end file information.\n");
				}
			}
		}
		
	}

	@Override
    public BoardDto selectBoardDetail(int boardIdx) throws Exception{
        BoardDto board = boardMapper.selectBoardDetail(boardIdx);  //게시글 내용 조회
        List<BoardFileDto> fileList = boardMapper.selectBoardFileList(boardIdx);  //게시글 번호로 게시글의 첨부파일 목록을 조회하고 게시글의 종보를 담고 있는 BoardDto클래스에 조회된 첨부파일 목록을 저장한다ㅏ
        board.setFileList(fileList);
        
        boardMapper.updateHitCount(boardIdx);  //조회수 증가
        //int i=10/0;

        return board;
    }

	@Override
	public void updateBoard(BoardDto board) throws Exception {
		boardMapper.updateBoard(board);
		
	}

	@Override
	public void deleteBoard(int boardIdx) throws Exception {
		boardMapper.deleteBoard(boardIdx);
		
	}

	@Override
	public BoardFileDto selectBoardFileInformation(int idx, int boardIdx) throws Exception {
		return boardMapper.selectBoardFileInformation(idx, boardIdx);
	}
	
	
}