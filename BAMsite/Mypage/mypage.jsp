<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/front.css" rel="stylesheet" type="text/css">

<%
	String id = (String) session.getAttribute("id");
System.out.println("$$$$$$$$$!!!!!!!!!!");
%>

</head>
<body>
	<div id="wrap">

		<!-- 헤더파일들어가는 곳 -->
		<jsp:include page="../inc/top.jsp"></jsp:include>
		<!-- 헤더파일들어가는 곳 -->

		<!-- 메인이미지 들어가는곳 -->
		<div class="clear"></div>
		<div id="main_img">
			<img src="./images/MAIN.jpg.jpg" width="971" height="282">
		</div>
		<!-- 메인이미지 들어가는곳 -->
		<!-- 메인 콘텐츠 들어가는 곳 -->
		<article id="front">
			<div id="solution">
				<div id="hosting">
					<h3>문의사항</h3>
					<table><tr>
					<td><h3>051-504-8282</h3></td> 
					 </tr></table>
				</div>
				<div id="security">
					<h3>오늘의 전시</h3>
					<table>
					
					<tr><td><h3>10:20 - 이중섭  모음집</h3></td> 
					<tr><td><h3>16:40 - 하늘과 별 그리고 바다</h3></td> 
					 </tr></table>
				</div>
				<div id="관람시간">
					<h3>관람시간</h3>
					<table>
					<tr><td><h3>평일 :10:00 ~ 23:00시</td></h3>
					<td><h3>/ 주말 :10:00 ~ 18:00시</h3></td> 
					 </tr></table>
				</div>
			</div>
			<div class="clear"></div>
			<div id="sec_news">
				<h3>
					<span class="orange"> 오늘의 소식</span> 
				</h3>
				<dl>
					<dt><a href="http://localhost:8088/Jsite/BoardContent.bo?num=6&pageNum=1">나는 미술간에 00간다</a></dt>
					<dd>오늘의 조형은 미술관에서 벌어지는 아무개 </dd>
				</dl>
				<dl>
					<dt><a href="http://localhost:8088/Jsite/BoardContent.bo?num=7&pageNum=1">어린이 갤러리</a></dt>
					<dd>미술관 1층에 어린이 갤러리를 오픈하였습니다 많은 이용부탁드립니다.</dd>
				</dl>
			</div>
			
				<%
				if (id != null) {
				System.out.println("www!!!!!!!!!!");
				if (id.equals("admin")) {
			%>
			<div id="news_notice">
				<h3 class="brown">로그인 정보</h3>
			<table border="1">
			
				<tr colspan="3">
					<th colspan="3"><h2><b><mark><%=id%></mark></b>님 환영 합니다.</h2></th>
				</tr>
				<tr>
					<td colspan="30"><h1>관리자 모드</h1>
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
			<div id="news_notice">
				<h3 class="brown">마이 페이지</h3>
			<table border="1">
				<tr>
					<td><b><mark><%=id%></mark></b>
					일반회원님 환영 합니다.</td>
				
				</tr>
				<tr>
					<td><input type="button" value="로그아웃"
						onclick=" location.href='./Logout.me'; ">
					<input type="button" value="정보수정"
						onclick=" location.href='./MemberUpdate.me'; "></td>

				</tr>
			</table>
			<%
				}
			} else {
			%><article></article><div id="news_notice">
<h3 class="brown">뉴스 &amp; 알림</h3>
<table>
	<span class="orange"> 화재 뉴스</span> 
				</h3>
				<dl>
					<dt><a href="http://localhost:8088/Jsite/BoardContent.bo?num=6&pageNum=1">주차장</a></dt>
					<dd>미술관에 불이 났습니다. </dd>
				</dl>
				<dl>
					<dt><a href="http://localhost:8088/Jsite/BoardContent.bo?num=7&pageNum=1">어린이 갤러리</a></dt>
					<dd>어린이가 없습니다.</dd>
				</dl>
</table>
</div><%
				}
			%>
			
		</article>
		<!-- 메인 콘텐츠 들어가는 곳 -->
		<div class="clear"></div>

		<!-- 푸터 들어가는 곳 -->
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
		<!-- 푸터 들어가는 곳 -->

	</div>
</body>
</html>