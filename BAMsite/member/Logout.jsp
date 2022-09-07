<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	session.invalidate();//세션의 모든 속성 제거
%>
 <script>
	alert("로그아웃 되었습니다.");
	location.href="./main.ma";
</script> 