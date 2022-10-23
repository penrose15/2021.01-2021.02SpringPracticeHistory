package org.zerock.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor  //생성자 자동 생성해줌 
public class MemberVO {
	
	private int mno;
	private String mid;
	private String mqw;
	private String mname;
	private Timestamp regdate;

}
