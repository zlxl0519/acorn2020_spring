package test.util;

import org.springframework.stereotype.Component;

<<<<<<< HEAD
@Component //component scan 을 통해서 bean 이 되기 위한 어노테이션
public class WritingUtil {
	//메소드가 수행되기 바로 이전에 적용될수 있도록 설정하는 어노테이션
	public void write1() {
		System.out.println("편지를 써요");
	}
	public void write2() {
		System.out.println("보고서를 써요");
	}
=======
//component scan 을 했을때 bean 이 될수 있도록 
@Component
public class WritingUtil {
	
	public void write1() {
		System.out.println("편지를 써요");
	}
	
	public void write2() {
		System.out.println("보고서를 써요");
	}
	
>>>>>>> refs/remotes/upstream/master
	public void write3() {
		System.out.println("일기를 써요");
	}
}
