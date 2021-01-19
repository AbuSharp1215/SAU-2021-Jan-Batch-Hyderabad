import javax.xml.bind.*;
import java.io.*;
import java.util.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
public class XMLtoObject {
	
	//Convert XML file content to Java object using JAXB
	public Map<Integer,Integer> unMarshall() {
		Map<Integer, Integer> studTotal = new HashMap();
		try {
			File file = new File("./src/data.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Students.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			Students students = (Students) unmarshaller.unmarshal(file);
			System.out.println("Xml file data converted to java object");
			// Student data in Object
			System.out.println(students);
			
			List<Student> student = students.getStudent();
			
			for(Student s:student) {
				studTotal.put(s.getRoll(), studTotal.getOrDefault(s.getRoll(), 0)+s.getMarks());
			}
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return studTotal;
	}
	
	

	// Deserialize the txt file to java object
	public Map<Integer, Integer> deSerializeObject(String filepath) {
		Map<Integer, Integer> map = new HashMap();
		 try {
			 
	            FileInputStream filestream = new FileInputStream(filepath);
	            ObjectInputStream objectstream = new ObjectInputStream(filestream);
	            map = (HashMap) objectstream.readObject();
	            objectstream.close();
	            filestream.close();
	            System.out.println("\nThe txt file deserialized to java object\n"+map);
	 
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
		 return map;
	}
	
	// serialize the java object to txt file
	public void serializeObject(Map<Integer, Integer> studentTotal) {
		File file = new File("./src/studentTotalMark.txt");
		 try {
			 
	            FileOutputStream filestream = new FileOutputStream(file);
	            ObjectOutputStream objectstream = new ObjectOutputStream(filestream);
	            objectstream.writeObject(studentTotal);
	            objectstream.close();
	            filestream.close();
	            System.out.println("\nThe Student Total object was succesfully written to a file");
	 
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	}
	
	
	// Export the top five rank with total marks in CSV file
	public void exportToCsv(Map<Integer, Integer> map) {
		Sort sort = new Sort();
		Map<Integer, Integer> rank = sort.sortByValue(map); 
		final String line_separator = "\n";
		final Object [] header = {"Rank", "Roll no", "Total Marks"};
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(line_separator);
		int i;
		try {
			FileWriter fileWriter = new FileWriter("./src/Rank.csv");
			CSVPrinter csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
			csvFilePrinter.printRecord(header);
			i=1;
			List<Integer> rankrecord;
			for (Map.Entry<Integer,Integer> entry : rank.entrySet()) {
				rankrecord = new ArrayList();
				rankrecord.add(i);
				rankrecord.add(entry.getKey());
				rankrecord.add(entry.getValue());
				csvFilePrinter.printRecord(rankrecord);
				i++;
				if(i>5) break;
			}
			fileWriter.flush();
            fileWriter.close();
            csvFilePrinter.close();
            System.out.println("\nTop 5 ranks are exported in CSV file");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		XMLtoObject object = new XMLtoObject();
		Map<Integer, Integer> studentTotal = new HashMap();
		studentTotal = object.unMarshall();
	
		object.serializeObject(studentTotal);
		
		Map<Integer, Integer> deSerializeObject = new HashMap();
		deSerializeObject = object.deSerializeObject("./src/studentTotalMark.txt");
		
		object.exportToCsv(deSerializeObject);

	}

}
