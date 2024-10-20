package Payment;

import Booking.Account;
import Booking.Seat;
import Theatre.Showtime;
import Movie.*;
import java.util.List;


public interface IPaymentPage {
    // Method to get a list of available payment methods
//    Object getPaymentMethod(String method);
    void confirmBooking(Showtime showtime, Movie movie, List<Seat> selectedSeats, int totalPrice, Account account, String payDate);
    void selectPaymentMethod (PaymentMethod paymentMethod);
    void confirmPay();
}
