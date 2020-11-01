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
	    	<h3 class="mb-3">All Classes</h3>
	    </div>
	   <div class="col">
				<c:url value="/classes-add.jsp" var="add"></c:url>
				<a href="${add }" class="btn btn-success">Add New Class</a>
		</div>
	</div>
  
  
  	<div class="row my-3"> 
	 	<div class="col-md-12">
    	       <table class="table table-bordered shadow" style="width:100%;height: 100px;">
                <thead style="background-color: #EA6B6F;color: white;">
                  <tr>
                    <th scope="col" width="50px">No</th>

                    <th scope="col" >Class Name</th>

                   	<th scope="col" width="50px">Start Date</th>

                    <th scope="col" colspan="2">Action</th>
                  </tr>
                </thead>
                <tbody>
                	<c:forEach items="${classes }" var="c">
                		<tr>
	                		<td>${c.id }</td>
	                		<td>${c.name }</td>
	                		<td>${c.start_date }</td>
	                		<td>
							<c:url var="edit" value="/classes-edit">
								<c:param name="classid">${c.id }</c:param>
							</c:url>
							<a href="${edit }" class="btn btn-outline-warning">Edit</a>
						</td>
						<td>
							<a href="#" class="btn btn-outline-danger">Delete</a>
						</td>
	                		
                	</tr>
                	</c:forEach>
                </tbody>
          </table>
        </div>
      </div>
   </div>
</body>
</html>
