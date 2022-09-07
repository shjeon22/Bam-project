package com.j.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	// 디비에 j_board 테이블 관련된 모든 동작을 처리

	// 공통변수 선언
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
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

	// insertBoard(bb)
	public void insertBoard(BoardDTO bb) {
		int num = 0; // 글번호 저장변수

		try {
			// 1.2. 디비연결
			con = getCon();

			// 3 sql 작성(글번호계산) & pstmt 객체
			sql = "select max(num) from j_board";
			pstmt = con.prepareStatement(sql);

			// 4 sql 실행
			rs = pstmt.executeQuery();

			// 5 데이터 처리
			if (rs.next()) { // max(num) 컬럼의 결과는 항상 존재함(커서 있음)
				num = rs.getInt(1) + 1;// 인덱스
//					num = rs.getInt("max(num)")+1;//컬럼명     '//글이 있을경우  최근글번호(번호가 가장큰값)+1
			}

			System.out.println(" DAO : 글번호 " + num);

			/////////////////////////////////////////////////////////////
			// 글쓰기

			// 이미 연결됨 (1.2. 단계 생략)
			// 3. sql(insert) 작성 & pstmt 객체
			sql = "insert into j_board(num,name,pass,subject,content,"
					+ "readcount,re_ref,re_lev,re_seq,date,ip,file) " + "values(?,?,?,?,?,?,?,?,?,now(),?,?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, num); // 직접 계산한 글번호
			pstmt.setString(2, bb.getName());
			pstmt.setString(3, bb.getPass());
			pstmt.setString(4, bb.getSubject());
			pstmt.setString(5, bb.getContent());

			pstmt.setInt(6, 0); // readcount 모든글의 조회수 0
			pstmt.setInt(7, num); // re_ref 답글 -> 그룹번호 일반글은 글번호와 동일
			pstmt.setInt(8, 0); // re_lev 답글 -> 들여쓰기 일반글은 0
			pstmt.setInt(9, 0); // re_seq 답글 -> 순서 일반글은 0

			pstmt.setString(10, bb.getIp());
			pstmt.setString(11, bb.getFile());

			// 4. sql 실행
			pstmt.executeUpdate();

			System.out.println(" DAO : 글쓰기 완료! ");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	// insertBoard(bb)

	// getBoardCount() 글개수 확인 실행
	public int getBoardCount() {
		int result = 0;

		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql 작성(select) & pstmt 객체
			sql = "select count(num) from j_board";
			pstmt = con.prepareStatement(sql);
			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리
			if (rs.next()) {
				// result = rs.getInt("count(num)");
				result = rs.getInt(1);
			}

			System.out.println(" DAO : 게시판 글개수 " + result + "개");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return result;
	}
	// getBoardCount()

	// getBoardList()// 글이 있을때, 글정보 전부를 가져오기
	public ArrayList getBoardList() {
		ArrayList boardList = new ArrayList();

		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql 작성 & pstmt 객체 (num 내림차순 정렬)
			sql = "select * from j_board";
//				sql = "select * from j_board order by num desc";
//				sql = "select * from j_board order by num desc limit 0,5";
			
/*			select * from 테이블명 order by 기준컬럼 desc limit 3( 0번째부터 3개 내림차순 출력)

			select * from 테이블명 order by 기준컬럼 asc limit 3, 3(4번째부터 3개 오름차순 출력)*/
			pstmt = con.prepareStatement(sql);
			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리
			while (rs.next()) { // 데이터 있을때 DB정보를 모두 저장
				// 글 1개의 정보 => BoardDTO 객체
				BoardDTO bb = new BoardDTO();

				bb.setContent(rs.getString("content"));
				bb.setDate(rs.getDate("date"));
				bb.setFile(rs.getString("file"));
				bb.setIp(rs.getString("ip"));
				bb.setName(rs.getString("name"));
				bb.setNum(rs.getInt("num"));
				bb.setPass(rs.getString("pass"));
				bb.setRe_lev(rs.getInt("re_lev"));
				bb.setRe_ref(rs.getInt("re_ref"));
				bb.setRe_seq(rs.getInt("re_seq"));
				bb.setReadcount(rs.getInt("readcount"));
				bb.setSubject(rs.getString("subject"));

				// BoardBean 객체의 정보 => ArrayList 한칸에 저장

				boardList.add(bb);
			} // while

			System.out.println(" DAO : 게시판 글 전체 목록 저장완료! ");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return boardList;
	}
	// getBoardList()

	// getBoardList(startRow,pageSize)
	public ArrayList<BoardDTO> getBoardList(int startRow, int pageSize) {
		ArrayList<BoardDTO> boardList = new ArrayList<BoardDTO>();

		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql 작성 & pstmt 객체 (num 내림차순 정렬, 페이징 처리)
			// => re_ref 내림차순, re_seq 오름차순 / limit 시작행-1,개수
			// sql = "select * from j_board order by num desc limit ?,?";
			sql = "select * from j_board order by re_ref desc, re_seq asc limit ?,?";
			pstmt = con.prepareStatement(sql);

			// ???
			pstmt.setInt(1, startRow - 1); // 시작행 - 1
			pstmt.setInt(2, pageSize); // 개수

			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리
			while (rs.next()) { // 데이터 있을때 DB정보를 모두 저장
				// 글 1개의 정보 => BoardDTO 객체
				BoardDTO bb = new BoardDTO();

				bb.setContent(rs.getString("content"));
				bb.setDate(rs.getDate("date"));
				bb.setFile(rs.getString("file"));
				bb.setIp(rs.getString("ip"));
				bb.setName(rs.getString("name"));
				bb.setNum(rs.getInt("num"));
				bb.setPass(rs.getString("pass"));
				bb.setRe_lev(rs.getInt("re_lev"));
				bb.setRe_ref(rs.getInt("re_ref"));
				bb.setRe_seq(rs.getInt("re_seq"));
				bb.setReadcount(rs.getInt("readcount"));
				bb.setSubject(rs.getString("subject"));

				// BoardBean 객체의 정보 => ArrayList 한칸에 저장

				boardList.add(bb);
			} // while

			System.out.println(" DAO : 게시판 글 전체 목록 저장완료! ");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return boardList;
	}
	// getBoardList(startRow,pageSize)

	// updateReadCount(num)
	public void updateReadCount(int num) {

		try {
			// 1.2 디비연결
			con = getCon();
			// 3. sql 작성 & pstmt 생성
			// 기존 조회수 + 1 구문
			sql = "update j_board set readcount = readcount + 1 where num=?";
			pstmt = con.prepareStatement(sql);

			// ???
			pstmt.setInt(1, num);

			// 4. sql 실행
			pstmt.executeUpdate();

			System.out.println(" DAO : " + num + "번글 조회수 1증가! ");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

	}
	// updateReadCount(num)

	// getBoard(num)
	public BoardDTO getBoard(int num) {
		BoardDTO bb = null;

		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql 작성 & pstmt 객체
			sql = "select * from j_board where num = ?";
			pstmt = con.prepareStatement(sql);

			// ???
			pstmt.setInt(1, num);

			// 4. sql 실행
			rs = pstmt.executeQuery();

			// 5. 데이터 처리
			if (rs.next()) {
				bb = new BoardDTO();

				bb.setContent(rs.getString("content"));
				bb.setDate(rs.getDate("date"));
				bb.setFile(rs.getString("file"));
				bb.setIp(rs.getString("ip"));
				bb.setName(rs.getString("name"));
				bb.setNum(rs.getInt("num"));
				bb.setPass(rs.getString("pass"));
				bb.setRe_lev(rs.getInt("re_lev"));
				bb.setRe_ref(rs.getInt("re_ref"));
				bb.setRe_seq(rs.getInt("re_seq"));
				bb.setReadcount(rs.getInt("readcount"));
				bb.setSubject(rs.getString("subject"));
			}
			System.out.println(" DAO : 게시판 글 1개 저장완료 ");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return bb;
	}
	// getBoard(num)

	// updateBoard(ubb)
	public int updateBoard(BoardDTO ubb) {
		int result = -1;

		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql 작성 & pstmt 객체
			// 글이 존재하는지 체크 (글을 쓴사람일때만 수정)
			sql = "select pass from j_board where num=?";
			pstmt = con.prepareStatement(sql);

			// ????
			pstmt.setInt(1, ubb.getNum());

			// 4. sql 실행
			rs = pstmt.executeQuery();

			// 5. 데이터처리
			if (rs.next()) { // 비밀번호가 존재한다 => 게시판글이 있음 => 수정
				if (ubb.getPass().equals(rs.getString("pass"))) {
					// 데이터 수정
					// 3. sql 생성(update) & pstmt 객체
					sql = "update j_board set name=?,subject=?,content=? where num=?";
					pstmt = con.prepareStatement(sql);

					pstmt.setString(1, ubb.getName());
					pstmt.setString(2, ubb.getSubject());
					pstmt.setString(3, ubb.getContent());
					pstmt.setInt(4, ubb.getNum());

					// 4. sql 실행
					pstmt.executeUpdate();

					result = 1;

				} else {
					result = 0;
				}
			} else {
				result = -1;
			}

			System.out.println(" DAO : 정보 수정완료 (" + result + ")");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return result;
	}

	// updateBoard(ubb)

	// deleteBoard(num,pass)
	public int deleteBoard(int num, String pass) {
		int result = -1;

		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql 작성 & pstmt 객체
			sql = "select pass from j_board where num=?";
			pstmt = con.prepareStatement(sql);

			// ???
			pstmt.setInt(1, num);

			// 4. sql 실행
			rs = pstmt.executeQuery();

			// 5. 데이터 처리
			if (rs.next()) {

				if (pass.equals(rs.getString("pass"))) {
					// 3. sql 작성(delete) & pstmt 객체
					sql = "delete from j_board where num=?";
					pstmt = con.prepareStatement(sql);

					// ??
					pstmt.setInt(1, num);

					// 4. sql 실행
					result = pstmt.executeUpdate();
					// result = 1;

				} else {
					result = 0;
				}

			} else {
				result = -1;
			}

			System.out.println(" DAO : 게시판 글 삭제 완료(" + result + ")");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return result;
	}
	// deleteBoard(num,pass)

	// reInsertBoard(reBB)
	public void reInsertBoard(BoardDTO reBB) {
		int num = 0;

		try {
			// 답글 번호 계산------------------------------------------------------
			// 1. 2. 디비연결
			con = getCon();
			// 3. sql 작성 & pstmt 객체
			sql = "select max(num) from j_board";
			pstmt = con.prepareStatement(sql);

			// 4 sql 실행
			rs = pstmt.executeQuery();

			// 5 데이터처리
			if (rs.next()) {
				num = rs.getInt(1) + 1;
			}

			System.out.println(" DAO : 답글의 번호 " + num);

			// 답글 순서 재배치 (기존의 답글)-------------------------------------
			// 3. sql 구문 & pstmt 객체
			// 답글중에서 seq값이 동일 한값이 있을때 1증가
			// re_ref(같은그룹) , re_seq(순서) 기존(부모글)의 값보다 큰값이 있을때
			sql = "update j_board set re_seq = re_seq + 1 where re_ref=? and re_seq>?";
			pstmt = con.prepareStatement(sql);

			// ??
			pstmt.setInt(1, reBB.getRe_ref());
			pstmt.setInt(2, reBB.getRe_seq());

			// 4. sql 실행
			int check = pstmt.executeUpdate();

			if (check > 0)
				System.out.println(" DAO : 답글 순서 재배치 완료! ");

			// 답글 저장 (ref-부모글의 값,lev-부모+1, seq-부모+1)------------------

			// 3. sql 작성 & pstmt 객체
			sql = "insert into j_board(num,name,pass,subject,content," + "readcount,re_ref,re_lev,re_seq,date,ip,file) "
					+ "values(?,?,?,?,?,?,?,?,?,now(),?,?)";

			pstmt = con.prepareStatement(sql);

			// ???
			pstmt.setInt(1, num);
			pstmt.setString(2, reBB.getName());
			pstmt.setString(3, reBB.getPass());
			pstmt.setString(4, reBB.getSubject());
			pstmt.setString(5, reBB.getContent());
			pstmt.setInt(6, 0); // 조회수 0
			pstmt.setInt(7, reBB.getRe_ref()); // ref - 부모글의 번호
			pstmt.setInt(8, reBB.getRe_lev() + 1); // lev - 부모글 + 1
			pstmt.setInt(9, reBB.getRe_seq() + 1); // seq - 부모글 + 1
			pstmt.setString(10, reBB.getIp());
			pstmt.setString(11, reBB.getFile());

			// 4. sql 실행

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	// reInsertBoard(reBB)

	// getBoardCount(search) - 오버로딩
	public int getBoardCount(String search) {
		int searchCnt = 0;

		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql 작성(select) & pstmt 객체
			sql = "select count(*) from j_board where subject like ?";
			pstmt = con.prepareStatement(sql);

			// ???
			pstmt.setString(1, "%" + search + "%"); // ? => '%검색어%'

			// 4. sql 실행
			rs = pstmt.executeQuery();

			// 5. 데이터 처리
			if (rs.next()) {
				searchCnt = rs.getInt(1); // 인덱스
				// searchCnt = rs.getInt("count(*)"); //컬럼명
			}
			System.out.println(" DAO : 검색된 글 개수 " + searchCnt);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return searchCnt;
	}
	// getBoardCount(search) - 오버로딩

	// getBoardList(startRow, pageSize, search) - 오버로딩
	public List<BoardDTO> getBoardList(int startRow, int pageSize, String search) {
		List<BoardDTO> searchBoardList = new ArrayList<BoardDTO>();

		try {
			// 1.2. 디비연결
			con = getCon();

			// 3. sql 작성 & pstmt 객체
			// 정렬 - re_ref (내림차순), re_seq(오름차순)
			// limit - 원하는 만큼만 가져오기
			// 검색어
			sql = "select * from j_board " + " where subject like ? " + " order by re_ref desc, re_seq asc limit ?,?";
			pstmt = con.prepareStatement(sql);

			// ???
			pstmt.setString(1, "%" + search + "%");
			pstmt.setInt(2, startRow - 1);
			pstmt.setInt(3, pageSize);

			// 4. sql 실행
			rs = pstmt.executeQuery();

			// 5. 데이터 처리 (list에 저장)
			while (rs.next()) {
				// 글 1개의 정보 => BoardDTO 객체
				BoardDTO bb = new BoardDTO();

				bb.setContent(rs.getString("content"));
				bb.setDate(rs.getDate("date"));
				bb.setFile(rs.getString("file"));
				bb.setIp(rs.getString("ip"));
				bb.setName(rs.getString("name"));
				bb.setNum(rs.getInt("num"));
				bb.setPass(rs.getString("pass"));
				bb.setRe_lev(rs.getInt("re_lev"));
				bb.setRe_ref(rs.getInt("re_ref"));
				bb.setRe_seq(rs.getInt("re_seq"));
				bb.setReadcount(rs.getInt("readcount"));
				bb.setSubject(rs.getString("subject"));

				// DTO -> arrylist 한칸에 저장
				searchBoardList.add(bb);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return searchBoardList;
	}
	// getBoardList(startRow, pageSize, search)

}
