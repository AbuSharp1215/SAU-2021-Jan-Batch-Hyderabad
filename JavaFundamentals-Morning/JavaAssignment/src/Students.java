import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Students {
	List<Student> student;

	
	public List<Student> getStudent() {
		return student;
	}
	
	@XmlElement
	public void setStudent(List<Student> student) {
		this.student = student;
	}


	@Override
	public String toString() {
		return "[studentData=" + student + "]";
	}
}
