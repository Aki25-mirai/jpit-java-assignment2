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
import com.mmit.controller.service.RegistrationService;
import com.mmit.controller.service.StudentService;
import com.mmit.entity.Student;
import com.mmit.entity.Classes;
import com.mmit.entity.Registration;

@WebServlet({"/reg-add","/reg-edit","/reg-delete","/registrations"})
public class RegistrationController extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	private RegistrationService regService;
	private StudentService stuService;
	private ClassesService classesService;
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
		classesService=new ClassesService(EMF.createEntityManager());
		regService=new RegistrationService(EMF.createEntityManager());
		
	}
	@Override
		public void destroy() {
			EntityManagerFactory emf=(EntityManagerFactory) getServletContext().getAttribute("emf");
			if(emf!=null && emf.isOpen()) {
				emf.close();
			}
		}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action=req.getServletPath();
		if("/reg-add".equals(action)||"/reg-edit".equals(action)) {
			//get student list from db
			
			List<Student> stulist=stuService.findAll();
			//add list to request
			req.setAttribute("student", stulist);
			
			//get class list from db
			
			List<Classes> classlist=classesService.findAll();
			//add list to request
			req.setAttribute("classes", classlist);
			
			if("/reg-edit".equals(action)) {
				//get reg id from req
				String id=req.getParameter("regid");
				
				//find registration to req
				Registration obj=regService.findById(Integer.parseInt(id));
				//add registration to req
				
				req.setAttribute("registrations", obj);
				
				
			}
			
			//forward page
			getServletContext().getRequestDispatcher("/registration-add.jsp").forward(req,resp);
		
		}else if("/registrations".equals(action)) {
			//get registration from db
			List<Registration> list=regService.findAll();
			
			//add registration to req
			req.setAttribute("registrations",list);
			//forward
			getServletContext().getRequestDispatcher("/registration.jsp").forward(req,resp);
		}else if("/reg-delete".equals(action)) {
			//get id from req
			String id=req.getParameter("regid");
			
			//remove from db
			
			regService.delete(Integer.parseInt(id));
			//redirect page
			resp.sendRedirect(req.getContextPath().concat("/registrations"));
		}
		}
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String action=req.getServletPath();
			if("/reg-add".equals(action)) {
				//get data from request
				String regid=req.getParameter("regid");
				String sId=req.getParameter("stuid");
				String cId=req.getParameter("classid");
				String rdate=req.getParameter("regdate");
				String amt=req.getParameter("amt");
				//create registration  obj
				
				Registration reg=(regid==null || regid.equals("") ? new Registration():regService.findById(Integer.parseInt(regid)));
				reg.setStudent(stuService.findById(Integer.parseInt(sId)));
				
				reg.setClasses(classesService.findById(Integer.parseInt(cId)));
				reg.setRegistration_date(LocalDate.parse(rdate));
				reg.setPaid_amount(Integer.parseInt(amt));
				
				//insert to db
				regService.save(reg);
				//redirect page
				resp.sendRedirect(req.getContextPath().concat("/registrations"));
				
			}
	}


}
