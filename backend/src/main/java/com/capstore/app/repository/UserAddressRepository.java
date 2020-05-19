package com.capstore.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstore.app.models.UserAddress;
@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Integer> {

}
