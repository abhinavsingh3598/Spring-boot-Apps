package com.mindtree.shopApp.service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.shopApp.common.PriceProduct;
import com.mindtree.shopApp.exception.ProductNotFoundException;
import com.mindtree.shopApp.exception.QuantityShouldNotNegative;
import com.mindtree.shopApp.exception.UserIdNotFound;
import com.mindtree.shopApp.model.Cart;
import com.mindtree.shopApp.model.Product;
import com.mindtree.shopApp.modelDto.ProductDto;
import com.mindtree.shopApp.repository.CartRepository;
import com.mindtree.shopApp.repository.ProductRepository;
import com.mindtree.shopApp.repository.UserRepository;
import com.mindtree.shopApp.service.CartService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartRepository cartRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ProductRepository productRepository;
	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public String addProductToCart(int userId, ProductDto productdto) throws UserIdNotFound {
		Product product = convertDtoToEntity(productdto);
		Cart cart = userRepository.findById(userId).orElseThrow(() -> new UserIdNotFound("id not found")).getUserCart();
		int count = 0;
		for (int i = 0; i < cart.getCartProducts().size(); i++) {
			if (product.getProductName().equals(cart.getCartProducts().get(i).getProductName())) {
				count = 1;
				cart.getCartProducts().get(i)
						.setProductquantity(cart.getCartProducts().get(i).getProductquantity() + 1);
			}
		}
		if (count == 0) {
			product.setProductquantity(1);
			cart.getCartProducts().add(product);
		}
		product.setProductCart(cart);
		cartRepository.saveAndFlush(cart);
		return "saved successfully";
	}

	@Override
	public ProductDto getProduct(String productName) throws ProductNotFoundException {
		Product product = productRepository.getProductByName(productName);

		if (product == null) {
			throw new ProductNotFoundException("Product with the given name is not available");
		}

		return convertEntityToDto(product);

	}

	@Override
	public ProductDto getProductById(int productId) throws ProductNotFoundException {
		if (!(productRepository.findById(productId).isPresent())) {
			throw new ProductNotFoundException("Product by this Id doesnt exist");
		}
		return convertEntityToDto(productRepository.findById(productId).get());

	}

	@Override
	public List<ProductDto> getProductsByCategory(String productCategory) throws ProductNotFoundException {

		if (productRepository.findByproductCategory(productCategory).size() == 0) {
			throw new ProductNotFoundException("Product by this Category not found");
		}
		return productRepository.findAll().stream().filter(p -> p.getProductCategory().equals(productCategory))
				.map(mapper -> convertEntityToDto(mapper)).collect(Collectors.toList());

	}

	@Override
	public PriceProduct getUserTotalAmount(int userId) throws UserIdNotFound {
		PriceProduct priceproduct = new PriceProduct();
		Cart cart = userRepository.findById(userId).orElseThrow(() -> new UserIdNotFound("id not found")).getUserCart();
		priceproduct
				.setTotalPrice(cart.getCartProducts().stream().map(p -> p.getProductPrice() * p.getProductquantity())
						.reduce(0.0, (element1, element2) -> element1 + element2));
		priceproduct.setProductDto(
				cart.getCartProducts().stream().map(i -> convertEntityToDto(i)).collect(Collectors.toList()));
		return priceproduct;
	}

	@Override
	public String updateTheQuantity(int userId, int quantity, int productId)
			throws UserIdNotFound, QuantityShouldNotNegative, ProductNotFoundException {
		int flag = 0;
		if (quantity < 0) {
			throw new QuantityShouldNotNegative("product quantity cant be set as negative");
		}
		Cart cart = userRepository.findById(userId).orElseThrow(() -> new UserIdNotFound("id not found")).getUserCart();

		if (quantity == 0) {

			productRepository.deleteQuantity(productId);

		} else {
			flag = productRepository.updateQuantity(productId, quantity);
		}

		if (flag != 1) {
			throw new ProductNotFoundException("Sorry!!! This product is not in this user cart");
		}
		cartRepository.saveAndFlush(cart);
		return "updated successfully";
	}

	@Override
	public String deleteProduct(List<Integer> productIds, int userId) throws UserIdNotFound, ProductNotFoundException {
		Cart cart = userRepository.findById(userId).orElseThrow(() -> new UserIdNotFound("id not found")).getUserCart();

		for (Integer id : productIds) {
			int count = 0;
			for (Product p : cart.getCartProducts()) {
				if (p.getProductId() == id) {
					count = productRepository.deleteQuantity(id);
				}
			}
			if (count != 1) {
				throw new ProductNotFoundException("Sorry!!! This product is not in this user cart");
			}

		}

		return "successFully deleted";
	}

	public Product convertDtoToEntity(ProductDto productdto) {

		return modelMapper.map(productdto, Product.class);
	}

	public ProductDto convertEntityToDto(Product product) {

		return modelMapper.map(product, ProductDto.class);
	}

}
