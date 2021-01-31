package com.sharp.main.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Teacher {
	 
	private Integer id;
	
	private String name;
	
	private String expertise;

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", expertise=" + expertise + "]";
	}
	
	
}
