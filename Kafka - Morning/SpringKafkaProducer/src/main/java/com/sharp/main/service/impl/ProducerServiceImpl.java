package com.sharp.main.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.sharp.main.model.Rank;
import com.sharp.main.model.Student;
import com.sharp.main.model.Teacher;
import com.sharp.main.service.ProducerService;


@Service
public class ProducerServiceImpl implements ProducerService{

	@Autowired
	KafkaTemplate<String, Object> kafkaTemplate;
	
	@Override
	public void sendKafkaStudentData(Student student) {
		System.out.println("Producer send message in the topic of student");
		kafkaTemplate.send("Student", student);
		
	}

	@Override
	public void sendKafkaTeacherData(Teacher teacher) {
		System.out.println("Producer send message in the topic of teacher");
		kafkaTemplate.send("Teacher", teacher);
	}

	@Override
	public void sendKafkaRankData(Rank rank) {
		System.out.println("Producer send message in the topic of rank");
		kafkaTemplate.send("Rank", rank);
	}

}
