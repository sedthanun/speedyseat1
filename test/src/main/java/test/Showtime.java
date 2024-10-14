package test;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

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
        JPanel leftPanel = new JPanel(new GridLayout(2,1));
        JLabel titleLabel = new JLabel("Title: Sample Movie");
        JLabel genreLabel = new JLabel("Genre: Action");
        JLabel Show = new JLabel("Select Theater:");
        JPanel blank1 = new JPanel(new GridLayout(2,1));
        showtimeLabel = new JLabel("Showtime: None Selected");
        theaterLabel = new JLabel("Theater: None Selected");
        titleLabel.setFont(new Font("SansSerif",Font.BOLD,18));
        genreLabel.setFont(new Font("SansSerif",Font.BOLD,18));
        showtimeLabel.setFont(new Font("SansSerif",Font.BOLD,18));
        theaterLabel.setFont(new Font("SansSerif",Font.BOLD,18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        genreLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        showtimeLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        theaterLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Movie poster
        JLabel posterLabel = new JLabel();
        posterLabel.setPreferredSize(new Dimension(150, 150));  // Set a fixed size
        posterLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Load image for poster
        ImageIcon posterImage = new ImageIcon("/C://Users//pleum//OneDrive//เดสก์ท็อป//images.jpg/"); // poster path
        posterLabel.setIcon(posterImage);  // Set the image as the poster
        leftPanel.add(posterLabel);


        // Movie details panel (title, genre, showtime, theater)
        JPanel posterDescription = new JPanel(new GridLayout(4, 1));
        posterDescription.add(titleLabel);
        posterDescription.add(genreLabel);
        posterDescription.add(theaterLabel);
        posterDescription.add(showtimeLabel);
        blank1.add(posterDescription);
        leftPanel.add(blank1);


        // Middle panel - Theater and Showtime buttons
        JPanel middlePanel = new JPanel(new GridLayout(0, 1));
        ArrayList<JButton> theaterButtons = createTheaterButtons();
        showtimeButtons = createShowtimeButtons();

        // Add theater buttons
        Show.setHorizontalAlignment(SwingConstants.CENTER);
        Show.setFont(new Font("SansSerif",Font.BOLD,40));
        middlePanel.add(Show);
        for (JButton button : theaterButtons) {
            middlePanel.add(button);
        }

        // Showtime panel (initially hidden)
        showtimePanel = new JPanel(new GridLayout(0, 1));
        showtimePanel.add(new JLabel("Select Showtime:"));
        for (JButton button : showtimeButtons) {
            showtimePanel.add(button);
        }
        showtimePanel.setVisible(false);
        middlePanel.add(showtimePanel);

        // Add panels to main panel
        mainPanel.add(leftPanel);
        mainPanel.add(middlePanel);

        // Adding main panel to the frame
        add(mainPanel);
    }

    private ArrayList<JButton> createTheaterButtons() {
        ArrayList<JButton> buttons = new ArrayList<>();
        String[] theaters = {"Theater 1", "Theater 2", "Theater 3", "Theater 4", "Theater 5"};

        for (String theater : theaters) {
            JButton button = new JButton(theater);
            button.setFont(new Font("SansSerif",Font.PLAIN,24));
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