package com.example.MongoDBFirstPractice.Services;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MongoDBFirstPractice.Dto.UpcomingPromotionDTO;
import com.example.MongoDBFirstPractice.Repository.UpcomingPromotionRepository;
import com.example.MongoDBFirstPractice.collections.Promotion;
import com.example.MongoDBFirstPractice.exception.ECartException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UpcomingPromotionService {

	private static final Logger logger = LoggerFactory.getLogger(UpcomingPromotionService.class);
	private final UpcomingPromotionRepository promotionRepository;

	
    @Autowired
    public UpcomingPromotionService(UpcomingPromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    public List<Promotion> getUpcomingPromotions() {
        try {
            LocalDate todayDate = LocalDate.parse("2023-12-01"); // Example date
            List<Promotion> upcomingPromotions = promotionRepository.findUpcomingPromotions(todayDate, "active");
            System.out.println("Upcoming Promotions: " + upcomingPromotions);
            return upcomingPromotions;
        } catch (DateTimeParseException e) {
            // Handle parsing exception
            e.printStackTrace();
            return null; // You should handle exceptions appropriately
        }
    }
   
 
    public List<Promotion> getActivePromotion() {
        List<Promotion> activePromotions = promotionRepository.findByStatus("active");

        if (activePromotions.isEmpty()) {
            System.out.println("No active promotions found.");
        } else {
            System.out.println("Active Promotions:");
            for (Promotion promotion : activePromotions) {
                System.out.println("Promotion ID: " + promotion.getPromotionID());
                System.out.println("Promotion Name: " + promotion.promotionDescription());
                // Print other attributes as needed
                System.out.println();
            }
        }
		return activePromotions;
    }
    
  /*
    public List<PromotionDTO> getActivePromotions() {
        Date currentDate = new Date();
        List<Promotion> activePromotions = promotionRepository.findByStartDateBeforeAndStatus(currentDate, "active");
        
        System.out.println("Date Passed from service Layer:" +currentDate);
        System.out.println("Active Promotions:" +activePromotions);
        if (activePromotions.isEmpty()) {
            System.out.println("No active promotions found.");
            return Collections.emptyList();
        }
        System.out.println("Date Passed from service Layer:" +currentDate);
        System.out.println("Active Promotions:" +activePromotions);
        for (Promotion promotion : activePromotions) {
            System.out.println("Promotion ID: " + promotion.getPromotionID());
            System.out.println("Promotion Name: " + promotion.promotionDescription());
            // Print other attributes as needed
            System.out.println();
        }

        return activePromotions.stream()
            .map(promotion -> new PromotionDTO(promotion.getPromotionID(), promotion.promotionDescription()))
            .collect(Collectors.toList());
    } 
    */
    
	/**
	 * Fetches Upcoming promotions
	 * 
	 * @param CurrentDate,Status Active
	 * @return
	 * @throws ECartException
	 */
    
    public List<UpcomingPromotionDTO> getActivePromotions() throws ECartException {
        try {
        	
            Date currentDate = new Date();

            // Calculate the end date as 30 days from the current date
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);
            calendar.add(Calendar.DATE, 30);
            Date endDate = calendar.getTime();

            List<Promotion> activePromotions = promotionRepository.findActivePromotions(currentDate, endDate, "active");

            System.out.println("Date Passed from service Layer: " + currentDate);
            System.out.println("Active Promotions: " + activePromotions);
            

            if (activePromotions.isEmpty()) {
                System.out.println("No active promotions found.");
                return Collections.emptyList();
            }

            //System.out.println("Date Passed from service Layer: " + currentDate);
            //System.out.println("Active Promotions: " + activePromotions);
            
            logger.info("Date Passed from service Layer:" + currentDate);

            for (Promotion promotion : activePromotions) {
            	 logger.info("Promotion ID: " + promotion.getPromotionID());
            	 logger.info("Promotion Name: " + promotion.promotionDescription());
            	 logger.info("Value change: " + promotion.getValueChange()); // Use getValueChange directly
      
            }

            return activePromotions.stream()
                    .map(promotion -> new UpcomingPromotionDTO(promotion.getPromotionID(), promotion.promotionDescription(), promotion.getValueChange())) // Use getValueChange directly
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error occurred " + e);
            throw new ECartException("Internal server Error");
        }
    }



    
    
}
