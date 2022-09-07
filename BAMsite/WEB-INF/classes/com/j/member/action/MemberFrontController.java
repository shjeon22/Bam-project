package com.j.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println(" MemberFrontController - doProcess() 호출");
		System.out.println(" GET/POST방식 모두 처리!! \n\n");

		//////////////////////// 1. 가상 주소
		//////////////////////// 계산/////////////////////////////////////
		System.out.println(" C : 1. 가상 주소 계산 시작");

		// 가상주소 가져오기
		String requestURI = request.getRequestURI();
		System.out.println(" C : requestURI - " + requestURI);
		// 프로젝트명 가져오기
		String ctxPath = request.getContextPath();
		System.out.println(" C : ctxPath - " + ctxPath);
		// 가상주소 계산 (가상주소 - 프로젝트명)
		String command = requestURI.substring(ctxPath.length());
		System.out.println(" C : command - " + command);

		System.out.println(" C : 1. 가상 주소 계산 끝\n");
		//////////////////////// 1. 가상 주소
		//////////////////////// 계산/////////////////////////////////////

		//////////////////////// 2. 가상 주소
		//////////////////////// 매핑/////////////////////////////////////
		System.out.println(" C : 2. 가상 주소 매핑 시작 ");
		Action action = null;
		ActionForward forward = null;
		// 로그인 화면
		if (command.equals("/login.me")) {
			System.out.println(" C : /login.me 호출 ");
			System.out.println(" C : DB사용X 정보입력페이지(view)");

			forward = new ActionForward();// 페이지 이동객체 생성
			forward.setPath("./member/login.jsp");
			forward.setRedirect(false);

		}
		// 회원가입 폼
		else if (command.equals("/join.me")) {
			System.out.println(" C : /join.me 호출 ");// 페이지 이동객체 생성
			System.out.println(" C : DB사용X 정보입력페이지(view)");

			forward = new ActionForward();
			forward.setPath("./member/join.jsp");
			forward.setRedirect(false);
		}
		// 로그인 중복체크 폼
		else if (command.equals("/idcheck.me")) {
			System.out.println("C :/idcheck.me 호출");
			System.out.println("C : DB 사용 o 정보 입력페이지 (view)");

			action =new MemberIdCheckAction();
			forward = new ActionForward();
			forward.setPath("./member/IdCheck.jsp");
			forward.setRedirect(false);
			/*
			 * try { forward = action.execute(request, response); } catch (Exception e) { //
			 * TODO Auto-generated catch block e.printStackTrace(); }
			 */
			
		}

		// 회원가입 버튼
		else if (command.equals("/MemberJoinAction.me")) {
			System.out.println(" C : /MemberJoinAction.me 호출 ");
			System.out.println(" C : DB사용ㅇ, 페이지 이동");
			action = new MemberJoinAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 로그인 클릭시
		else if (command.equals("/MemberLoginAction.me")) {
			System.out.println("C : /MemberLoginAction.me 호출");
			System.out.println("C : DB사용 O ,페이지이동");

			action = new MemberLoginAction();
			try {

				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 로그아웃 페이지 session.invalidate =>Logout.jsp=====================
		else if (command.equals("/Logout.me")) {
			System.out.println("/Logout.me 호출");
			System.out.println("c :DB 사용 X 정보 입력페이지 (view)");

			forward = new ActionForward();
			forward.setPath("./member/Logout.jsp");
			forward.setRedirect(false);
		}

		// 로그인 에러 시 가상페이지 (비번오류나 로그인 실패시 나오는 페이지 .. 화면에 로그인창이랑 동시출력?구현,,아직불가)
		else if (command.equals("/LoginError.me")) {
			System.out.println("C : /LoginError.me 호출");
			System.out.println("C : DB 사용 X 정보 입력페이지 (VIEW)");

			forward = new ActionForward();
			forward.setPath("./member/LoginError.jsp");
			forward.setRedirect(false);
		}
		// 로그인 성공후 관리자로 회원 목록 출력하는 페이지
		else if (command.equals("/MemberList.me")) {
			System.out.println("C : /MemberList.me 호출");
			System.out.println(" C : DB정보를 가져와서, 화면에 출력");

			action = new MemberListAction();

			try {

				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/MemberInfo.me")) {
			System.out.println("C : /MemberInfo.me 호출");
			System.out.println("C : DB정보가져와서 화면 출력");

			action = new MemberInfoAction();

			try {

				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 회원삭제
		else if (command.equals("/MemberDeleteAction.me")) {
			action = new MemberDeleteAction();
			try {

				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		// 회원정보 수정
		// 회원정보 수정의 경우 DB에서 정보를 불러오면서 또 DB에 다시 정보를 수정해야한다.
		// .DB에서 정보를 가지고와야함
		else if (command.equals("/MemberUpdate.me")) {
			System.out.println("C : /MemberUpdate.me 호출");
			System.out.println("C : DB정보가져와서 화면 출력");
			action = new MemberUpdateAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 비밀번호가 일치하면 회원정보를 수정
		else if (command.equals("/MemberUpdatePro.me")) {
			System.out.println("가상주소 MemberUpdatePro.me 홏ㄹ실제주소: ./member/memberUpdate.jsp");
			action = new MemberUpdateProAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		System.out.println(" C : 2. 가상 주소 매핑 끝\n ");
		//////////////////////// 2. 가상 주소
		//////////////////////// 매핑/////////////////////////////////////
		//////////////////////// 3. 페이지 이동/////////////////////////////////////
		System.out.println(" C : 3. 페이지 이동 시작");
		if (forward != null) { // 페이지 이동정보가 있을때

			if (forward.isRedirect()) { // true
				System.out.println(" C : redirect방식, " + forward.getPath() + "로 이동");
				response.sendRedirect(forward.getPath());

			} else { // false
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());

				System.out.println(" C : forward방식, " + forward.getPath() + "로 이동");
				dis.forward(request, response);
			}

		}
		System.out.println(" C : 3. 페이지 이동 끝\n ");
		//////////////////////// 3. 페이지 이동/////////////////////////////////////

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(" C : doGet() -> doProcess()");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(" C : doPost() -> doProcess()");
		doProcess(request, response);
	}

}