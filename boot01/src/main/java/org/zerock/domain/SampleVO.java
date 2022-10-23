package org.zerock.domain;


import lombok.Data;
import lombok.ToString;


@Data  //getter, setter 대신 작성해주고 equals(), hashcode(), toString(),파라미터 없는 기본 생성자까지 자동으로 만들어줌
@ToString(exclude= {"val3"})//val3 속성은 출력 안함
public class SampleVO {
	
	private String val1;
	private String val2;
	private String val3;
	
	
	//*toString()메서드는 매체가 가진 정보, 값들을 문자열로 리턴해주는 메서드임 
	//근데 재정의 해서 쓸수 있음
	//@override 한 후에 리턴 값 바꿔서 재정의 가능 ㅅㄱ
	
	//getter, setter 메소드는 데이터 유출을 막기 위해 데이터 접근은 막아놓고 메서드만 열어둬서 외부의 메서드를 통해 데이터 접근을 유도함
	//public 리턴타입 getSample() { return sample;}
	//public void setSample(타입 ___) {this.sample = sample;} ....이런 식으로 사용되는건데 안써도 된다

}
