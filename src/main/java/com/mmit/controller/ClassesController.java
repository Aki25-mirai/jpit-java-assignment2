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
import com.mmit.entity.Classes;
import com.mmit.entity.Course;




@WebServlet({"/classes-add","/classes","/classes-edit","/classes-delete"})
public class ClassesController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private ClassesService  classesService;
	
	//for user click
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
		classesService =new ClassesService(EMF.createEntityManager());
		
	
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
		
		if("/classes-add".equals(action))
		{
			//get data from req
			String classid=req.getParameter("classid");
			String name=req.getParameter("classname");
			String start_date=req.getParameter("start_date");
			LocalDate ldate = LocalDate.parse(start_date);
			
			
			//create item obj
			Classes cl=(classid==null || classid.equals("") ? new Classes() : classesService.findById(Integer.parseInt(classid)));
			//Item item=new Item();
			cl.setName(name);
			cl.setStart_date(ldate);
			
			//insert to db
			ClassesService.save(cl);
			
			//set to request
			req.setAttribute("classes", cl);
			
			//redirect page
			resp.sendRedirect(req.getContextPath().concat("/classes"));
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path=req.getServletPath();
		if("/classes-add".equals(path) || "/classes-edit".equals(path))
		{
			//get data from db
			List<Classes> list=classesService.findAll();
			
			
			//set data to req
			req.setAttribute("classes", list);
			
			if("/classes-edit".equals(path))
			{
				//get id from req
				String id=req.getParameter("classid");
				//get data from db
				Classes cl=classesService.findById(Integer.parseInt(id));
				//set data to req
				req.setAttribute("classes", cl);
				
			}
			
			//forward page
			getServletContext().getRequestDispatcher("/classes-add.jsp").forward(req, resp);
			
		}
		else if("/classes".equals(path))
		{
			//get items from db
			List<Classes> list=classesService.findAll();
			//add items to req
			req.setAttribute("classes", list);
			//forward page
			getServletContext().getRequestDispatcher("/classes.jsp").forward(req, resp);
		}
		else if("/classes-delete".equals(path))
		{
			//get id from req data
			String classid=req.getParameter("classid");
			
			//remove from db
			classesService.delete(Integer.parseInt(classid));
			
			//redirect page
			resp.sendRedirect(req.getContextPath().concat("/classes"));
		}
		
	}
}

