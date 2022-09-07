<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% //화면상단에 위치하는 메뉴바 처리페이지
String id = (String)session.getAttribute("id"); //다운캐스팅 Object -> String
%>
<header>
<div id="login">
	<%if(id == null){
		%>
		<div id="login"><a href="./login.me">로그인</a> | <a href="./join.me">회원가입</a></div>
		<%
	}else if(id != null){
		%>
		<div id="login"><%=id %>님 환영합니다 | <a href="./Logout.me">로그아웃</a></div>
	<%
	}
	%>
</div>
	<div class="clear"></div>
	<!-- 로고들어가는 곳 -->
<nav id="top_menu">
<ul>
	<li><a href="./main.ma">메인홈</a></li>
	<li><a href="./BoardList.bo">게시판</a></li>
	<li><a href="./map.mp">찾아오시는 길</a></li>
	<li><a href="./main.ma">마이페이지</a></li>
</ul>
</nav>
	<div id="logo">
		<img src="./images/LOGO.jpg" width="250" height="80" alt="Fun Web">
	</div>
	<!-- 로고들어가는 곳 -->
	
	
	<table id="T_contnet" width="58" height="50" border="0" cellpadding="0" cellspacing="0">	
	<tr>
		<td width="300" height="158">
			<!-- login --><jsp:include page="./login.me" flush="false" /></td>
			
	
	</tr>
	</table>
	
</header>    