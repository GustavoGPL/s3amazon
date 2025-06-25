package com.example.s3amazon.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.s3amazon.model.dto.UserRequest;
import com.example.s3amazon.model.dto.UserResponse;
import com.example.s3amazon.model.entity.User;
import com.example.s3amazon.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StorageFileService storageFileService;
	
	public UserResponse saveUser(UserRequest userRequest) {
		
		String profileImageUrl = storageFileService.uploadFile(userRequest.profileImageFile(), userRequest.name().trim().replaceAll("\\s+", " "), "profileImage");
		User user = new User(userRequest.name(), profileImageUrl);
		
		userRepository.save(user);
		
		return new UserResponse(user.getName(), profileImageUrl);
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public Optional<User> findById(UUID id) {
		return userRepository.findById(id);
	}
	
	public void delete(UUID id) {
		userRepository.deleteById(id);
	}
}
