package com.eshopping.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eshopping.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
