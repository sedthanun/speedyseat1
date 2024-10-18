
import java.util.ArrayList;
import java.util.List;

public class Payment {
    // Attribute: List of PaymentMethod (Aggregation)
    private Movie movie;
    private Showtime showtime;
    private ArrayList<String> selectedSeats;
    private int amount;
    private String paymentMethod;
    private String dat_of_Payment;
    private List<PaymentMethod> paymentMethods;

    // Constructor
    public Payment(Movie movie, Showtime showtime, ArrayList<String> selectedSeats, String paymentMethod, int amount, String date_of_Payment) {
        this.paymentMethods = new ArrayList<>();

        this.movie = movie;
        this.showtime = showtime;
        this.selectedSeats = selectedSeats;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.dat_of_Payment = date_of_Payment;

        System.out.println("\nMovie: " + this.movie.getMovieInfo().get("movieName"));
        System.out.println("Theatre: " + ((Theatre) showtime.getShowtimeInfo().get("theatre")).getTheatreInfo().get("theatreNumber"));
        System.out.println("SelectedSeats: " + String.join(", ", this.selectedSeats));
        System.out.println("PaymentMethod: " + paymentMethod);
        System.out.println("Amount: " + amount);
        System.out.println("Date of Pay: " + date_of_Payment);
    }

    // Getter for payment methods
    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    // Make a payment using a specific payment method
    public void makePayment(PaymentMethod paymentMethod) {

    }
}
