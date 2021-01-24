package com.main;

public class Main {

	public static void main(String[] args) throws CloneNotSupportedException {
		
		User user1 = new User(UserList.getListFromDB());
		
		System.out.println("Original Object");
		user1.printUserList();
		
		User userTemp = (User) user1.clone();
		
		System.out.println("Cloned Object");
		userTemp.printUserList();
		
		//Modify the name in cloned object for some purpose
		userTemp.getUserList().set(3,"Rajesh");
		System.out.println("3 index is modified in cloned object for some purpose\n");
		
		System.out.println("Cloned Object");
		userTemp.printUserList();
		
		System.out.println("The modification does not affect the original object\n");
		System.out.println("Original Object");
		user1.printUserList();
		
		// Here no new objects are created
		//instead of creating new object, we cloned the existing object
	}

}
