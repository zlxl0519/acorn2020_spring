package test.util;

import org.springframework.stereotype.Component;
<<<<<<< HEAD
// component scan 을 했을때 bean 이 되도록 어노테이션을 붙인다.
@Component //(new Messenger() 로 spring bean container에서 관리)
=======

// component scan 을 했을때 bean 이 되도록 어노테이션을 붙인다. 
@Component
>>>>>>> refs/remotes/upstream/master
public class Messenger {
	public void sendGreeting(String msg) {
		System.out.println("전달된 오늘의 인사:"+msg);
	}
	public String getMessage() {
		System.out.println("getMessage() 메소드가 수행 됩니다.");
		return "공부하자!";
	}
}
