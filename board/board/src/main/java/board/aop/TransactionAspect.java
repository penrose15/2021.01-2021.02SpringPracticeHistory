package board.aop;

import java.util.Collections;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

@Configuration
public class TransactionAspect {
	
	private static final String AOP_TRANSACTION_METHOD_NAME = "*";
	private static final String AOP_TRANSACTION_EXPRESSION = "execution(* board..service.*Impl.*(..))";  //트랜잭션 설정시 사용되는 설정값을 상수로 선언합니다.
	
	@Autowired
	private TransactionManager transactionManager;
	
	@Bean
	public TransactionInterceptor transactionAdvice() {
		MatchAlwaysTransactionAttributeSource source =
				new MatchAlwaysTransactionAttributeSource();
		RuleBasedTransactionAttribute transactionAttribute = 
				new RuleBasedTransactionAttribute();
		transactionAttribute.setName(AOP_TRANSACTION_METHOD_NAME);  //트랜젝션 이름 설정, 트랜젝션 모니터에서 트랜젝션의 이름으로 확인 가능
		transactionAttribute.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));//롤백 룰 설정 만약 예외가 일어나면 롤백이 수행되도록 지정 exception.class를 룰로 등록하여 자바의 모든 예외는 exception클래스를 상속받기 때문에 어떠한 예외가 발생해도 롤백 수행
		source.setTransactionAttribute(transactionAttribute);
		
		return new TransactionInterceptor(transactionManager, source);
	}
	
	@Bean
	public Advisor transactionAdvisor() {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression(AOP_TRANSACTION_EXPRESSION);  //AOP포인트컷을 설정한다 여기서는 비즈니스 로직이 수행되는 Serviceimpl의 모든 메서드 지정
		return new DefaultPointcutAdvisor(pointcut, transactionAdvice());
	}
	
	//포인트 컷은 어드바이스를 적용할 조인포인트를 선별하는 과정이나 그 기능을 정의한 모듈
	//어드바이스는 관점의 ㅣ구편체로 조인포인트에 삽입되어 동작하는 것
	
	
	
	
	

}
