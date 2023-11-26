package com.example.MongoDBFirstPractice.Repository;

import java.util.List;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.MongoDBFirstPractice.collections.Promotion;

@Repository
public interface UpcomingPromotionRepository extends MongoRepository<Promotion, String> {
    
   @Query("{ 'endDate' : { $gte : ?0 }, 'status' : ?1 }")
    List<Promotion> findUpcomingPromotions(LocalDate todayDate, String status); 
    
    List<Promotion> findByStatus(String status);
/*
    @Query("{'startDate': { '$gte': ?0 },'endDate': { '$gt': ?0 }, 'status': { '$eq': ?1 }}")
	List<Promotion> findByStartDateBeforeAndStatus(Date startDate, String status);
   */
    
    @Query("{'startDate': { '$gt': ?0, '$lte': ?1 }, 'endDate': { '$gt': ?0 }, 'status': ?2}")
	List<Promotion> findActivePromotions(Date startDate, Date endDate, String status);


}