package com.mmit.entity;

import java.io.Serializable;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;


/**
 * Entity implementation class for Entity: Classes
 *
 */
@Entity
@NamedQuery(name="Classes.getAll",query="select i from Classes i")
public class Classes implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private LocalDate start_date;
	@OneToMany(mappedBy = "classes")
	private List<Registration> registration;
	

	public List<Registration> getRegistration() {
		return registration;
	}

	public void setRegistration(List<Registration> registration) {
		this.registration = registration;
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

	public LocalDate getStart_date() {
		return start_date;
	}

	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}

	private static final long serialVersionUID = 1L;

	public Classes() {
		super();
	}
   
}
