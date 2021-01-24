package com.main;
import java.util.ArrayList;
import java.util.List;

public class UserList {
	private static List<String> userList = new ArrayList<String>();
	
	public static List<String> getListFromDB(){
		userList.add("Ramesh");
		userList.add("Suresh");
		userList.add("Hari");
		userList.add("Vicky");
		userList.add("Ganesh");
		
		return userList;
	}
}
