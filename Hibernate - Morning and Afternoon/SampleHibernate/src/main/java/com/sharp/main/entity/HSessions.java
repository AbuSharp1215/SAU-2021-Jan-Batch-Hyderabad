package com.sharp.main.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.ManyToAny;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class HSessions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String sessionName;
	
	@ManyToMany(mappedBy = "sessions")
	private List<Employee> employee = new ArrayList<>();

	public HSessions(String sessionName) {
		super();
		this.sessionName = sessionName;
	}

	public HSessions() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "HSessions [id=" + id + ", sessionName=" + sessionName + "]";
	}
	
	
	
}
