package com.sharp.main;

import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sharp.main.entity.Address;
import com.sharp.main.entity.Employee;
import com.sharp.main.entity.HSessions;
import com.sharp.main.entity.PersonalInfo;

public class EmployeeDemo {
	
	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
											.addAnnotatedClass(Employee.class)
											.addAnnotatedClass(HSessions.class)
											.addAnnotatedClass(Address.class)
											.addAnnotatedClass(PersonalInfo.class).buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		
		Employee employee = new Employee("Abu","Dhahir", new Date());
		Employee employee2 = new Employee("Abu","Sharp", new Date());
		Employee employee3 = new Employee("Sheik","Abudhahir", new Date());
			
		employee.getSkillSet().add("Java");
		employee.getSkillSet().add("C");
		employee.getSkillSet().add("C++");
		
		employee2.getSkillSet().add("Java");
		employee3.getSkillSet().add("C");
		employee3.getSkillSet().add("C++");
		
		PersonalInfo info1 = new PersonalInfo("9080512621","sheik@gmail.com");
		PersonalInfo info2 = new PersonalInfo("9994648368","abu@gmail.com");
		PersonalInfo info3 = new PersonalInfo("9585112121","sharp@gmail.com");
		
		info1.setEmployee(employee);
		info2.setEmployee(employee2);
		info3.setEmployee(employee3);
		
//		employee.setInfo(info1);
//		employee2.setInfo(info2);
//		employee3.setInfo(info3);
		
		Address adderss1 = new Address("183, Anna Nagar", "Chennai", "600025");
		Address adderss2 = new Address("72, Besant Nage", "Chennai", "600025");
		
		Address adderss3 = new Address("12, Rayapuram", "Chennai", "600026");
		Address adderss4 = new Address("76, Triplicane", "Chennai", "600026");
		
		Address adderss5 = new Address("14, Adayar", "Chennai", "600028");

		
//		employee.getAddress().add(adderss1);
//		employee.getAddress().add(adderss2);
		
		adderss1.setEmployee(employee);
		adderss2.setEmployee(employee);
		
		employee.getAddress().add(adderss1);
		employee.getAddress().add(adderss2);
		
		adderss3.setEmployee(employee2);
		adderss4.setEmployee(employee2);
		
		employee2.getAddress().add(adderss3);
		employee2.getAddress().add(adderss4);
		
		adderss5.setEmployee(employee3);
		
		employee3.getAddress().add(adderss5);
		
		
		
		HSessions session1 = new HSessions();
		session1.setSessionName("Hibernate");
		
		
		HSessions session2 = new HSessions();
		session2.setSessionName("Spring");
		
		
		HSessions session3 = new HSessions();
		session3.setSessionName("HTML");
		
		employee.getSessions().add(session1);
		employee.getSessions().add(session2);
		employee2.getSessions().add(session2);
		employee2.getSessions().add(session3);
		
		session1.getEmployee().add(employee);
		session2.getEmployee().add(employee);
		session2.getEmployee().add(employee2);
		session3.getEmployee().add(employee2);
		
		
		//employee.setSessions(sessions);
		
		
		try {
			session.beginTransaction();
			//session.save(sessions);
			session.save(info1);
			session.save(info2);
			session.save(info3);
			session.save(employee);
			session.save(employee2);
			session.save(employee3);
			session.getTransaction().commit();
			session.close();
			
			session = sessionFactory.openSession();
			Employee empFromDB = (Employee)session.get(Employee.class, 1);
			
			System.out.println("Retrived from DB "+empFromDB.getFirstName());
			System.out.println("Retrived from DB "+empFromDB.getSkillSet());
			//System.out.println("Retrived from DB "+empFromDB.getSessions());
			
			session.close();
			
			
		}finally {
			//session.close();
			
			sessionFactory.close();
		}
	}

}
