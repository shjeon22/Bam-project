package com.j.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.j.member.db.MemberDAO;
import com.mysql.cj.jdbc.SuspendableXAConnection;

public class MemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session =request.getSession();
		
		System.out.println(" M : MemberDeleteAction_execute() 호출 ");
		// 한글 처리 생략
		MemberDAO dao = new MemberDAO();
		
		ActionForward forward = new ActionForward();
		boolean result = false;

		try {
			String id =request.getParameter("id");
			result = dao.memberDelete(id);

			if (result == false) {
				System.out.println("회원삭제 실패");
				forward.setPath("./MemberList.me");
				forward.setRedirect(true);
				return forward;
			}else{
				
			System.out.println("회원삭제");
			forward.setPath("./MemberList.me");
			forward.setRedirect(true);
			}

		} catch (Exception e) {
		System.out.println("삭제 실패");
		forward.setPath("./MemberList.me");
		forward.setRedirect(true);
			e.printStackTrace();
		}

		return forward;
	}

}
