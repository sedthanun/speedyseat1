import Database.DBquery;

import java.sql.ResultSet;
import java.util.*;

public class Movie {
    private int movieID;
    private String movieName;
    private String movieDescription;
    private int movieRuntime;
    private String movieGenre;
    private float movieRating;
    private byte[] moviePoster;
    private List<Showtime> showtimes;
    private static ArrayList<Movie> movies;

    public Movie(int movieID, String movieName, String movieDescription, int movieRuntime, String movieGenre, float movieRating, byte[] moviePoster){
        this.movieID = movieID;
        this.movieName= movieName;
        this.movieDescription = movieDescription;
        this.movieRuntime = movieRuntime;
        this.movieGenre = movieGenre;
        this.movieRating = movieRating;
        this.moviePoster = moviePoster;
    }

    public Dictionary<String, Object> getMovieInfo() {
        Dictionary<String, Object> movieInfo = new Hashtable<>();

        movieInfo.put("movieID", movieID);
        movieInfo.put("movieName", movieName);
        movieInfo.put("movieDescription", movieDescription);
        movieInfo.put("movieRuntime", movieRuntime);
        movieInfo.put("movieGenre", movieGenre);
        movieInfo.put("movieRating", movieRating);
        movieInfo.put("moviePoster", moviePoster);

        return movieInfo;
    }

    public List<Showtime> getShowtimeList(){
        return this.showtimes;
    }

    public static List<Movie> getMovieList(){
        return movies;
    }

    public static void setMovieListFromDB(){
        Movie.movies = new ArrayList<Movie>();
        try {
            ResultSet rs = DBquery.getInstance().getSelect("SELECT * FROM Movie WHERE movie_status='Now Showing';");
            while (rs.next()) {
                int movieID = rs.getInt("movie_id");
                String movieName = rs.getString("movie_name");
                String movieDescription = rs.getString("movie_description");
                byte[] moviePoster = rs.getBytes("movie_poster");
                float movieRating = rs.getFloat("movie_rating");
                int movieRuntime = rs.getInt("movie_runtime");
                String movieGenre = rs.getString("movie_genre");

                Movie.movies.add(new Movie(movieID, movieName, movieDescription, movieRuntime, movieGenre, movieRating, moviePoster));
            }
            DBquery.getInstance().disconnect(); //อย่าลืม disconnectด้วยครับ
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setShowtimeListFromDB(){
        for (Movie movie : Movie.movies){
            try {
                movie.showtimes = new ArrayList<Showtime>();;
                ResultSet rs = DBquery.getInstance().getSelect(String.format("SELECT * FROM Showtime WHERE movie_id=%d;", movie.movieID));
                while (rs.next()) {
                    int showtimeID = rs.getInt("showtime_id");
                    String showtimeDateTime = rs.getString("showtime_date_time");
                    String screenFormat = rs.getString("screen_format");
                    String sound = rs.getString("sound");
                    String subtitle = rs.getString("subtitle");


                    Showtime showtime = new Showtime(showtimeID, showtimeDateTime, screenFormat, sound, subtitle);

                    showtime.setTheatre(showtime.getTheatreFromDB());
                    showtime.setSeats(showtime.getSeatsFromDB());
                    
                    movie.showtimes.add(showtime);
                }
                DBquery.getInstance().disconnect(); //อย่าลืม disconnectด้วยครับ
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}