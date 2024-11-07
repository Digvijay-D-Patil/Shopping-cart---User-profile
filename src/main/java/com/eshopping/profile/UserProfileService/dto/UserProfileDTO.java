package com.eshopping.profile.UserProfileService.dto;

import java.time.LocalDate;
import java.util.List;

import com.eshopping.profile.UserProfileService.entity.RoleEnum;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserProfileDTO {

	private int profileId;

	@NotEmpty(message = "Full name cannot be empty")
	@Size(min = 3, max = 50, message = "Full name must be between 3 and 50 characters")
	private String fullName;

	private String image;

	@NotNull(message = "Email cannot be null")
	@Email(message = "Email should be valid")
	private String emailId;

	@NotNull(message = "Mobile number cannot be null")
	private Long mobileNumber;

	private String about;

	@NotNull(message = "Date of birth cannot be null")
	private LocalDate dateOfBirth;

	@NotNull(message = "Gender cannot be null")
	private String gender;

	@NotNull(message = "Role cannot be null")
	private RoleEnum role; // Role Enum validation is handled automatically.

	@NotEmpty(message = "Password cannot be empty")
	@Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
	private String password;

	private List<AddressDTO> addresses; // Assuming you have an AddressDTO as well.

	// Getters and setters

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public RoleEnum getRole() {
		return role;
	}

	public void setRole(RoleEnum role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<AddressDTO> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressDTO> addresses) {
		this.addresses = addresses;
	}
}
