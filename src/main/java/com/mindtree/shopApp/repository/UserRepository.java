package com.mindtree.shopApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.shopApp.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
