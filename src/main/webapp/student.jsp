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
<div class="row my-3">
	  	<div class="col-4">
	    	<h3 class="mb-3">All Students</h3>
	    </div>
	   <div class="col offset-2">
				<c:url value="/student-add.jsp" var="add"></c:url>
				<a href="${add }" class="btn btn-success">Add New Student</a>
		</div>
	</div>
  
  
  	<div class="row my-3"> 
	 	<div class="col-md-12">
    	       <table class="table table-bordered shadow" style="width:100%;height: 100px;">
                <thead style="background-color: #EA6B6F;color: white;">
                  <tr>
                    <th scope="col" width="50px">No</th>

                    <th scope="col" >Student Name</th>
                    
                    <th scope="col" >Email</th>

                   	<th scope="col">Phone Number</th>

                    <th scope="col">Action</th>
                  </tr>
                </thead>
                <tbody>
                	<c:forEach items="${student }" var="s">
                		<tr>
						<td><c:set var="numberOfRows" value="${numberOfRows+1}"/>
							<c:out value="${numberOfRows}"/></td>
						<td>${s.name }</td>
						<td>${s.email }</td>
						<td>${s.phone }</td>
						
						<td>
						<c:url var="action" value="/student-edit">
						<c:param name="studentid">${s.id }</c:param></c:url>
						<a href="${action}" class="btn btn-outline-success">Edit</a>
						<c:url var="del" value="/student-delete">
						<c:param name="studentid">${s.id }</c:param></c:url>
						<a href="${del }" class="btn btn-outline-danger">Delete</a></td>
						
					</tr>
                	</c:forEach>
                </tbody>
          </table>
        </div>
      </div>
   </div>
</body>
</html>
