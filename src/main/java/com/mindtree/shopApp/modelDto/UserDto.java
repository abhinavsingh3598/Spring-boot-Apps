package com.mindtree.shopApp.modelDto;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;

import com.mindtree.shopApp.model.Cart;

public class UserDto {

	private int userId;
	private String userName;
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "cartUser")
	private Cart userCart;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Cart getUserCart() {
		return userCart;
	}
	public void setUserCart(Cart userCart) {
		this.userCart = userCart;
	}

}
