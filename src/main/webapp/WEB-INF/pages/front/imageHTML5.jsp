<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${userId == null}">
	<% response.sendRedirect("../login"); %>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>影像查調 with HTML5</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<script src="//rawgithub.com/sydlawrence/jQuery-Shadow/master/jquery.shadow/jquery.shadow.js"></script>
<script src="<c:url value="../static/js/script.js" />"></script>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css">
<link rel="stylesheet" href="//rawgithub.com/sydlawrence/jQuery-Shadow/master/jquery.shadow/jquery.shadow.css">
<link rel="stylesheet" href="<c:url value="../static/css/style.css" />">
<script type="text/javascript" src="http://jqueryrotate.googlecode.com/svn/trunk/jQueryRotate.js"></script>
<script>
$(function(){
	var img = new Image();
	img.src = "${link}";
	var mycanvas = document.getElementById('caa');
	var context = mycanvas.getContext('2d');
	context.drawImage(img, 10, 10);
});
</script>
</head>

<body style='background-color:silver;'>
	<div id='sampleHead' class='sample'>
		<input type='button' class='mybtn' id='plus' value='Plus'/>
		<input type='button' class='mybtn' id='minus' value='Minus'/>
		<input type='button' class='mybtn' id='rL' value='Rotate Left'/>
		<input type='button' class='mybtn' id='rR' value='Rotate Right'/>
	</div>
	<div id='sampleDiv' class='sample'>
		<canvas id="caa" width="1000"></canvas>
	</div>
</body>
</html>