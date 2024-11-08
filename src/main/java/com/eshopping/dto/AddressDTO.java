package com.eshopping.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class AddressDTO {

	private int addressId;

	@NotEmpty(message = "Street address cannot be empty")
	@Size(min = 3, max = 100, message = "Street address must be between 3 and 100 characters")
	private String street;

	@NotEmpty(message = "City cannot be empty")
	@Size(min = 2, max = 50, message = "City name must be between 2 and 50 characters")
	private String city;

	@NotEmpty(message = "State cannot be empty")
	@Size(min = 2, max = 50, message = "State name must be between 2 and 50 characters")
	private String state;

	@NotEmpty(message = "Country cannot be empty")
	@Size(min = 2, max = 50, message = "Country name must be between 2 and 50 characters")
	private String country;

	@NotEmpty(message = "Zip code cannot be empty")
	@Size(min = 5, max = 10, message = "Zip code must be between 5 and 10 characters")
	private String zipCode;

	// Optionally, you may add a reference to the `UserProfileDTO` in case you need
	// to include it in the DTO
	private int userProfileId; // You can use the `profileId` from the UserProfile

	// Getters and setters

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

//	public int getUserProfileId() {
//		return userProfileId;
//	}

	public void setUserProfileId(int userProfileId) {
		this.userProfileId = userProfileId;
	}
}
