package test.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MessengerAspect {
	// return type 은 void 이고 send로 시작하는 모든 메소드가 point cut(aop 가 적용되는 위치)이다.
	@Around("execution(void send*(..))") // around 는 ProceedingJoinPoint 를 사용할 수 있다.
	public void checkGreeting(ProceedingJoinPoint joinPoint) 
				throws Throwable{
		//aop 가 적용된 메소드 수행하기 이전에 해야할 작업
		
		//메소드에 전달되 인자들 목록을 얻어내기
		Object[] args=joinPoint.getArgs();
		for(Object tmp:args) {// 반목문 돌면서 원하는 타입을 찾아야 한다. 타입확인법 (instanceof 라는 메소드 사용)
			//만일 인자가 String type 이면
			if(tmp instanceof String) {// 이 타입이 맞으면 true, false 를 리턴한다.
				//원래 type 으로 casting 
				String msg=(String)tmp;
				System.out.println("aspect 에서 읽어낸 내용:"+msg);
				if(msg.contains("바보")) { // 조건부를 이용해서 메소드를 수행할지 말지를 결정할수 있다.
					System.out.println("바보는 금지된 단어 입니다.");
					return; //메소드를 여기서 끝내기(proceed() 가 호출이 안된다)
				}
			}
		}
		
		// 이걸 호출하지 않으면 send* 메소드가 수행되지 않는다. //메소드를 수행시키지 않을수 있다.
		joinPoint.proceed();// aop 가 적용된 메소드 수행하기 
		
		//aop 가 적용된 메소드 후행된 이후에 해야할 작업
	}
	@Around("execution(String get*())") // 리턴타입은 String 메소드는 get으로 시작하는 인자는 없음
	public Object checkReturn(ProceedingJoinPoint joinPoint) throws Throwable {// jointPoint 예외 발생해서 throws
		
		//aop 가 적용된 메소드가 리턴하는 데이터 얻어내기
		Object obj=joinPoint.proceed();
		
		//aop 가 적용된 메소드가 리턴하는 데이터
		return "놀자 놀자~";
	}
}
