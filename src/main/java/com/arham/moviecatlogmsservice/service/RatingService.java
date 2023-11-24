package com.arham.moviecatlogmsservice.service;

import com.arham.moviecatlogmsservice.model.MovieRating;
import com.arham.moviecatlogmsservice.model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class RatingService {


    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackUserRatings")
     public UserRating getUserRatings(String userId) {
        return restTemplate.getForObject("http://movie-rating-service/rating/users/" + userId, UserRating.class);
    }

    public UserRating getFallbackUserRatings(String userId) {
        UserRating userRating=new UserRating();
        userRating.setRatings(Arrays.asList(new MovieRating("0",0)));
        return userRating;
    }
}
