package com.j.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.j.member.db.MemberDAO;
import com.j.member.db.MemberDTO;

public class MemberUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M :MemberUpdateProAction_execute() 실행됨");
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		String id = (String) request.getAttribute("id");

		ActionForward forward = new ActionForward();
		/*
		 * if (id == null) { forward.setPath("./main.ma"); forward.setRedirect(true);
		 * System.out.
		 * println("아이디 NULL 값!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"); return
		 * forward;
		 * 
		 * }
		 */
		// 전달되는정보를 모두저장
		MemberDTO mdto = new MemberDTO();
		mdto.setId(request.getParameter("id"));
		mdto.setPw(request.getParameter("pw"));
		mdto.setName(request.getParameter("name"));
		mdto.setPin1(Integer.parseInt(request.getParameter("pin1")));
		mdto.setPin2(Integer.parseInt(request.getParameter("pin2")));
		mdto.setEmail(request.getParameter("email"));
		mdto.setTel(request.getParameter("tel"));
		mdto.setZipcode(Integer.parseInt(request.getParameter("zipcode")));
		mdto.setAddress(request.getParameter("address"));
		mdto.setAddress2(request.getParameter("address2"));
		mdto.setJob(request.getParameter("job"));

		System.out.println("Action업데이트 정보" + mdto);

		// .MemberDAO객체생성 =>updateMember()생성
		MemberDAO mdao = new MemberDAO();
		int result = mdao.updateMember(mdto);
		System.out.println("업데이트프로액션 결과" + result);
///////////////////////////////////////////////////////////////////
		// 처리결과에 따른 페이지 이동(js)
		// => Action 페이지에서 js 이동시에는 컨트롤러를 통한 페이지 이동 X

		// 처리 응답 결과는 html형태로 보여주겠다. (MIME타입)
		response.setContentType("text/html; charset=UTF-8");
		// 응답결과를 처리하는 연결통로를 지정(데이터 보낼 준비)
		PrintWriter out = response.getWriter();

		if (result == 0) { // 로그인 비번오류
			// 자바스크립트사용하여 alert()창띄우기

			out.print("<script>");
			out.print("alert('비밀번호오류');");
			out.print("history.back();");
			out.print("</script>");
			System.out.println("@@@@ 모델 : 자바스크립트 페이지이동");
			// 자원해제
			out.close();// 응답처리 연결통로를 제거 (자원해제)
			return null; // null이란 Controller에서 페이지 이동하지않겠다는 의미
		} 
		else if (result == -1) {
			// 자바스크립트사용하여 alert()창띄우기

			out.print("<script>");
			out.print("alert('존재하지 않는 아이디입니다');");
			out.print("history.back();");
			out.print("</script>");
			System.out.println("@@@@ 모델 : 자바스크립트 페이지이동");
			// 자원해제
			out.close();
			return null; // null이란 Controller에서 페이지 이동하지않겠다는 의미
		}
		// 정상 로그인처리
		// result == 1 //정상 로그인처리
		// 자바스크립트사용하여 alert()창띄우기
		
		out.print("<script>");
		out.print("alert('회원정보 수정이 정상 처리되었습니다');");
		out.print("location.href='./main.ma'");
		out.print("</script>");
		System.out.println(" @@@모델 : 자바스크립트 페이지이동");
		// 자원해제
		out.close();

		return null;

	}

}
