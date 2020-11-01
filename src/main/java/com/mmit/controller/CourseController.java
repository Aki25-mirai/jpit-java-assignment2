package com.mmit.controller;

import java.io.IOException;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.mmit.controller.service.CourseService;
import com.mmit.entity.Course;




@WebServlet({"/courses","/course-add","/course-edit","/course-delete"})
public class CourseController extends HttpServlet{

	
	private static final long serialVersionUID = 1L;
	
	private CourseService courseService;
	@Override
	public void init(ServletConfig config) throws ServletException {
	
		
		super.init(config);
		EntityManagerFactory EMF=null;
		Object obj=getServletContext().getAttribute("emf");//application scope
		
		if(obj==null)
		{
			EMF=Persistence.createEntityManagerFactory("student-reg-system.assignment");
			getServletContext().setAttribute("emf", EMF);
		}
		else
		{
			EMF=(EntityManagerFactory) obj;
		}
		courseService=new CourseService(EMF.createEntityManager());
		
	
	
	}
	
	@Override
	public void destroy() {
		EntityManagerFactory emf=(EntityManagerFactory) getServletContext().getAttribute("emf");
		if(emf!=null && emf.isOpen())
			emf.close();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String action=req.getServletPath();
		if("/course-add".equals(action)) {
			//get data from req
			
			String id=req.getParameter("courseid");
			String name=req.getParameter("coursename");
			String fees=req.getParameter("coursefees");
			String duration=req.getParameter("courseduration");
			String level=req.getParameter("courselevel");
			
			
			//create obj
			
			Course c=(id==null || id.equals("") ? new Course() : courseService.findById(Integer.parseInt(id)));
			c.setName(name);
			c.setFees(Integer.parseInt(fees));
			c.setDuration(duration);
			c.setLevel(level);
			req.setAttribute("courses", c);
			
			//insert to db
			courseService.save(c);
			
			//redirect page
			resp.sendRedirect(req.getContextPath().concat("/courses"));
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action=req.getServletPath();
		if("/courses".equals(action))
		{
			//get data from db
			List<Course> list=courseService.findAll();
			
			//set data to req
			req.setAttribute("courses", list);
			
			//forward page
			getServletContext().getRequestDispatcher("/course.jsp").forward(req, resp);
		}
		else if("/course-edit".equals(action)) {
			
			//get id from req
			String courseId=req.getParameter("courseid");
			
			//get data from db
			Course cou=courseService.findById(Integer.parseInt(courseId));
			
			//set data to req
			req.setAttribute("course", cou);
			
			//forward page
			getServletContext().getRequestDispatcher("/course-add.jsp").forward(req, resp);
		}
	}
}