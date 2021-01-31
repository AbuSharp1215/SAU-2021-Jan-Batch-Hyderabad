package com.sharp.main.model;

import java.sql.Timestamp;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Rank {
	
	private Integer id;
	
	private String name;
	
	private Timestamp timeOfSubmission;
	
	private Integer rank;

	@Override
	public String toString() {
		return "Rank [id=" + id + ", name=" + name + ", timeOfSubmission=" + timeOfSubmission + ", rank=" + rank + "]";
	}
	
	
	
	
}
