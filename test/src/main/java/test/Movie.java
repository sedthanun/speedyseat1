package test;

import Database.DBquery;

import java.sql.ResultSet;
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

    public void getMovie(int movie_id){
        try {
            ResultSet rs = DBquery.getInstance().getSelect(String.format("SELECT * FROM Movie WHERE movie_id = %d;",movie_id));
            while (rs.next()) {
                this.movieName = rs.getString("movie_name");
                System.out.println(this.movieName);
            }
            DBquery.getInstance().disconnect(); //อย่าลืม disconnectด้วยครับ
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Showtime> getShowtimeList(){
        return showtimes;
    }

    public List<Movie> getMovieList(){
        return movies;
    }

    public static void main(String[] args) {
//        Movie myMovie = new Movie("Teeyod 2", "Directed by Taweewat Wantha",110,"Horror", "IMAX");
//        System.out.println("Movie: " + myMovie.movieName + " Description :" + myMovie.movieDescription + " Runtime: " + myMovie.movieRuntime + " Genre: " + myMovie.movieGenre + " System: " + myMovie.movieScreenFormat);
        Movie movie =  new Movie();
        movie.getMovie(1);
    }



}
