<%@page import="com.j.board.db.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>

    <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/subpage.css" rel="stylesheet" type="text/css">

 
 <script src="./script/jquery-3.6.0.js"></script> 
 <script type="text/javascript">
     $(document).ready(function(){
    	// alert('jQuery 준비 완료~'); 
    	 
    	 // 수정버튼 클릭시 수정페이지로 이동(정보 포함)
    	 $('#upBtn').click(function(){
    		//  alert("수정버튼 클릭!");
    		  // 폼태그의 action페이지를 설정
    		  $('#fr').attr("action","./BoardUpdate.bo");
    		  
    		  // 폼 태그, 전달방식을 변경
    		  $('#fr').attr("method","get");
    		  
    		  // 글번호를 가지고 수정페이지로 이동(submit)
    		  $('#fr').submit();    		  
    	 }); // 수정버튼
    	 
    	 // 삭제버튼
    	 $('#delBtn').click(function(){
    		 // 폼태그 action페이지 정보 수정
    		 $('#fr').attr("action","./BoardDelete.bo");
    		 // 폼태그 method변경
    		 $('#fr').attr("method","get");
    		 // 폼태그 submit
    		 $('#fr').submit();    		 
    	 });
    	 // 삭제버튼
    	 
     }); // jquery
 </script>
 
 
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
<h1>자유 게시판</h1>
 
 <%
		//request 영역에 글정보를 저장
		//request.setAttribute("dto", dto);
 		BoardDTO dto = (BoardDTO) request.getAttribute("dto");
 		
 		String pageNum = (String)request.getAttribute("pageNum");
 
 %>
 <!-- jQuery를 사용하여 submit동작 실행 -->
 
 <!-- form태그 안에 submit 버튼이 있어야 동작한다. -->
 <form method="post" id="fr">
    <input type="hidden" name="num" value="${dto.num }">
    <input type="hidden" name="pageNum" value="${pageNum }">
 </form>
 
 
<table id="notice">
	<tr>
    	<th class="ttitle" colspan="5"> 게시판 본문(내용) </th>
	</tr>
	<tr>
		<td>글번호</td>
		<td><%=dto.getNum() %></td>
		<td>조회수</td>
		<td><%=dto.getReadcount() %></td>
    </tr>
    <tr>
        <td>글쓴이</td>
        <td>${dto.name}</td>
        <td>작성일</td>
        <td> 
          <fmt:formatDate value="${dto.date }" pattern="yy-MM-dd"/>
        </td>
    </tr>
    <tr>
		<td>제목</td>
	    <td colspan="4">${dto.subject }</td>
    </tr>
    <tr>
		<td>첨부파일</td>
	    <td colspan="4">
	    	<a href="./upload/${dto.file }">${dto.file }</a> 
	    	<hr>
	    	<a href="./center/fileDown.jsp?fileName=${dto.file }">${dto.file }</a>
	    </td>
    </tr>
    <tr>
		<td>내용</td>
	    <td colspan="4">${dto.content }</td>
    </tr>

</table>

<div id="table_search">
	<!-- 글번호(num),페이지넘버(pageNum) -->
	<input type="submit" value="수정" class="btn" id="upBtn">
	<input type="submit" value="삭제" class="btn" id="delBtn">
	<input type="button" value="답글" class="btn" id="reBtn" 
	     onclick="location.href='./BoardReWrite.bo?num=<%=dto.getNum() %>&re_ref=<%=dto.getRe_ref() %>&re_lev=<%=dto.getRe_lev() %>&re_seq=<%=dto.getRe_seq() %>';"
	>
	<input type="button" value="목록" class="btn" 
		   onclick="location.href='./BoardList.bo?pageNum=<%=pageNum %>';" >
</div>

 
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