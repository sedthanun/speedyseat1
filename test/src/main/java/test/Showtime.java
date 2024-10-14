package test;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import Database.*;

public class Showtime extends JFrame {
    private JLabel showtimeLabel;
    private JLabel theaterLabel;
    private JPanel showtimePanel;
    private ArrayList<JButton> showtimeButtons;

    public Showtime() {
        setTitle("ShowtimeDemo");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel ที่แบ่งเป็น 2 ส่วน
        JPanel mainPanel = new JPanel(new GridLayout(1, 2));

        // Left panel - โปสเตอร์ กับ คำอธิบาย
        JPanel leftPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Movie poster
        JPanel posterContainer = new JPanel(new BorderLayout());
        JLabel posterLabel = new JLabel();
        posterLabel.setPreferredSize(new Dimension(500, 600));  // Set a larger size

        //ดึงค่าจาก dtb
        try {
            ResultSet rs = DBquery.getInstance().getSelect("SELECT * FROM Movie WHERE movie_id = 1;");
            while (rs.next()) {
                byte[] ii = rs.getBytes("movie_poster"); // SELECT
                posterLabel.setIcon(new ImageIcon(ByteToImage(ii)));
            }
            DBquery.getInstance().disconnect(); //อย่าลืม disconnectด้วยครับ
        } catch (Exception e) {
            e.printStackTrace();
        }


        // Load image for poster
        ImageIcon posterImage = new ImageIcon("C:/Users/pleum/Downloads/tee Yod.jpg"); // poster path
        Image scaledImage = posterImage.getImage().getScaledInstance(500, 600, Image.SCALE_SMOOTH);
        posterContainer.add(posterLabel, BorderLayout.CENTER);






//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.weightx = 1.0;
//        gbc.weighty = 0.8;
        gbc.fill = GridBagConstraints.BOTH;
        leftPanel.add(posterContainer, gbc);

        // Movie details panel (title, genre, showtime, theater)
        JPanel posterDescription = new JPanel(new GridLayout(4, 1, 5, 5));
        JLabel titleLabel = new JLabel("Title: Sample Movie");
        JLabel genreLabel = new JLabel("Genre: Action");
        theaterLabel = new JLabel("Theater: None Selected");
        showtimeLabel = new JLabel("Showtime: None Selected");

        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        genreLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        theaterLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        showtimeLabel.setFont(new Font("SansSerif", Font.BOLD, 18));

        posterDescription.add(titleLabel);
        posterDescription.add(genreLabel);
        posterDescription.add(theaterLabel);
        posterDescription.add(showtimeLabel);

        gbc.gridy = 1;
        gbc.weighty = 0.2;
        leftPanel.add(posterDescription, gbc);

        // Middle panel - Theater and Showtime buttons
        JPanel middlePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcMiddle = new GridBagConstraints();
        gbcMiddle.gridwidth = GridBagConstraints.REMAINDER;
        gbcMiddle.fill = GridBagConstraints.HORIZONTAL;
        gbcMiddle.insets = new Insets(5, 5, 5, 5);

        ArrayList<JButton> theaterButtons = createTheaterButtons();
        showtimeButtons = createShowtimeButtons();

        // Add theater buttons
        JLabel showLabel = new JLabel("Select Theater:", SwingConstants.CENTER);
        showLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        middlePanel.add(showLabel, gbcMiddle);

        for (JButton button : theaterButtons) {
            button.setFont(new Font("SansSerif", Font.PLAIN, 18));
            middlePanel.add(button, gbcMiddle);
        }

        // Showtime panel (initially hidden)
        showtimePanel = new JPanel(new GridBagLayout());
        JLabel showtimeLabel = new JLabel("Select Showtime:", SwingConstants.CENTER);
        showtimeLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        showtimePanel.add(showtimeLabel, gbcMiddle);

        for (JButton button : showtimeButtons) {
            button.setFont(new Font("SansSerif", Font.PLAIN, 18));
            showtimePanel.add(button, gbcMiddle);
        }
        showtimePanel.setVisible(false);

        // Add components to middle panel
        gbcMiddle.weighty = 1;
        gbcMiddle.fill = GridBagConstraints.BOTH;
        middlePanel.add(showtimePanel, gbcMiddle);

        // Add scroll pane to middle panel
        JScrollPane scrollPane = new JScrollPane(middlePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Add panels to main panel
        mainPanel.add(leftPanel);
        mainPanel.add(scrollPane);

        // Adding main panel to the frame
        add(mainPanel);
    }

    private ArrayList<JButton> createTheaterButtons() {
        ArrayList<JButton> buttons = new ArrayList<>();
        String[] theaters = {"Theater 1", "Theater 2", "Theater 3", "Theater 4", "Theater 5"};

        for (String theater : theaters) {
            JButton button = new JButton(theater);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    theaterLabel.setText("Theater: " + theater);
                    showtimePanel.setVisible(true);  // Show showtime buttons after selecting a theater
                    showtimeLabel.setText("Showtime: None Selected");  // Reset showtime selection
                }
            });
            buttons.add(button);
        }
        return buttons;
    }

    private ArrayList<JButton> createShowtimeButtons() {
        ArrayList<JButton> buttons = new ArrayList<>();
        String[] showtimes = {"10:00 AM", "1:00 PM", "4:00 PM", "7:00 PM", "10:00 PM"};

        for (String time : showtimes) {
            JButton button = new JButton(time);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showtimeLabel.setText("Showtime: " + time);
                }
            });
            buttons.add(button);
        }
        return buttons;
    }
    public BufferedImage ByteToImage(byte[] b){
        try {

            ByteArrayInputStream bis = new ByteArrayInputStream(b);
            BufferedImage image = ImageIO.read(bis);
            return image;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            new Showtime().setVisible(true);
        });
    }
}