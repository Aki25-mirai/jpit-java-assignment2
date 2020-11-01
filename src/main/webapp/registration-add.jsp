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
<h4 class="col-6">${not empty registrations?'Edit Registration':'Add New Registration' }</h4>

</div>
<c:url value="/reg-add" var="save"></c:url>
<form action="${save }" class="form" method="post">
	<input type="hidden" name="regid" value="${registrations.id }" >
	<div class="form-group">
	<label>Student</label>
	<select name="stuid" class="form-control col-6">
	<c:forEach items="${student}" var="s">
	<option value="${s.id }" ${registrations.student.id==s.id?'selected':'' }>${s.name }</option>
	</c:forEach>
	</select>
	</div>
	<div class="form-group">
	<label>Class</label>
	<select name="classid" class="form-control col-6">
	<c:forEach items="${classes}" var="c">
	<option value="${c.id }" ${registrations.classes.id==c.id?'selected':'' }>${c.name }</option>
	</c:forEach>
	</select>
	</div>
	<div class="form-group">
	<label>Registration Date</label>
	<input type="date" value="${registrations.registration_date}" name="regdate" class="form-control" required="required" />
	<label>Paid Amount</label>
	<input type="number" value="${registrations.paid_amount}" name="amt" class="form-control" required="required" placeholder="Enter  Paid Amount"/>
	
	</div>
	<button type="submit" class="btn btn-success">Save</button>
	<button type="reset" class="btn btn-danger">Clear</button>
	
</form>
	</div>
</body>
</html>
