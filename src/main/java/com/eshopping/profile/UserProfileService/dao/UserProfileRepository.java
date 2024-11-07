package com.eshopping.profile.UserProfileService.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eshopping.profile.UserProfileService.entity.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {

	public UserProfile findByMobileNumber(Long mobileNumber);

	public UserProfile findByFullName(String fullName);

}
