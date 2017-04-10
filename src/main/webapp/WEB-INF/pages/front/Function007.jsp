<%@ include file="../module/header.jsp"%>
<script type="text/javascript" src="<c:url value="/static/js/jqgrid.user.js" />"></script>
<script>
$(function(){
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
<%@ include file="../module/navigation.jsp"%>
<c:url value="/FUNC007/fileUpload" var="theAction"/>
<form:form method="post" enctype="multipart/form-data" 
	modelAttribute="uploadedFile" action='${theAction}' >
	<table>
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
		
		<tr>
			<td>&nbsp;</td>
			<td><input type="submit" value="Upload" />
			</td>
			<td>&nbsp;</td>
		</tr>
	</table>
</form:form>

<c:if test="${message != null}">
	<h1>${message}</h1>
</c:if>

</body>
<%@ include file="../module/footer.jsp"%>
</html>