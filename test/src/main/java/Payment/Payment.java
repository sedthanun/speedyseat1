package Payment;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import Booking.Account;
import Booking.Seat;
import Theatre.*;
import Movie.*;
public class Payment implements IPaymentPage {
    // Attribute: List of PaymentMethod (Aggregation)
    private PayByQR payByQR;
    private PayByCash payByCash;
    private PayByBanking payByBanking;

    private Movie movie;
    private Showtime showtime;
    private List<Seat> selectedSeats;
    private int amount;
    private String paymentMethod;
    private String dat_of_Payment;

    // Constructor
    public Payment() {
        payByQR = new PayByQR();
        payByCash = new PayByCash();
        payByBanking = new PayByBanking();
    }
    public Dictionary<String, Object> getPaymentInfo(){
        Dictionary<String, Object> paymentInfo = new Hashtable<>();

        paymentInfo.put("paymentMethod", paymentMethod);
        paymentInfo.put("dat_of_Payment", dat_of_Payment);
        paymentInfo.put("amount", amount);

        return paymentInfo;
    }
    public Payment(Movie movie, Showtime showtime, List<Seat> selectedSeats, String paymentMethod, int amount, String date_of_Payment) {
        this();

        this.movie = movie;
        this.showtime = showtime;
        this.selectedSeats = selectedSeats;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.dat_of_Payment = date_of_Payment;

        System.out.println("\nMovie: " + this.movie.getMovieInfo().get("movieName"));
        System.out.println("Theatre: " + ((Theatre) showtime.getShowtimeInfo().get("theatre")).getTheatreInfo().get("theatreNumber"));
        //System.out.println("SelectedSeats: " + String.join(", ", this.selectedSeats));
        System.out.println("PaymentMethod: " + paymentMethod);
        System.out.println("Amount: " + amount);
        System.out.println("Date of Pay: " + date_of_Payment);
    }

    public Object getPaymentMethod(String method) {
        Dictionary<String, Object> paymentMethod = new Hashtable<>();

        paymentMethod.put("payByQR", payByQR);
        paymentMethod.put("payByCash", payByCash);
        paymentMethod.put("payByBanking", payByBanking);

        return paymentMethod.get(method);
    }

    @Override
    public void confirmBooking(Showtime showtime, Movie movie, List<Seat> selectedSeats, int totalPrice, Account account, String payDate) {

    }

    @Override
    public void confirmPay() {

    }

    @Override
    public void selectPaymentMethod(PaymentMethod paymentMethod) {

    }

    // Make a payment using a specific payment method
    public void makePayment(PaymentMethod paymentMethod) {
    }
}
