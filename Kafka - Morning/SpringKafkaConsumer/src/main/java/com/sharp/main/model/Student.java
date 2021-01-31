package com.sharp.main.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Student {
	
	private Integer id;
	
	private String name;
	
	private String clas;

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", clas=" + clas + "]";
	}
	
	
}
