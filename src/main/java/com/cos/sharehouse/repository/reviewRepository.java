package com.cos.sharehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.sharehouse.model.Review;

public interface reviewRepository extends JpaRepository<Review,Integer>{
	//리뷰 수 증가
	@Modifying
	@Query("update House house set house.reviewcount = house.reviewcount + 1 where house.id = :id")
	void plusReviewCount(int id);
}
