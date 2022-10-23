package org.zerock;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.domain.Board;
import org.zerock.domain.QBoard;
import org.zerock.persistence.BoardRepository;

import com.querydsl.core.BooleanBuilder;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class Boot03ApplicationTests {
	
	@Autowired
	private BoardRepository repo;

	/*
	@Test
	public void testInsert200() {
		for(int i=1;i<=200; i++) {
			Board board = new Board();
			board.setTitle("제목.."+i);
			board.setContent("내용...."+i+"채우기");
			board.setWriter("user0"+(i%10));
			repo.save(board);
		}
	}
	*/
	
	//@Test
	//public void testByTitle() {
		//repo.findBoardByTitle("제목..177").forEach(board->System.out.println(board));
		
		//find...by...는 find 뒤에 엔티티 타입 지정하고 by 뒤에 칼럼명을 구성한다
	//}
	//이 외에도 존나 많은 쿼리 메소드가 있다
	//https://docs.spring.io/spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-creation
	
	//@Test
	//public void testByWriter() {
		//Collection<Board> results = repo.findByWriter("user00");
		
		//results.forEach(board -> System.out.println(board));
		
	//}
	//@Test
	//public void testByWriterContaining() {
		//Collection<Board> results = repo.findByWriterContaining("05");
		//results.forEach(board->System.out.println(board));
	//}
	/*
	@Test
	public void testByTitleOrContentsContainint() {
		Collection<Board> results = repo.findByTitleContainingOrContentContaining("6", "채우기");
		results.forEach(board ->System.out.println(board));
	} */
	/*
	@Test
	public void testByTitleAndBno() {
		Collection<Board> results = repo.findByTitleContainingAndBnoGreaterThan("5", 50L);
		results.forEach(board -> System.out.println(board));
		
	}
	@Test
	public void testBnoOrderBy() {
		Collection<Board> results =repo.findByBnoGreaterThanOrderByBnoDesc(90L);
		results.forEach(board -> System.out.println(board));
		
	}
	@Test
	public void testBnoOrderByPaging() {
		Pageable paging = PageRequest.of(0, 10);
		
		Collection<Board> results = repo.findByBnoGreaterThanOrderByBnoDesc(0L, paging);
		
		results.forEach(board -> System.out.println(board));
	}*/
	/*
	@Test
	public void testBnoPagingSort() {
		Pageable paging = PageRequest.of(0, 10, Sort.Direction.ASC, "bno");
		
		Collection<Board> results = repo.findByBnoGreaterThan(0L, paging);
		results.forEach(board -> System.out.println(board));
	}*/
	
	@Test
	public void testBnoPagingSort() {
		Pageable paging = PageRequest.of(0, 10, Sort.Direction.ASC, "bno");
		
		Page<Board> result = repo.findByBnoGreaterThan(0L, paging);
		
		System.out.println("PAGE SIZE : "+result.getSize());
		System.out.println("TOTAL PAGES : "+result.getTotalPages());
		System.out.println("TOTAL COUNT : "+result.getTotalElements());
		System.out.println("NEXT : "+result.nextPageable());
		
		List<Board> list = result.getContent();
		
		list.forEach(board->System.out.println(board));
		
	}
	
	/*@Query를 통한 검색
	@Test
	public void testByTitle2() {
		repo.findByTitle("17").forEach(board ->System.out.println(board));
	}*/
	@Test
	public void testByTitle17() {
		repo.findByTitle2("17").forEach(arr ->System.out.println(Arrays.toString(arr)));
	}
	@Test
	public void testByPaging() {
		Pageable pageable = PageRequest.of(0,10);
		repo.findBypage(pageable).forEach(board-> System.out.println(board));
	}
	
	@Test
	public void testPredicate() {
		String type ="t";
		String keyword ="17";
		
		BooleanBuilder builder = new BooleanBuilder();
		
		QBoard board = QBoard.board;
		
		if(type.equals("t")) {
			builder.and(board.title.like("%"+keyword+"%"));
			
		}
		//bno>0
		builder.and(board.bno.gt(0L));
		
		Pageable pageable = PageRequest.of(0, 10);
		Page<Board> result = repo.findAll(builder, pageable);
		
		System.out.println("PAGE SIZE: "+result.getSize());
		System.out.println("TOTAL PAGES"+result.getTotalPages());
		System.out.println("TOTAL COUNT: "+result.nextPageable());
		
		List<Board> list = result.getContent();
		list.forEach(b -> System.out.println(b));
	}

}
