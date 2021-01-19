
import javax.xml.bind.annotation.*;
import userexception.*;

public class Student {
	
	private int roll;
	
	private int marks;
	
	private String subject;
	
	public int getRoll() {
		return roll;
	}
	
	@XmlElement
	public void setRoll(int roll) {
		this.roll = roll;
	}
	
	public int getMarks() {
		return marks;
	}
	
	@XmlElement
	public void setMarks(int marks) {
		try {
			if(marks<0 || marks>100)
				throw new UserException(marks);
			this.marks = marks;
		}
		catch(Exception e) {
			System.out.println(""+e);
		}
	}
	
	public String getSubject() {
		return subject;
	}
	
	@XmlElement
	public void setSubject(String subject) {
		this.subject = subject;
	}


	@Override
	public String toString() {
		return "StudentData [roll=" + roll + ", marks=" + marks + ", subject=" + subject + "]";
	}
	
	
	
	
}
