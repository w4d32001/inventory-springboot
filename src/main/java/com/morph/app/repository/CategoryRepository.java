package com.morph.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.morph.app.entity.TCategory;

public interface CategoryRepository extends JpaRepository<TCategory, String> {}
