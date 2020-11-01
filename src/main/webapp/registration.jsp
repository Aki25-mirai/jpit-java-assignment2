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
	    	<h3 class="mb-3">All Registrations</h3>
	    </div>
	   <div class="col">
				<c:url value="/reg-add.jsp" var="add"></c:url>
				<a href="${add }" class="btn btn-success">Add New Registration</a>
		</div>
	</div>
  	<div class="row my-3"> 
	 	<div class="col-md-12">
    	       <table class="table table-bordered shadow" style="width:100%;height: 100px;">
                <thead style="background-color: #EA6B6F;color: white;">
                  <tr>
                    <th scope="col" width="50px">No</th>

                    <th scope="col" >Student</th>
					
					<th scope="col" >Class</th>
					
                   	<th scope="col">Registration Date</th>

                    <th scope="col">Paid Amount</th>
                  </tr>
                </thead>
                <tbody>
                	<c:forEach items="${registrations }" var="r">
                		<tr>
						<td><c:set var="numberOfRows" value="${numberOfRows+1}"/>
							<c:out value="${numberOfRows}"/></td>
						<td>${r.student.name }</td>
						<td>${r.classes.name}</td>
						<td>${r.registration_date }</td>
						<td>${r.paid_amount}</td>
						<td><c:url var="edit" value="/reg-edit">
								<c:param name="regid">${r.id}</c:param>
							</c:url> <a href="${edit}" class="btn btn-outline-success">Edit</a></td>
						<td><c:url var="delete" value="/reg-delete">
								<c:param name="regid">${r.id }</c:param>
							</c:url> <a href="${delete}" class="btn btn-outline-danger">Delete</a></td>
					</tr>
                	</c:forEach>
                </tbody>
          </table>
        </div>
      </div>
   </div>
</body>
</html>