package com.mindtree.shopApp.modelDto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CartDto {
	private int cartId;
	private String cartName;
	@JsonIgnore
	private List<ProductDto> cartProducts;
	private UserDto cartUser;
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public String getCartName() {
		return cartName;
	}
	public void setCartName(String cartName) {
		this.cartName = cartName;
	}
	public List<ProductDto> getCartProducts() {
		return cartProducts;
	}
	public void setCartProducts(List<ProductDto> cartProducts) {
		this.cartProducts = cartProducts;
	}
	public UserDto getCartUser() {
		return cartUser;
	}
	public void setCartUser(UserDto cartUser) {
		this.cartUser = cartUser;
	}

}
