package com.mindtree.shopApp.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cart implements Comparable<Cart>{
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cartId;
	private String cartName;
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.REMOVE} ,mappedBy ="productCart" ,orphanRemoval = true)
	private List<Product> cartProducts;
	@OneToOne
	private User cartUser;

	public Cart(int cartId, String cartName, List<Product> cartProducts, User cartUser) {
		super();
		this.cartId = cartId;
		this.cartName = cartName;
		this.cartProducts = cartProducts;
		this.cartUser = cartUser;
	}

	public List<Product> getCartProducts() {
		return cartProducts;
	}

	public void setCartProducts(List<Product> cartProducts) {
		this.cartProducts = cartProducts;
	}

	public User getCartUser() {
		return cartUser;
	}

	public void setCartUser(User cartUser) {
		this.cartUser = cartUser;
	}

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

	public Cart() {
	}
	@Override
	public int hashCode() {
		return Objects.hash(cartId, cartName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		return cartId == other.cartId && Objects.equals(cartName, other.cartName);
	}

	@Override
	public int compareTo(Cart o) {
		return this.cartId-o.cartId;
	}
}
