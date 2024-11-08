package com.eshopping.service;

import java.util.List;

import com.eshopping.dto.UserProfileDTO;
import com.eshopping.entity.UserProfile;

public interface UserProfileService {

	// Add a new Customer profile
	UserProfileDTO addNewCustomerProfile(UserProfile userProfile);

	// Get all user profiles
	List<UserProfileDTO> getAllProfiles();

	// Get a user profile by profile ID
	UserProfileDTO getByProfileId(int profileId);

	// Update a user profile
	void updateProfile(UserProfile userProfile);

	// Delete a user profile by profile ID
	void deleteProfile(int profileId);

	// Add a new Merchant profile
	void addNewMerchantProfile(UserProfile userProfile);

	// Add a new Delivery Agent profile
	void addNewDeliveryProfile(UserProfile userProfile);

	// Find a user profile by mobile number
	UserProfileDTO findByMobileNo(Long mobileNumber);

	// Get a user profile by username (email)
	UserProfileDTO getByUserName(String userName);
}
