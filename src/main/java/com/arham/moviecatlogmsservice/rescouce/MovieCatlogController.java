package com.arham.moviecatlogmsservice.rescouce;

import com.arham.moviecatlogmsservice.model.CatlogItem;
import com.arham.moviecatlogmsservice.model.Movie;
import com.arham.moviecatlogmsservice.model.MovieRating;
import com.arham.moviecatlogmsservice.model.UserRating;
import com.arham.moviecatlogmsservice.service.MovieInfo;
import com.arham.moviecatlogmsservice.service.RatingService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// synchronise
// asynchronise
//              statement1      func1();
//              statement2      func2();
//              statement3      func3();

// Reactive Programming
// node js


// 1) RestTemplate     Synchronise
// 2) WebClient        Asynchronise   Reactive Programming
// spring - web

@RestController
@RequestMapping("/catlog")
public class MovieCatlogController{

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    MovieInfo movieInfo;

    @Autowired
    RatingService ratingService;

     @Autowired
     WebClient.Builder webClient;


    @RequestMapping("/{userId}")
    @HystrixCommand(fallbackMethod = "getfallbackCatlogItem")
    public List<CatlogItem> getCatlogItemByUserId(@PathVariable String userId){

         // get all movie ratings
       UserRating    ratings= ratingService.getUserRatings(userId);

        return ratings.getRatings().stream().map(rating-> {
                    return movieInfo.getCatlogItem(rating);

                })
                .collect(Collectors.toList());

    }







    public List<CatlogItem> getfallbackCatlogItem(@PathVariable String userId) {
        return Arrays.asList(new CatlogItem("No Movie","",0));
    }



    }
