package board.common;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice  //@controllerAdvice 어노테이션으로 해등클래스가 예외처리 클래스임을 알림
public class ExceptionHandler {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)  //해당 메서드에 처리할 에러를 지정한다(exception.class)로 해서 모든 예외 처기를 함(원래는 한번에 모든 예외를 처리하진 않음)
	public  ModelAndView defaultExceptionHandler(HttpServletRequest request, Exception exception) {
		ModelAndView mv = new ModelAndView("/error/error_default");  //예외 발생시 보여줄 화면 지정
		mv.addObject("exception", exception);
		
		log.error("exception",exception);  //에러 로그 출력
		
		return mv;
		
		/*
		컨트롤러 클래스 제작 순서
		1. @controller를 이용해 클래스 생성
		2.@requestMapping를 이용해 view 의 요청경로 지정
		3. 요청 처리 메소드 구현
		4. 뷰 이름으로 리턴
		
		ModelAndView 객체를 파라미터로 방다 데이터를 뷰로 넘길 수 있다
		Model과 다른 점은 데이터와 뷰를 동시에 설정 가능하다
		 */
	}

}
