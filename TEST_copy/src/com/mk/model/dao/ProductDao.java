package com.mk.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mk.model.vo.Product;

public class ProductDao {

	public int insertProduct(Connection conn, Product p) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO PRODUCT VALUES(?,?,?,?,?)";		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","DBADMIN","java1234");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, p.getProductId());
			pstmt.setString(2, p.getpName());
			pstmt.setInt(3, p.getPrice());
			pstmt.setString(4, p.getDescription());
			pstmt.setInt(5, p.getStock());

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
	
	//전체회원 조회
	public ArrayList<Product> selectList() {
		ArrayList<Product> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement  pstmt = null;
		ResultSet  rset = null;
		
		String sql = "SELECT * FROM PRODUCT";
	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","DBADMIN","java1234");
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Product p = new Product();
				
				// rset에서 user_no컬럼을 읽어서 m객체의 setter(setUserNo)를 이용하여 입력
				p.setProductId(rset.getString("product_id"));  
				p.setpName(rset.getString("p_name"));
				p.setPrice(rset.getInt("price"));
				p.setDescription(rset.getString("description"));
				p.setStock(rset.getInt("stock"));
				
				
				list.add(p);
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
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return list;
	}
	
	// 상품명으로 검색
	public Product selectByPname(String pName) {
		Product p = null;
		
		Connection conn = null;
		PreparedStatement  pstmt = null;
		ResultSet  rset = null;
		
		String sql = "SELECT * FROM PRODUCT WHERE P_NAME = ?";
	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","DBADMIN","java1234");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, pName);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				p = new Product();
				
			
				p.setProductId(rset.getString("product_id"));  
				p.setpName(rset.getString("p_name"));
				p.setPrice(rset.getInt("price"));
				p.setDescription(rset.getString("description"));
				p.setStock(rset.getInt("stock"));
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
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return p;
	}	
	

	
	//Controller에서 회원정보 변경을 수행
	public int updateProduct(Product p) {
		int result = 0;

		Connection conn = null;
		PreparedStatement  pstmt = null;
		
		String sql = "UPDATE PRODUCT SET PRICE = ?,"
				+       "    DESCRIPTION = ?"
				+     "WHERE PRODUCT_ID = ?";
		
				
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","DBADMIN","java1234");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, p.getPrice());
			pstmt.setString(2, p.getDescription());
			pstmt.setString(3, p.getProductId());
			
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
	
	//Controller에서 회원정보 삭제 작업을 수행
	public int deleteProduct(String productId)  {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement  pstmt = null;
		
		String sql = "DELETE FROM PRODUCT WHERE PRODUCT_ID = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","DBADMIN","java1234");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, productId);
			result = pstmt.executeUpdate();
			
			if(result > 0) { //성공
				conn.commit();
			}else {
				conn.rollback();
			}
			
		} catch (Exception e) {
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
	
	
}