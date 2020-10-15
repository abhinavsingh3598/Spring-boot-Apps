package com.mindtree.shopApp.modelDto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ProductDto {

	private int productId;
	private String productName;
	private double productPrice;
	private String productCategory;
	private int productquantity;
	@JsonIgnore
	private CartDto productCart;
	
	public ProductDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductDto(int productId, String productName, double productPrice, String productCategory,
			int productquantity, CartDto productCart) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productCategory = productCategory;
		this.productquantity = productquantity;
		this.productCart = productCart;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public int getProductquantity() {
		return productquantity;
	}
	public void setProductquantity(int productquantity) {
		this.productquantity = productquantity;
	}
	public CartDto getProductCart() {
		return productCart;
	}
	public void setProductCart(CartDto productCart) {
		this.productCart = productCart;
	}
	
	
	
}
