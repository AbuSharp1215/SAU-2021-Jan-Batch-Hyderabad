package com.sharp.main.controller;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharp.main.model.Rank;
import com.sharp.main.model.Student;
import com.sharp.main.model.Teacher;
import com.sharp.main.service.ProducerService;

@RestController
@RequestMapping("/api")
public class ProducerController {
	private static Integer ranking=1;
	@Autowired
	ProducerService service;
	
	@PostMapping("/student")
	public ResponseEntity<Object> sendStudentData(@RequestBody Student student){
		service.sendKafkaStudentData(student);
		return new ResponseEntity<Object>(student, HttpStatus.OK);
	}
	
	@PostMapping("/teacher")
	public ResponseEntity<Object> sendTeacherData(@RequestBody Teacher teacher){
		service.sendKafkaTeacherData(teacher);
		return new ResponseEntity<Object>(teacher, HttpStatus.OK);
	}
	
	@PostMapping("/rank")
	public ResponseEntity<Object> sendRankData(@RequestBody Rank rank){
		rank.setTimeOfSubmission(Timestamp.from(Instant.now()));
		rank.setRank(ranking++);
		service.sendKafkaRankData(rank);
		return new ResponseEntity<Object>(rank, HttpStatus.OK);
	}

}
