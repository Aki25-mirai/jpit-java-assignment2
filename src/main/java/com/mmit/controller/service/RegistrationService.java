package com.mmit.controller.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.mmit.entity.Registration;

public class RegistrationService {

	private static EntityManager em;
	public RegistrationService(EntityManager em) {
		this.em=em;
	}
	public Registration findById(int id) {
		
		return em.find(Registration.class, id);
	}
	public void delete(int id) {
		em.getTransaction().begin();
		Registration reg=em.find(Registration.class,id);
		em.remove(reg);
		em.getTransaction().commit();
		
	}
	public void save(Registration reg) {
		em.getTransaction().begin();
		
		if(reg.getId()==0) em.persist(reg);
		else em.merge(reg);
		em.getTransaction().commit();
		
	}
	public List<Registration> findAll() {
		TypedQuery< Registration> query=em.createNamedQuery("Registration.getAll", Registration.class);
		List<Registration> list=query.getResultList();
		return list;
	}

}
