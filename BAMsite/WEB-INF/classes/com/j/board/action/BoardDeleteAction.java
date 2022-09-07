package com.j.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.j.board.db.BoardDAO;
import com.j.board.db.BoardDTO;

public class BoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println(" M : BoardDeleteAction_execute() 호출 ");
		
		// 한글처리(생략)
		
		// 전달되는 정보 저장 (num, pass, pageNum)
		// => DTO에 저장
		BoardDTO dto = new BoardDTO();
		dto.setNum(Integer.parseInt(request.getParameter("num")));
		dto.setPass(request.getParameter("pass"));
		
		String pageNum = request.getParameter("pageNum");
		
		// DAO 객체  -> 삭제 메서드
		BoardDAO dao = new BoardDAO();
		int result = dao.deleteBoard(dto.getNum(), dto.getPass());
		
		// 삭제 처리 결과에 따른 페이지 이동(js)
		// 처리 응답 결과는 html형태로 보여주겠다. (MIME타입)
		response.setContentType("text/html; charset=UTF-8");
		// 응답결과를 처리하는 연결통로를 지정(데이터 보낼 준비)
		PrintWriter out = response.getWriter();
//		out.print("안녕하세요!");
		
		if(result == 0){ // 비밀번호 오류			
			//out.print("HTML코드를 출력가능!");
			out.print("<script>");
			out.print(" alert('비밀번호 오류!!!(삭제x)'); ");
			out.print(" history.back(); ");
			out.print("</script>");
			
			// 응답처리 연결통로를 제거 (자원해제)
			out.close();
			
			return null; // 컨트롤러로 이동 X			
		}else if(result == -1){
			out.print("<script>");
			out.print(" alert('글정보 없음!!!(삭제x)'); ");
			out.print(" history.back(); ");
			out.print("</script>");
			
			out.close();	
			return null; // 컨트롤러로 이동 X
		}
		
		// result == 1 삭제 성공
		out.print("<script>");
		out.print(" alert('글 삭제 완료!!!(삭제O)'); ");
		out.print(" location.href='./BoardList.bo?pageNum="+pageNum+"'; ");
		out.print("</script>");
		
		out.close();	
		
		return null;
	}

}
