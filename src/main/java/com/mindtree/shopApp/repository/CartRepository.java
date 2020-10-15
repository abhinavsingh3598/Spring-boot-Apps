package com.mindtree.shopApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.shopApp.model.Cart;
@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

}
