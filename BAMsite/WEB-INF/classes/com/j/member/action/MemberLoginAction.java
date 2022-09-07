package com.j.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.j.member.db.MemberDAO;
import com.j.member.db.MemberDTO;
import com.mysql.cj.jdbc.SuspendableXAConnection;

public class MemberLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 세션값 제어
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		// String id =(String)session.setAttribute("ID", id);

		MemberDAO dao = new MemberDAO();// dao쿼리문 날릴 객체생성

		// String id = request.getParameter("id");
		// String pw = request.getParameter("pw");

		int result = dao.loginCheck(id, pw);// -1 비회원 0 비밀번호 오류 1 정상작동
		ActionForward forward = new ActionForward();
		if (result == 1) {
			System.out.println("MemberLoginAction :로그인 성공");
			session.setAttribute("id", id);
			if (id.equals("admin")) {
				forward.setRedirect(true);
				forward.setPath("./main.ma");
				System.out.println("11111111111111111111111111111111111");
				return forward; // 관리자일떄 이동하는 페이지 ex>관리자 목록페이지 me로 컨트롤러이동

			} else {// admin관리자가 아니고 다른 아이디로 접속시 이동해야되는 페이지

				forward.setRedirect(true);// 지금 jsp로 이동하기떄문에 false 지만 me 컨트롤러
											// 매핑하게되면
											// true로 관리자로 받아와서 페이지 이동
				forward.setPath("./main.ma");
				System.out.println("22222222222222222222222222222222222222222222222222222222");
				return forward;
			}
		} else if (result == -1) {

			System.out.println("MemberLoginAction :비회원입니다!");
			forward.setPath("./LoginError.me");
			forward.setRedirect(true);
			return forward;

		} else {
			System.out.println("MemberLoginAction : 비밀번호 오류!!");
			forward.setPath("/LoginError.me");
			forward.setRedirect(false);
			return forward;
		}

	}

}
