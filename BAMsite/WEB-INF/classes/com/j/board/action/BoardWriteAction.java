package com.j.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.j.board.db.BoardDAO;
import com.j.board.db.BoardDTO;

public class BoardWriteAction implements Action{

//	public void method(){
//		request.setCharacterEncoding("UTF-8");
//	}
	// 게시판 글쓰기 동작 처리
	
	// alt shift s + v
	@Override
	public ActionForward execute(HttpServletRequest request,
			       HttpServletResponse response) throws Exception {
		
		System.out.println(" M : BoardWriteAction-execute()호출 ");

		// 한글처리
		request.setCharacterEncoding("UTF-8");
		
		// 전달된 정보를 저장(BoardDTO)
		BoardDTO dto = new BoardDTO();
		dto.setName(request.getParameter("name"));
		dto.setPass(request.getParameter("pass"));
		dto.setSubject(request.getParameter("subject"));
		dto.setContent(request.getParameter("content"));
		
		dto.setIp(request.getRemoteAddr());
		
		System.out.println(" M : "+dto);		
		
		// BoardDAO 객체 생성
		BoardDAO dao = new BoardDAO();
		// insertBoard() 글쓰기 동작 실행
		dao.insertBoard(dto);
		
		// 페이지 이동 -> 이동정보를 저장해서 컨트롤러 전달
		ActionForward forward = new ActionForward();
		forward.setPath("./BoardList.bo");
		forward.setRedirect(true);
		
		return forward;
	}
	
	

}
