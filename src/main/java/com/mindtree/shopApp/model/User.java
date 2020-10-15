package com.mindtree.shopApp.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class User implements Comparable<User>{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	private String userName;
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "cartUser")
	private Cart userCart;

	public User() {
	}

	public User(int userId, String userName, Cart userCart) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userCart = userCart;
	}

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

	@Override
	public int hashCode() {
		return Objects.hash(userId, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return userId == other.userId && Objects.equals(userName, other.userName);
	}

	@Override
	public int compareTo(User o) {
		return this.userId-o.userId;
	}

}
