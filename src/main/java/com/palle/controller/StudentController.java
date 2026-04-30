package com.palle.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palle.dto.StudentDto;
import com.palle.entity.Student;
import com.palle.service.StudentService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.val;


@RestController
@RequestMapping("/studentapp")
public class StudentController {
	@Autowired
	public StudentService service;
	
	//rest Api to insert one data
	@PostMapping("/insert")
	public Student insertData(@RequestBody @Valid StudentDto st) {
		return service.insertStudent(st);
	}
	
	//rest Api to read one data
	@GetMapping("/readone/{id}")
	public Optional<Student> readOneData(@PathVariable int id) {
		return service.readOneStudent(id);
	}
	

	//rest Api to read all data
	@GetMapping("/readall")
	public List<Student> readAllData() {
		return service.readAllStudent();
	}
	
	//rest api to get the token
	@GetMapping("/get-token")
	public CsrfToken getToken(HttpServletRequest request) {
		return (CsrfToken) request.getAttribute("_csrf");
	}
	
	//rest Api to update data
	@PutMapping("/update/{id}")
	public Student updateStudent(@PathVariable int id,@RequestBody @Valid StudentDto stu) {
		return service.updateStudent(id, stu);
	}
	
	
	
	//rest Api to delete data
	@DeleteMapping("/delete/{id}")
	public Student deleteData (@PathVariable int id) {
		return service.deleteStudent(id);
	}
}
