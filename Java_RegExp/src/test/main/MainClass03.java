package test.main;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainClass03 {
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		System.out.println("특수 문자를 제외한 5~10글자를 입력하세요:");
		String str=scan.nextLine();
		String reg="^[a-zA-Z0-9]{5,10}$";
		String reg2="^[\\w]{5,10}$";
		
		boolean result=str.matches(reg);
		boolean result2=str.matches(reg2);
		//정규 표현식을 compile 한 Pattern 객체 얻어내기
		Pattern p=Pattern.compile(reg);
		//Pattern 객체에 검증할 문자열을 전달해서 Matchar 객체 얻어내기
		Matcher m=p.matcher(str);//매칭되는것을 다 찾아낼때 사용한다.
		//찾아지는 문자열이 있는지 boolean type 으로 얻어내기
		boolean result3=m.find();
		System.out.println(result+"|"+result2+"|"+result3);
	}
}
