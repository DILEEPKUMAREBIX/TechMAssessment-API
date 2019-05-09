package com.techm.assessment.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.techm.assessment.ResourceNotFoundException;
import com.techm.assessment.dao.ProfileRepository;
import com.techm.assessment.model.UserProfile;
import com.techm.assessment.service.StorageService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
@ComponentScan(basePackages = { "com.example.easynotes.controller" })
public class ProfileController {

	@Autowired
	ProfileRepository profileRepository;

	@Autowired
	StorageService storageService;

	// Get All Notes
	@GetMapping("/users")
	public List<UserProfile> getAllUsers() {

		return profileRepository.findAll();
	}

	// Create a new Note
	@PostMapping("/user")
	public UserProfile createUser(@Valid @RequestBody UserProfile user) {
		return profileRepository.save(user);
	}

	// Get a Single Note
	@GetMapping("/user/{id}")
	public UserProfile getUserById(@PathVariable(value = "id") Long id) {
		return profileRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("user profile", "id", id));
	}

	// Update a Note
//	@PutMapping("user")
//	public UserProfile updateUser(@Valid @RequestBody UserProfile userDetails) {
//		UserProfile user = profileRepository.findById(userDetails.getId())
//				.orElseThrow(() -> new ResourceNotFoundException("user profile", "id", userDetails.getId()));
//		user.setFirstName(userDetails.getFirstName());
//		user.setLastName(userDetails.getLastName());
//		user.setPhone(userDetails.getPhone());
//		user.setEmail(userDetails.getEmail());
//		user.setAddress(userDetails.getAddress());
//
//		UserProfile updatedUser = profileRepository.save(user);
//		return updatedUser;
//	}

	// Delete a Note
	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId) {
		UserProfile user = profileRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user profile", "id", userId));

		profileRepository.delete(user);

		return ResponseEntity.ok().build();
	}

	@PutMapping("user")
	public ResponseEntity<String> updateUser(@RequestPart("profileImage") MultipartFile profileImage,
			@RequestPart("user") String userString) throws JsonParseException, JsonMappingException, IOException {
		String message = "";
		try {
			UserProfile userProfile = new ObjectMapper().readValue(userString, UserProfile.class);
			storageService.store(profileImage);
			Resource resource = storageService.loadFile(profileImage.getOriginalFilename());

			// profileRepository.save(user);

			UserProfile user = profileRepository.findById(userProfile.getId())
					.orElseThrow(() -> new ResourceNotFoundException("user profile", "id", userProfile.getId()));
			user.setFirstName(userProfile.getFirstName());
			user.setLastName(userProfile.getLastName());
			user.setPhone(userProfile.getPhone());
			user.setEmail(userProfile.getEmail());
			user.setAddress(userProfile.getAddress());
			user.setFilepath(resource.getURL().getFile().toString());

			profileRepository.save(user);
			message = "You successfully Updated user and uploaded " + profileImage.getOriginalFilename() + "!";

			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = "FAIL to upload " + profileImage.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}

	}
}