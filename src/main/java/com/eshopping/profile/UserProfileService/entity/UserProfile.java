package com.eshopping.profile.UserProfileService.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class UserProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int profileId;

	private String fullName;

	private String image;

	private String emailId;

	private Long mobileNumber;

	private String about;

	private LocalDate dateOfBirth;

	private String gender;

	@Enumerated(EnumType.STRING) // To store enum as string in the database
	private RoleEnum role; // Changed to RoleEnum

	private String password;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Address> addresses;

	// Constructors, getters, and setters

	public UserProfile(int profileId, String fullName, String image, String emailId, Long mobileNumber, String about,
			LocalDate dateOfBirth, String gender, RoleEnum role, String password, List<Address> addresses) {
		this.profileId = profileId;
		this.fullName = fullName;
		this.image = image;
		this.emailId = emailId;
		this.mobileNumber = mobileNumber;
		this.about = about;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.role = role;
		this.password = password;
		this.addresses = addresses;
	}

	public UserProfile() {
	}

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

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
}
