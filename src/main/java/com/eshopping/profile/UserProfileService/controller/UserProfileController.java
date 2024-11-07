package com.eshopping.profile.UserProfileService.controller;

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

import com.eshopping.profile.UserProfileService.dto.UserProfileDTO;
import com.eshopping.profile.UserProfileService.entity.UserProfile;
import com.eshopping.profile.UserProfileService.service.UserProfileServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/userprofiles") // Base URL for user profiles
public class UserProfileController {

	@Autowired
	private UserProfileServiceImpl userProfileService;

	// Endpoint to add a new Customer Profile

	@PostMapping("/customer")
	public ResponseEntity<UserProfileDTO> addNewCustomerProfile(@Valid @RequestBody UserProfile userProfile) {
		UserProfileDTO createdProfile = userProfileService.addNewCustomerProfile(userProfile);
		return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
	}

	// Endpoint to add a new Merchant Profile

	@PostMapping("/merchant")
	public ResponseEntity<Void> addNewMerchantProfile(@Valid @RequestBody UserProfile userProfile) {
		userProfileService.addNewMerchantProfile(userProfile);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	// Endpoint to add a new Delivery Agent Profile
	@PostMapping("/delivery-agent")
	public ResponseEntity<Void> addNewDeliveryProfile(@Valid @RequestBody UserProfile userProfile) {
		userProfileService.addNewDeliveryProfile(userProfile);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	// get all User Profiles

	@GetMapping("/all")
	public ResponseEntity<List<UserProfileDTO>> getAllProfiles() {
		List<UserProfileDTO> userProfiles = userProfileService.getAllProfiles();
		return new ResponseEntity<>(userProfiles, HttpStatus.OK);
	}

	// Endpoint to get User Profile by Profile ID
	@GetMapping("/{profileId}")
	public ResponseEntity<UserProfileDTO> getUserProfileById(@PathVariable("profileId") int profileId) {
		UserProfileDTO userProfile = userProfileService.getByProfileId(profileId);
		return ResponseEntity.ok(userProfile);
	}

	// Endpoint to update User Profile
	@PutMapping("/{profileId}")
	public ResponseEntity<Void> updateProfile(@Valid @PathVariable("profileId") int profileId,
			@RequestBody UserProfile userProfile) {
		userProfile.setProfileId(profileId);
		userProfileService.updateProfile(userProfile);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// Endpoint to delete User Profile by Profile ID
	@DeleteMapping("/{profileId}")
	public ResponseEntity<Void> deleteProfile(@PathVariable("profileId") int profileId) {
		userProfileService.deleteProfile(profileId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// Endpoint to find User Profile by mobile number
	@GetMapping("/mobile/{mobileNumber}")
	public ResponseEntity<UserProfileDTO> findByMobileNo(@PathVariable("mobileNumber") Long mobileNumber) {
		UserProfileDTO userProfile = userProfileService.findByMobileNo(mobileNumber);
		return new ResponseEntity<>(userProfile, HttpStatus.OK);
	}

	// Endpoint to get User Profile by username (full name)
	@GetMapping("/username/{userName}")
	public ResponseEntity<UserProfileDTO> getByUserName(@PathVariable("userName") String userName) {
		UserProfileDTO userProfile = userProfileService.getByUserName(userName);
		return new ResponseEntity<>(userProfile, HttpStatus.OK);
	}

}
