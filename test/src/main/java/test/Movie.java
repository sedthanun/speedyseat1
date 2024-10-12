package test;

import java.util.*;

public class Movie {
    private String movieName;
    private String movieDescription;
    private int movieRuntime;
    private String movieGenre;
    private String movieScreenFormat;
    private List<Movie> movies;
    private List<Showtime> showtimes;

    public Movie(){

    }

    public Movie(String movieName, String movieDescription
            , int movieRuntime, String movieGenre, String movieScreenFormat){
        this.movieName = movieName;
        this.movieDescription = movieDescription;
        this.movieRuntime = movieRuntime;
        this.movieGenre = movieGenre;
        this.movieScreenFormat = movieScreenFormat;
    }

    public void getMovie(){

    }

    public List<Showtime> getShowtimeList(){
        return showtimes;
    }

    public List<Movie> getMovieList(){
        return movies;
    }

    public static void main(String[] args) {
        Movie myMovie = new Movie("Teeyod 2", "Directed by Taweewat Wantha",110,"Horror", "IMAX");
        System.out.println("Movie: " + myMovie.movieName + " Description :" + myMovie.movieDescription + " Runtime: " + myMovie.movieRuntime + " Genre: " + myMovie.movieGenre + " System: " + myMovie.movieScreenFormat);
    }



}
