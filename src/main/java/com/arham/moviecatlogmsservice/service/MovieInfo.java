package com.arham.moviecatlogmsservice.service;

import com.arham.moviecatlogmsservice.model.CatlogItem;
import com.arham.moviecatlogmsservice.model.Movie;
import com.arham.moviecatlogmsservice.model.MovieRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfo {

    @Autowired
    RestTemplate restTemplate;
    @HystrixCommand(fallbackMethod = "getFallbackCatlogItem")
    public CatlogItem getCatlogItem(MovieRating rating) {
        Movie movie=restTemplate.getForObject("http://movie-info-service/movie/"+ rating.getMovieId(), Movie.class);
        return new CatlogItem(movie.getMovieName(), "Rajnikant movie", rating.getRating());
    }


    public CatlogItem getFallbackCatlogItem(MovieRating rating) {
        return new CatlogItem("No Item","",0);
    }


}
