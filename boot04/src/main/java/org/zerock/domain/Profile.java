package org.zerock.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "member")
@Entity
@Table(name="tbl_profile")
@EqualsAndHashCode(of="fno")
public class Profile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //데이터 베이스에 맞게 자동으로 식별키를 처리하는 방식으로 동작한다 / hibernate_sequence라는 테이블이 생성되는 것을 볼 수 있습니다.
	private Long fno;
	private String fname;
	private boolean current;
	
	@ManyToOne   //profile과 member는 다대다관계이다
	private Member member;
	
	@CreationTimestamp
	private Timestamp regdate;
	@CreationTimestamp
	private Timestamp updatedate;
	

}
