package com.rating.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private Long productId;
    private String productName;
    private Float productRating = 0.0f;
    @JsonIgnore
    private Long totalRatings = 0l;
    @JsonIgnore
    @ElementCollection
    private Map<Long, Integer> usersRatingMap = new HashMap<>();


    public void rateProductByUser(Long userId, int rating){
        if(isProductRatedByUser(userId)){
            int userNetRating = rating - usersRatingMap.get(userId);
            totalRatings += userNetRating;
        }else{
            totalRatings += rating;
        }
        this.usersRatingMap.put(userId, rating);
        this.productRating = totalRatings.floatValue()/this.usersRatingMap.size();
    }

    private boolean isProductRatedByUser(Long userId){
        return usersRatingMap.containsKey(userId);
    }

    public void unrateProductByUser(Long userId){
        if(isProductRatedByUser(userId)){
            totalRatings -= usersRatingMap.get(userId);
            this.usersRatingMap.remove(userId);
            this.productRating = totalRatings.floatValue()/usersRatingMap.size();
        }
    }

}
