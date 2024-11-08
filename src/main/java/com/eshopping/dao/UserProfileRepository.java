package com.eshopping.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eshopping.entity.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {

	public UserProfile findByMobileNumber(Long mobileNumber);

	public UserProfile findByFullName(String fullName);

}
