package com.example.PS40753_TranHoang_SD19308.repository;

import com.example.PS40753_TranHoang_SD19308.entity.Product;
import com.example.PS40753_TranHoang_SD19308.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
