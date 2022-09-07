package com.j.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.j.board.db.BoardDAO;
import com.j.board.db.BoardDTO;

public class BoardContentAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println(" M : BoardContentAction_execute() 호출 ");
		
		// 전달된 데이터 저장(num,pageNum)
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		// BoardDAO 객체 생성
		BoardDAO dao = new BoardDAO();
		
		// 조회수 1증가 동작 실행
		dao.updateReadCount(num);
		System.out.println(" M : 조회수 1증가 완료");
		
		// 글번호에 해당하는 글 전체의 정보를 가져오기
		BoardDTO dto = dao.getBoard(num);
		System.out.println(" M : 글정보 1개 조회 완료");

		// request 영역에 글정보를 저장
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", pageNum);
		
		//request.setAttribute("dto", dao.getBoard(num));
		
		// 페이지 이동(출력) (./center/content.jsp)
		ActionForward forward = new ActionForward();
		forward.setPath("./center/content.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
	
	

}
