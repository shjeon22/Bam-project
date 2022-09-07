package com.j.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.j.board.db.BoardDAO;
import com.j.board.db.BoardDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardFileUploadAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println(" M : BoardFileUploadAction_execute 호출 ");
		
		// 1) 파일 업로드
		// 	- 가상의 업로드 폴더 설정  upload폴더 생성	
		String path = request.getRealPath("/upload");
		System.out.println(" M : "+path);
		//  - 업로드 파일의 크기 설정(제한)
		int maxSize = 5 * 1024 * 1024; //5mb
		//  - MultipartRequest 객체 생성(업로드)
		MultipartRequest multi 
		       = new MultipartRequest(
		    		   request,
		    		   path,
		    		   maxSize,
		    		   "UTF-8",
		    		   new DefaultFileRenamePolicy()
		    		   );
		
		System.out.println(" M : 파일 업로드 완료! ");
		
		
		// 2) DB저장 		
		//  - 전달정보를 저장 (DTO)
		BoardDTO dto = new BoardDTO();
		// name,subject,pass,content,file,ip
		dto.setName(multi.getParameter("name"));
		dto.setSubject(multi.getParameter("subject"));
		dto.setPass(multi.getParameter("pass"));
		dto.setContent(multi.getParameter("content"));
		
		dto.setFile(multi.getFilesystemName("file"));// 서버에 업로드된 파일명
		
		dto.setIp(request.getRemoteAddr());
		
		//System.out.println(" M : "+dto);
		
		//  - DAO 객체 생성 - 업로드 메서드 호출
		BoardDAO dao = new BoardDAO();
		dao.insertBoard(dto);
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./BoardList.bo");
		forward.setRedirect(true);
		
		return forward;
	}

}
