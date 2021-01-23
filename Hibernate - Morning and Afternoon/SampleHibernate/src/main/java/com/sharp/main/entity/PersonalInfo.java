package com.sharp.main.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PersonalInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pId;
	
	private String phone;
	
	private String email;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Employee employee;

	public PersonalInfo() {
		super();
	}
		
	public PersonalInfo(String phone, String email) {
		super();
		this.phone = phone;
		this.email = email;
	}
	

}
