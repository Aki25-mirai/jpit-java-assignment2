<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark mt-2">
		<a href="navbar-brand" href="#">StudentRegSystem</a>
		<ul class="navbar-nav">
			<c:url value="/courses" var="course"></c:url>
			<c:url value="/classes" var="classes"></c:url>
			<c:url value="/registrations" var="registration"></c:url>
			<c:url value="/students" var="student"></c:url>
			<li class="nav-item">
				<a href="${course }" class="nav-link">Courses</a>
			</li>
			<li class="nav-item">
				<a href="${classes }" class="nav-link">Classes</a>
			</li>
			<li class="nav-item">
				<a href="${registration }" class="nav-link">Registration</a>
			</li>
			<li class="nav-item">
				<a href="${student }" class="nav-link">Students</a>
			</li>
		</ul>
</nav>