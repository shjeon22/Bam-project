
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="dao" class="com.j.member.db.MemberDAO" />
<%
	request.setCharacterEncoding("UTF-8");
String id = request.getParameter("id");//아이디 중복검사

boolean result = dao.Idcheck(id);
%>
<html>
<head>
<title>ID중복체크</title>
<link href="style.css" rel="stylesheet" type="text/css">

</head>
<body bgcolor="#FFFFCC">
	<br>
	<div align="center">
	<br>	<b><%=id%></b>
		<%
			if (result) {
			out.println("는 이미 존재하는 ID입니다.<p>");

		} else {
			out.println("는 사용 가능 합니다.<p>");

		}
		%>
		<a href="#" onClick="self.close()">닫기</a>
	</div>
</body>
</html>