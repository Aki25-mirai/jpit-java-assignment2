package com.mmit.controller;


import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mmit.controller.service.ClassesService;
import com.mmit.controller.service.StudentService;
import com.mmit.entity.Classes;
import com.mmit.entity.Student;
@WebServlet({"/students","/student-add","/student-edit","/student-delete"})
public class StudentController extends HttpServlet{

	
	private static final long serialVersionUID = 1L;
	private StudentService stuService;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		EntityManagerFactory EMF=null;
		Object obj=getServletContext().getAttribute("emf");//application scope
		if(obj==null) {
			EMF=Persistence.createEntityManagerFactory("student-reg-system.assignment");
			getServletContext().setAttribute("emf", EMF);
		}else {
			EMF=(EntityManagerFactory) obj;
		}
		stuService=new StudentService(EMF.createEntityManager());
		
	}
	@Override
		public void destroy() {
			EntityManagerFactory emf=(EntityManagerFactory) getServletContext().getAttribute("emf");
			if(emf!=null && emf.isOpen()) {
				emf.close();
			}
		}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path=req.getServletPath();
		if("/student-add".equals(path)) {
			
			String sid=req.getParameter("studentid");
			String name=req.getParameter("studentname");
			String email=req.getParameter("email");
			String phone=req.getParameter("phone");
			
			//create object
			
			Student s=(sid==null || sid.equals("") ? new Student():stuService.findById(Integer.parseInt(sid)));
			s.setName(name);
			s.setEmail(email);
			s.setPhone(phone);
			//add to db
			
			stuService.save(s);
			//set to request
			req.setAttribute("student",s);
			//redirect page
			
			resp.sendRedirect(req.getContextPath().concat("/students"));
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path=req.getServletPath();
		if("/students".equals(path)) {
			List<Student> list=stuService.findAll();
			//set to request
			
			req.setAttribute("student",list);
			//forward
			getServletContext().getRequestDispatcher("/student.jsp").forward(req,resp);
		
		}else if("/student-add".equals(path)||"/student-edit".equals(path)) {
			//get data from db
			
			List<Student> list=stuService.findAll();
			
			//set data to req
			req.setAttribute("student",list);
			
		 if("/student-edit".equals(path)) {
			//get id from request 
			String sId=req.getParameter("studentid");
			
			//get data from db
			
			Student s=stuService.findById(Integer.parseInt(sId));
			
			//set data to req
			
			req.setAttribute("student", s);
			
		 }
		//forward page
			
			getServletContext().getRequestDispatcher("/student-add.jsp").forward(req, resp);
			
		}else if("/student-delete".equals(path)){
			String id=req.getParameter("studentid");
			
			stuService.delete(Integer.parseInt(id));
			resp.sendRedirect(req.getContextPath().concat("/students"));
			
	
	}
	}
	
	

}
