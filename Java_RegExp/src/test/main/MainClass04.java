package test.main;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainClass04 {
	public static void main(String[] args) {
		String str="abcd   1234   abcd   5678   abcd";
		//String reg="abc";
		//String reg="[0-9]+";
		//String reg="[\\d]+";
		String reg="[^ ]+";
		Pattern p=Pattern.compile(reg);//1. 정규표현식을 pattern compile 하고
		Matcher m=p.matcher(str);//2. pattern 에서 matcher메소드를 이용해서 검증할 문자열 입력
		//결과 값을 담을 가변 배열
		List<String> list=new ArrayList<>();
		//Matcher 객체의 find() 메소드를 호출하면서 반복문을 수행해서
		while(m.find()){//3. 검증한것 찾아서
			//찾았으면 찾은 문자열을 출력한다.
			System.out.println(m.group());//4. .group 메소드 사용해서 가지고 오기
			//결과값 누적 시키기
			list.add(m.group());
		}
	}
}
