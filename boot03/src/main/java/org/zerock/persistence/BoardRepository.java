package org.zerock.persistence;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.zerock.domain.Board;


public interface BoardRepository extends CrudRepository<Board, Long>,QuerydslPredicateExecutor<Board>{
	
	//public List<Board> findBoardByTitle(String title);
	
	//Collection은 순서나 집합적인 저장공간
	//public Collection<Board> findByWriter(String writer);
	//findBy로 시작하는 쿼리 메소드는 지정하는 속성의 값에 따라 파라미터의 타입이 결정된다
	
	//작성자에 대한 like % 키워드 %(Containing)
	//public Collection<Board> findByWriterContaining(String writer);
	
	//OR 조건의 처리
	//public Collection<Board> findByTitleContainingOrContentContaining(String title, String content);
	
	
	//>, <는 GreaterThan, LesserThan으로 이용해서 처리할 수 있음
	//title LIKE %?%AND BNO > ?
	public Collection<Board> findByTitleContainingAndBnoGreaterThan(String keywoard, Long num);
	
	//데이터 순서 지정 OrderBy +속성+Asc/Desc
	//bno > ? ORDER BY bno DESC
	public Collection<Board> findByBnoGreaterThanOrderByBnoDesc(Long Bno);
	
	//bno > ? ORDER BY bno DESC limit ?, ?
	public List<Board> findByBnoGreaterThanOrderByBnoDesc(Long Bno, Pageable paging);
	
	//public List<Board> findByBnoGreaterThan(Long Bno, Pageable paging);
	//page<T>로 하면 스프링과 연계시 편함
	public Page<Board> findByBnoGreaterThan(Long Bno, Pageable paging);
	
	/*@Query 는 JPQL을 사용한다 ㅜㅜ
	@Query("SELECT b FROM Board b WHERE b.title LIKE %:content% AND b.bno > 0 ORDER BY b.bno DESC")
	public List<Board> findByTitle(@Param("content") String content);
	@Param으로 여려개의 파라미터 전달 가능*/
	@Query("SELECT b.bno, b.title, b.writer, b.regdate FROM Board b WHERE b.title LIKE %?1% AND b.bno>0 ORDER BY b.bno DESC")
	public List<Object[]> findByTitle2(String title);
	
	@Query("SELECT b FROM Board b WHERE b.bno>0 ORDER BY b.bno DESC")
	public List<Board> findBypage(Pageable pageable);
	
	

}
