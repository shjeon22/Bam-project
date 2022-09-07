package com.j.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Execute;

public class MemberDAO {
	// 공통변수 선언
	private Connection con = null;
	private PreparedStatement pstmt = null; // sql문 ->db에 전달
	private ResultSet rs = null; // select문의 결과를 저장하는 객체
	private String sql = "";

	// 디비연결 메서드
	private Connection getCon() throws Exception {
		// 1.2. 디비연결

		// 1) 프로젝트 정보를 초기화
		Context initCTX = new InitialContext();
		// 2) 프로젝트에 저장된 리소스 정보를 불러오기
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/board");
		// 3) 디비연결
		con = ds.getConnection();

		System.out.println(" DAO : 디비연결 성공(커넥션풀) ");
		System.out.println(" DAO : " + con);

		return con;
	}
	// 디비연결 메서드

	// 디비 자원해제 메서드
	public void closeDB() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
			System.out.println(" DAO : 디비 연결 자원해제 ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// 디비 자원해제 메서드

	// 회원가입
	public/* boolean */void MemberInsert(MemberDTO mm) {
		int num = 0;// 회원번호 저장 변수
		// int result = 0;// 밑에 pstmt 객체넣음

		try {
			con = getCon();
			sql = "select max(num) from j_member"; // 멤버넘 안먹히면 db다시 num으로
													// 변경,,
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// 한명씩 회원번호 +1 해서 num으로 초기화
				num = rs.getInt(1) + 1;
			} else {
				num = 1;// 회원이 없으면 기본값 1들어감
			}
			System.out.println(" DAO : 회원번호" + num);

			// sql insert 작성 &pstmt 객체
			sql = "insert into j_member values(?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num); // 55번 num변수 (회원번호) 가져옴(MEMBER_NUM먹힘)
			pstmt.setString(2, mm.getId());
			pstmt.setString(3, mm.getPw());
			pstmt.setString(4, mm.getName());
			pstmt.setInt(5, mm.getPin1());
			pstmt.setInt(6, mm.getPin2());
			pstmt.setString(7, mm.getEmail());
			pstmt.setString(8, mm.getTel());
			pstmt.setInt(9, mm.getZipcode());
			pstmt.setString(10, mm.getAddress());
			pstmt.setString(11, mm.getAddress2());
			pstmt.setString(12, mm.getJob());

			/* result = */pstmt.executeUpdate();
			/*
			 * if (result == 0) { return false; } return true;
			 */
		} catch (Exception e) {
			System.out.println("DAO : MemberInsert 에러 : " + e);
			e.printStackTrace();
		} finally {
			closeDB();
		}

		/* return false; */

	}// 회원가입

	// 로그인 ///////////////////// loginCheck(id,pw);
	public int loginCheck(String id, String pw) {
		int result = -1;

		try {
			con = getCon();
			sql = "select pw from j_member where id=?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (pw.equals(rs.getString("pw"))) {// 입력정보(id==pw) 디비와일치할때
													// 로그인성공 1
					result = 1;
					System.out.println("로그인성공");
				} else {
					result = 0;// 입력정보(id!=pw 디비에 지정된 비번이랑다를때)0
					System.out.println("로그인실패!!!");
				}
			} else {
				result = -1; // 아이디에 해당하는 비번없다(비회원) -1
				System.out.println("로그인실패");
			}
			System.out.println("DAO 로그인 체크 완료" + result);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return result;
	} ///////////////////// loginCheck(id,pw);
// Idcheck(String id)

	public boolean Idcheck(String id) {
		boolean flag = false;
		try {
			con = getCon();
			sql = "select id from j_member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			flag = pstmt.executeQuery().next();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return flag;
	}// Idcheck(String id)

	//
	// 회원목록 memberList();
	public ArrayList<Object> memberList() {
		ArrayList<Object> list = new ArrayList<>();
		try {
			con = getCon();
			sql = "select*from j_member";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				MemberDTO dto = new MemberDTO();

				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setPin1(rs.getInt("pin1"));
				dto.setPin2(rs.getInt("pin2"));
				dto.setEmail(rs.getString("email"));
				dto.setTel(rs.getString("tel"));
				dto.setZipcode(rs.getInt("zipcode"));
				dto.setAddress(rs.getString("address"));
				dto.setAddress2(rs.getString("address2"));
				dto.setJob(rs.getString("job"));

				list.add(dto); // ArrayList에 MemberDTO dto값을 추가
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return list;
	}// memberList();

	// getMember--------------------------------아이디값 불러오는 작업 다른테이블
	// 5. 회원정보 가져오는 메서드 getMember:회원정보 전부 리턴 -> 테이블에 추가
	public MemberDTO getMember(String id) {
		MemberDTO mb = null; // 객체 레퍼런스 생성
		// 왜 null인가? 정보가 생성되는 시점은 rs에 있을때이다.
		// 따라서 rs가 없을땐 굳이 만들필요가 없다.

		try {
			// 5-1. 드라이브로드 디비연결
			con = getCon();
			// 5-2. SQL & pstmt 생성
			sql = "select * from j_member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			// 5-3. 실행 -> rs저장
			rs = pstmt.executeQuery();
			// 5-4. 데이터처리 : DB에 있는 회원정보 저장 후 memberinfo페이지로 전달
			// DB의컬럼명을 입력할 것
			if (rs.next()) {
				mb = new MemberDTO();
				mb.setId(rs.getString("id"));
				mb.setPw(rs.getString("pw"));
				mb.setName(rs.getString("name"));
				mb.setPin1(rs.getInt("pin1"));
				mb.setPin2(rs.getInt("pin2"));
				mb.setEmail(rs.getString("email"));
				mb.setTel(rs.getString("tel"));
				mb.setZipcode(rs.getInt("zipcode"));
				mb.setAddress(rs.getString("address"));
				mb.setAddress2(rs.getString("address2"));
				mb.setJob(rs.getString("job"));

			}
			System.out.println("@@@@ DAO : 회원정보저장완료" + mb);
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 5-5. 자원해제
			closeDB();
		}
		return mb;
	}// getMember닫힘
		// getMember--------------------------------아이디값 불러오는 작업 다른테이블 goodorder

	// 아이디 중복체크
	public int checkId(String id) {// 유저가 입력한 값을 매개변수로 한다
		int idCheck = 0;
		try {
			con = getCon();
			sql = "select *from j_member where id =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (id.equals(rs.getString("id"))) {
				idCheck = 0;// id=db에 이미 존재 =>생성불가능
			} else {
				idCheck = 1; // id 존재 x =>id 생성가능
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return idCheck;
	}
	// 아이디 중복체크

	// 개인회원정보
	public MemberDTO memberInfo(MemberDTO mdto) {

		try {
			con = getCon();
			sql = "select *from j_member";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setPin1(rs.getInt("pin1"));
				dto.setPin2(rs.getInt("pin2"));
				dto.setEmail(rs.getString("email"));
				dto.setTel(rs.getString("tel"));
				dto.setZipcode(rs.getInt("zipcode"));
				dto.setAddress(rs.getString("address"));
				dto.setAddress2(rs.getString("address2"));
				dto.setJob(rs.getString("job"));
				return dto;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}// (관리자용) 회원 개인정보 저장

	// 회원 삭제
	public boolean memberDelete(String id) {
		boolean result2 = false;
		try {
			con = getCon();
			sql = "delete from j_member where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			int result = pstmt.executeUpdate();
			if (result != 0) {
				result2 = true;
			}
			System.out.println(result);
			System.out.println(result2);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result2;
	} // 회원 삭제

	// 회원수정 업데이트프로액션
	public int updateMember(MemberDTO mdto) {
		int result = -1;

		try {
			con = getCon();
			sql = "select pw from j_member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mdto.getId());
			rs = pstmt.executeQuery();

			// 데이터처리 : DB에 있는 회원인 경우 수정 아닌 경우 에러
			if (rs.next()) {
				if (mdto.getPw().equals(rs.getString("pw"))) {// 비번일치하면 밑에서 수정 sql작업

					sql = "update j_member set name=?,pin1=?,pin2=?,email=?,tel=?,zipcode=?,address=?,address2=?,job=? "
							+ "where id=?";

					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, mdto.getName());
					pstmt.setInt(2, mdto.getPin1());
					pstmt.setInt(3, mdto.getPin2());
					pstmt.setString(4, mdto.getEmail());
					pstmt.setString(5, mdto.getTel());
					pstmt.setInt(6, mdto.getZipcode());
					pstmt.setString(7, mdto.getAddress());
					pstmt.setString(8, mdto.getAddress2());
					pstmt.setString(9, mdto.getJob());
					pstmt.setString(10, mdto.getId());

					pstmt.executeUpdate();
					System.out.println("회원정보수정성공");
					result = 1;
				} else {
					result = 0;
					System.out.println("아디일치,비번불일치 - 회원정보수정실패");
				}
			} else { // DB에 없는 회원
				result = -1;
				System.out.println("존재하지않는아이디 - 회원정보수정실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}
	// 회원수정 업데이트프로액션

}// MemberDAO 끝
