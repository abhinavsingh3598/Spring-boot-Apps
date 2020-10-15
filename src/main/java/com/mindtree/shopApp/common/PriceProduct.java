package com.mindtree.shopApp.common;

import java.util.List;

import com.mindtree.shopApp.modelDto.ProductDto;

public class PriceProduct {

	private double totalPrice;
	private List<ProductDto> ProductDto;
	public PriceProduct() {
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<ProductDto> getProductDto() {
		return ProductDto;
	}
	public void setProductDto(List<ProductDto> productDto) {
		ProductDto = productDto;
	}
	
}
