package com.arham.moviecatlogmsservice.model;

import java.util.List;

public class UserRating
{
    List<MovieRating> ratings;

    public UserRating() {
    }

    public List<MovieRating> getRatings() {
        return ratings;
    }

    public void setRatings(List<MovieRating> ratings) {
        this.ratings = ratings;
    }




}
