package com.palle.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Student_DB_management_Spring")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int stuId;
	private String stuName;
	private int stuClass;
	private String stuLocation;
	private int stuFees;
	private String stuPhone;
	public int getStuId() {
		return stuId;
	}
	public void setStuId(int stuId) {
		this.stuId = stuId;
	}
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
	public void setStuLocation(String location) {
		this.stuLocation = location;
	}
	public int getStuFees() {
		return stuFees;
	}
	public void setStuFees(int fees) {
		this.stuFees = fees;
	}
	public String getStuPhone() {
		return stuPhone;
	}
	public void setStuPhone(String phone) {
		this.stuPhone = phone;
	}
	public Student(String stuName, int stuClass, String stuLocation, int stuFees, String stuPhone) {
		super();
		this.stuName = stuName;
		this.stuClass = stuClass;
		this.stuLocation = stuLocation;
		this.stuFees = stuFees;
		this.stuPhone = stuPhone;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
