package com.goodee.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.goodee.model.vo.Member;

public class MemberDao {

/*
 * DAO(Data Access Object)
 * : DB에 직접 접근해서 사용자의 요청에 맞는 SQL문을 실행한 후 결과를 받음.(JDBC이용)
 *   결과를 Controller로 반환함.
 * 
 * >> Statement와 PrerparedStatement의 특징
 * - 두 객체 모두 sql문을 실행하고 결과를 받아내는 객체. Connection 객체를 이용해서 생성.(둘 중 하나 이용)
 * - Statement가 PrerparedStatement의 부모(상속 구조)
 * 
 * 
 * - Statement와 PrerparedStatement의 차이점
 * - Statement는 sql문을 전달하면서 바로 실행하는 객체
 * 	(즉, sql문을 완성형태로 만들어야함. => 사용자가 입력한 값이 완전히 채워진 사애. 그렇지 않으면 SQL 예외 발생)
 * 
 * 
 *	> 기존의 Statement 방식
 *		1) Connection 객체를 통해 Statement 객체 생성
 *			- stmt = conn.createStatement ();
 *		2) Statement객체를 통해서 완성된 sql문 실행 및 결과 받기
 *			- rset = stmt.executeXXX(완성된 sql) 
 *									(select > xxx에 query : 결과를 받을 필요 없을 때, insert,update, delete > xxx에 update : int값 반환)
 * 
 * 
 * * PreparedStatement 같은 경우 미완성 sql문을 잠시 보관해두었다가 나중에 완성한 후 실행할 수 있는 객체
 * 		(즉, 사용자가 입력한 값을 채워두지 않고 각각 들어갈 공간(? 사용)을 미리 확보만 해두면 됨.)
 * 
 * 	> PrerparedStatement 방식
 *	 	1) Connection 객체를 통해 PreparedStatement 객체 생성
 * 			- pstmt = conn.prepareStatement (미완성 sql문) ;
 * 		2) pstmt에 담긴 sql문이 미완성 상태일 때 우선 완성시켜야함.
 * 			- pstmt.setXXX(1, 대체할값);
 * 			- pstmt.setXXX(2, 대체할값);
 * 		3) sql문 실행 및 결과 받기
 * 			- 결과(rset 또는 int변수) = pstmt.executeXXX();
 * 		
 */
	
	public int insertMember(Member m) {
		
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		
//		Statement  stmt = null;
		
//		String sql = "INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL, '"+ m.getUserId()  + "', "
//															  + "'"+ m.getUserPwd() + "', "
//															  + "'"+ m.getUserName() + "', "
//															  + "'"+ m.getGender() + "', "
//															  	   + m.getAge() + ", "
//															  + "'"+ m.getEmail() + "', "
//															  + "'"+ m.getPhone() + "', "
//															  + "'"+ m.getAddress() + "', "
//															  + "'"+ m.getHobby() + "', SYSDATE)";
//		System.out.println(sql);		
		
		String sql = "INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL,?,?,?,?,?,?,?,?,?,SYSDATE)";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
//			stmt = conn.createStatement();
			pstmt =conn.prepareStatement(sql);
			
			//pstmt.setString(물음표순서, 대체할값) => '대체할값' (양옆에 홑따옴표로 감싸준 데이터가 들어감)
			//pstmt.setInt(물음표순서, 대체할값)	=> 홑따옴표 없이 데이터가 들어감
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());
			
			
			
			System.out.println(sql);			
			
			
			
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {  // 성공
				conn.commit();
			}else {           // 실패
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	// 전체 회원을 조회하는 메서드
	public ArrayList<Member> selectList() {
		ArrayList<Member> list = new ArrayList<>();
		
		Connection conn = null;
		Statement  stmt = null;
		ResultSet  rset = null;
		
		String sql = "SELECT * FROM MEMBER";
	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				Member m = new Member();
				
				// rset에서 user_no컬럼을 읽어서 m객체의 setter(setUserNo)를 이용하여 입력
				m.setUserNo(rset.getInt("user_no"));  
				m.setUserId(rset.getString("user_id"));
				m.setUserPwd(rset.getString("user_pwd"));
				m.setUserName(rset.getString("user_name"));
				m.setGender(rset.getString("gender"));
				m.setEmail(rset.getString("email"));
				m.setAge(rset.getInt("age"));
				m.setPhone(rset.getString("phone"));
				m.setAddress(rset.getString("address"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrollDate(rset.getDate("enroll_date"));
				
				list.add(m);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return list;
	}
	
	// 회원 아이디로 검색 요청시 조회하는 메서드
	public Member selectByUserId(String userId) {
		Member m = null;
		
		
		Connection conn = null;
		Statement  stmt = null;
		ResultSet  rset = null;
		
		String sql = "SELECT * FROM MEMBER WHERE USER_ID = '" + userId + "'";
	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			
			if(rset.next()) {
				m = new Member();
				
				// rset에서 user_no컬럼을 읽어서 m객체의 setter(setUserNo)를 이용하여 입력
				m.setUserNo(rset.getInt("user_no"));  
				m.setUserId(rset.getString("user_id"));
				m.setUserPwd(rset.getString("user_pwd"));
				m.setUserName(rset.getString("user_name"));
				m.setGender(rset.getString("gender"));
				m.setEmail(rset.getString("email"));
				m.setAge(rset.getInt("age"));
				m.setPhone(rset.getString("phone"));
				m.setAddress(rset.getString("address"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrollDate(rset.getDate("enroll_date"));
				
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return m;
	}	
	
	public ArrayList<Member> selectByUserName(String userName) {
		ArrayList<Member> list = new ArrayList<>();
		
		Connection conn = null;
		Statement  stmt = null;
		ResultSet  rset = null;
		
		String sql = "SELECT * FROM MEMBER WHERE USER_NAME like '%" + userName + "%'";
	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				Member m = new Member();
				
				// rset에서 user_no컬럼을 읽어서 m객체의 setter(setUserNo)를 이용하여 입력
				m.setUserNo(rset.getInt("user_no"));  
				m.setUserId(rset.getString("user_id"));
				m.setUserPwd(rset.getString("user_pwd"));
				m.setUserName(rset.getString("user_name"));
				m.setGender(rset.getString("gender"));
				m.setEmail(rset.getString("email"));
				m.setAge(rset.getInt("age"));
				m.setPhone(rset.getString("phone"));
				m.setAddress(rset.getString("address"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrollDate(rset.getDate("enroll_date"));
				
				list.add(m);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
				
		return list;
	}
	
	// Controller에서 요청하는 회원정보 변경을 수행하는 메서드
	public int updateMember(Member m) {
		int result = 0;

		Connection conn = null;
		Statement  stmt = null;
		
		String sql = "UPDATE MEMBER "
				+       "SET USER_PWD = '" + m.getUserPwd() +"',"
				+       "    EMAIL = '" + m.getEmail() +"',"
				+       "    PHONE = '" + m.getPhone() +"',"
				+       "    ADDRESS = '" + m.getAddress() +"'"
				+     "WHERE USER_ID = '" + m.getUserId() +"'";
		
		System.out.println(sql);
				
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			
			if(result > 0) {  // 성공
				conn.commit();
			}else {           // 실패
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	public int deleteMember(String userId) {
		int result = 0;
		
		Connection conn = null;
		Statement stmt = null;
		
		String sql = "DELETE FROM MEMBER"
				+" WHERE USER_ID ='" + userId +"'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			
			if(result > 0) { //성공
				conn.commit();
			} else {
				conn.rollback();
			}
					
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
}