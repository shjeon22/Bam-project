package com.j.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	//상수,추상메서드
	
	// 인터페이스에 있는 메서드는 반드시 추상메서드
	// 기본적으로 public 사용, abstract 키워드 사용 => 모두 생략 가능
/*	public abstract void method1();
	public abstract void method();
	public  abstract void method2();
	public abstract void method3();*/
	
	// 호출할때 request,response 정보를 필요로하고,
	// 처리동작 후 ActionForward(페이지 이동객체) 리턴
	
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	
}
