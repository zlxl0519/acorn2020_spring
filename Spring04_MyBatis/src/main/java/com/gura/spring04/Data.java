package com.gura.spring04;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="data")//<data> </data>
public class Data {
	private int num;//필드명이 요소명이 된다. <num> </num>
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
