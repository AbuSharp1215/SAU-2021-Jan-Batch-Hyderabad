package com.sharp.xmlparser;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Assignments {
	List<Assign> assign;
	
	public List<Assign> getAssign() {
		return assign;
	}
	
	@XmlElement
	public void setAssign(List<Assign> assign) {
		this.assign = assign;
	}


	@Override
	public String toString() {
		return ""+assign;
	}
}
