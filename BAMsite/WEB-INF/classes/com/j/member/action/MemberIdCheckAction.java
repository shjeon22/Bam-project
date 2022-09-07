package com.j.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.j.member.db.MemberDAO;
import com.j.member.db.MemberDTO;

public class MemberIdCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : MemberIdCheckAction-execute()호출 ");

		// 사용자정보 체크(로그인여부) 이뷰뷴운 필요없울둣
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		// MemberDAO객체생성
		MemberDAO mdao = new MemberDAO();
		ActionForward forward = new ActionForward();
		
	
		
		
	

///////////////////////////////////////////////////////////////////
// 처리결과에 따른 페이지 이동(js)
// => Action 페이지에서 js 이동시에는 컨트롤러를 통한 페이지 이동 X

// 처리 응답 결과는 html형태로 보여주겠다. (MIME타입)
		response.setContentType("text/html; charset=UTF-8");
// 응답결과를 처리하는 연결통로를 지정(데이터 보낼 준비)
		PrintWriter out = response.getWriter();
		
		
		int result = mdao.checkId(id);
		System.out.println("아이디 중복결과 0:중복 1 생성가능>" + result);
		if (result == 0) { // 아이디 중복 0값
			// 자바스크립트사용하여 alert()창띄우기

			out.print("<script>");
			out.print("alert('아이디 사용 불가합니다.');");
			out.print("history.back();");
			out.print("</script>");
			System.out.println("@@@@ 모델 : 자바스크립트 페이지이동");
			// 자원해제
			out.close();// 응답처리 연결통로를 제거 (자원해제)
			return null; // null이란 Controller에서 페이지 이동하지않겠다는 의미
		} else {
			// 정상 로그인처리
			// result == 1 //정상 로그인처리
			// 자바스크립트사용하여 alert()창띄우기
			
			out.print("<script>");
			out.print("alert('아이디 사용가능하십니다.');");
			out.print("location.href='./join.me'");
			out.print("</script>");
			System.out.println(" @@@모델 : 자바스크립트 페이지이동");
			// 자원해제
			out.close();

			return null;

		}

	}

}
