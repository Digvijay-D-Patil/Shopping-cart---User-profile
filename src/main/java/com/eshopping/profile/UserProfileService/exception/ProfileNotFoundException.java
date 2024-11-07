package com.eshopping.profile.UserProfileService.exception;

public class ProfileNotFoundException extends RuntimeException {
	public ProfileNotFoundException(String message) {
		super(message);
	}
}
