package com.j.member.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.j.member.db.MemberDAO;
import com.j.member.db.MemberDTO;

public class MemberInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println(" M : MemberInfoAction 호출 ");

	
		
		MemberDAO mdao = new MemberDAO(); // DAO 쿼리문을 날릴 객체 생성
		MemberDTO mdto = new MemberDTO(); // DTO 클래스에 데이터를 삽입할 객체 생성
		ActionForward forward = new ActionForward(); // actionforward의 객체를 생성하면 안의 변수를 사용가능함.
		
		try {
		mdto.setId(request.getParameter("id"));
		
		MemberDTO member = mdao.memberInfo(mdto);
		
		request.setAttribute("member", member);
		
		forward.setRedirect(false);
		forward.setPath("./member/memberInfo.jsp");
   		return forward;
		}catch(Exception e) {
			System.out.println("INFO 액션 : 회원정보 가져오기 실패");
			e.printStackTrace();
		}
		return forward;
	}
	
}
