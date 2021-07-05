<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"> 
		<style>
		<%@ include file='style.css' %>
		</style>
		
	</head>
	
	<body>
		<div class="body">
			<div class="header">
				<div class="header"> 
					<div class="title">
						<h2>Online management system for Learner’s Academy</h2>
					</div> 
					<div class="clear"></div>
				</div>
				<div class="clear"></div>
			</div>
			
			
			<div class="menuBox">
				<ul>
					<%@ include file='menu.html' %>
				  
				</ul>
			</div>
			
			<div class="mainContent">  
					   
				    <div align="center">
				        <table border="1" cellpadding="5">
				            <caption><h2>List of Teachers</h2></caption>
				            <tr>
				                <th>ID</th>
				                <th>Name</th>
				                <th>Link with Subject</th>
				                <th>View Lined Subjects</th>
				                <th>Edit</th>
				                <th>Delete</th>
				            </tr>
				            <c:forEach var="teacher" items="${listTeachers}">
				                <tr>
				                    <td><c:out value="${teacher.id}" /></td>
				                    <td><c:out value="${teacher.name}" /></td> 
				                    <td><a href="addSubject?id=<c:out value='${teacher.id}' />">Add Subject</a></td>
				                    <td><a href="viewSubject?id=<c:out value='${teacher.id}' />">View Subjects</a></td>
				                    <td><a href="teacherEdit?id=<c:out value='${teacher.id}' />">Edit</a></td>
				                    <td><a href="teacherDelete?id=<c:out value='${teacher.id}' />">Delete</a></td>
				                </tr>
				            </c:forEach>
				        </table>
				    </div>     
				<div class="clear"></div>				
			</div>
			
			<div class="footer"> 
				<div class="copy">
					<p> Copy right @ 2021</p>
				</div>
				<div class="contacts">
					<i class="fa fa-envelope"></i> &nbsp; &nbsp;<a href="#"> email@company.com</a>
					<br/>
					<i class="fa fa-phone"></i>&nbsp; &nbsp; <a href="#">011 5555 55555</a>
					
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</body>
</html>

