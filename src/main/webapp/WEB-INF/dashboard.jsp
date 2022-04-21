<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	<div class="d-flex justify-content-between">
		<h1>Welcome <c:out value="${user_session.getFirst_name()}"/>!</h1>
		<a href="/logout" class="btn btn-danger">Log out</a>
	</div>
	
	<h2>Course schedule</h2>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>Class Name</th>
				<th>Instructor</th>
				<th>Week Day</th>
				<th>Time</th>
				<th>Price</th>
				<th class="d-flex justify-content-around">Options</th>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach var="course" items="${courses}">
				<tr>
					<td><a href="/courses/${course.getId()}"><c:out value="${course.getName()}" /></a></td>
					<td><c:out value="${course.getInstructor()}" /></td>
					<td><c:out value="${course.getWeekday()}" /></td>
					<td><c:out value="${course.getTime()}" /></td>
					<td>$ <c:out value="${course.getPrice()}" /></td>
					<td class="">
						<c:if test="${course.getCreator().getId()==user_session.getId()}">
							<%--EDIT --%>
							<a href="/courses/edit/${course.getId()}" class="btn btn-warning btn-sm">Edit</a>	
							<%--DELETE --%>
							<form action="/courses/delete/${course.getId()}" method="post">
								<input type="hidden" name="_method" value="DELETE">
								<button type="submit" class="btn btn-danger btn-sm">Delete</button>
							</form>
						</c:if></td>
					</tr>
			</c:forEach>

		</tbody>
	</table>
	<a href="/courses/new" class="btn btn-primary">Add course</a>
	<%-- <a href="/instructor/new" class="btn btn-primary">Add Instructor</a>--%>
</body>
</html>