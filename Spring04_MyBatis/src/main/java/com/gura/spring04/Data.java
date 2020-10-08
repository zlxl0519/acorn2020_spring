package com.gura.spring04;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

<<<<<<< HEAD
@XmlRootElement(name="data")//<data> </data>
public class Data {
	private int num;//필드명이 요소명이 된다. <num> </num>
=======
@XmlRootElement(name="data")
public class Data {
	private int num;
>>>>>>> refs/remotes/upstream/master
	private String name;
	
	public int getNum() {
		return num;
	}
	@XmlElement
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	
	
}
