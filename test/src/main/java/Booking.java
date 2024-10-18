import java.util.ArrayList;
import java.util.List;

public class Booking {
    private int bookingID;
    private List<Seat> seats;
    private float bookingPrice;
    private String status;
    private Showtime showtime;
    private Movie movie;
    private String paymentDatetime;
    private String paymentMethod;
    private int ticketCount;
    private List<Ticket> tikets;

    public Booking(List<Seat> seats, float bookingPrice, Showtime showtime, Movie movie, String paymentDatetime, String paymentMehtod){
        this.seats = seats;
        this.bookingPrice = bookingPrice;
        this.showtime = showtime;
        this.movie = movie;
        this.paymentDatetime = paymentDatetime;
        this.paymentMethod = paymentMehtod;
        this.tikets = new ArrayList<Ticket>();
    }

    public static Booking createBooking(List<Seat> seats, float bookingPrice, Showtime showtime, Movie movie, String paymentDatetime, String paymentMehtod){
        Booking booking = new Booking(seats, bookingPrice, showtime, movie, paymentDatetime, paymentMehtod);

        for (Seat seat: seats){
            seat.reservedSeat();
            Ticket ticket = Ticket.createTicket(seat);
            booking.addTicket(ticket);
        }

        return booking;
    }

    public void addTicket(Ticket ticket){
        this.tikets.add(ticket);
    }
}
