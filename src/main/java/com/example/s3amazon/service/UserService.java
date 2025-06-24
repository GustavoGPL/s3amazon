package com.example.s3amazon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.s3amazon.model.dto.UserRequest;
import com.example.s3amazon.model.entity.User;
import com.example.s3amazon.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StorageFileService storageFileService;
	
	public String saveUser(UserRequest userRequest) {
		
		String profileImageUrl = storageFileService.uploadFile(userRequest.profileImageFile(), userRequest.name(), "profileImage");
		User user = new User(userRequest.name(), profileImageUrl);
		
		userRepository.save(user);
		
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("user Name" + userRequest.name());
		stringBuilder.append("user profile" + profileImageUrl);
		
		
		return stringBuilder.toString();
	}
}
