package com.eshopping.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.eshopping.dao.UserProfileRepository;
import com.eshopping.dto.UserProfileDTO;
import com.eshopping.entity.UserProfile;
import com.eshopping.exception.ProfileNotFoundException;

@SpringBootTest
public class UserProfileServiceImplTest {

	@Mock
	private UserProfileRepository userProfileRepository;

	@InjectMocks
	private UserProfileServiceImpl userProfileService;

	private UserProfile userProfile;
	private UserProfileDTO userProfileDTO;

	@BeforeEach
	public void setUp() {
		userProfile = new UserProfile();
		userProfile.setProfileId(1);
		userProfile.setFullName("John Doe");
		userProfile.setEmailId("john.doe@example.com");
		userProfile.setMobileNumber(1234567890L);

		userProfileDTO = new UserProfileDTO();
		userProfileDTO.setProfileId(1);
		userProfileDTO.setFullName("John Doe");
		userProfileDTO.setEmailId("john.doe@example.com");
		userProfileDTO.setMobileNumber(1234567890L);
	}

	@Test
	public void testAddNewCustomerProfile() {
		when(userProfileRepository.save(any(UserProfile.class))).thenReturn(userProfile);

		UserProfileDTO result = userProfileService.addNewCustomerProfile(userProfile);

		assertNotNull(result);
		assertEquals("John Doe", result.getFullName());
		assertEquals("john.doe@example.com", result.getEmailId());
	}

	@Test
	public void testAddNewMerchantProfile() {
		when(userProfileRepository.save(any(UserProfile.class))).thenReturn(userProfile);

		userProfileService.addNewMerchantProfile(userProfile);

		verify(userProfileRepository, times(1)).save(userProfile);
	}

	@Test
	public void testAddNewDeliveryProfile() {
		when(userProfileRepository.save(any(UserProfile.class))).thenReturn(userProfile);

		userProfileService.addNewDeliveryProfile(userProfile);

		verify(userProfileRepository, times(1)).save(userProfile);
	}

	@Test
	public void testGetAllProfiles() {
		ArrayList<UserProfile> userProfiles = new ArrayList<>();
		userProfiles.add(userProfile);
		when(userProfileRepository.findAll()).thenReturn(userProfiles);

		var result = userProfileService.getAllProfiles();

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals("John Doe", result.get(0).getFullName());
	}

	@Test
	public void testGetByProfileId() {
		when(userProfileRepository.findById(1)).thenReturn(Optional.of(userProfile));

		UserProfileDTO result = userProfileService.getByProfileId(1);

		assertNotNull(result);
		assertEquals(1, result.getProfileId());
		assertEquals("John Doe", result.getFullName());
	}

	@Test
	public void testGetByProfileIdNotFound() {
		when(userProfileRepository.findById(1)).thenReturn(Optional.empty());

		ProfileNotFoundException thrown = assertThrows(ProfileNotFoundException.class, () -> {
			userProfileService.getByProfileId(1);
		});

		assertEquals("Profile not found with id: 1", thrown.getMessage());
	}

	@Test
	public void testUpdateProfile() {
		when(userProfileRepository.existsById(1)).thenReturn(true);
		when(userProfileRepository.save(any(UserProfile.class))).thenReturn(userProfile);

		userProfileService.updateProfile(userProfile);

		verify(userProfileRepository, times(1)).save(userProfile);
	}

	@Test
	public void testUpdateProfileNotFound() {
		when(userProfileRepository.existsById(1)).thenReturn(false);

		ProfileNotFoundException thrown = assertThrows(ProfileNotFoundException.class, () -> {
			userProfileService.updateProfile(userProfile);
		});

		assertEquals("Profile not found with id: 1", thrown.getMessage());
	}

	@Test
	public void testDeleteProfile() {
		when(userProfileRepository.existsById(1)).thenReturn(true);

		userProfileService.deleteProfile(1);

		verify(userProfileRepository, times(1)).deleteById(1);
	}

	@Test
	public void testDeleteProfileNotFound() {
		when(userProfileRepository.existsById(1)).thenReturn(false);

		ProfileNotFoundException thrown = assertThrows(ProfileNotFoundException.class, () -> {
			userProfileService.deleteProfile(1);
		});

		assertEquals("Profile not found with id: 1", thrown.getMessage());
	}

	@Test
	public void testFindByMobileNo() {
		when(userProfileRepository.findByMobileNumber(1234567890L)).thenReturn(userProfile);

		UserProfileDTO result = userProfileService.findByMobileNo(1234567890L);

		assertNotNull(result);
		assertEquals(1234567890L, result.getMobileNumber());
	}

	@Test
	public void testGetByUserName() {
		when(userProfileRepository.findByFullName("John Doe")).thenReturn(userProfile);

		UserProfileDTO result = userProfileService.getByUserName("John Doe");

		assertNotNull(result);
		assertEquals("John Doe", result.getFullName());
	}

	@Test
	public void testGetByUserNameNotFound() {
		when(userProfileRepository.findByFullName("Jane Doe")).thenReturn(null);

		ProfileNotFoundException thrown = assertThrows(ProfileNotFoundException.class, () -> {
			userProfileService.getByUserName("Jane Doe");
		});

		assertEquals("Profile not found with username: Jane Doe", thrown.getMessage());
	}
}
