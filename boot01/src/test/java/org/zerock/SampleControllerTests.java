package org.zerock;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;//junit5
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.zerock.controller.SampleController;

import ch.qos.logback.core.status.Status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SampleController.class) 
public class SampleControllerTests {
	
	@Autowired
	MockMvc mock;
	
	@Test
	public void testHello() throws Exception{
		
		mock.perform(get("/hello")).andExpect(content().string("Hello World"));
		//.andExpect(status().isOk()).andExpect(content().string("Hello World")).andDoPrint();
	

}
}

//junit5에서는 @RunWith를 지원 안한다 그러므로 @ExtendWith사용해라
