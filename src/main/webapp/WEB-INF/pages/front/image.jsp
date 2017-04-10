<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${userId == null}">
	<% response.sendRedirect("../login"); %>
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>臺灣師範大學教師成績總表文件數位化系統</title>
<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<script type="text/javascript" src="<c:url value="/static/js/jquery.elevatezoom.js"/>"></script>
<!-- 
<script type="text/javascript" src='//rawgithub.com/elevateweb/elevatezoom/master/jquery.elevateZoom-3.0.8.min.js'></script>
 -->
<script type="text/javascript" src="http://jqueryrotate.googlecode.com/svn/trunk/jQueryRotate.js"></script>
<link type="text/css" rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<link type="text/css" rel="stylesheet" href="<c:url value="/static/css/style.css" />">
<!-- bootstrap -->
<link type="text/css" rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.3.2/css/bootstrap-responsive.min.css">
<script>
$(function(){
	$('#sampleDiv').draggable();
	var oriWidth = "${width}";
	if (oriWidth > $(window).width()) {
		$('#samplePic').css('width', $(window).width() * 0.9);
		//.css('height', $(window).height() * 0.9);
	}
	$('#plus').click(function(){
		var wwidth = $('#samplePic').width() + ($('#samplePic').width() * 0.1);
		var hheight = $('#samplePic').height() + ($('#samplePic').height() * 0.1);
		$('#samplePic').css('width', wwidth).css('height', hheight);
	});
	$('#minus').click(function(){
		var wwidth = $('#samplePic').width() - ($('#samplePic').width() * 0.1);
		var hheight = $('#samplePic').height() - ($('#samplePic').height() * 0.1);
		$('#samplePic').css('width', wwidth).css('height', hheight);
	});
	$('#rR').click(function(){
		var formData = {
				fileName: $('#fileName').val(),
				rotate: "right", 
				year: $('#year').val()
			};
		$.ajax({
			url : "rotateImage",
			data : formData,
			method : "GET",
			success: function(datas) {
				var response = jQuery.parseJSON(datas);
				if (response.width2 > $(window).width()) {
					$('#samplePic').attr('src', response.link).css('width', $(window).width() * 0.9);//.css('height', $(window).height() * 0.9);
				} else {
					$('#samplePic').attr('src', response.link).css('width', response.width2);//.css('height', $(window).height() * 0.9);
				}
				$('.zoomWindow').css('background-image', "url(" + response.link + ")");
			}	
		});
		/* if (rotate <= 360) {
			rotate += 90;
		} else {
			rotate = 0;
		}
		$('#samplePic').rotate(rotate); */
	});
	$('#rL').click(function(){
		var formData = {
				fileName: $('#fileName').val(),
				rotate: "left", 
				year: $('#year').val()
			};
		$.ajax({
			url : "rotateImage",
			data : formData,
			method : "GET",
			success: function(datas) {
		    	var response = jQuery.parseJSON(datas);
				if (response.width2 > $(window).width()) {
					$('#samplePic').attr('data-zoom-image',response.link).attr('src', response.link).css('width', $(window).width() * 0.9);//.css('height', $(window).height() * 0.9);
				} else {
					$('#samplePic').attr('data-zoom-image',response.link).attr('src', response.link).css('width', response.width2);//.css('height', $(window).height() * 0.9);
				}
				$('.zoomWindow').css('background-image', "url(" + response.link + ")");

			}
		});
		/* if (rotate >= -360) {
			rotate -= 90;
		} else {
			rotate = 0;
		}
		$('#samplePic').rotate(rotate); */
	});
	/* $("#samplePic").elevateZoom({
		zoomWindowPosition: 12,
		constrainType:"height",
		constrainSize:274,
		zoomType: "lens", 
		containLensZoom: true, 
		gallery:'gallery_01', 
		cursor: 'pointer', 
		galleryActiveClass: "active"
	}); */ 
	$("#samplePic").elevateZoom({
		zoomWindowPosition: "container", 
		zoomWindowHeight: 250, 
		zoomWindowWidth:350, 
		borderSize: 0, 
		easing:false
	});
	$('body').mousemove(function(e){
		$('.zoomContainer').remove();
	});
	$('#print').click(function(){
		window.print();
		console.log(window);
	});
});
</script>
<style>
	@media print {
	    #sampleHead { display: none; }
	    #sampleDiv, #samplePic {width:100%;height:100%}
	}
</style>
</head>

<body style='background-color:silver;'>
<div id='sampleHead' class='sample'>
	<input type='button' class='mybtn' id='plus' value='Plus'/>
	<input type='button' class='mybtn' id='minus' value='Minus'/>
	<input type='button' class='mybtn' id='rL' value='Rotate Left'/>
	<input type='button' class='mybtn' id='rR' value='Rotate Right'/>
	<input type='button' class='mybtn' id='print' value='print'/>
</div>
	<div id='sampleDiv' class='sample'>
		<img src="${link}" id='samplePic' data-zoom-image="${link}"/>
	</div>
<div style="background-  color: white; position: fixed;
left: 0; top: 0; height: 250px; width: 350px; opacity: 0.4;" id="container"></div>	
<input type='hidden' id='fileName' name='fileName' value="${fileName}" />
<input type='hidden' id='qtype' name='qtype' value="${qtype}" />
<input type='hidden' id='year' name='year' value="${year}" />
</body>
</html>