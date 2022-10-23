package org.zerock.domain;

//https://brunch.co.kr/@topherlee/67 mysql에 관련 내용
//https://deftkang.tistory.com/51 key에 대한 설명

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
 
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


//출처: https://memories95.tistory.com/135?category=832097 [취미로 음악을 하는 개발자]


@Getter
@Setter
@ToString
@Entity
@Table(name="tbi_boards")

public class Board {    
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  //식별자의 생성전략을 지정한다, IDENTITY는 기본 키 생성방식 자체를 데이터베이스에 위임하는 방식이다
	
	private Long bno;
	private String title;
	private String writer;
	private String content;
	
	
	
	@CreationTimestamp
	private Timestamp regdate; //LocalDatetime
	@UpdateTimestamp
	private Timestamp updatedate; //LocalDatetime ->엔티티 시간을 처리할때 사용된다
	
}