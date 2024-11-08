package com.eshopping.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshopping.dao.UserProfileRepository;
import com.eshopping.dto.AddressDTO;
import com.eshopping.dto.UserProfileDTO;
import com.eshopping.entity.Address;
import com.eshopping.entity.RoleEnum;
import com.eshopping.entity.UserProfile;
import com.eshopping.exception.ProfileNotFoundException;

@Service
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	private UserProfileRepository userProfileRepository;

	// Add a new Customer Profile
	@Override
	public UserProfileDTO addNewCustomerProfile(UserProfile userProfile) {
		userProfile.setRole(RoleEnum.CUSTOMER);
		userProfileRepository.save(userProfile);// Setting the role as CUSTOMER
		return convertEntityToDTO(userProfile); // Save and return the saved profile
	}

	// Add a new Merchant Profile
	@Override
	public void addNewMerchantProfile(UserProfile userProfile) {
		userProfile.setRole(RoleEnum.MERCHANT); // Setting the role as MERCHANT
		userProfileRepository.save(userProfile); // Save the profile
	}

	// Add a new Delivery Agent Profile
	@Override
	public void addNewDeliveryProfile(UserProfile userProfile) {
		userProfile.setRole(RoleEnum.DELIVERY_AGENT); // Setting the role as DELIVERY_AGENT
		userProfileRepository.save(userProfile); // Save the profile
	}

	// Get all User Profiles

	@Override
	public List<UserProfileDTO> getAllProfiles() {
		List<UserProfile> all = userProfileRepository.findAll();
		return convertToDTOList(all); // Return all profiles from the repository
	}

	// Get User Profile by Profile ID
	@Override
	public UserProfileDTO getByProfileId(int profileId) {
		Optional<UserProfile> byId = userProfileRepository.findById(profileId);

		if (byId.isPresent()) {

			UserProfile uProfile = byId.get();
			return convertEntityToDTO(uProfile);
		} else {
			throw new ProfileNotFoundException("Profile not found with id: " + profileId);
		}

	}

	// Update User Profile
	@Override
	public void updateProfile(UserProfile userProfile) {

		boolean profileExists = userProfileRepository.existsById(userProfile.getProfileId());

		if (!profileExists) {
			throw new ProfileNotFoundException("Profile not found with id: " + userProfile.getProfileId());
		}

		userProfileRepository.save(userProfile);
	}

	// Delete User Profile by Profile ID
	@Override
	public void deleteProfile(int profileId) {
		if (userProfileRepository.existsById(profileId)) {
			userProfileRepository.deleteById(profileId); // Delete the profile
		} else {
			throw new ProfileNotFoundException("Profile not found with id: " + profileId);
		}
	}

	@Override
	public UserProfileDTO findByMobileNo(Long mobileNumber) {
		UserProfile byMobileNumber = userProfileRepository.findByMobileNumber(mobileNumber);
		return convertEntityToDTO(byMobileNumber);
	}

	@Override
	public UserProfileDTO getByUserName(String userName) {
		UserProfile byFullName = userProfileRepository.findByFullName(userName);

		if (byFullName == null) { // If no profile is found with the given username
			throw new ProfileNotFoundException("Profile not found with username: " + userName);
		}

		return convertEntityToDTO(byFullName);
	}

	// ---------------- Mapping ----------------------

	public UserProfileDTO convertEntityToDTO(UserProfile userProfile) {
		if (userProfile == null) {
			return null;
		}

		UserProfileDTO dto = new UserProfileDTO();
		dto.setProfileId(userProfile.getProfileId());
		dto.setFullName(userProfile.getFullName());
		dto.setImage(userProfile.getImage());
		dto.setEmailId(userProfile.getEmailId());
		dto.setMobileNumber(userProfile.getMobileNumber());
		dto.setAbout(userProfile.getAbout());
		dto.setDateOfBirth(userProfile.getDateOfBirth());
		dto.setGender(userProfile.getGender());
		dto.setRole(userProfile.getRole());
		dto.setPassword(userProfile.getPassword()); // Typically, you would NOT send the password in DTO
		dto.setAddresses(mapAddresses(userProfile.getAddresses())); // Map address list if present

		return dto;
	}

	// Helper method to map List<Address> to List<AddressDTO>
	private List<AddressDTO> mapAddresses(List<Address> addresses) {
		if (addresses == null) {
			return null;
		}

		return addresses.stream().map(address -> {
			AddressDTO addressDTO = new AddressDTO();
			addressDTO.setStreet(address.getStreet());
			addressDTO.setCity(address.getCity());
			addressDTO.setState(address.getState());
			addressDTO.setCountry(address.getCountry());
			addressDTO.setZipCode(address.getZipCode());
			addressDTO.setAddressId(address.getAddressId());
//			addressDTO.setUserProfileId(address.getUserProfile().getProfileId());
			return addressDTO;
		}).collect(Collectors.toList());
	}

	public List<UserProfileDTO> convertToDTOList(List<UserProfile> userProfiles) {
		if (userProfiles == null) {
			return null;
		}

		return userProfiles.stream().map(this::convertEntityToDTO) // Convert each UserProfile entity to UserProfileDTO
				.collect(Collectors.toList());
	}

}
