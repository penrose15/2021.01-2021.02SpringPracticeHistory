package org.zerock;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.FreeBoard;
import org.zerock.domain.FreeBoardReply;
import org.zerock.persistence.FreeBoardReplyRepository;
import org.zerock.persistence.FreeBoardRepository;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit //트랜잭션종료 후 업데이트 된것을 모든 사람들에게 공개
public class FreeBoardTest {
	
	@Autowired
	FreeBoardRepository boardRepo;
	
	@Autowired
	FreeBoardReplyRepository replyRepo;
	
	/*
	@Test
	public void dummiedata() {
		IntStream.range(1, 200).forEach(i -> {
			FreeBoard board = new FreeBoard();
			board.setTitle("Free Board..."+i);
			board.setContent("Free Content..."+i);
			board.setWriter("user"+i%10);
			
			boardRepo.save(board);
		});
	}*/
	/*
	@Transactional
	@Test  //양방향이므로 FreeBoard객체를 얻어온 후 FreeBoardReply를 댓글 리스트에 추가한 후에 FreeBoard 자체를 저장함
	public void insertReply2Way() {
		Optional<FreeBoard> result = boardRepo.findById(199L);
		
		result.ifPresent(board -> {
			List<FreeBoardReply> replies = board.getReplies();
			FreeBoardReply reply = new FreeBoardReply();
			reply.setReply("Reply...........");
			
			replies.add(reply);
			
			board.setReplies(replies);
			
			boardRepo.save(board);
		});
	}
	
	@Test//단방향
	public void insertReply1Way() {
		FreeBoard board = new FreeBoard();
		board.setBno(198L);
		
		FreeBoardReply reply = new FreeBoardReply();
		reply.setReply("REPLY..........");
		reply.setReplyer("replyer00");
		reply.setBoard(board);
		
		replyRepo.save(reply);
	}
	*/
	/*
	@Test //pageRequest() : 몇페이지, 한페이지 사이즈, Sorting방법을 가지고 Repository에 Paging을 요청할 때 사용하는 것
	public void testList1() {
		Pageable page = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
		
		boardRepo.findByBnoGreaterThan(0L, page).forEach(board -> {
			log.info(board.getBno()+": "+board.getTitle());
		});
	}*/
	/*
	@Transactional
	@Test
	public void testList2() {
		Pageable page = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
		
		boardRepo.findByBnoGreaterThan(0L, page).forEach(board -> {
			log.info(board.getBno()+": "+board.getTitle()+": "+board.getReplies().size());
		});
	}*/
	
	@Test
	public void testList3() {
		Pageable page = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
		
		boardRepo.getPage(page).forEach(arr -> log.info(Arrays.toString(arr)));
		
	}
	
	

}
