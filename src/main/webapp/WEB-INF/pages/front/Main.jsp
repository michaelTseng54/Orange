<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:if test="${userId == null}">
	<% response.sendRedirect("../login"); %>
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OSN轉檔程式</title>
<%@ include file="../module/import.jsp"%>
<script type="text/javascript" src="<c:url value="/static/js/jqgrid.user.js" />"></script>
<script src="//rawgithub.com/sydlawrence/jQuery-Shadow/master/jquery.shadow/jquery.shadow.js"></script>
<link rel="stylesheet" href="//rawgithub.com/sydlawrence/jQuery-Shadow/master/jquery.shadow/jquery.shadow.css">
</head>

<script>
$(function(){
	$('#main').shadow();
	$('#bt1').click(function(){
		$.ajax({
			url : "FUNC007/query",
			data : "test=" + $('#query').val(),
			method : "POST"
		});
	});
	$('#bt2').click(function(){
		$.ajax({
			url : "FUNC007/insert",
			data : "test=" + $('#insert').val(),
			method : "POST"
		});		
	});
	$('#bt3').click(function(){
		$.ajax({
			url : "FUNC007/update",
			data : "test=" + $('#update').val(),
			method : "POST"
		});		
	});
	$('#bt4').click(function(){
		$.ajax({
			url : "FUNC007/delete",
			data : "test=" + $('#delete').val(),
			method : "POST"
		});		
	});
});
</script>
</head>
<body>
<div id='main' style='width:400px; margin-left:auto; margin-right:auto; margin-top:1em; padding-left:15px; padding-top:20px;padding-bottom:20px;'>
<div style='width:380px; height: 16px; margin-left:auto; margin-right:auto;'>
<strong>OSN轉檔程式</strong><br/><hr style='width:360px; padding-left:-10px;'/>
</div>
<br/><br/>
<c:url value="/FUNC007/fileUpload" var="theAction"/>
<form:form method="post" enctype="multipart/form-data" 
	modelAttribute="uploadedFile" action='${theAction}' >
	<table border='0' width='95%'>
		<tr>
			<td>Upload File:&nbsp;</td>
			<td><input type="file" name="file" />
			</td>
			<td style="color: red; font-style: italic;">
			<form:errors path="file" />
			</td>
		</tr>
		<tr>
			<td>Currency:&nbsp;</td>
			<td><input type="text" name="curId" value="USD" size='3'/>
			</td>
			<td style="color: red; font-style: italic;">
			</td>
		</tr>
		<tr><td colspan='2'><hr/></td></tr>
		<tr>
			<td colspan='2'><center><input type="submit" value="Upload" /></center></td>
		</tr>
	</table>
</form:form>

<c:if test="${message != null}">
	<h1>${message}</h1>
</c:if>
</div>
</body>
</html>