public class Seat {
    private int seatID;
    private String SeatNumber;
    private String seatType;
    private float seatPrice;
    private boolean isAvailable;


    public Seat(int seatID, String SeatNumber, String seatType, float seatPrice, boolean isAvailable){
        this.seatID = seatID;
        this.SeatNumber = SeatNumber;
        this.seatType = seatType;
        this.seatPrice = seatPrice;
        this.isAvailable = isAvailable;
    }

    public boolean getisAvailable(){
        return  isAvailable;
    }

    public void reservedSeat(){

    }


}
