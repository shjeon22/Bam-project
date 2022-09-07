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

<!-- 게시판 -->
<article>
<h1>model2 게시판(MVC)</h1>
<%
    String pageNum = (String)request.getParameter("pageNum");
    System.out.println(" V : "+pageNum);
    int num = Integer.parseInt(request.getParameter("num"));
    System.out.println(" V : "+num);
%>

<form action="./BoardDeleteAction.bo?pageNum=<%=pageNum %>" method="post">
  	<input type="text" name="num" value="<%=num%>">

<table id="notice">
	<tr>
    	<th class="ttitle" colspan="5"> 게시판 글 삭제하기 </th>
	</tr>
    <tr>
		<td>비밀번호</td>
	    <td><input type="password" size="25" name="pass" placeholder="비밀번호를 입력하세요."></td>
    </tr>

</table>

<div id="table_search">
	<input type="submit" value="삭제하기" class="btn">
</div>

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