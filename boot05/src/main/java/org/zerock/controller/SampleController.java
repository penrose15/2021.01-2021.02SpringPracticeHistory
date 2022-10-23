package org.zerock.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.zerock.domain.MemberVO;

@Controller
public class SampleController {
	
	@GetMapping("/sample1") //get요청의 API를 만들 때 사용
	public void sample(Model model) {
		//model.addAttribute("greeting", "Hello World");
		model.addAttribute("greeting", "반갑습네다");
	}
	
	@GetMapping("/sample2")
	public void sample2(Model model) {
		MemberVO vo = new MemberVO(123, "u00", "p00","홍길동", new Timestamp(System.currentTimeMillis()));
		
		model.addAttribute("vo", vo);
		
	}
	@GetMapping("/sample3")
	public void sample3(Model model) {
		
		List<MemberVO> list = new ArrayList<>();
		
		for (int i=0;i<10;i++) {
			list.add(new MemberVO(123, "u0"+i,"p0"+i,"하현우"+i,new Timestamp(System.currentTimeMillis())));
			
		}
		model.addAttribute("list", list);
	}
	
	@GetMapping("/sample4")
	public void sample4(Model model) {
		
		List<MemberVO> list = new ArrayList<>();
		
		for(int i=0; i<10; i++) {
			list.add(new MemberVO( i, "u000"+i%3, "p0000"+i%3, "홍길동"+i,new Timestamp(System.currentTimeMillis())));
			}
		model.addAttribute("list", list);
		}
	
	@GetMapping("/sample5")
	public void sample5(Model model) { //model은 데이터를 저장하는 역할을 함(데이터를 담는 그릇?)
		
		String result = "SUCCESS";
		
		model.addAttribute("result", result); //addAttribute는 value객체를 name이름으로 추가한다, 뷰 코드에서는 name으로 지정한 이름을 통해서 value를 사용한다.
		//그러니깐 html에 "result"를 찾아 sample5의 로컬변수 result를 사용
		
	}
	
	@GetMapping("/sample6")
	public void sample6(Model model) {
		List<MemberVO> list = new ArrayList<>();
		for (int i =0; i<10; i++) {
			list.add(new MemberVO(i,"u0"+1,"p0"+i,"홍길동"+i,new Timestamp(System.currentTimeMillis())));
		}
		
		model.addAttribute("list", list);
		
		String result = "SUCCESS";
		
		model.addAttribute("result", result);
	}
	
	@GetMapping("/sample7")
	public void sample7(Model model) {
		
		model.addAttribute("now", new Date());
		model.addAttribute("price", 811125);
		model.addAttribute("title","국카스텐 덕질 하실분 (1/100000000)");
		model.addAttribute("option", Arrays.asList("AAAA", "BBB", "CCC", "DDD"));
	}
	
	
	
	}



