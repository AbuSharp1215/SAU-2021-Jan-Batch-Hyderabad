package com.sharp.main.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.sharp.main.model.Student;

@Service
public class ConsumerService {
	
	@KafkaListener(topics="Student", groupId = "Student-Teacher-Group", containerFactory = "kafkaListenerContainerFactory")
	public void consumeStudent(ConsumerRecord<String, Object> message, String student) {
		System.out.println("Student Topic");
		System.out.println(student);
	}
	
	@KafkaListener(topics="Teacher", groupId = "Student-Teacher-Group", containerFactory = "kafkaListenerContainerFactory")
	public void consumeTeacher(ConsumerRecord<String, Object> message, String teacher) {
		System.out.println("Teacher Topic");
		System.out.println(teacher);
	}
	
	@KafkaListener(topics="Rank", groupId = "Student-Teacher-Group", containerFactory = "kafkaListenerContainerFactory")
	public void consumeRank(ConsumerRecord<String, Object> message, String rank) {
		System.out.println("Rank Topic");
		System.out.println(rank);
	}
}
