package com.palle.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.palle.dto.StudentDto;
import com.palle.entity.Student;
import com.palle.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	public StudentRepository repository;
	
	//method to insert data
	public Student insertStudent(StudentDto studentDto) {
		Student studentService=new Student();
		studentService.setStuClass(studentDto.getStuClass());
		studentService.setStuFees(studentDto.getStuFees());
		studentService.setStuLocation(studentDto.getStuLocation());
		studentService.setStuName(studentDto.getStuName());
		studentService.setStuPhone(studentDto.getStuPhone());
		return repository.save(studentService);
	}
	
	//method to insert one data
	public Optional<Student> readOneStudent(int id) {
		Optional<Student> byId = repository.findById(id);
		return byId;
	}
	
	//method to read all data
	public List<Student> readAllStudent(){
		List<Student> all = repository.findAll();
		return all;
	}
	
	//method to update data
	public Student updateStudent(int id, Student newStu) {
		Optional<Student> idPresent = repository.findById(id);
		if(idPresent.isPresent()) {
			Student oldStu = idPresent.get();
			oldStu.setStuClass(newStu.getStuClass());
			oldStu.setStuFees(newStu.getStuFees());
			oldStu.setStuLocation(newStu.getStuLocation());
			oldStu.setStuName(newStu.getStuName());
			oldStu.setStuPhone(newStu.getStuPhone());
			return repository.save(oldStu);
		}
		
		return null;
	}
	
	//method to delete data
	public Student deleteStudent(int id) {
		Optional<Student> studentData = repository.findById(id);
		if(studentData.isPresent()) {
			Student stu=studentData.get();
			repository.delete(stu);
			return stu;
		}
		return null;
	}
}
