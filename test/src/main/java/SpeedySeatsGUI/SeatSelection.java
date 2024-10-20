package SpeedySeatsGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import Theatre.*;
import Booking.*;
import Movie.*;
public class SeatSelection extends JFrame {
    private final List<Seat> selectedSeats = new ArrayList<>();
    private final JButton[][] seatButtons = new JButton[5][5];
    private final JLabel selectedSeatLabel = new JLabel("SELECTED SEAT");
    private final JLabel totalPriceLabel = new JLabel("TOTAL PRICE");
    private final JLabel selectedSeatListLabel = new JLabel("");
    private final JLabel totalPriceValueLabel = new JLabel("0 THB");
    private int totalPrice = 0;
    private Account account;

    public SeatSelection(Showtime showtime, Movie movie, Account account) {
        setTitle("Seat Selection");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        this.account = account;

        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 7;
        gbc.insets = new Insets(10, 10, 15, 10);
        JLabel screenLabel = new JLabel("SCREEN", SwingConstants.CENTER);
        screenLabel.setFont(new Font("Arial", Font.BOLD, 16));
        screenLabel.setForeground(Color.blue);
        add(screenLabel, gbc);


        gbc.gridwidth = 1;

        List<Seat> allSeats = (List<Seat>)(showtime.getShowtimeInfo().get("seats"));

        String[][] seatRows = {
                {"E", "Standard"},
                {"D"},
                {"C", "Premium"},
                {"B"},
                {"A", "Honeymoon"}
        };

        int seatsPerRow = 5;  // 5 seats per row

        for (int i = 0; i < seatRows.length; i++) {
            final int row = i;
            gbc.gridy = row + 1;
            gbc.gridx = 0;
            JLabel seatRowLabel = new JLabel(seatRows[row][0]);
            gbc.insets = new Insets(10, 10, 10, 10);
            add(seatRowLabel, gbc);

            if (seatRows[row][0].equals("A") || seatRows[row][0].equals("C") || seatRows[row][0].equals("E")) {
                JLabel seatTypeLabel = new JLabel("(" + seatRows[row][1] + ")");
                gbc.gridx = 1;
                add(seatTypeLabel, gbc);
            } else {
                gbc.gridx = 1;
                add(new JLabel(" "), gbc);
            }

            createSeatButtons(row, seatRows[row], gbc, showtime);
        }

        // SELECTED SEAT
        gbc.gridy = seatRows.length + 1;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(20, 10, 5, 10);
        selectedSeatLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(selectedSeatLabel, gbc);

        // (under SELECTED SEAT)
        gbc.gridy = seatRows.length + 2;
        add(selectedSeatListLabel, gbc);

        // TOTAL PRICE
        gbc.gridy = seatRows.length + 1;
        gbc.gridx = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(20, 10, 5, 10);
        totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(totalPriceLabel, gbc);

        // (under TOTAL PRICE)
        gbc.gridy = seatRows.length + 2;
        gbc.gridx = 5;
        add(totalPriceValueLabel, gbc);

        // Create "BUY NOW" button
        JButton buyNowButton = new JButton("BUY NOW");
        gbc.gridy = seatRows.length + 3;
        gbc.gridx = 0;
        gbc.gridwidth = 7;
        gbc.insets = new Insets(20, 10, 10, 10);
        gbc.anchor = GridBagConstraints.EAST;

        buyNowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectedSeats.isEmpty()){
                    JOptionPane.showMessageDialog(SeatSelection.this,
                            "YOU HAVE TO SELECTED THE SEAT!",
                            "Limit Exceeded",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    new PaymentUI(showtime, movie, selectedSeats, totalPrice, account).setVisible(true);
                    System.out.println("New Payment UI");
                }
            }
        });

        add(buyNowButton, gbc);

        // CREATE "RESET" BUTTON
        JButton resetButton = new JButton("RESET");
        gbc.gridy = seatRows.length + 3;
        gbc.gridx = 0;
        gbc.gridwidth = 7;
        gbc.insets = new Insets(20, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // รีเซ็ตปุ่มเฉพาะที่นั่งที่ผู้ใช้ปัจจุบันเลือก
                for (int i = 0; i < seatButtons.length; i++) {
                    for (int j = 0; j < seatButtons[i].length; j++) {
                        JButton seatButton = seatButtons[i][j];
                        Seat seat = allSeats.get((4 - i) * 5 + j); // ดึงที่นั่งจาก allSeats

                        // รีเซ็ตปุ่มเฉพาะที่นั่งที่ผู้ใช้ปัจจุบันเลือก
                        if (selectedSeats.contains(seat)) {
                            seatButton.setEnabled(true);
                        }
                    }
                }

                // ล้างข้อมูลที่นั่งที่ถูกเลือก
                selectedSeats.clear();

                // ตั้งค่า Total Price กลับเป็น 0
                totalPrice = 0;

                // อัพเดตข้อมูลที่นั่งและราคาบนหน้าจอ
                updateSelectedSeatLabel();
                updateTotalPriceLabel();
            }
        });


        add(resetButton, gbc);
        setVisible(true);
    }

    private void createSeatButtons(int row, String[] seatRow, GridBagConstraints gbc, Showtime showtime) {
        // Get the List<Seat> from the showtime info
        List<Seat> allSeats = (List<Seat>) (showtime.getShowtimeInfo().get("seats"));

        int seatsPerRow = 5;  // 5 seats per row

        // Ensure we have enough seats for the rows
        if (allSeats == null || allSeats.size() < (row + 1) * seatsPerRow) {
            throw new IllegalArgumentException("Seat info size must match the row and column structure");
        }

        // Reverse the row order (row 0 should be "E", row 1 should be "D", etc.)
        String[] reversedRows = {"E", "D", "C", "B", "A"};

        // Calculate the index for the current row (which is actually reversed)
        for (int j = 0; j < seatsPerRow; j++) {
            gbc.gridx = j + 2;  // Adjust grid layout position

            // Calculate the correct seat index from the original seat list
            int seatIndex = (4 - row) * seatsPerRow + j;  // Reverse the row index to match the seat's actual position
            final Seat seat = allSeats.get(seatIndex);  // Get the Seat object

            // Create the new seat name (e.g., "E1", "D1")
            final String seatName = reversedRows[row] + (j + 1);

            // Create the seat button with the seat name
            final JButton seatButton = new JButton(seatName);
            seatButton.setPreferredSize(new Dimension(50, 30));
            seatButtons[row][j] = seatButton;  // Assuming seatButtons is a 2D array of JButton

            // Check if the seat is available and set button state accordingly
            if (!seat.getisAvailable()) {
                seatButton.setEnabled(false);  // Disable the button if the seat is not available
            }

            // Add ActionListener for seat selection
            seatButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!seat.getisAvailable()) {
                        JOptionPane.showMessageDialog(SeatSelection.this,
                                "Seat " + seatName + " is not available!",
                                "Seat Selection",
                                JOptionPane.WARNING_MESSAGE);
                        return; // Exit if the seat is not available
                    }

                    Dictionary<String, Object> seatInfo = seat.getSeatInfo();
                    Float seatPriceFloat = (Float) seatInfo.get("seatPrice");
                    int seatPrice = seatPriceFloat != null ? seatPriceFloat.intValue() : 0;

                    // Use the Seat object for selection
                    if (selectedSeats.contains(seat)) {
                        selectedSeats.remove(seat);
                        seatButton.setEnabled(true);  // Re-enable the button
                        totalPrice -= seatPrice;
                        JOptionPane.showMessageDialog(SeatSelection.this,
                                "Seat " + seatName + " has been deselected",
                                "Seat Selection",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else if (selectedSeats.size() < 5) {
                        selectedSeats.add(seat);
                        seatButton.setEnabled(false); // Disable the button as it's now selected
                        totalPrice += seatPrice;
                        JOptionPane.showMessageDialog(SeatSelection.this,
                                "SEAT " + seatName + " SELECTED",
                                "Seat Selection",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(SeatSelection.this,
                                "YOU CAN ONLY SELECT UP TO 5 SEATS!",
                                "Limit Exceeded",
                                JOptionPane.WARNING_MESSAGE);
                    }

                    updateSelectedSeatLabel();
                    updateTotalPriceLabel();
                }
            });


            // Add seat button to the layout
            add(seatButton, gbc);
        }
    }


    private int getSeatPrice(String seatRow) {
        if (seatRow.equals("A") || seatRow.equals("B")) {
            return 300;
        } else if (seatRow.equals("C") || seatRow.equals("D")) {
            return 200;
        } else {
            return 100;
        }
    }

    private void updateSelectedSeatLabel() {
        List<String> seatNames = new ArrayList<>();
        for (Seat seat : selectedSeats) {
            // Get the seat info dictionary from the Seat object
            Dictionary<String, Object> seatInfo = seat.getSeatInfo();

            // Retrieve the seatNumber (which acts as the seat name) from the dictionary
            String seatNumber = (String) seatInfo.get("seatNumber");

            // Add the seat number to the list of selected seat names
            if (seatNumber != null) {
                seatNames.add(seatNumber);
            }
        }

        // Join the seat names into a single string and update the label
        selectedSeatListLabel.setText(String.join(", ", seatNames));
    }


    private void updateTotalPriceLabel() {
        totalPriceValueLabel.setText(totalPrice + " THB");
    }

    // Your Movie and Showtime classes should be here with the methods like getisAvailable, getShowtimeInfo, etc.

    public static void main(String[] args) {
        // Example test
        // new SeatSelection(new Showtime(), new Movie()).setVisible(true);
    }
}
