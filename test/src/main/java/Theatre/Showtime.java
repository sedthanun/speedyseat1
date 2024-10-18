package Theatre;

import Database.DBquery;

import java.sql.ResultSet;
import java.util.*;
import Booking.*;
import Theatre.*;

public class Showtime {
    private int showtimeID;
    private String showtimeDateTime;
    private Theatre theatre;
    private String screenFormat;
    private ArrayList<Seat> seats;
    private String sound;
    private String subtitle;

    public Showtime(int showtimeID, String showtimeDateTime, String screenFormat, String sound, String subtitle){
        this.showtimeID = showtimeID;
        this.showtimeDateTime = showtimeDateTime;
        this.screenFormat = screenFormat;
        this.sound = sound;
        this.subtitle = subtitle;
    }

    public Dictionary<String, Object> getShowtimeInfo(){
        Dictionary<String, Object> showtimeInfo = new Hashtable<>();

        showtimeInfo.put("showtimeID", showtimeID);
        showtimeInfo.put("showtimeDateTime", showtimeDateTime);
        showtimeInfo.put("seats", seats);
        showtimeInfo.put("theatre", theatre);
        showtimeInfo.put("screenFormat", screenFormat);
        showtimeInfo.put("sound", sound);
        showtimeInfo.put("subtitle", subtitle);

        return showtimeInfo;
    }

    public Theatre getTheatreFromDB() {
        try {
            Theatre theatre = null;
            ResultSet rs = DBquery.getInstance().getSelect(String.format("SELECT * FROM Theatre JOIN showtime USING (theatre_id) WHERE showtime_id=%d;", this.showtimeID));
            while (rs.next()) {
                int theatreID = rs.getInt("theatre_id");
                String theatreBranch = rs.getString("theatre_branch");
                int theatreNumber = rs.getInt("theatre_number");

                theatre = new Theatre(theatreID, theatreBranch, theatreNumber);
            }
            DBquery.getInstance().disconnect(); //อย่าลืม disconnectด้วยครับ
            return theatre;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    public ArrayList<Seat> getSeatsFromDB() {
        try {
            ArrayList<Seat> seats = new ArrayList<Seat>();
            ResultSet rs = DBquery.getInstance().getSelect(String.format("SELECT *, IFNULL(booking_id, 0) bookingID FROM Seat JOIN Showtime USING (showtime_id) WHERE showtime_id=%d;", this.showtimeID));
            while (rs.next()) {
                int seatID = rs.getInt("seat_id");
                String seatNumber = rs.getString("seat_number");
                String seatType = rs.getString("seat_type");
                float seatPrice = rs.getFloat("seat_price");
                int bookingID = rs.getInt("bookingID");
                boolean isAvailable;
                if(bookingID==0){
                    isAvailable = true;
                }else{
                    isAvailable = false;
                }

                seats.add(new Seat(seatID, seatNumber, seatType, seatPrice, isAvailable));
            }
            DBquery.getInstance().disconnect(); //อย่าลืม disconnectด้วยครับ
            return seats;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setSeats(ArrayList<Seat> seats){
        this.seats = seats;
    }
}
