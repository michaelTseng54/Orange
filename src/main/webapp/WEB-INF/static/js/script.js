$(function(){
	$('.settingDiv').css('width', $(window).width() - 80);
	$('#login').shadow();
	$('#btn').click(function(){
		if($.trim($('#userId').val()).length == 0 || $.trim($('#password').val()).length == 0) {
			$('#hint').empty();
			$('#hint').html("<hr/><span style='padding-left:20px; color:red;'>請確定您有輸入完整的帳號密碼資料。</span><hr/>");
			return false;
		}
	});
	$('#ycmJMGProgram').change(function(){
		  $.ajax({
		      url: 'TEST2',
		      method: 'POST',
		      data: "code=" + $('#ycmJMGProgram').val()
		  });
	});
	$('#testBtn').click(function(){
		  $.ajax({
		      url: 'FUNC007/query',
		      method: 'POST'
		  });
	});
});
