<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- 컴퓨터 어느곳에서도 해상도에 맞게 알아서 디자인이 변경되는 반응형 웹 메타태그 -->
<meta name="viewport" content="width-device-width", initial-scale="1">
<!-- css 폴더 안의 bootstrap.css 를 참조해서 이 홈페이지의 디자인을 사용하는 링크 -->
<link ref="stylesheet" href="css/bootstrap.css">

<title>JSP 게시판 웹 사이트</title>
</head>
<body>
	<%
		//메인 페이지로 이동했을 떄 세션에 값이 담겨있는지 체크
		String userID = null;
		if(session.getAttribute("useID") != null){
			userID = (String) session.getAttribute("userID");
		}
	%>
	<% System.out.println("11 : "+userID);%>
	<!-- 네이게이션 영역 -->
	<nav class = "navbar navbar-default">
		<!-- 헤더 영역(홈페이지 로고 등을 담당) -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" 
			data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
			
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>			
			</button>
			<!-- 상단 바에 제목이 나타나고 클릭하면 main 페이지로 이동한다 -->
			<a class="navbar-brand" href="main.jsp">JSP 게시판 웹사이트</a>
		</div>
		
		<!-- 게시판 제목 이름 옆에 나타나는 메뉴 영역 -->
		<div clas="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a href="main.jsp"> 메인 </a></li>
				<li><a href="bbs.jsp"> 게시판 </a></li>
			</ul>
			<%
				//로그인 하지 않았을 떄 보여지는 화면
				if(userID ==null){
					
			%>
			<% System.out.println("22 : "+userID);%>
			
			<!-- 헤더 우측에 나타나는 드랍다운 영역 -->
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class-"dropdown-toggle" data-toggle="dropdown" role="button" 
					aria-haspopup="true" aria-expander="false"> 접속하기 <span class="caret"></span></a>
					<!-- 드랍다운 아이템 영역 -->
					<ul class = "dropdown-menu">
						<li><a href="login.jsp">로그인</a></li>
						<li><a href = "join.jsp">회원가입</a></li>
					</ul>
				</li>
			</ul>
			<%
				//로그인이 되어있는 상태에서 보여주는 화면
				}else{
			%>
			<% System.out.println("33 : "+userID);%>
			<!-- 헤더 우측에 나타나는 드랍다운 영역 -->
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class-"dropdown-toggle" data-toggle="dropdown" role="button" 
					aria-haspopup="true" aria-expander="false"> 접속하기 <span class="caret"></span></a>
					<!-- 드랍다운 아이템 영역 -->
					<ul class = "dropdown-menu">
						<li><a href="logoutAction.jsp">로그아웃</a></li>
					</ul>
				</li>
			</ul>
			<%
				}
			%>
		</div>
	</nav>
	
	
	<!-- 부트스트랩 참조 영역 -->
	<script scr="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script scr="js/bootstrap.js"></script>
</body>
</html>