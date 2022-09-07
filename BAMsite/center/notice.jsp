<%@page import="com.j.board.db.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    

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

<%
		//request 영역에 글정보(list) 저장
		//request.setAttribute("boardList", boardList);
		//List boardList = (List)request.getAttribute("boardList");
		//System.out.println(" V : "+boardList);
		
		// request 영역에 페이징처리 정보를 저장
		// 		request.setAttribute("pageNum", pageNum);
		// 		request.setAttribute("result", result);
		// 		request.setAttribute("pageCount", pageCount);
		// 		request.setAttribute("pageBlock", pageBlock);
		// 		request.setAttribute("startPage", startPage);
		// 		request.setAttribute("endPage", endPage);
		String pageNum = (String)request.getAttribute("pageNum");
		int result = (int)request.getAttribute("result"); // 글개수
		int pageCount = (int)request.getAttribute("pageCount");
		int pageBlock = (int)request.getAttribute("pageBlock");
		int startPage = (int)request.getAttribute("startPage");
		int endPage = (int) request.getAttribute("endPage");
		
		
%>
<!-- 게시판 -->
<article>
<h1>자유게시판</h1>
<table id="notice">
<tr><th class="tno">순번</th>
    <th class="ttitle">제목</th>
    <th class="twrite">작성자</th>
    <th class="tdate">날짜</th>
    <th class="tread">조회수</th></tr>
    
	
	<c:forEach var="dto"  items="${boardList }">
		<tr>
		   <td>${dto.num }</td>
		   <td class="left">
		       
		       <c:if test="${dto.re_lev > 0 }">
		        	<img src="./images/center/level.gif" width="${dto.re_lev * 10 }">
		        	<img src="./images/center/re.gif">
		       </c:if> 
		       
		   		<a href="./BoardContent.bo?num=${dto.num }&pageNum=<%=pageNum%>">
		   			${dto.subject }
		   		</a>
		   		
		   		<c:if test="${dto.file != null }">
		   			<img src="./images/center/save.png" width="20" height="20">
		   		</c:if>
		   </td>
		   <td>${dto.name }</td>
		   <td>${dto.date }</td>
		   <td>${dto.readcount }</td>
		</tr>	
	</c:forEach>
	
	
</table>


<div id="table_search">
   <!--  검색창 동작 -->
<!--    	String pageNum = (String)request.getAttribute("pageNum"); -->
<!-- 		int result = (int)request.getAttribute("result"); // 글개수 -->
<!-- 		int pageCount = (int)request.getAttribute("pageCount"); -->
<!-- 		int pageBlock = (int)request.getAttribute("pageBlock"); -->
<!-- 		int startPage = (int)request.getAttribute("startPage"); -->
<!-- 		int endPage = (int) request.getAttribute("endPage"); -->
   
    <form action="./BoardSearch.bo" method="get">
        <input type="hidden" name="result" value="<%=result%>">
        <input type="hidden" name="pageCount" value="<%=pageCount%>">
        <input type="hidden" name="pageBlock" value="<%=pageBlock%>">
        <input type="hidden" name="startPage" value="<%=startPage%>">
        <input type="hidden" name="endPage" value="<%=endPage%>">
        
		<input type="text" name="search" class="input_box">
		<input type="submit" value="search" class="btn">
	</form>
   <!--  검색창 동작 -->
	<hr>
	<input type="button" value="글쓰기" id="btn" onclick=" location.href='./BoardWrite.bo'; ">
</div>

<div class="clear"></div>
	
	<div id="page_control">
    	<%
    	if(result != 0){
    		
		// 이전
   		if(startPage > pageBlock){
   		%>
   		   <a href="./BoardList.bo?pageNum=<%=startPage-pageBlock%>">[이전]</a>
  		<%
    	}
    	
   		//  1 2 3 4 ... 10   11 12 13.... 20 
    	for(int i=startPage;i<=endPage;i++){
    		   %>
    		       <a href="./BoardList.bo?pageNum=<%=i%>">[<%=i %>]</a>      		   
    		   <% 		
    	}
   		
    	// 다음
    	if(endPage < pageCount){
    		%>
    		    <a href="./BoardList.bo?pageNum=<%=startPage+pageBlock%>">[다음]</a>
    		<%
    	}
    	
    	}
   		
    	%>
	</div>
	
	
	
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