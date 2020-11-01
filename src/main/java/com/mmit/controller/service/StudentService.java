package com.mmit.controller.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


import com.mmit.entity.Student;

public class StudentService {
	private static EntityManager em;
	public StudentService(EntityManager em) {
		this.em=em;
	}
	public Student findById(int id) {
		
		return em.find(Student.class, id);
	}
	public void save(Student s) {
		em.getTransaction().begin();
		if(s.getId()==0) 
		em.persist(s);
		else em.merge(s);
		em.getTransaction().commit();
		
	}
	public List<Student> findAll() {
		TypedQuery< Student> query=em.createNamedQuery("Student.getAll",Student.class);
		List<Student> list=query.getResultList();
		return list;
	}
	public void delete(int id) {

		em.getTransaction().begin();
		Student stu=em.find(Student.class, id);
		em.remove(stu);
		em.getTransaction().commit();
		
		
	}

}