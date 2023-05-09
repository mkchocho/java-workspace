package com.sy.view;


import java.util.ArrayList;
import java.util.Scanner;

import com.sy.controller.ProductController;
import com.sy.model.vo.Product;


public class MainMenu {

	private Scanner sc = new Scanner(System.in);
	private ProductController pd = new ProductController();
	
	public void mainMenu() {
		
		while(true) {
			System.out.println("\n=== 상품 관리 프로그램 ===");
			System.out.println("1. 상품 전체 조회");
			System.out.println("2. 상품 추가");
			System.out.println("3. 상품 수정");
			System.out.println("4. 상품 삭제");
			System.out.println("5. 상품 검색");
			System.out.println("0. 프로그램 종료");
			
			System.out.println(">> 메뉴 선택 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1 : pd.selectList();break;// Controll 패키지에 있는 클래스에 처리   
			case 2 : insertProduct();break;   
			case 3 : updateProduct();break;   
			case 4 : pd.deleteProduct(inputProductId());break;   // Controll 패키지에 있는 클래스에 처리
			case 5 : pd.selectByPname(inputProductId());break;   // Controll 패키지에 있는 클래스에 처리
			case 0: System.out.println("이용해 주셔서 감사합니다."); return;
			default : System.out.println("메뉴를 잘못입력했습니다. 다시 입력해주세요.");
			
			}
		}
	}
	
	public void insertProduct() {
		System.out.println("\n== 상품 추가 ==");
		
		System.out.println("상품아이디 : ");
		String productId = sc.nextLine();
		
		System.out.println("상품이름 : ");
		String pName = sc.nextLine();
		
		System.out.println("가격 : ");
		String price = sc.nextLine();
		
		System.out.println("부가설명 : ");
		String description = sc.nextLine();

		System.out.println("재고 : ");
		String stock = sc.nextLine();
		
		pd.insertProduct(productId,pName,price,description,stock);
	}
	
	public String inputProductName() {
		System.out.println("\n상품 이름 입력 : ");
		return sc.nextLine();
	}
	
	public String inputProductId() {
		System.out.println("\n상품 아이디 입력 : ");
		return sc.nextLine();
	}
	
	public void updateProduct() {
		System.out.println("\n== 상품 정보 변경 ==");
		
		String productId = inputProductId();
		
		System.out.println("변경할 가격 : ");
		String price = sc.nextLine();
		
		System.out.println("번경할 설명 : ");
		String description = sc.nextLine();

	
		pd.updateProduct(productId,price,description);
	}
	


	//----------------------응답화면-------------------------------------
	// 서비스 요청 처리후 성공했을 때 사용자가 보게될 화면
	public void displaySuccess(String message) {
		System.out.println("\n서비스 요청 성공 : " + message);
	}
	
	// 서비스 요청 처리 실패했을때 사용자가 보게될 화면
	public void displayFail(String message) {
		System.out.println("\n서비스 요청 실패 : " + message);
	}
	
	// 조회 서비스 요청 처리 후 조회결과가 없을 경우 사용자가 보게 될 화면
	public void displayNoData(String message) {
		System.out.println("\n" + message);
	}
	
	// 조회 서비스 요청 처리 후 조회결과가 여러개일 경우 사용자가 보게될 화면
	public void displayMemberList(ArrayList<Product> list) {
		System.out.println("\n조회된 데이터는 다음과 같습니다.\n");
		
		for(Product m: list) {
			System.out.println(m);
		}
	}
	
	public void displayMember(Product m) {
		System.out.println("\n조회된 데이터는 다음과 같습니다.");
		System.out.println(m);
	}
	
}