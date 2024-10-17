package Payments;

import java.util.ArrayList;
import java.util.List;

public class Ticket {
    private int movieID;
    private String movieName;
    private int movieRuntime;
    private String movieGenre;
    private byte[] moviePoster;
    private String movieShowtime;
    private String movieSound;
    private String movieSubtitle;
    private String theatre;
    private String seats;
    private String price;
    private boolean paymentConfirm;

    public Ticket() {
        this.paymentConfirm = false;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    // Getter and Setter for movieName
    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    // Getter and Setter for movieRuntime
    public int getMovieRuntime() {
        return movieRuntime;
    }

    public void setMovieRuntime(int movieRuntime) {
        this.movieRuntime = movieRuntime;
    }

    // Getter and Setter for movieGenre
    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    // Getter and Setter for moviePoster
    public byte[] getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(byte[] moviePoster) {
        this.moviePoster = moviePoster;
    }

    // Getter and Setter for movieShowtime
    public String getMovieShowtime() {
        return movieShowtime;
    }

    public void setMovieShowtime(String movieShowtime) {
        this.movieShowtime = movieShowtime;
    }

    // Getter and Setter for movieSound
    public String getMovieSound() {
        return movieSound;
    }

    public void setMovieSound(String movieSound) {
        this.movieSound = movieSound;
    }

    // Getter and Setter for movieSubtitle
    public String getMovieSubtitle() {
        return movieSubtitle;
    }

    public void setMovieSubtitle(String movieSubtitle) {
        this.movieSubtitle = movieSubtitle;
    }

    // Getter and Setter for theatre
    public String getTheatre() {
        return theatre;
    }

    public void setTheatre(String theatre) {
        this.theatre = theatre;
    }

    // Getter and Setter for seats
    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }
}
