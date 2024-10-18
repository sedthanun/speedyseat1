import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import com.formdev.flatlaf.themes.*;

public class SeatSelection extends JFrame {
    private final ArrayList<String> selectedSeats = new ArrayList<>();
    private final JButton[][] seatButtons = new JButton[5][5];
    private final JLabel selectedSeatLabel = new JLabel("SELECTED SEAT");
    private final JLabel totalPriceLabel = new JLabel("TOTAL PRICE");
    private final JLabel selectedSeatListLabel = new JLabel("");
    private final JLabel totalPriceValueLabel = new JLabel("0 THB");
    private int totalPrice = 0;

    public SeatSelection(Showtime showtime, Movie movie) {
        setTitle("Seat Selection");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

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
                    new PaymentUI(showtime, movie, selectedSeats, totalPrice).setVisible(true);
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

                // รีเซ็ตเฉพาะที่นั่งที่ผู้ใช้ปัจจุบันเลือก
                for (int i = 0; i < seatButtons.length; i++) {
                    for (int j = 0; j < seatButtons[i].length; j++) {
                        JButton seatButton = seatButtons[i][j];
                        String seatName = seatButton.getText();

                        // รีเซ็ตปุ่มเฉพาะที่นั่งที่ผู้ใช้ปัจจุบันเลือก
                        if (selectedSeats.contains(seatName)) {
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
            seatButtons[row][j] = seatButton;

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
                        return;
                    }

                    int seatPrice = getSeatPrice(reversedRows[row]);

                    if (selectedSeats.contains(seatName)) {
                        selectedSeats.remove(seatName);
                        seatButton.setEnabled(true);
                        totalPrice -= seatPrice;
                        JOptionPane.showMessageDialog(SeatSelection.this,
                                "Seat " + seatName + " has been deselected",
                                "Seat Selection",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else if (selectedSeats.size() < 5) {
                        selectedSeats.add(seatName);
                        seatButton.setEnabled(false);
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
        selectedSeatListLabel.setText(String.join(", ", selectedSeats));
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
