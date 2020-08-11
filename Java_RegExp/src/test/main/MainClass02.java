package test.main;

public class MainClass02 {
	public static void main(String[] args) {
		String str="i_am_gura";
		// .split(분리 시킬 정규 표현식 pattern);
		String[] result=str.split("_");
		for(String tmp:result) {
			System.out.println(tmp);
		}
	}
	
}
