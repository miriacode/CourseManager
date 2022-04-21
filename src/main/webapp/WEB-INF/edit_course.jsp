<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Course</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>

	<div class="container">
		<h1>New Course</h1>
		<a href="/dashboard">Back To Projects</a>
		<form:form action="/courses/update" method="post" modelAttribute="course">
		<%--TO UPDATE: Necesario mandar el put y id--%>
			<input type="hidden" name="_method" value="put">
			<form:hidden path="id" value="${course.getId()}" />

			<div class="form-group">
				<form:label path="name">Name:</form:label>
				<form:input path="name" class="form-control"/>
				<form:errors path="name"/>
			</div>
			<div class="form-group">
				<form:label path="instructor">Instructor:</form:label>
				<form:input path="instructor" class="form-control"/>
				<form:errors path="instructor"/>
			</div>
			<div class="form-group">
				<form:label path="weekday">Weekday:</form:label>
				<form:input path="weekday" class="form-control"/>
				<form:errors path="weekday"/>
			</div>
			<div class="form-group">
				<form:label path="price">Price:</form:label>
				<%--Number with 2 decimals --%>
				<div class="input-group mb-3">
				  <div class="input-group-prepend">
				    <span class="input-group-text">$</span>
				  </div>
				  <form:input  path="price" pattern="^\d*(.\d{0,2})?$" class="form-control"/>
				</div>
				<form:errors path="price"/>
			</div>
			<div class="form-group">
				<form:label path="time">Time:</form:label>
				<form:input  path="time" type="time" class="form-control"/>
				<form:errors path="time"/>
			</div>
			<%--One to Many --%>
			<form:hidden path="creator" value="${user_session.getId() }"/>
			
			<a href="/dashboard" class="btn btn-danger">Cancel</a>
			<input type="submit" value="Update" class="btn btn-success">
		</form:form>
	</div>


</body>
</html>