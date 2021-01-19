package singletondemo;

public class Main {
	public static void main(String[] args) {
		SingletonDemo s1 = SingletonDemo.getInstance();
		SingletonDemo s2 = SingletonDemo.getInstance();
		
		s1.printData();
		s2.printData();
		// Here I set the data to object s1
		s1.setData(10);
		System.out.println("set data to s1");
		s1.printData();
		s2.printData();
		
		s2.setData(20);
		System.out.println("set data to s2");
		s1.printData();
		s2.printData();
		
	}
}
