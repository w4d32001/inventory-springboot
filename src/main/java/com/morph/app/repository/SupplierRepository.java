package com.morph.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.morph.app.entity.TSupplier;

public interface SupplierRepository extends JpaRepository<TSupplier, String> {}
