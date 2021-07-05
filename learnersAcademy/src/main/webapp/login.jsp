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
				<div class="title">
					<h2>Online management system for Learner’s Academy</h2>
				</div> 
				<div class="clear"></div>
			</div>
			
			 
			
			
			<div class="mainContent">
				<div style="width:80%; margin-left: auto; margin-right: auto; border: 1px solid black">
					<h1 style="text-align: center;">Login Page</h1>
					<c:if test="${loginProcess != null}">
			            <h3 style="text-align: center; color: red">Invalid user</h3>
			        </c:if>
					<form action="./adminLogin" method="post">  
						<table style="width:50%; margin-left: auto; margin-right: auto;">
							<tr>
								<td>
									Name:
								</td>
								<td>
									<input type="text" name="name"><br>  
								</td>
							</tr>
							<tr>
								<td>
									Password:
								</td>
								<td>
									<input type="password" name="password"><br>  
								</td>
							</tr>
							<tr>
								<td colspan="2"> 
									<input type="submit" value="login"> 
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