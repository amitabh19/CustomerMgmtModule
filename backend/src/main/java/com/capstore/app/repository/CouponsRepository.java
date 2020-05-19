package com.capstore.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capstore.app.models.Coupon;

public interface CouponsRepository extends JpaRepository<Coupon, Integer>{

	@Query("from Coupon where couponCode=:couponCode")
	public Coupon findByCouponCode(String couponCode);
}
