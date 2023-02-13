package com.cos.sharehouse.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cos.sharehouse.model.House;

public interface houseRepository extends JpaRepository<House,Integer>{

//	Page<House> findByBed(String string);
		
//		@Query("select * from House where type in '('+:type+')' and category in '('+:category+')' and bedroom in(:bedroom) and amenities like (:amenities) ")

//		@Query(value="select * from House h where h.amenities like %:amenities%",nativeQuery=true)
//    	Page<House> search(@Param("amenities") String amenities,Pageable page);  //이건 성공
		
//		@Query(value="select * from House h where h.bedroom in :bedroom",nativeQuery=true)
//		Page<House> searchbedroom(@Param("bedroom")int[] bedroom,Pageable page);  //이건 성공
//		@Query(value="select * from House h where h.category in :category",nativeQuery =true)
//		Page<House> searchcategory(@Param("category")String[] category,Pageable page); //이건 성공
	
//		@Query(value="select *from House h where h.type in :type",nativeQuery=true)
//		Page<House> searchtype(@Param("type")String[] type,Pageable page);//이건 성공
	
		@Query(value="select *from House h where h.type in :type and  h.category in :category and h.bedroom in :bedroom and h.amenities like %:amenities%",nativeQuery=true)
		Page<House> searchhouse(@Param("type")String[] type,@Param("category")String[] category,@Param("bedroom")int[] bedroom,@Param("amenities") String amenities,Pageable page);
}
