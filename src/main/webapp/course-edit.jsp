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
		<h4>Edit Course</h4>
	</div>
	<form action="" class="form">
		<input type="hidden" name="courseid " value="${course.id }"/>
		<div class="form-group">
			<label for="">Course Name</label>
			<input type="text" name="coursename" value="${course.name }" />
		</div>
		<div class="form-group">
			<label for="">Course Name</label>
			<input type="number" name="coursefees" value="${course.fees }" />
		</div>
		<div class="form-group">
			<label for="">Course Name</label>
			<input type="text" name="courseduration" value="${course.duration }" />
		</div>
		<div class="form-group">
			<label for="">Course Name</label>
			<input type="text" name="courselevel" value="${course.level }" />
		</div>
		<button class="btn btn-success" type="submit">Save</button>
		<button class="btn btn-danger" type="reset">Clear</button>
	</form>
</div>
</body>
</html>
