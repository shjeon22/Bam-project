package com.j.member.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.j.member.db.MemberDAO;

public class MemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println(" M : MemberListAction 호출 ");

		MemberDAO memberdao = new MemberDAO(); // DAO 쿼리문을 날릴 객체 생성
		ActionForward forward = new ActionForward(); // actionforward의 객체를 생성하면 안의 변수를 사용가능함.

		ArrayList<Object> memberlist = new ArrayList<>();
		memberlist = memberdao.memberList();

		request.setAttribute("memberlist", memberlist);

		forward.setRedirect(false);
		forward.setPath("./member/memberList.jsp");
		return forward;

	}
}

/*
 * // 사용자 정보 체크 로그인 여부 HttpSession session = request.getSession(); String id =
 * (String) session.getAttribute("id"); ActionForward forward = new
 * ActionForward(); System.out.println("####################id "+id);
 * if(id==null){
 * 
 * forward.setPath("./login.me"); forward.setRedirect(true); return forward; }
 * //session.setAttribute("id", id);
 * 
 * 
 * MemberDAO dao = new MemberDAO();
 * 
 * ArrayList<Object> memberlist = new ArrayList<>(); memberlist =
 * dao.memberList();
 * 
 * request.setAttribute("memberlist", memberlist);
 * 
 * 
 * forward.setPath("./member/memberList.jsp"); forward.setRedirect(false);
 * 
 * return forward; }
 */
