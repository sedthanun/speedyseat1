public class Seat {
    private int SeatNumber;
    private String seatType;
    private float seatPrice;
    private boolean isAvailable;

    public Seat(){

    }

    public Seat(int SeatNumber, String seatType, float seatPrice, boolean isAvailable){
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
