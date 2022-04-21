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
		<h1>${course.getName()}</h1>
		<p>
			<b>Day:</b> ${course.getWeekday() }
		</p>
		<p>
			<b>Cost:</b>$  ${course.getPrice() }
		</p>
		<p>
			<b>Time:</b> ${course.getTime()}
		</p>
	</div>
	<br>
	<c:if test="${course.getCreator().getId()==user_session.getId()}">
	<div class="container border border-dark">
		<div class="container">
			<h2>Students</h2>
			<form:form action="/students/create" method="post" modelAttribute="student">
				<form:hidden path="course" value="${course.getId()}"/>
				
				<div class="form-group">
					<form:label path="student_email">Email:</form:label>
					<form:input path="student_email" type="email" class="form-control"/>
					<form:errors path="student_email"/>
				</div>
				<div class="form-group">
					<form:label path="student_name">Name:</form:label>
					<form:input path="student_name" class="form-control"/>
					<form:errors path="student_name"/>
				</div>
				
				<input type="submit" value="Create" class="btn btn-primary">
			</form:form>
		</div>
		
		
		<%-- <div class="container">
			<form:form method="post" action="/courses/add_student" modelAttribute="student">
				<div class="form-group">
					<form:label path="student">Existing Students:</form:label>
					<form:select path="student" class="form-control">
						<c:forEach var="student" items="${students}">
							<option value="${student}">${student.getStudent_name()} - ${student.getStudent_email()} </option>
						</c:forEach>
					</form:select>
					<form:errors path="state" class="text-danger" />
				</div>
			</form:form>
			<input type="submit" value="Add" class="btn btn-primary">
		</div>--%>
	</div>
	</c:if>
	
</body>
</html>