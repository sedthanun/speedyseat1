import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import Payments.PaymentUI;
import com.formdev.flatlaf.themes.*;

public class SeatSelection extends JFrame {
    private final ArrayList<String> selectedSeats = new ArrayList<>();
    private final JButton[][] seatButtons = new JButton[5][5];
    private final JLabel selectedSeatLabel = new JLabel("SELECTED SEAT");
    private final JLabel totalPriceLabel = new JLabel("TOTAL PRICE");
    private final JLabel selectedSeatListLabel = new JLabel("");
    private final JLabel totalPriceValueLabel = new JLabel("0 THB");
    private int totalPrice = 0; // Variable to track the total price

    public SeatSelection() {
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
        screenLabel.setForeground(Color.blue); // ตั้งค่าสีฟ้าให้กับข้อความ
        add(screenLabel, gbc);

        gbc.gridwidth = 1;

        String[][] seatRows = {
                {"E", "Standard"},
                {"D"},
                {"C", "Premium"},
                {"B"},
                {"A", "Honeymoon"}
        };

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

            for (int j = 0; j < 5; j++) {
                gbc.gridx = j + 2;
                final String seatName = seatRows[row][0] + (1 + j);
                final JButton seatButton = new JButton(seatName);
                seatButton.setPreferredSize(new Dimension(50, 30));
                seatButtons[row][j] = seatButton;

                seatButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int seatPrice = getSeatPrice(seatRows[row][0]); // Get the price of the seat

                        if (selectedSeats.contains(seatName)) {
                            selectedSeats.remove(seatName);
                            seatButton.setEnabled(true);
                            totalPrice -= seatPrice; // Subtract the price when deselecting
                            JOptionPane.showMessageDialog(SeatSelection.this,
                                    "Seat " + seatName + " has been deselected",
                                    "Seat Selection",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else if (selectedSeats.size() < 5) {
                            selectedSeats.add(seatName);
                            seatButton.setEnabled(false);
                            totalPrice += seatPrice; // Add the price when selecting
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

        // SELECTED SEAT
        gbc.gridy = seatRows.length + 1;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(20, 10, 5, 10);
        selectedSeatLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(selectedSeatLabel, gbc);

        //  (under SELECTED SEAT)
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
                }else{
                    new PaymentUI().setVisible(true);
                    System.out.println("New Payment UI");
                }

            }
        });

        add(buyNowButton, gbc);

        // CREATE "RESET" BUTTON
        JButton ResetButton = new JButton("RESET");
        gbc.gridy = seatRows.length + 3;
        gbc.gridx = 0;
        gbc.gridwidth = 7;
        gbc.insets = new Insets(20, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        ResetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear the selected seats list
                selectedSeats.clear();

                // Reset the state of all seat buttons
                for (int i = 0; i < seatButtons.length; i++) {
                    for (int j = 0; j < seatButtons[i].length; j++) {
                        seatButtons[i][j].setEnabled(true);
                    }
                }

                // Reset total price
                totalPrice = 0;

                // Update the labels
                updateSelectedSeatLabel();
                updateTotalPriceLabel();
            }
        });

        add(ResetButton, gbc);
        add(ResetButton, gbc);



        setVisible(true);
    }

    // Price of the seat based on the row
    private int getSeatPrice(String row) {
        switch (row) {
            case "A":
                return 500; // Honeymoon
            case "B":
                return 300; // Premium
            case "C":
                return 300; // Premium
            case "D":
                return 220; // Standard
            case "E":
                return 220; // Standard
            default:
                return 220;
        }
    }

    // the selected seats method
    private void updateSelectedSeatLabel() {
        StringBuilder seatText = new StringBuilder();
        for (String seat : selectedSeats) {
            seatText.append(seat).append(" ");
        }
        selectedSeatListLabel.setText(seatText.toString().trim());
    }

    // update the total price method
    private void updateTotalPriceLabel() {
        totalPriceValueLabel.setText(totalPrice + " THB");
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        new SeatSelection();
    }
}
