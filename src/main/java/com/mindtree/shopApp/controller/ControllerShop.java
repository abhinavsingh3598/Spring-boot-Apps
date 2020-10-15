package com.mindtree.shopApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.shopApp.common.ApiResponse;
import com.mindtree.shopApp.exception.ProductNotFoundException;
import com.mindtree.shopApp.exception.QuantityShouldNotNegative;
import com.mindtree.shopApp.exception.UserIdNotFound;
import com.mindtree.shopApp.modelDto.ProductDto;
import com.mindtree.shopApp.service.CartService;

@RestController
public class ControllerShop {
	@Autowired
	CartService cartService;

	@PostMapping("add/{userId}")
	public ResponseEntity<ApiResponse> addProductToCart(@RequestBody ProductDto productdto, @PathVariable int userId)
			throws UserIdNotFound {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setBody(cartService.addProductToCart(userId, productdto));
		apiResponse.setMessage("Product Added Sucessfully !!");
		apiResponse.setError(false);
		apiResponse.setStatus(HttpStatus.CREATED);
		return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);

	}

	@PutMapping("update/Quantity")
	public ResponseEntity<ApiResponse> updateTheQuantity(@RequestParam("userId") int userId,
			@RequestParam("quantity") int quantity, @RequestParam("productId") int productId)
			throws UserIdNotFound, QuantityShouldNotNegative, ProductNotFoundException {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setBody(cartService.updateTheQuantity(userId, quantity, productId));
		apiResponse.setMessage("Quantity updated Successfully !!");
		apiResponse.setError(false);
		apiResponse.setStatus(HttpStatus.FOUND);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	@GetMapping("get/totalAmount/{userId}")
	public ResponseEntity<ApiResponse> getUserTotalAmount(@PathVariable int userId) throws UserIdNotFound {

		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setBody(cartService.getUserTotalAmount(userId));
		apiResponse.setMessage("All detail of user With Total Amount: ");
		apiResponse.setError(false);
		apiResponse.setStatus(HttpStatus.OK);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

	}

	@GetMapping("get/productName/{productName}")
	public ResponseEntity<ApiResponse> getProduct(@PathVariable String productName) throws ProductNotFoundException {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setBody(cartService.getProduct(productName));
		apiResponse.setMessage("Product Values of " + productName);
		apiResponse.setError(false);
		apiResponse.setStatus(HttpStatus.OK);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

	}

	@GetMapping("get/productId/{productId}")
	public ResponseEntity<ApiResponse> getProductById(@PathVariable int productId) throws ProductNotFoundException {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setBody(cartService.getProductById(productId));
		apiResponse.setMessage("Product Values of " + productId);
		apiResponse.setError(false);
		apiResponse.setStatus(HttpStatus.OK);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

	}

	@GetMapping("get/productCategory/{productCategory}")
	public ResponseEntity<ApiResponse> getProductsByCategory(@PathVariable String productCategory)
			throws ProductNotFoundException {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setBody(cartService.getProductsByCategory(productCategory));
		apiResponse.setMessage("All Product of this Category " + productCategory + " are: ");
		apiResponse.setError(false);
		apiResponse.setStatus(HttpStatus.OK);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

	}

	@DeleteMapping("delete/{userId}")
	public ResponseEntity<ApiResponse> deleteEmployee(@RequestParam("productIds") List<Integer> productIds,
			@PathVariable int userId) throws UserIdNotFound, ProductNotFoundException {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setBody(cartService.deleteProduct(productIds, userId));
		apiResponse.setMessage("Deleted !! " + productIds);
		apiResponse.setError(false);
		apiResponse.setStatus(HttpStatus.NO_CONTENT);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

	}

}
