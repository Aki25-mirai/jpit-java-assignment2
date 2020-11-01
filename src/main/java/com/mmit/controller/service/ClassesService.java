package com.mmit.controller.service;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.mmit.entity.Classes;


public class ClassesService {
	
	private static EntityManager em;
	
	public ClassesService(EntityManager em) {
		this.em=em;
	}
	public List<Classes> findAll() {
		TypedQuery<Classes> query=em.createNamedQuery("Classes.getAll",Classes.class);
		List<Classes> list=query.getResultList();
		return list;
	}
	public static void save(Classes cl) {
		
		em.getTransaction().begin();
		if(cl.getId()==0)
			em.persist(cl);
		else
			em.merge(cl);
		
		em.getTransaction().commit();
		
	}
	public Classes findById(int id) {
		return em.find(Classes.class, id);
	}
	
	public void delete(int id) {
		em.getTransaction().begin();
		
		Classes cl=em.find(Classes.class,id);
		
		em.remove(cl);
		
		em.getTransaction().commit();
		
	}

}
