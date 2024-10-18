package Booking;

import java.util.Dictionary;
import java.util.Hashtable;

public class Seat {
    private int seatID;
    private String seatNumber;
    private String seatType;
    private float seatPrice;
    private boolean isAvailable;


    public Seat(int seatID, String SeatNumber, String seatType, float seatPrice, boolean isAvailable){
        this.seatID = seatID;
        this.seatNumber = SeatNumber;
        this.seatType = seatType;
        this.seatPrice = seatPrice;
        this.isAvailable = isAvailable;
    }

    public Dictionary<String, Object> getSeatInfo(){
        Dictionary<String, Object> SeatInfo = new Hashtable<>();
        SeatInfo.put("seatID", seatID);
        SeatInfo.put("seatNumber",  seatNumber);
        SeatInfo.put("seatType",  seatType);
        SeatInfo.put("seatPrice", seatPrice);
        return SeatInfo;
    }

    public boolean getisAvailable(){
        return  isAvailable;
    }

    public void reservedSeat(){
        this.isAvailable = false;
    }


}
