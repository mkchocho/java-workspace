package com.sy.model.vo;

import java.sql.Date;

public class Product {

	private String productId;
	private String pName;
	private int price;
	private String description;
	private int stock;
	

	public Product() {}



	public Product(String productId, String pName, int price, String description, int stock) {
		super();
		this.productId = productId;
		this.pName = pName;
		this.price = price;
		this.description = description;
		this.stock = stock;
	}
	public Product(String productId, int price, String description) {
		super();
		this.productId = productId;
		this.price = price;
		this.description = description;
	}


	public String getProductId() {
		return productId;
	}



	public void setProductId(String productId) {
		this.productId = productId;
	}



	public String getpName() {
		return pName;
	}



	public void setpName(String pName) {
		this.pName = pName;
	}



	public int getPrice() {
		return price;
	}



	public void setPrice(int price) {
		this.price = price;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public int getStock() {
		return stock;
	}



	public void setStock(int stock) {
		this.stock = stock;
	}



	@Override
	public String toString() {
		return "Product [productId=" + productId + ", pName=" + pName + ", price=" + price + ", description="
				+ description + ", stock=" + stock + "]";
	}


	
	
	
}