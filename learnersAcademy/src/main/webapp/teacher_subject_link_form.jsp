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
				        <form action="addSubject" method="post">
				        <table border="1" cellpadding="5" width="50%">
				            <caption>
				                <h2> Link Teacher [<c:out value='${teacher.name}' />] with Subjects</h2>
				            </caption>
				                <c:if test="${teacher != null}">
				                    <input type="hidden" name="id" value="<c:out value='${teacher.id}' />" />
				                </c:if>           
				            <tr>
				                <th>Class: </th>
				                <td>
									<select name="class_id" style="width: 80%" onchange="this.form.submit()"> 
										<option value="0">....</option>
					                    <c:forEach var="_class" items="${listClasses}"> 
							                <option value ="${_class.id}" <c:if test="${class_id == _class.id}">selected</c:if>>${_class.name}</option>
							            </c:forEach>
									</select>
				                </td>
				            </tr>        
							<tr>
				                <th>Subject: </th>
				                <td>
									<select name="subject_id" style="width: 80%"> 
										<option value="0">....</option>
					                    <c:forEach var="subject" items="${listSubjects}"> 
											<c:if test="${class_id == subject.classid}">
							               		<option value ="${subject.id}" >${subject.name}</option>
							                </c:if>   
							            </c:forEach>
									</select>
				                </td>
				            </tr>         
				            <tr>
				                <td colspan="2" align="center">
				                    <input type="submit" value="Add" name="add" />
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
