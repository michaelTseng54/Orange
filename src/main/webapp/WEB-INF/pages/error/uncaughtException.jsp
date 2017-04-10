<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.apache.log4j.Logger" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.io.StringWriter" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Exception Handler</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<script src="//rawgithub.com/sydlawrence/jQuery-Shadow/master/jquery.shadow/jquery.shadow.js"></script>
<script src="./static/js/script.js"></script>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css">
<link rel="stylesheet" href="//rawgithub.com/sydlawrence/jQuery-Shadow/master/jquery.shadow/jquery.shadow.css">
<link rel="stylesheet" href="./static/css/style.css">
</head>
<body>
<%  response.setStatus(200);%>
<%  Exception ex = (Exception) request.getAttribute("javax.servlet.error.exception");
	Logger logger = Logger.getLogger("uncaughtException.jsp");
	StringWriter sw = null;
	if (ex != null) {
		sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
	}
%>
<div id='exceptionMessageHint'>
<c:choose>
	<c:when test="${status == null}">
		<h3>Exception Message</h3>
		<hr/>
		使用者ID : ${userId} <br/>
		發生時間 : <%=new Date() %> <br/>
		發生狀況 : <%=ex.getMessage()%> <br/>
		例外種類 : <%=ex%>
		<hr/>
		詳細訊息 : <%=sw.toString() %>
	</c:when>
	<c:otherwise>
		<h3>Error Message</h3>
		<hr/>
		使用者ID: ${userId} <br/>
		發生時間: ${time} <br/>
		頁面狀況: ${status} <br/>
	</c:otherwise>
</c:choose>
</div>
</body>
</html>