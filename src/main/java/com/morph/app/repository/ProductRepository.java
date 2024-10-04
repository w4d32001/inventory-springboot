package com.morph.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.morph.app.entity.TProduct;

public interface ProductRepository extends JpaRepository<TProduct, String> {}
