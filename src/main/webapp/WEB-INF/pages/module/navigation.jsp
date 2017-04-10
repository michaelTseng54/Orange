<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar">aaa</span><span class="icon-bar">bbb</span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<c:url value="/Main" />">臺灣師範大學</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="<c:url value="/FUNC001" />">Function001</a></li>
				<li><a href="<c:url value="/FUNC002" />">Function002</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">Function003 <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="<c:url value="/FUNC003/Action" />">Action</a></li>
						<li><a href="<c:url value="/FUNC003/AnotherAction" />">Another
								action</a></li>
						<li><a href="<c:url value="/FUNC003/SomethingHere" />">Something
								else here</a></li>
						<li class="divider"></li>
						<li><a href="<c:url value="/FUNC003/SeparatedLink" />">Separated
								link</a></li>
						<li class="divider"></li>
						<li><a href="<c:url value="/FUNC003/OneMore" />">One more
								separated link</a></li>
					</ul></li>
					<li class="menu-item dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Function004<b class="caret"></b></a>
                        <ul class="dropdown-menu">
							<li><a href="<c:url value="/FUNC004/A1" />">Func4-st1A1</a></li>                            
							<li class="divider"></li>
                            <li class="menu-item dropdown dropdown-submenu">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Func4-st1B</a>
                              <ul class="dropdown-menu" style="left: 158px;">
                                <li class="menu-item "><a href="<c:url value="/FUNC004/B1" />">Func4-st2B1</a></li>
                                <li class="menu-item "><a href="<c:url value="/FUNC004/B2" />">Func4-st2B2</a></li>
                                <li class="menu-item dropdown dropdown-submenu">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">ToSt3</a>
                                  <ul class="dropdown-menu" style="left: 156px;">
	                                  <li><a href="<c:url value="/FUNC004/C1" />">Func4-st3C1</a></li>
	                                  <li><a href="<c:url value="/FUNC004/C2" />">Func4-st3C2</a></li>
                                  </ul>
                                </li>
                              </ul>
                            </li>
						<li class="divider"></li>
						<li><a href="<c:url value="/FUNC004/A2" />">Func4-st1A2</a></li>
                        </ul>
					</li>
				<li><a href="<c:url value="/FUNC005" />">查詢資料</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">院所代碼<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="<c:url value="/FUNC006/FACULTY" />">學院代碼</a></li>
						<li class="divider"></li>
						<li><a href="<c:url value="/FUNC006/DEPT" />">系所代碼</a></li>
					</ul></li>
				<c:choose>
					<c:when test="${manager == 'true'}">
					</c:when>
				</c:choose>
						<li><a href="<c:url value="/FUNC007" />">使用者管理</a></li>
			</ul>
			<!-- 
			<form class="navbar-form navbar-left" role="search">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Search">
				</div>
				<button type="submit" class="btn btn-default">查詢</button>
			</form>
			 -->
			<p class="navbar-text" style='float: right;'>
				<a href="<c:url value="/logout" />" class="navbar-link">登出</a>
			</p>
			<p class="navbar-text" style='float: right;'>
				<img src="<c:url value="/static/imgs/user_icon.png" />" /> Hello, <a
					href="<c:url value="/USER/${userId}" />" class="navbar-link">${userId}</a>. Welcome back!
			</p>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>