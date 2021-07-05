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
				        <c:if test="${teacher != null}">
				            <form action="updateTeacher" method="post">
				        </c:if>
				        <c:if test="${teacher == null}">
				            <form action="insertTeacher" method="post">
				        </c:if>
				        <table border="1" cellpadding="5">
				            <caption>
				                <h2>
				                    <c:if test="${teacher != null}">Edit Teacher</c:if>
				                    <c:if test="${teacher == null}">Add New Teacher</c:if>
				                </h2>
				            </caption>
				                <c:if test="${teacher != null}">
				                    <input type="hidden" name="id" value="<c:out value='${teacher.id}' />" />
				                </c:if>           
				            <tr>
				                <th>Name: </th>
				                <td>
				                    <input type="text" name="name" size="45" value="<c:out value='${teacher.name}' />" />
				                </td>
				            </tr>
				            <tr>
				                <td colspan="2" align="center">
				                    <input type="submit" value="Save" />
				                </td>
				            </tr>
				        </table>
				        </form>
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
