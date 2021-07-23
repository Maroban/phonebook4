package com.javaex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;

@Repository
public class PhoneDao {

	// 필드

	// 1. 필드 DataSource 추가
	@Autowired
	private DataSource dataSource;

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	 /*
	 **** 2. 드라이버, URL, id, pw 삭제(applicationContext.xml에 세팅 해놓음) ****
	 private String driver = "oracle.jdbc.driver.OracleDriver"; // 드라이버
	 private String url = "jdbc:oracle:thin:@localhost:1521:xe"; // IP주소와 포트번호
	 private String id = "phonedb"; // SQL 계정 이름
	 private String pw = "phonedb"; // SQL 계정 비밀번호
	 */

	// DB 연결
	private void getConnection() {
		try {
			// 3. 위와 마찬가지로 세팅 해놨기 때문에 삭제
			// Class.forName(driver);

			// 4. DriverManager가 아닌 dataSource의 getConnection 사용
			conn = dataSource.getConnection();

			 /*
			   **** 5. ClassNotFoundException e 삭제 ****
			   } catch (ClassNotFoundException e) {
			   System.out.println("error: 드라이버 로딩 실패 - " + e);
			 */
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// 자원 정리
	private void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

	// 전화번호부 출력
	public List<PersonVo> getPersonList() {
		return getPersonList("");

	}

	// 전화번호부 출력 & 검색
	public List<PersonVo> getPersonList(String keyword) {
		List<PersonVo> personList = new ArrayList<PersonVo>();

		// 2번, 4번 메소드
		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " SELECT ";
			query += "     person_id, ";
			query += "     name, ";
			query += "     hp, ";
			query += "     company ";
			query += " FROM ";
			query += "     person ";

			if (keyword == null || keyword.equals("")) {

				query += " ORDER BY ";
				query += "     person_id ASC ";

			} else {
				query += " WHERE ";
				query += "     person_id LIKE '%" + keyword + "%' ";
				query += "     OR name LIKE '%" + keyword + "%' ";
				query += "     OR hp LIKE '%" + keyword + "%' ";
				query += "     OR company LIKE '%" + keyword + "%' ";
			}

			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int person_id = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");

				PersonVo personVo = new PersonVo(person_id, name, hp, company);

				personList.add(personVo);
			}

			// 4.결과처리

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 5번 메소드
		this.close();

		return personList;
	}

	// 전화번호 등록
	public int insert(PersonVo personVo) {

		int count = -1;

		// 2번, 4번 메소드
		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String insert = "";
			insert += " INSERT INTO person VALUES ( ";
			insert += "     seq_person_id.NEXTVAL, ";
			insert += "     ?, ";
			insert += "     ?, ";
			insert += "     ? ";
			insert += " ) ";

			pstmt = conn.prepareStatement(insert);
			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("[" + personVo.getName() + "님이 등록되었습니다.]");
			System.out.println();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 5번 메소드
		this.close();
		return count;
	}

	// 전화번호 수정
	public int update(PersonVo personVo) {

		int count = -1;

		// 2번, 4번 메소드
		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String update = "";
			update += " UPDATE person ";
			update += " SET ";
			update += "     name = ?, ";
			update += "     hp = ?, ";
			update += "     company = ? ";
			update += " WHERE ";
			update += "     person_id = ? ";

			pstmt = conn.prepareStatement(update);
			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());
			pstmt.setInt(4, personVo.getPerson_id());

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("[" + count + "건 수정되었습니다.]");
			System.out.println();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 5번 메소드
		this.close();
		return count;
	}

	// 전화번호 삭제
	public int delete(int personId) {

		int count = -1;

		// 2번, 4번 메소드
		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String delete = "";
			delete += " DELETE FROM person ";
			delete += " WHERE ";
			delete += "     person_id = ? ";

			pstmt = conn.prepareStatement(delete);
			pstmt.setInt(1, personId);

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("[" + count + "건 삭제되었습니다.]");
			System.out.println();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 5번 메소드
		this.close();
		return count;
	}

	// 사람 1명 가져오기
	public PersonVo getPerson(int personId) {
		PersonVo personVo = null;

		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " SELECT ";
			query += "     person_id, ";
			query += "     name, ";
			query += "     hp, ";
			query += "     company ";
			query += " FROM ";
			query += "     person ";
			query += " WHERE ";
			query += "     person_id = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, personId);

			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int pid = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");

				personVo = new PersonVo(pid, name, hp, company);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 4.결과처리

		close();

		return personVo;

	}

}
