import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Dictionary;
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

        List<Seat> Allseats = (List<Seat>)(showtime.getShowtimeInfo().get("seats"));

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
            //System.out.println(showtime.getShowtimeInfo().get("seats"));
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

                selectedSeats.clear();

                for (int i = 0; i < seatButtons.length; i++) {
                    for (int j = 0; j < seatButtons[i].length; j++) {
                        seatButtons[i][j].setEnabled(true);
                    }
                }
                totalPrice = 0;
                updateSelectedSeatLabel();
                updateTotalPriceLabel();
            }
        });

        add(resetButton, gbc);
        setVisible(true);
    }

    private void createSeatButtons(int row, String[] seatRow, GridBagConstraints gbc, Showtime showtime) {
        // Get the List<Seat> from the showtime info
        List<Seat> Allseats = (List<Seat>)(showtime.getShowtimeInfo().get("seats"));

        int seatsPerRow = 5;  // 5 seats per row
        int totalRows = Allseats.size() / seatsPerRow;  // Calculate based on seat count

        // Ensure we have enough seats for the rows
        if (Allseats == null || Allseats.size() != totalRows * seatsPerRow) {
            throw new IllegalArgumentException("Seat info size must match the row and column structure");
        }

        // Reverse the row order (row 0 should be "E", row 1 should be "D", etc.)
        String[] reversedRows = {"E", "D", "C", "B", "A"};  // This will map the rows properly

        // Iterate through each seat in the current row
        for (int j = 0; j < seatsPerRow; j++) {
            gbc.gridx = j + 2;  // Adjust grid layout position

            final int seatIndex = row * seatsPerRow + j;  // Calculate seat index in the list
            final Seat seat = Allseats.get(seatIndex);  // Get the Seat object

            // Extract seat information from the seat's getSeatInfo() dictionary
            Dictionary<String, Object> seatInfo = seat.getSeatInfo();
            final String originalSeatName = (String) seatInfo.get("seatNumber");  // The original seat name (e.g., "A1", "B1", etc.)

            // Adjust seat row for the reversed order (E -> 0, D -> 1, etc.)
            final String rowIdentifier = reversedRows[row];  // Get the correct row identifier (E, D, C, B, A)

            // Create the new seat name (e.g., "E1", "D2")
            final String seatName = rowIdentifier + (j + 1);

            // Create the seat button with the seat name
            final JButton seatButton = new JButton(seatName);
            seatButton.setPreferredSize(new Dimension(50, 30));
            seatButtons[row][j] = seatButton;

            // Check if the seat is available
            if (!seat.getisAvailable()) {
                seatButton.setEnabled(false);  // Disable the button if the seat is not available
            }

            seatButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int seatPrice = getSeatPrice(rowIdentifier);  // Now pass the correct row (E, D, etc.)

                    // Use the correct seatName for selection
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

            // Add seat button to the layout
            add(seatButton, gbc);
        }
    }






    private int getSeatPrice(String row) {
        switch (row) {
            case "A":
                return 500;
            case "B":
            case "C":
                return 300;
            case "D":
            case "E":
            default:
                return 220;
        }
    }

    private void updateSelectedSeatLabel() {
        StringBuilder seatText = new StringBuilder();
        for (String seat : selectedSeats) {
            seatText.append(seat).append(" ");
        }
        selectedSeatListLabel.setText(seatText.toString().trim());
    }

    private void updateTotalPriceLabel() {
        totalPriceValueLabel.setText(totalPrice + " THB");
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        //new SeatSelection(Showtime showtime, Movie movie);
    }
}
