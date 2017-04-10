<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登入系統</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<script src="//rawgithub.com/sydlawrence/jQuery-Shadow/master/jquery.shadow/jquery.shadow.js"></script>
<script src="./static/js/script.js"></script>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css">
<link rel="stylesheet" href="//rawgithub.com/sydlawrence/jQuery-Shadow/master/jquery.shadow/jquery.shadow.css">
<link rel="stylesheet" href="./static/css/style.css">

</head>
<body>
<div id='login'>
	<form method='post' action='Main'>
		<table width='300' border='0'>
			<tr><td colspan='2'><hr/></td></tr>
			<tr><td colspan='2' style="padding-left:20px; color:silver;"><img src='./static/imgs/logo.png' width='260'/></td></tr>
			<tr><td colspan='2'><hr/></td></tr>
			<tr><td width='30%' style='padding-left:20px;'>帳號</td><td><input type='text' name='userId' id='userId' value='admin'/></td></tr>
			<tr><td width='30%' style='padding-left:20px;'>密碼</td><td><input type='password' name='password' id='password' value='admin'/></td></tr>
			<tr><td colspan='2'>
			<c:choose>
				<c:when test="${error == null}">
					<div id='hint'><hr/></div>
				</c:when>
				<c:otherwise>
				<hr />
				<span style='padding-left:20px; color:red;'>${error}</span>
				<hr />
				</c:otherwise>
			</c:choose>
			</td></tr>
			<tr><td colspan='2' style='text-align:center;padding-bottom:15px;'>
			<input type='submit' value='送出' id='btn'/>
			
			</td></tr>
		</table>
	</form>
</div>
<!-- insert fake data button -->
<!-- 
<div style='margin-left:auto; margin-right:auto; margin-top:1em; width:100px;'>
	<form method='post' action='doFakeDatas'>
		<input type='submit' id='fake' value='insertFakeDatas'>
	</form>	
</div>
 -->
</body>
</html>