package com.j.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.j.member.db.MemberDAO;
import com.j.member.db.MemberDTO;

public class MemberUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Action: MemberUpdateAction_ execute() 호출");

		request.setCharacterEncoding("UTF-8");

		// 세션값제어 ID 로그인 유무 확인
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		ActionForward forward = new ActionForward();
		if (id == null) {
			// model2방식이므로 req.sendRedirect()가 아닌 ActionForward방식으로 이동
			forward.setPath("./main.ma");
			forward.setRedirect(true);
			return forward;
		}
		// MemberDAO객체생성
		MemberDAO mdao = new MemberDAO();

		// 회원정보가져오는 메서드 getMember()이미있으므로 생성 아닌 호출
		MemberDTO mdto = mdao.getMember(id);

		// 회원정보를 request영역에 저장(request안되면 session이나 application에 담아야함)
		request.setAttribute("mdto", mdto);// key값과 value 명은 같이하면 안헷갈리게 적기가능

		// 페이지이동(./member/updateForm.jsp) //.은 webcontent라고 생각할것
		forward.setPath("./member/memberUpdate.jsp");
		forward.setRedirect(false);// 주소는 그대로인데 화면은 jsp이여야함 forward 방식
		System.out.println("MODEL : DB에서 회원정보 가져와서 저장 후 memberUpdate.jsp페이지이동");
		return forward;

	}

}
