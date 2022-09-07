<%@page import="com.j.member.db.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html><html><head><link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/subpage.css" rel="stylesheet" type="text/css">

<script type="text/javascript">
	function fnSubmit() {
		if (confirm("정말 수정하시겠습니까?")) {
			return true;
		}
		return false;
	}
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

<article>
<%
	//1.로그인여부체크 (로그인x->로그인페이지로이동)
request.setCharacterEncoding("UTF-8");
String id = (String) session.getAttribute("id");//object이기에 형변환필수

//2. 드라이브로드 & 디비연결 : 로그인한 사용자의 정보를 가져오기 => MemberUpdateAction.java에서 처리
MemberDTO mdto = (MemberDTO) request.getAttribute("mdto");

System.out.println("@@@@ 뷰 : 액션페이지에서 정보 전달받음" + mdto);
//3. 데이터처리 : 테이블형식으로 출력
//4. 비밀번호를 입력받은 뒤 수정하기 ->updatePro.jsp에서 진행
%>	
<h1>회원정보 수정하기</h1>
<form action="./MemberUpdatePro.me" method="post" name="fr"
	onsubmit="fnSubmit()">
	<input type="hidden" name="member_id" value="<%=mdto.getId()%>" />
	<table border ="1">
		<tr>
			<td>아이디</td>
			<td><input type="text" name="id" value="<%=mdto.getId()%>"
				readonly>아이디는 변경불가입니다</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="pw" placeholder="비밀번호를 입력하세요"
				required>  비밀번호 확인!
			</td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="name" value="<%=mdto.getName()%>">변경가능</td>
		</tr>
		<tr>
			<td>주민번호</td>
			<td><input type="text" name="pin1" value="<%=mdto.getPin1()%>">-
				<input type="text" name="pin2" value="<%=mdto.getPin1()%>"></td>
		</tr>

		<tr>
			<td>이메일</td>
			<td><input type="text" name="email" value="<%=mdto.getEmail()%>"></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><input type="text" name="tel" value="<%=mdto.getTel()%>"></td>
		</tr>
		<tr>
			<td>우편주소</td>
			<td><input type="text" name="zipcode"
				value="<%=mdto.getZipcode()%>"></td>
		</tr>
		<tr>
			<td>주소</td>
			<td><input type="text" name="address"
				value="<%=mdto.getAddress()%>"> <input type="text"
				name="address2" value="<%=mdto.getAddress2()%>">
			</td>
		</tr>
		<tr>
			<td>직업</td>
			<td><input type="text" name="job" value="<%=mdto.getJob()%>"></td>
		</tr>
		<tr>
			<td><input type="submit" class="btn" value="회원정보수정하기"></td>
		
<td><button class="btn" onclick="location.href='./main.ma'">뒤로가기</button></td></tr>
	</table>
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

