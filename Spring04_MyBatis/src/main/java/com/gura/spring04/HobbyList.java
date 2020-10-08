package com.gura.spring04;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

<<<<<<< HEAD
@XmlRootElement(name = "hobby-list")
public class HobbyList {
	private List<String> hobby;
	
	@XmlElement
	public void setHobby(List<String> hobby) {
		this.hobby = hobby;
	}
	
	public List<String> getHobby() {
		return hobby;
	}
}
=======
@XmlRootElement(name="hobby-list")
public class HobbyList {
	private List<String> hobby;
	
	@XmlElement
	public void setHobby(List<String> hobby) {
		this.hobby = hobby;
	}
	public List<String> getHobby() {
		return hobby;
	}
}







>>>>>>> refs/remotes/upstream/master
