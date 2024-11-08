package com.eshopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshopping.dto.UserProfileDTO;
import com.eshopping.entity.UserProfile;
import com.eshopping.service.UserProfileServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/userprofiles") // Base URL for user profiles
public class UserProfileController {

	@Autowired
	private UserProfileServiceImpl userProfileService;

	// Endpoint to add a new Customer Profile

	@Operation(summary = "Create a new user profile")
	@PostMapping("/customer")
	public ResponseEntity<UserProfileDTO> addNewCustomerProfile(@Valid @RequestBody UserProfile userProfile) {
		UserProfileDTO createdProfile = userProfileService.addNewCustomerProfile(userProfile);
		return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
	}

	// Endpoint to add a new Merchant Profile

	@Operation(summary = "Add a new merchant profile ")
	@PostMapping("/merchant")
	public ResponseEntity<Void> addNewMerchantProfile(@Valid @RequestBody UserProfile userProfile) {
		userProfileService.addNewMerchantProfile(userProfile);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	// Endpoint to add a new Delivery Agent Profile
	@Operation(summary = "Add a new user profile")
	@PostMapping("/delivery-agent")
	public ResponseEntity<Void> addNewDeliveryProfile(@Valid @RequestBody UserProfile userProfile) {
		userProfileService.addNewDeliveryProfile(userProfile);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	// get all User Profiles

	@Operation(summary = "Get all user profiles")
	@GetMapping("/all")
	public ResponseEntity<List<UserProfileDTO>> getAllProfiles() {
		List<UserProfileDTO> userProfiles = userProfileService.getAllProfiles();
		return new ResponseEntity<>(userProfiles, HttpStatus.OK);
	}

	// Endpoint to get User Profile by Profile ID

	@Operation(summary = "Get user profile byId")
	@GetMapping("/{profileId}")
	public ResponseEntity<UserProfileDTO> getUserProfileById(@PathVariable("profileId") int profileId) {
		UserProfileDTO userProfile = userProfileService.getByProfileId(profileId);
		return ResponseEntity.ok(userProfile);
	}

	// Endpoint to update User Profile

	@Operation(summary = "Update user profile byId")
	@PutMapping("/{profileId}")
	public ResponseEntity<Void> updateProfile(@Valid @PathVariable("profileId") int profileId,
			@RequestBody UserProfile userProfile) {
		userProfile.setProfileId(profileId);
		userProfileService.updateProfile(userProfile);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// Endpoint to delete User Profile by Profile ID

	@Operation(summary = "Delete user profile byId")
	@DeleteMapping("/{profileId}")
	public ResponseEntity<Void> deleteProfile(@PathVariable("profileId") int profileId) {
		userProfileService.deleteProfile(profileId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// Endpoint to find User Profile by mobile number
	@Operation(summary = "Find user profile ById")
	@GetMapping("/mobile/{mobileNumber}")
	public ResponseEntity<UserProfileDTO> findByMobileNo(@PathVariable("mobileNumber") Long mobileNumber) {
		UserProfileDTO userProfile = userProfileService.findByMobileNo(mobileNumber);
		return new ResponseEntity<>(userProfile, HttpStatus.OK);
	}

	// Endpoint to get User Profile by username (full name)
	@Operation(summary = "Find user profile ByName")
	@GetMapping("/username/{userName}")
	public ResponseEntity<UserProfileDTO> getByUserName(@PathVariable("userName") String userName) {
		UserProfileDTO userProfile = userProfileService.getByUserName(userName);
		return new ResponseEntity<>(userProfile, HttpStatus.OK);
	}

}
