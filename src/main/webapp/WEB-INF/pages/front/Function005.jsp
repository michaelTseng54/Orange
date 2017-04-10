<%@ include file="../module/header.jsp"%>
<script type="text/javascript" src="<c:url value="/static/js/jqgrid.score.js" />"></script>
</head>
<body>
<%@ include file="../module/navigation.jsp"%>
<div id='showTypebar'>
	<input type='radio' class='showType' name='showType' value='javascript' checked/>javascript&nbsp;
	<input type='radio' class='showType' name='showType' value='HTML5'/>HTML5&nbsp;
	<input type='radio' class='showType' name='showType' value='webservice'/>webservice
</div>
<%@ include file="../module/grid.jsp"%>
</body>
<%@ include file="../module/footer.jsp"%>
</html>