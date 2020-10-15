package com.mindtree.shopApp.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Product implements Comparable<Product>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int productId;
	private String productName;
	private double productPrice;
	private String productCategory;
	private int productquantity;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Cart productCart;

	public Product() {
	}

	public Product(int productId, String productName, double productPrice, String productCategory, int productquantity,
			Cart productCart) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productCategory = productCategory;
		this.productquantity = productquantity;
		this.productCart = productCart;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
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

	public Cart getProductCart() {
		return productCart;
	}

	public void setProductCart(Cart productCart) {
		this.productCart = productCart;
	}

	public int getProductquantity() {
		return productquantity;
	}

	public void setProductquantity(int productquantity) {
		this.productquantity = productquantity;
	}
	@Override
	public int hashCode() {
		return Objects.hash(productCategory, productId, productName, productPrice, productquantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(productCategory, other.productCategory) && productId == other.productId
				&& Objects.equals(productName, other.productName)
				&& Double.doubleToLongBits(productPrice) == Double.doubleToLongBits(other.productPrice)
				&& productquantity == other.productquantity;
	}

	@Override
	public int compareTo(Product o) {
		return this.productId-o.productId;
	}

}
