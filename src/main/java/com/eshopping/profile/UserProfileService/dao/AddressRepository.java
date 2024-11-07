package com.eshopping.profile.UserProfileService.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eshopping.profile.UserProfileService.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
