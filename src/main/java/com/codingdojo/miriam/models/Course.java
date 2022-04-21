package com.codingdojo.miriam.models;


import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="courses")
public class Course{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message="Name of the course is required.")
	@Size(min=4, max=100, message="The course name length must be from 4 to 100 characters long.")
	private String name;
	
	@NotNull(message="Name of the instructor is required.")
	@Size(min=4, max=100, message="The instructor name length must be from 4 to 100 characters long.")
	private String instructor;
	
	@NotNull(message="Weekday is required.")
	@Size(min=3, max=100, message="The weekday length must be from 3 to 100 characters long.")
	private String weekday;
	
	@NotNull
	@Min(0)
	@Max(1000000)
	private Double price;
	
	//JUST HOUR
	@DateTimeFormat(pattern="HH:mm")
	private LocalTime time;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date created_at;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updated_at;
	
	//*
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User creator;
	
	//**
	@OneToMany(mappedBy="course", fetch=FetchType.LAZY)
	private List<Student> students;

	public Course() {
		
	}

	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getInstructor() {
		return instructor;
	}



	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}



	public String getWeekday() {
		return weekday;
	}



	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}



	public Double getPrice() {
		return price;
	}



	public void setPrice(Double price) {
		this.price = price;
	}



	public LocalTime getTime() {
		return time;
	}



	public void setTime(LocalTime time) {
		this.time = time;
	}



	public User getCreator() {
		return creator;
	}



	public void setCreator(User creator) {
		this.creator = creator;
	}



	public List<Student> getStudents() {
		return students;
	}



	public void setStudents(List<Student> students) {
		this.students = students;
	}



	@PrePersist
    protected void onCreate(){
        this.created_at = new Date();
    }
    
	@PreUpdate
    protected void onUpdate(){
        this.updated_at = new Date();
    }
	
}
