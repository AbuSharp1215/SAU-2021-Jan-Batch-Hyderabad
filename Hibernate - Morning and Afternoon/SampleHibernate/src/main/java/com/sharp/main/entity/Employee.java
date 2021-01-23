package com.sharp.main.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Emp") // for diff table name
@Setter
@Getter
public class Employee { //table create with the class name default
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int empId;
	
	@Column(name="First_Name", length=25)
	private String firstName;
	
	@Column(name="Last_Name") // if we need another name for columns use annotation
	private String lastName; // by default column take this name
	
	@Temporal(TemporalType.DATE)
	private Date joiningDate;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> skillSet = new ArrayList<>();
	
//	@OneToOne(cascade = CascadeType.ALL)
//	private PersonalInfo info = new PersonalInfo();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	private Collection<Address> address = new HashSet<>();
	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name="sid")
//	private HSessions sessions;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="emp_session")
	private Collection<HSessions> sessions = new HashSet<>();
	
	
	public Employee() {
		super();
	}

	
	public Employee(String firstName, String lastName, Date joiningDate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.joiningDate = joiningDate;
	}


	public Employee(int empId, String firstName, String lastName) {
		super();
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
	}


	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", firstName=" + firstName + ", lastName=" + lastName + ", joiningDate="
				+ joiningDate + "]";
	}

	

}
