package com.example.s3amazon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.s3amazon.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
