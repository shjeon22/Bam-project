<%@page import="com.j.member.db.MemberDTO"%>
<%@page import="com.j.member.db.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>   
     <!--JSTL을 사용해서 관리자 아이디 값 불러오기  -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/subpage.css" rel="stylesheet" type="text/css">

</head>
<body>
	
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

</nav>
<!-- 왼쪽메뉴 -->
<!-- 본문내용 -->
<article>
<% MemberDTO dto = new MemberDTO();%>
<h1>관리자용 메인 페이지</h1>
<form action="./MemberInfo.me" id="join" name="logmove" >

<fieldset>
<legend>Administrator</legend>
<h3>${dto.id}로 로그인 하셧습니다.</h3>
<%-- <label>아이디</label>
<input type="text" name="id" value="${dto.id }"><br>
<label>비밀번호</label>                                     //로그인 시 개별 값 출력할떄 이용하는 페이지에 작성
<input type="password" name="pw" value="${dto.pw }"><br> --%>
</fieldset>
<div class="clear"></div>
<div id="buttons">
<button id="button" value=" 회원목록보기" class="submit " onclick="location.href='./MemberInfo.me';">버튼1 안되?</button>
           
<input type="button" value="회원정보" class="cancel"onclick=" location.href='./MemberList.me'; ">
</div>
</form>
</article>
<!-- 본문내용 -->
<!-- 본문들어가는 곳 -->

<div class="clear"></div>
<!-- 푸터들어가는 곳 -->
<jsp:include page="../inc/bottom.jsp"></jsp:include>
<!-- 푸터들어가는 곳 -->
</div>
</body>
</html>