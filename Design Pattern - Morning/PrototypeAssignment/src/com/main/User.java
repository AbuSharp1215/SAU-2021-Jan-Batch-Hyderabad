package com.main;
import java.util.ArrayList;
import java.util.List;

public class User implements Cloneable {

	private List<String> userList;
	
	public User(){
		userList = new ArrayList<String>();
	}
	
	public User(List<String> userList){
		this.userList = userList;
	}
	
	
	public List<String> getUserList() {
		return userList;
	}

	public void setUserList(List<String> userList) {
		this.userList = userList;
	}
	
	public void printUserList() {
		for(String str : this.getUserList()){
			System.out.print(str+" ");
		}
		System.out.println("\n");
	}

	@Override
	public Object clone() throws CloneNotSupportedException{
			List<String> clonedList = new ArrayList<String>();
			
			for(String str : this.getUserList()){
				clonedList.add(str);
			}
			
			return new User(clonedList);
	}
}
