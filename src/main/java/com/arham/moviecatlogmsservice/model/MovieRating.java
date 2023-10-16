package com.arham.moviecatlogmsservice.model;

public class MovieRating {

    String movieId;

    int rating;

    public MovieRating() {
    }

    public MovieRating(String movieId, int rating) {
        this.movieId = movieId;
        this.rating = rating;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
