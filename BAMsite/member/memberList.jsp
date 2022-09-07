<%@page import="com.j.member.db.MemberDAO"%>
<%@page import="com.j.member.db.MemberDTO"%>

<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/subpage.css" rel="stylesheet" type="text/css">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function delSubmit() {
		if (confirm("정말 삭제 하시겠습니까?")) {
			return true;
		}
		return false;
	}  //밑에 form없는데 이함수어떻게 넣을까?,,,,,,,,,,,,, onsubmit?
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
	<h1>관리자 전용 회원 목록 관리 & 삭제</h1>


	<%
		// 로그인 체크 & 관리자 여부 체크
		String id = (String) session.getAttribute("id");

		if (id == null || !id.equals("admin")) {
			response.sendRedirect("./login.me");
		}

		// MemberDAO 객체 생성
		MemberDAO dao = new MemberDAO();

		// 회원 전체 명단을 가져오는 동작
		ArrayList memberlist = dao.memberList();

		// 회원정보를 모두 출력
	%>
	<article>
	<table border="1" >
		<tr>
			<td>아이디</td>
			<td>비밀번호</td>
			<td>이름</td>
			<td>주번</td>
			<td>이메일</td>
			<td>전화번호</td>
			<td>우편번호</td>
			<td>주소</td>
			<td>직업</td>
			<td>삭제</td>
		</tr>

		<%
			for (int i = 0; i < memberlist.size(); i++) {
				// 리스트 한칸의 데이터를 MemberDTO형태로 형변환
				MemberDTO mb = (MemberDTO) memberlist.get(i);

				// 관리자 정보만 출력 제외
				if (mb.getId().equals("admin")) {
					continue;
				}
		%>
		<tr>
			<td><%=mb.getId()%></td>
			<td><%=mb.getPw()%></td>
			<td><%=mb.getName()%></td>
			<td><%=mb.getPin1()%> - <%=mb.getPin2()%></td>
			<td><%=mb.getEmail()%></td>
			<td><%=mb.getTel()%></td>
			<td><%=mb.getZipcode() %></td>
			<td><%=mb.getAddress() %><%=mb.getAddress2() %></td>
			<td><%=mb.getJob() %></td>
				<td><a href="./MemberDeleteAction.me?id=<%=mb.getId()%>">삭제</a></td>
			
		</tr>
		<%
			}
		%>

	</table>

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