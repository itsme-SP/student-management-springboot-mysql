package com.palle.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class StudentDto {
	@NotBlank(message = "The name cannot be blank")
	private String stuName;
	@Min(1)
	@Max(12)
	private int stuClass;
	@NotBlank(message = "The location cant be blank")
	private String stuLocation;
	@Min(1)
	private int stuFees;
	@Pattern(regexp = "^[7-9]\\d{9}$")
	private String stuPhone;
	
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public int getStuClass() {
		return stuClass;
	}
	public void setStuClass(int stuClass) {
		this.stuClass = stuClass;
	}
	public String getStuLocation() {
		return stuLocation;
	}
	public void setStuLocation(String stuLocation) {
		this.stuLocation = stuLocation;
	}
	public int getStuFees() {
		return stuFees;
	}
	public void setStuFees(int stuFees) {
		this.stuFees = stuFees;
	}
	public String getStuPhone() {
		return stuPhone;
	}
	public void setStuPhone(String stuPhone) {
		this.stuPhone = stuPhone;
	}
	
	public StudentDto(String stuName, int stuClass, String stuLocation, int stuFees, String stuPhone) {
		super();
		this.stuName = stuName;
		this.stuClass = stuClass;
		this.stuLocation = stuLocation;
		this.stuFees = stuFees;
		this.stuPhone = stuPhone;
	}
	public StudentDto() {
		super();
		// TODO Auto-generated constructor stub
	}
}
