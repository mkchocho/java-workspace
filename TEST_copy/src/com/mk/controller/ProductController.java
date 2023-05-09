package com.mk.controller;

import java.util.ArrayList;

import com.mk.model.dao.ProductDao;
import com.mk.model.vo.Product;
import com.mk.view.MainMenu;

public class ProductController {


	
	//상품 추가
	public void insertProduct(String productId,String pName,String price,String description,String stock) {
		
		Product p = new Product(productId,pName, Integer.parseInt(price), description, Integer.parseInt(stock));
		
		int result = new ProductDao().insertProduct(p);
		
		if(result > 0) {
			new MainMenu().displaySuccess("성공적으로 추가되었습니다.");
		}else {         
			new MainMenu().displayFail("추가에 실패했습니다.");
		}
	}
	
	 //상품전체를 조회
	public void selectList() {
		ArrayList<Product> list = new ProductDao().selectList();
		
		if(list.isEmpty()) {
			new MainMenu().displayNoData("조회 결과 데이터가 없습니다.");
		}else {
			new MainMenu().displayMemberList(list);
		}
		
	}
	
	// 상품명으로 검색
	public void selectByPname(String pName) {
		Product p = new ProductDao().selectByPname(pName);
		
		if(p == null) {
			new MainMenu().displayNoData(pName + "에 대한 조회결과가 없습니다.");
		}else {
			new MainMenu().displayMember(p);
		}
	}
	

	
	// 상품 정보(가격,설명) 변경 
	public void updateProduct(String productId,String price,String description) {
		Product p = new Product();
		p.setProductId(productId);
		p.setPrice(Integer.parseInt(price));
		p.setDescription(description);
		
		int result = new ProductDao().updateProduct(p);
		
		if(result > 0 ) { //성공
			new MainMenu().displaySuccess("성공적으로 변경되었습니다.");
		}else { //실패
			new MainMenu().displayFail("변경에 실패했습니다.");
		}
	}
	
	// 상품 삭제
	public void deleteProduct(String productId) {
		int result = new ProductDao().deleteProduct(productId);
		
		if(result > 0) { //성공
			new MainMenu().displaySuccess("정보를 성공적으로 삭제했습니다.");
		}else {
			new MainMenu().displayFail("정보를 삭제하는데 실패했습니다.");
		}
	}
	
	
}