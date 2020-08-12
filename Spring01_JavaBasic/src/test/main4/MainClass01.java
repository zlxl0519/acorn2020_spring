package test.main4;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MainClass01 {
	public static void main(String[] args) {
		//비밀번호라고 가정
		String pwd="abcd1234";
		CharSequence pwd2="abcd1234";
		//비밀번호를 인코딩할 객체 생성 //spring security 에 있는 객체
		BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
		
		//결과 출력해 보기
		for(int i=0; i<10; i++) {
			//비밀번호를 암호화한 문자열 얻어내기 // 역으로 비밀번호를 유추는 못하지만 어디에서 파생된지는 알아야 된다.
			String result=encoder.encode(pwd);
			//수행 할때 마다 다른 문자열이 나온다.
			System.out.println(result);
		}
	}
}
