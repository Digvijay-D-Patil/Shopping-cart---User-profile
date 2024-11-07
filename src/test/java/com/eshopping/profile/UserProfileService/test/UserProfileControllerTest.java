
package com.eshopping.profile.UserProfileService.test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.eshopping.profile.UserProfileService.controller.UserProfileController;
import com.eshopping.profile.UserProfileService.dto.UserProfileDTO;
import com.eshopping.profile.UserProfileService.entity.UserProfile;
import com.eshopping.profile.UserProfileService.service.UserProfileServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class UserProfileControllerTest {

	private MockMvc mockMvc;

	@Mock
	private UserProfileServiceImpl userProfileService;

	@InjectMocks
	private UserProfileController userProfileController;

	private ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	public void setUp() {
		// Initialize MockMvc before each test
		mockMvc = MockMvcBuilders.standaloneSetup(userProfileController).build();
	}

	@Test
	public void testAddNewCustomerProfile() throws Exception {
		UserProfile userProfile = new UserProfile();
		userProfile.setFullName("John Doe");
		userProfile.setEmailId("john.doe@example.com");

		UserProfileDTO userProfileDTO = new UserProfileDTO();
		userProfileDTO.setFullName("John Doe");
		userProfileDTO.setEmailId("john.doe@example.com");

		when(userProfileService.addNewCustomerProfile(any(UserProfile.class))).thenReturn(userProfileDTO);

		mockMvc.perform(post("/api/userprofiles/customer").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(userProfile))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.fullName").value("John Doe"))
				.andExpect(jsonPath("$.emailId").value("john.doe@example.com"));

		verify(userProfileService, times(1)).addNewCustomerProfile(any(UserProfile.class));
	}

	@Test
	public void testGetUserProfileById() throws Exception {
		int profileId = 1;
		UserProfileDTO userProfileDTO = new UserProfileDTO();
		userProfileDTO.setProfileId(profileId);
		userProfileDTO.setFullName("John Doe");

		when(userProfileService.getByProfileId(profileId)).thenReturn(userProfileDTO);

		mockMvc.perform(get("/api/userprofiles/{profileId}", profileId)).andExpect(status().isOk())
				.andExpect(jsonPath("$.profileId").value(profileId))
				.andExpect(jsonPath("$.fullName").value("John Doe"));

		verify(userProfileService, times(1)).getByProfileId(profileId);
	}

	@Test
	public void testUpdateProfile() throws Exception {
		int profileId = 1;
		UserProfile userProfile = new UserProfile();
		userProfile.setProfileId(profileId);
		userProfile.setFullName("Updated Name");
		userProfile.setEmailId("updated.email@example.com");

		doNothing().when(userProfileService).updateProfile(any(UserProfile.class));

		mockMvc.perform(put("/api/userprofiles/{profileId}", profileId).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(userProfile))).andExpect(status().isNoContent());

		verify(userProfileService, times(1)).updateProfile(any(UserProfile.class));
	}

//	@Test
//	public void testUpdateProfile_ProfileNotFound() throws Exception {
//		int profileId = 999; // Non-existent profile ID
//		UserProfile userProfile = new UserProfile();
//		userProfile.setProfileId(profileId);
//		userProfile.setFullName("Non-existent Profile");
//		userProfile.setEmailId("nonexistent.email@example.com");
//
//		doNothing().when(userProfileService).updateProfile(any(UserProfile.class));
//
//		mockMvc.perform(put("/api/userprofiles/{profileId}", profileId).contentType(MediaType.APPLICATION_JSON)
//				.content(objectMapper.writeValueAsString(userProfile))).andExpect(status().isNotFound());
//
//		verify(userProfileService, times(1)).updateProfile(any(UserProfile.class));
//	}

	@Test
	public void testUpdateProfile_Success() throws Exception {
		int profileId = 1;
		UserProfile userProfile = new UserProfile();
		userProfile.setProfileId(profileId);
		userProfile.setFullName("Updated Name");
		userProfile.setEmailId("updated.email@example.com");

		// Mocking the updateProfile call
		doNothing().when(userProfileService).updateProfile(any(UserProfile.class));

		mockMvc.perform(put("/api/userprofiles/{profileId}", profileId).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(userProfile))).andExpect(status().isNoContent()); // Expecting
																											// 204 No
																											// Content

		verify(userProfileService, times(1)).updateProfile(any(UserProfile.class));
	}

	@Test
	public void testDeleteProfile() throws Exception {
		int profileId = 1;

		mockMvc.perform(delete("/api/userprofiles/{profileId}", profileId)).andExpect(status().isNoContent());

		verify(userProfileService, times(1)).deleteProfile(profileId);
	}

	@Test
	public void testGetAllProfiles() throws Exception {
		UserProfileDTO userProfileDTO1 = new UserProfileDTO();
		userProfileDTO1.setFullName("John Doe");

		UserProfileDTO userProfileDTO2 = new UserProfileDTO();
		userProfileDTO2.setFullName("Jane Doe");

		when(userProfileService.getAllProfiles()).thenReturn(Arrays.asList(userProfileDTO1, userProfileDTO2));

		mockMvc.perform(get("/api/userprofiles/all")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].fullName").value("John Doe"))
				.andExpect(jsonPath("$[1].fullName").value("Jane Doe"));

		verify(userProfileService, times(1)).getAllProfiles();
	}

	@Test
	public void testGetAllProfiles_Empty() throws Exception {
		when(userProfileService.getAllProfiles()).thenReturn(Arrays.asList());

		mockMvc.perform(get("/api/userprofiles/all")).andExpect(status().isOk()).andExpect(jsonPath("$").isEmpty());

		verify(userProfileService, times(1)).getAllProfiles();
	}

	@Test
	public void testAddNewMerchantProfile() throws Exception {
		UserProfile userProfile = new UserProfile();
		userProfile.setFullName("Merchant Name");
		userProfile.setEmailId("merchant@example.com");

		doNothing().when(userProfileService).addNewMerchantProfile(any(UserProfile.class));

		mockMvc.perform(post("/api/userprofiles/merchant").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(userProfile))).andExpect(status().isCreated());

		verify(userProfileService, times(1)).addNewMerchantProfile(any(UserProfile.class));
	}

	@Test
	public void testAddNewDeliveryProfile() throws Exception {
		UserProfile userProfile = new UserProfile();
		userProfile.setFullName("Delivery Agent");
		userProfile.setEmailId("delivery@example.com");

		doNothing().when(userProfileService).addNewDeliveryProfile(any(UserProfile.class));

		mockMvc.perform(post("/api/userprofiles/delivery-agent").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(userProfile))).andExpect(status().isCreated());

		verify(userProfileService, times(1)).addNewDeliveryProfile(any(UserProfile.class));
	}

	@Test
	public void testFindByMobileNo() throws Exception {
		long mobileNumber = 1234567890L;
		UserProfileDTO userProfileDTO = new UserProfileDTO();
		userProfileDTO.setMobileNumber(mobileNumber);
		userProfileDTO.setFullName("John Doe");

		when(userProfileService.findByMobileNo(mobileNumber)).thenReturn(userProfileDTO);

		mockMvc.perform(get("/api/userprofiles/mobile/{mobileNumber}", mobileNumber)).andExpect(status().isOk())
				.andExpect(jsonPath("$.mobileNumber").value(mobileNumber))
				.andExpect(jsonPath("$.fullName").value("John Doe"));

		verify(userProfileService, times(1)).findByMobileNo(mobileNumber);
	}

	@Test
	public void testGetByUserName_Success() throws Exception {
		String userName = "john_doe";
		UserProfileDTO userProfileDTO = new UserProfileDTO();
		userProfileDTO.setFullName("John Doe");
		userProfileDTO.setEmailId("john.doe@example.com");

		when(userProfileService.getByUserName(userName)).thenReturn(userProfileDTO);

		mockMvc.perform(get("/api/userprofiles/username/{userName}", userName)).andExpect(status().isOk())
				.andExpect(jsonPath("$.fullName").value("John Doe"))
				.andExpect(jsonPath("$.emailId").value("john.doe@example.com"));

		verify(userProfileService, times(1)).getByUserName(userName);
	}

}
