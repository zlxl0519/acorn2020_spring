package test.main;

public class MainClass01 {
	public static void main(String[] args) {
		//검증할 문자열
		String str="abcd1234";
		//검증할 정규표현식 정보를 가지고 있는 문자열
		String reg="abcd";
		//정규 표현식에 매칭되는지 여부 "^abcd$" 와 같이 완벽히 일치해야 true 가 나온다.
		boolean isMatch=str.matches(reg);
		System.out.println(isMatch);
	}
}
