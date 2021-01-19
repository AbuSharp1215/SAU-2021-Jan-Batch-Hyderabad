package singletondemo;

public class SingletonDemo {
	
	private static SingletonDemo single = new SingletonDemo();
	
	private static int data;
	
	private SingletonDemo() {
		data = 0;
	}
	
	public static SingletonDemo getInstance() {
		return single;
	}
	
	public static void setData(int val) {
		data = val;
	}
	
	public static void printData() {
		System.out.println("Singleton Variable Value : "+data);
	}

}
