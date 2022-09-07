<%@page import="com.j.member.db.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/subpage.css" rel="stylesheet" type="text/css">


<%
	String id = (String) session.getAttribute("id");
	System.out.println("$$$$$$$$$!!!!!!!!!!");
%>

<title>로그인</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="script.js">
	
</script>
</head>
<body bgcolor="white">

	<div id="wrap">
		<!-- 헤더들어가는 곳 -->
		<jsp:include page="../inc/top.jsp"></jsp:include>
		<!-- 헤더들어가는 곳 -->

		<!-- 본문들어가는 곳 -->
		<!-- 본문메인이미지 -->
		<div id="sub_img_member"></div>
		<!-- 본문메인이미지 -->
		<!-- 왼쪽메뉴 -->
		<nav id="sub_menu">
			<ul>
				<li><a href="./login.me">로그인</a></li>
				<li><a href="./join.me">회원가입</a></li>

			</ul>
		</nav>
		<!-- 왼쪽메뉴 -->
		<!-- 본문내용 -->
		<article>
			<%
				if (id != null) {
					System.out.println("www!!!!!!!!!!");
					if (id.equals("admin")) {
			%>
			<table border="1">
				<tr colspan="3">
					<th colspan="3"><b><mark><%=id%></mark></b>님 환영 합니다.</th>
				</tr>
				<tr>
					<td colspan="30">관리자 모드
						<p />
					</td>
				</tr>
				<td><input type="button" value="로그아웃"
					onclick=" location.href='./Logout.me'; "></td>
				<td><input type="button" value="회원목록관리"
					onclick=" location.href='./MemberList.me'; "></td>
				<td><input type="button" value="계정정보"
					onclick=" location.href='./MemberInfo.me'; "></td>
				<td><input type="button" value="회원정보수정" class="btn"
					onclick="location.href='./MemberUpdate.me'"></td>
				</tr>

			</table>
			<%
				} else if (id != "admin") {
			%>
			<table border="1">
				<tr>
					<td><b><mark><%=id%></mark></b> 일반회원님 환영 합니다.</td>

				</tr>
				<tr>
					<td><input type="button" value="로그아웃"
						onclick=" location.href='./Logout.me'; "> <input
						type="button" value="정보수정"
						onclick=" location.href='./MemberUpdate.me'; "></td>

				</tr>
			</table>
			<%
				}
				} else {
			%>

			<form action="./MemberLoginAction.me" id="join" name="logmove">
				<table style="">
					<tr>
						<td align="center" colspan="2"><h4>로그인</h4></td>
					</tr>
					<tr>
						<td>아이디</td>
						<td><input type="text" name="id"></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="password" name="pw"></td>
					</tr>
					<tr>
					</tr>
				</table>
				<div class="clear"></div>
				<div id="buttons">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button id="button" value="로그인" onclick="loginCheck()">로그인</button>
					&nbsp;&nbsp;<input type="button" value="회원가입"
						onclick=" location.href='./join.me'; ">
				</div>

			</form>

			<%
				}
			%>

		</article>
		<div class="clear"></div>
		<!-- 푸터들어가는 곳 -->
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
		<!-- 푸터들어가는 곳 -->
	</div>
</body>
</html>