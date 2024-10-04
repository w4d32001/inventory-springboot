package com.morph.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.morph.app.entity.TUser;

public interface PersonRepository extends JpaRepository<TUser, String> {}
