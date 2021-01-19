package userexception;

public class UserException extends Exception{
	int val;
	public UserException(int val){
		this.val = val;
	}

	@Override
	public String toString() {
		return "UserException : {Value must between 0 and 100}\nYour input is "+val;
	}
	
	
}
