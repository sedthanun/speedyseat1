package SpeedySeatsGUI;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import javax.swing.*;
import Booking.*;
import Movie.*;
import Theatre.*;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

/**
 *
 * @author USER
 */
public class TicketUI extends javax.swing.JFrame {

    /**
     * Creates new form TicketUI
     */
    private Booking booking;

    public TicketUI(Booking booking) {
        initComponents();

        this.booking = booking;
        Dictionary<String, Object> bookingInfo = booking.getBookingInfo();
        Movie movie = (Movie) bookingInfo.get("movie");
        Showtime shotime = (Showtime) bookingInfo.get("showtime");
        Theatre theatre = (Theatre) shotime.getShowtimeInfo().get("theatre");
        List<Seat> seats =  (List<Seat>) bookingInfo.get("seats");

        ArrayList<String> seatName = new ArrayList<>();

        for (Seat seat : seats) {
            // Get the seat info dictionary from the Seat object
            Dictionary<String, Object> seatInfo = seat.getSeatInfo();

            // Retrieve the seatNumber (which acts as the seat name) from the dictionary
            String seatNumber = (String) seatInfo.get("seatNumber");

            if (seatNumber != null) {
                seatName.add(seatNumber);
            }
        }

        movieName.setText("Movie: " + movie.getMovieInfo().get("movieName"));
        jtheatre.setText("Theatre: " + theatre.getTheatreInfo().get("theatreNumber") );
        branch.setText("Branch: " + theatre.getTheatreInfo().get("theatreBranch"));
        showtime.setText("Date/Showtime: " + shotime.getShowtimeInfo().get("showtimeDateTime"));
        seat.setText("Seat: " + String.join(", ", seatName));

        setTitle("SpeedySeat");

        setLocationRelativeTo(null);
        setResizable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">

    private void initComponents() {

        movieName = new javax.swing.JLabel();
        jtheatre = new javax.swing.JLabel();
        branch = new javax.swing.JLabel();
        showtime = new javax.swing.JLabel();
        seat = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        movieName.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        movieName.setText("Movie:");

        jtheatre.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jtheatre.setText("Theatre:");

        branch.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        branch.setText("Branch:");

        showtime.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        showtime.setText("Date/Shotime:");

        seat.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        seat.setText("Seat No:");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/qr_code_payment.png"))); // NOI18N
        jLabel6.setText("jLabel6");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel6)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel7.setText("SpeedySeat");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(seat)
                                        .addComponent(jtheatre)
                                        .addComponent(movieName)
                                        .addComponent(branch)
                                        .addComponent(showtime))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(135, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(124, 124, 124))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addGap(139, 139, 139))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(jLabel7)
                                .addGap(60, 60, 60)
                                .addComponent(movieName)
                                .addGap(18, 18, 18)
                                .addComponent(jtheatre)
                                .addGap(18, 18, 18)
                                .addComponent(branch)
                                .addGap(18, 18, 18)
                                .addComponent(showtime)
                                .addGap(18, 18, 18)
                                .addComponent(seat)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(79, 79, 79))
        );

        pack();
    }// </editor-fold>

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new TicketUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JLabel branch;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel movieName;
    private javax.swing.JLabel seat;
    private javax.swing.JLabel showtime;
    private javax.swing.JLabel jtheatre;
    // End of variables declaration
}
