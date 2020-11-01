<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<jsp:include page="resources/common.jsp"></jsp:include>
</head>
<body>
<div class="container">
	<jsp:include page="resources/menu.jsp"></jsp:include>
<div class="row">
			<h4 class="col-6 mt-2">${not empty student ? 'Edit Student':'Add New Student'}</h4>
		</div>

		<c:url value="/student-add" var="save"></c:url>
		<form action="${save }" class="form col-6" method="post" >
			<input type="hidden" name="studentid" value="${student.id }"> 
			
			<div class="form-group">
				<label>Student Name</label> <input type="text" 
					name="studentname" value="${student.name }" class="form-control" required="required"
					placeholder="Enter Student Name" />
					
				 <label>Email</label> 
				 <input type="email" name="email" value="${student.email }" class="form-control" required="required" placeholder="Enter  Email" />
				 
				 <label>Phone Number</label> 
				 <input type="text" name="phone" value="${student.phone }" class="form-control" required="required" placeholder="Enter Phone No" />

				</div>
			<button type="submit" class="btn btn-success">Save</button>
			<button type="reset" class="btn btn-danger">Clear</button>

		</form>
		
	</div>
</body>
</html>
