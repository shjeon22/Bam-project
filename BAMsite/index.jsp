<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
	//index.jsp => 무조건 실행은 여기서@@@@@@@@@@@@@
	//프로젝트의 시작지점
	// *MVC 프로젝트에서 실행가능한 유일한 JSP 페이지!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	// ( index.jsp 페이지 내의 주소줄에 .jsp 주소가 s노출된 경우 잘못된 구조)

	//	response.sendRedirect("main/main.jsp");
	//	response.sendRedirect("./itwill.bo"); //BoardFrontController.java에서 가상주소 requestURI 를 가져와서 출력
	/*  BoardFrontController - doGET() 호출
	 	BoardFrontController - doProcess() 호출
		GET/POST방식 모두 처리!! 
		C: requestURI - /jspMVC/itwill.bo  가 출력됨*/

	response.sendRedirect("./main.ma");
	//response.sendRedirect("./login.me");//로그인
	//response.sendRedirect("./join.me");//회원가입
	// response.sendRedirect("./BoardWrite.bo");//글쓰기
	// response.sendRedirect("./BoardList.bo");//게시판리스트
%>