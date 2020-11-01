package com.mmit.entity;

import java.io.Serializable;


import javax.persistence.*;

/**
 * Entity implementation class for Entity: Course
 *
 */
@Entity
@NamedQuery(name="Course.getAll",query="select c from Course c")
public class Course implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int fees;
	private String duration;
	private String level;
	
	
	public int getFees() {
		return fees;
	}

	public void setFees(int fees) {
		this.fees = fees;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	
	
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	private static final long serialVersionUID = 1L;

	public Course() {
		super();
	}
   
}
