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
		<h3 class="col-6 mt-2">${not empty course ? 'Edit Course' : 'Add new Course' }</h3>
	</div>
	<c:url var="save" value="/course-add"></c:url>
	<form action="${save }" class="form col-6" method="post">
	<input type="hidden" name="courseid" value="${ course.id}" />
	<div class="form-group">
			<label for=""> Name</label>
			<input type="text" value="${course.name }" name="coursename" class="form-control" placeholder="Enter course name" required="required" />
		
			<label for="">Fees</label>
			<input type="number" value="${course.fees }" name="coursefees" class="form-control" placeholder="Enter course fees" required="required" />
		
	
			<label for="">Duration</label>
			<input type="text" value="${course.duration }" name="courseduration" class="form-control" placeholder="Enter course duration" required="required" />
		
			<label>Level</label> 
				 <select name="courselevel" class="form-control">
				 <option value="Basic" ${"Basic"==course.level?'selected':'' }>Basic</option>
				 <option value="Intermediate" ${"Intermediate"==course.level?'selected':'' }>Intermediate</option>
				 <option value="Advance" ${"Advance"==course.level?'selected':'' }>Advance</option>
				 </select>
	</div>
		<button class="btn btn-success" type="submit">Save</button>
		<button class="btn btn-danger" type="reset">Clear</button>
	</form>
</div>
</body>
</html>
