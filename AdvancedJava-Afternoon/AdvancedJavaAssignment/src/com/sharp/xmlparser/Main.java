package com.sharp.xmlparser;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.sharp.customannotation.AnnotationDate;
import com.sharp.customannotation.UpperCaseAnnotation;
import com.sharp.jdbc.DbConnection;

public class Main {
	
	public List<Assign> unMarshall() throws IllegalArgumentException, IllegalAccessException {
		List<Assign> students = new ArrayList<>();
		try {
			File file = new File("./src/data.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Assignments.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			Assignments assignments = (Assignments) unmarshaller.unmarshal(file);
			System.out.println("Xml file data converted to java object");
			
			students = assignments.getAssign();
			
			for(Assign student:students) {
				UpperCaseAnnotation.toUpperCase(student);
				AnnotationDate.addDate(student);
			}
			
			System.out.println(students);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		return students;
		
	}
	
	

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		// TODO Auto-generated method stub
		Main m = new Main();
		DbConnection connection = new DbConnection();
		
		List<Assign> assign = m.unMarshall();
		
		connection.storeData(assign);

	}

}
