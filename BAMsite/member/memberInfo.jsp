<%@page import="com.j.member.db.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>

<%
	MemberDTO mdto = (MemberDTO)request.getAttribute("mdto");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="./bootst/css/bootstrap.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>

<div class = "container">
<div align ="center">
<a href="./login.me">뒤로가기</a><br>
<h1>개인정보</h1>
<form action="./MemberInfo.me" method="post" name="fr"
	>
	<input type="hidden" name="member_id" value="<%=mdto.getId()%>" />
	<table border ="1">
		<tr>
			<td>아이디</td>
			<td><%=mdto.getId()%>
				</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><%=mdto.getPw() %> 비밀번호 확인!
			</td>
		</tr>
		<tr>
			<td>이름</td>
			<td><%=mdto.getName()%></td>
		</tr>
		<tr>
			<td>주민번호</td>
			<td><%=mdto.getPin1()%>
			+"-"+
				<%=mdto.getPin1()%></td>
		</tr>

		<tr>
			<td>이메일</td>
			<td><%=mdto.getEmail()%>"</td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><%=mdto.getTel()%></td>
		</tr>
		<tr>
			<td>우편주소</td>
			<td><%=mdto.getZipcode()%></td>
		</tr>
		<tr>
			<td>주소</td>
			<td><%=mdto.getAddress()%> <%=mdto.getAddress2()%>>
			</td>
		</tr>
		<tr>
			<td>직업</td>
			<td><%=mdto.getJob()%></td>
		</tr>
		<tr>
		
<td><button class="btn" onclick="location.href='./main.ma'">뒤로가기</button></td></tr>
	</table>
</div>
</div>


</body>
</html>

