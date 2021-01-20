package com.sharp.xmlparser;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import com.sharp.customannotation.Adate;
import com.sharp.customannotation.UpperCase;

public class Assign {
	int id;
	
	@UpperCase
	String name;
	
	@UpperCase
	String prg_lang;
	
	String status;
	
	@Adate
	String date;
	
	public int getId() {
		return id;
	}
	
	@XmlAttribute
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	
		
	public String getPrg_lang() {
		return prg_lang;
	}

	@XmlElement
	public void setPrg_lang(String prg_lang) {
		this.prg_lang = prg_lang;
	}

	public String getStatus() {
		return status;
	}
	
	@XmlElement
	public void setStatus(String status) {
		this.status = status;
	}
	

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "\nid=" + id + "\nname=" + name + "\nprg_lang=" + prg_lang + "\nstatus=" + status + "\ndate=" +date+ "\n";
	}

	
	
	
	
}
