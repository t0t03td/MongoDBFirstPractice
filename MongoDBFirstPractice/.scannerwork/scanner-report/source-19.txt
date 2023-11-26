package com.example.MongoDBFirstPractice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.MongoDBFirstPractice.Dto.UpcomingPromotionDTO;
import com.example.MongoDBFirstPractice.Services.UpcomingPromotionService;
import com.example.MongoDBFirstPractice.collections.Person;
import com.example.MongoDBFirstPractice.collections.Promotion;

@RestController
@RequestMapping("/promotions")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UpcomingPromotionsController {
	
    @Autowired
    private UpcomingPromotionService promotionService;
    
	/*
    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    } */
/*
    @GetMapping("/upcoming")
    public List<Promotion> getUpcomingPromotions() {
        return promotionService.getUpcomingPromotions();
    }
		*/
    
   /* @GetMapping("/active")
    @ResponseBody
    public List<Promotion> getActivePromotions() {
        return promotionService.getActivePromotions();
    } */

    
    @GetMapping("/active")
    @ResponseBody
    public ResponseEntity<List<Promotion>> getActivePromotion() {
        try {
            List<Promotion> activePromotions = promotionService.getActivePromotion();
            return ResponseEntity.ok(activePromotions);
        } catch (Exception e) {
            // Handle the exception and log it
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
 
    @GetMapping("/actives")
    public ResponseEntity<List<UpcomingPromotionDTO>> getActivePromotions() {
        try {
            List<UpcomingPromotionDTO> activePromotions = promotionService.getActivePromotions();
            return ResponseEntity.ok(activePromotions);
        } catch (Exception e) {
            // Handle the exception and log it
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    } 
    
    
}
