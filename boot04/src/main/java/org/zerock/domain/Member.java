package org.zerock.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="tbl_members")
@EqualsAndHashCode(of="uid")  //equals는 두 객체 내용이 같은지 확인하고 hashcode는 두 객체가 같은 객체인지를 확인한다 그러니깐 EqualsAndHashCode는 둘다 해쳐먹는다는 소리
public class Member {
	
	@Id
	private String uid;
	private String upw;
	private String uname;

}
