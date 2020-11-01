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
		<h4 class="col-6">${(not empty classes) ? 'Edit Class' : 'Add New Class' }</h4>
			<c:url var="save" value="/classes-add"></c:url>
			<form action="${save }" class="form col-6" method="post">
				<input type="hidden" name="classid" value="${classes.id }" />
					<div>
						<label for="">Class Name</label>
						<input type="text" value="${classes.name }" name="classname" class="form-control" required="required" placeholder="Enter New Class Name"/>
						<label for="">Start Date</label>
						<input type="date" value="${classes.start_date }" name="start_date" class="form-control" required="required" placeholder="Enter Start Date"/>
					</div>
				<button type="submit" class="btn btn-success">Save</button>
				<button type="reset" class="btn btn-danger">Cancel</button>
				
			</form>
			
	</div>
</div>
</body>
</html>

