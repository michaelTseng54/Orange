<%@ include file="../module/header.jsp"%>
<c:choose>
	<c:when test="${method == 'dept'}">
		<script type="text/javascript" src="<c:url value="/static/js/jqgrid.dept.js" />"></script>
	</c:when>
	<c:when test="${method == 'faculty'}">
		<script type="text/javascript" src="<c:url value="/static/js/jqgrid.faculty.js" />"></script>
	</c:when>
	
</c:choose>
</head>
<body>
<input type='hidden' value='${methodCname}' id='method'/>
<%@ include file="../module/navigation.jsp"%>
<%@ include file="../module/grid.jsp"%>
</body>
<%@ include file="../module/footer.jsp"%>
</html>