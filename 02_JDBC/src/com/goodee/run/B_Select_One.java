package com.goodee.run;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.goodee.model.vo.Test;

public class B_Select_One {
	public static void main(String[] args) {
			/*
			 * Scanner sc = new Scanner(System.in);
			 * 
			 * System.out.print("조회할 번호를 입력하세요 : ");
			 * int no = sc.nextInt();
			 * 
			 * String sql = "SELECT TNO, TNAME, TDATE FROM TEST WHERE TNO = " + no;
			 */

	Test t = null;

	//JDBC에 사용될 객체 생성 및 초기회
	Connection conn = null;
	Statement stmt = null;
	ResultSet rset = null;
	
	Scanner sc = new Scanner(System.in);
	System.out.print("조회할 번호를 입력하세요 : ");
	
	int no = sc.nextInt();

	//실행 할 SQL문
	String sql = "SELECT TNO, TNAME, TDATE FROM TEST WHERE TNO = " + no;

	try {
		//1)JDBC  드라이버 등록
		Class.forName("oracle.jdbc.driver.OracleDriver");

		//2)Connection 객체 생성
		conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");

		//3)Statement 객체 생성
		stmt = conn.createStatement();

		//4),5) SQL문을 stmt를 이용해서 전달하고 실행 후 결과값을 ResultSet 객체 받음
		rset = stmt.executeQuery(sql);

		//6)ResultSet 객체에 담긴 데이터 추출

																	//while(rset.next()) {   --한행에 대하여 실행할 것이기 때문에 while문 필요 없음
		if(rset.next()) {
		
			// vo클래스인 Test클래스의 객체 생성. 이 객체에 데이터를 담을 예정 (setter이용).
			//변수 3개 데이터타입 t하나로 묶어서 넣음
			t = new Test(rset.getInt("tno"), rset.getString("tname"), rset.getDate("tdate") );
			
															//t.setTestNo(rset.getInt("tno")); 
															//t.setTestName(rset.getString("tname"));
															//t.setTestDate(rset.getDate("tdate"));
		}
																	//}

	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		//7) 객체 반환
		try {
			rset.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 조회된 결과 출력
	if (t == null) {
		System.out.println("조회된 데이터가 없습니다.");
	}else {
			System.out.println(t);
	}

	}

}
