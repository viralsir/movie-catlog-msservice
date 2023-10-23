package com.arham.moviecatlogmsservice.rescouce;

import com.arham.moviecatlogmsservice.model.CatlogItem;
import com.arham.moviecatlogmsservice.model.Movie;
import com.arham.moviecatlogmsservice.model.MovieRating;
import com.arham.moviecatlogmsservice.model.UserRating;
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
     WebClient.Builder webClient;

    @RequestMapping("/{userId}")
    public List<CatlogItem> getCatlogItemByUserId(@PathVariable String userId){

      //  RestTemplate restTemplate=new RestTemplate();
     //     WebClient.Builder webClientBuilder = WebClient.builder();


         // get all movie ratings
       UserRating    ratings= restTemplate.getForObject("http://movie-rating-service/rating/users/"+userId, UserRating.class);


        return ratings.getRatings().stream().map(rating-> {
             Movie movie=restTemplate.getForObject("http://movie-info-service/movie/"+rating.getMovieId(), Movie.class);
//            Movie movie=webClient.build().get().uri("http://localhost:8091/movie/"+rating.getMovieId()).
//                         retrieve().bodyToMono(Movie.class).block();


                    return new CatlogItem(movie.getMovieName(), "Rajnikant movie", rating.getRating());

                })
                .collect(Collectors.toList());

        // get all movie info for all ratings


        // return Collections.singletonList(new CatlogItem("Jailer","Rajnikant movie",4));

    }

}
