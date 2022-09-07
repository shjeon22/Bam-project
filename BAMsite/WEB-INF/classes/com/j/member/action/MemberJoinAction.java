package com.j.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.j.member.db.MemberDAO;
import com.j.member.db.MemberDTO;

public class MemberJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : MemberJoinAction-execute()호출 ");
		// 한글처리

		MemberDAO dao = new MemberDAO();// DAO에 쿼리문을 보내는 객체 생성
		MemberDTO dto = new MemberDTO();// DTO클래스에 데이터 삽입할 객체 생성
		ActionForward forward = new ActionForward();// ActionForward의 객체를 생성하면안의
													// 변수를 사용가능함.
		//boolean result = false;// dao메서드 호출결과를 저장할 변수
		// https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=jinhyuk2174&logNo=70189247365
		// 참조
	
			// 전달된 정보를 저장(MemberDTO)
			dto.setId(request.getParameter("id"));
			dto.setPw(request.getParameter("pw"));
			dto.setName(request.getParameter("name"));
			dto.setPin1(Integer.parseInt(request.getParameter("pin1")));
			dto.setPin2(Integer.parseInt(request.getParameter("pin2")));
			dto.setEmail(request.getParameter("email"));
			dto.setTel(request.getParameter("tel"));
			dto.setZipcode(Integer.parseInt(request.getParameter("zipcode")));
			dto.setAddress(request.getParameter("address"));
			dto.setAddress2(request.getParameter("address2"));
			dto.setJob(request.getParameter("job"));

			// 멤버 DAO의 Insert 함수에 dto의 값을 넣어라 (dto 클래스)
			/*result = */dao.MemberInsert(dto);
			
			
			//아이디 중복검사 ==씨발안eo~~~~~~~
			String id =request.getParameter("id");
			int result =dao.checkId(id);
			
			//아이디 중복검사 ==안돼~~~~~~~

			
			
			System.out.println("회원등록완료 (MemberjoinAction클래스)");
			forward.setPath("./login.me");//회원가입완료 => 로그인페이지로 이동
			forward.setRedirect(true);
			return forward;
		
		
	}

}
