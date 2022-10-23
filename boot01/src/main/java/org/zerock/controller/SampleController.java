package org.zerock.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;


@RestController  //rest 방식의 컨트롤러
public class SampleController {
	
	@GetMapping("/hello")
	public String sayHello() {
		
		return "Hello World";
	}
	@GetMapping("/sample")
	public SampleVO makeSample() {
		SampleVO vo= new SampleVO();
		
		
		System.out.println(vo);
		
		return vo;
	}

}
