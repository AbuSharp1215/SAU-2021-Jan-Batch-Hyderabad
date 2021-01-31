package com.sharp.main.service;

import com.sharp.main.model.Rank;
import com.sharp.main.model.Student;
import com.sharp.main.model.Teacher;

public interface ProducerService {
	public void sendKafkaStudentData(Student student);
	public void sendKafkaTeacherData(Teacher teacher);
	public void sendKafkaRankData(Rank rank);
}
