import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

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

            createSeatButtons(row, seatRows[row], gbc);
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

    private void createSeatButtons(int row, String[] seatRow, GridBagConstraints gbc) {
        for (int j = 0; j < 5; j++) {
            gbc.gridx = j + 2;
            final String seatName = seatRow[0] + (1 + j);
            final JButton seatButton = new JButton(seatName);
            seatButton.setPreferredSize(new Dimension(50, 30));
            seatButtons[row][j] = seatButton;

            seatButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int seatPrice = getSeatPrice(seatRow[0]);
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
