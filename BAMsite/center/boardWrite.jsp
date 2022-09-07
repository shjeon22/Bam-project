<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
	<jsp:include page="../inc/top.jsp"/>	
<!-- 헤더들어가는 곳 -->

<!-- 본문들어가는 곳 -->
<!-- 메인이미지 -->
<div id="sub_img_center"></div>
<!-- 메인이미지 -->

<!-- 왼쪽메뉴 -->
  <jsp:include page="../inc/left.jsp"></jsp:include>
<!-- 왼쪽메뉴 -->
<% //화면상단에 위치하는 메뉴바 처리페이지
String id = (String)session.getAttribute("id"); //다운캐스팅 Object -> String
%>
<!-- 게시판 -->
	<%if(id == null){
		%>
		<script>
			alert("회원만 이용 가능한 게시판입니다. 로그인 해 주세요.");
			location.href="./login.me";
		</script>
<!-- 	<a href="./login.me"><h1>로그인 하러가기!!Cleck</h1></a> -->
		<%
	}else if(id != null){
		%>
	
<article>
<h1>자유 게시판</h1>
<!-- action=""  이동할 페이지정보가 없을경우
    자기자신의 페이지로 이동 
-->

<form action="./BoardWriteAction.bo" method="post">
<table id="notice">
	<tr>
    	<th class="ttitle" colspan="5"> 게시판 글쓰기 </th>
	</tr>
	<tr>
		<td>글쓴이</td>
	    <td><input type="text" size="25" name="name"></td>
    </tr>
    <tr>
		<td>비밀번호</td>
	    <td><input type="password" size="25" name="pass"></td>
    </tr>
    <tr>
		<td>제목</td>
	    <td><input type="text" size="25" name="subject"></td>
    </tr>
    <tr>
		<td>내용</td>
	    <td><textarea rows="15" cols="27" name="content"></textarea> </td>
    </tr>

</table>

<div id="table_search">
	<input type="submit" value="글쓰기" class="btn">
</div>
	
	<%
	}
	%>
</form>
<div class="clear"></div>
</article>
<!-- 게시판 -->
<!-- 본문들어가는 곳 -->
<div class="clear"></div>
<!-- 푸터들어가는 곳 -->
	<jsp:include page="../inc/bottom.jsp"/>
<!-- 푸터들어가는 곳 -->
</div>
</body>
</html>