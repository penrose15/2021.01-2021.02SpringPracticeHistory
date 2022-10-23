package org.zerock.persistence;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.zerock.domain.WebBoard;
import org.zerock.domain.QWebBoard;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface WebBoardRepository extends CrudRepository<WebBoard, Long>,QuerydslPredicateExecutor<WebBoard>{
	
	public default Predicate makePredicate(String type, String keyword) {  //makepredicate는 검색에 필요한 type,keyword를 이용헤 쿼리 생성
	//페이징 테스트	
		BooleanBuilder builder = new BooleanBuilder();  //BooleanBuilder는 쿼리 조건설정 시 where튀에 조건을 붙여줌ㅇㅇ
		QWebBoard board = QWebBoard.webBoard;
		
		//type if ~ else
		
		//bno>0
		builder.and(board.bno.gt(0)); //gt는 greaterthan을 의미하는것 같다ㅏ
		
		if(type == null) {
			return builder;
		}
		
		switch(type) {
		case "t": 
			builder.and(board.title.like("%"+keyword+"%"));
			break;
		case "c":
			builder.and(board.content.like("%"+keyword+"%"));
			break;
		case "w":
			builder.and(board.writer.like("%"+keyword+"%"));
			break;
		}
		
		return builder;
		
	}

}
