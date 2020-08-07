package test.main;

import test.mypac.Weapon;
import test.mypac.WeaponImpl;

public class MainClass01 {//MainClass01은 WeaponImpl 에 의존하는 관계이다.
	public static void main(String[] args) {
		//Application 의 목적이 무언가를 공격하는게 목적이라면? // 유지보수도 쉬어지게끔 만드는 것이 목적
		
		//1. 목적을 달성하기 위해 필요한 핵심 의존 객체를 직접 생성해서 //핵심 의존 객체 : 의존해야되는 객체들이 있다 jsp 에서 dao, dto 같은것
		Weapon w=new WeaponImpl();
		//2. 메소드를 호출해서 목적을 달성한다.
		w.attack();
	}
}
