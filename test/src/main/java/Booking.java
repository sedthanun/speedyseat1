import Database.DBmanipulation;
import Database.DBquery;

import java.awt.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class Booking {
    private int bookingID;
    private List<Seat> seats;
    private float bookingPrice;
    private String status;
    private Showtime showtime;
    private Movie movie;
    private Payment payment;
    private int ticketCount;
    private List<Ticket> tikets;

    public Booking(List<Seat> seats, float bookingPrice, Showtime showtime, Movie movie, Payment payment){
        this.seats = seats;
        this.bookingPrice = bookingPrice;
        this.showtime = showtime;
        this.movie = movie;
        this.payment = payment;
        this.tikets = new ArrayList<Ticket>();
    }

    public Dictionary<String, Object> getBookingInfo() {
        Dictionary<String, Object> bookingInfo = new Hashtable<>();
        bookingInfo.put("bookingID", bookingID);
        bookingInfo.put("seats", seats);
        bookingInfo.put("bookingPrice", bookingPrice);
//        bookingInfo.put("status", status);
        bookingInfo.put("showtime", showtime);
        bookingInfo.put("movie", movie);
        bookingInfo.put("payment", payment);
        bookingInfo.put("tikets", tikets);

        return bookingInfo;
    }

    public static Booking createBooking(List<Seat> seats, float bookingPrice, Showtime showtime, Movie movie, Payment payment){
        Booking booking = new Booking(seats, bookingPrice, showtime, movie, payment);

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

    public static void saveBookingToDB(Booking booking, Account account){

        // insert into Booking
        Integer bookingId = null;
        Showtime showtime = (Showtime)booking.getBookingInfo().get("showtime");
        try {
            System.out.println("insert into Booking");
            DBmanipulation.getInstance().getUpdate(String.format("INSERT INTO Booking (booking_price, showtime_id, account_id) VALUES ('%f', '%d', '%d');", (Float)booking.getBookingInfo().get("bookingPrice"), (Integer)showtime.getShowtimeInfo().get("showtimeID"), (Integer)account.getAccountInfo().get("accountID")));

            // ใช้ LAST_INSERT_ID() เพื่อดึง booking_id ที่ถูกสร้างขึ้น
            ResultSet resultSet = DBquery.getInstance().getSelect("SELECT last_insert_rowid() AS booking_id");
            if (resultSet != null && resultSet.next()) {
                bookingId = resultSet.getInt("booking_id");
                System.out.println(bookingId);
            }
            resultSet.close();
//           DBquery.getInstance().disconnect();

            DBmanipulation.getInstance().disconnect(); //อย่าลืม disconnectด้วยครับ



        } catch (Exception e) {
            e.printStackTrace();
        }

        Payment payment = ((Payment)booking.getBookingInfo().get("payment"));
        // insert into Payment
        try {
            System.out.println("insert into Payment");
            DBmanipulation.getInstance().getUpdate(String.format("INSERT INTO Payment (payment_method, date_of_payment, amount, account_id, booking_id) VALUES ('%s', '%s', '%d', '%d', '%d');", (String)payment.getPaymentInfo().get("paymentMethod"), (String)payment.getPaymentInfo().get("dat_of_Payment"), (Integer)payment.getPaymentInfo().get("amount"), (Integer)account.getAccountInfo().get("accountID"), bookingId));
            DBmanipulation.getInstance().disconnect(); //อย่าลืม disconnectด้วยครับ
        } catch (Exception e) {
            e.printStackTrace();
        }


        // update Seat
        List<Seat> seats = (List<Seat>)booking.getBookingInfo().get("seats");
        for(Seat seat: seats){
            try {
                System.out.println("update Seat");
                DBmanipulation.getInstance().getUpdate(String.format("UPDATE Seat SET booking_id = %d WHERE seat_id = %d;", bookingId, (Integer)seat.getSeatInfo().get("seatID")));
                DBmanipulation.getInstance().disconnect(); //อย่าลืม disconnectด้วยครับ
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

//    public static void main(String[] args) {
//        Account account = null;
//        Booking booking = new Booking()
//    }
}
