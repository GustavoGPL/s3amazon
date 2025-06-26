package com.example.s3amazon.model.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "users")
@Entity
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	private String name;

	private String email;

	private String profileImageUrl;

	public User(String name, String email, String profileImageUrl) {
		super();
		this.name = name;
		this.email = email;
		this.profileImageUrl = profileImageUrl;
	}

	public User() {

	}
}
