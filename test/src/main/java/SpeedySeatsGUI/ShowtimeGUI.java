package SpeedySeatsGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.*;

import Booking.Account;
import Movie.*;
import Theatre.*;

public class ShowtimeGUI extends JFrame {
    private JLabel showtimeLabel;
    private JLabel theaterLabel;
    private JPanel showtimePanel;
    private ArrayList<JButton> showtimeButtons;
    private GridBagConstraints gbcMiddle;
    private Account account;

    public ShowtimeGUI(Movie movie, Account account) {
        this.account = account;
        setTitle("ShowtimeDemo");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel ที่แบ่งเป็น 2 ส่วน
        JPanel mainPanel = new JPanel(new GridLayout(1, 2));

        // Left panel - โปสเตอร์ กับ คำอธิบาย
        JPanel leftPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel blank1 = new JLabel();
        // Movie poster
        JPanel posterContainer = new JPanel(new BorderLayout());
        posterContainer.setPreferredSize(new Dimension(500, 600));
        JLabel posterLabel = new JLabel();
        posterLabel.setPreferredSize(new Dimension(500, 600));  // Set a larger size
        posterLabel.setHorizontalAlignment(SwingConstants.CENTER);

        byte[] moviePos = (byte[]) movie.getMovieInfo().get("moviePoster");
        posterLabel.setIcon(new ImageIcon(moviePos));

        // Load image for poster

        posterContainer.add(posterLabel, BorderLayout.CENTER);


//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.weightx = 1.0;
//        gbc.weighty = 0.8;
        gbc.fill = GridBagConstraints.BOTH;
        leftPanel.add(posterContainer, gbc);

        // Movie details panel (title, genre, showtime, theater)
        JPanel posterDescription = new JPanel(new GridLayout(4, 2, 3, 3));
        JPanel under = new JPanel(new GridLayout(2, 1, 3, 3));
        JLabel titleLabel = new JLabel("Title: " + movie.getMovieInfo().get("movieName"));
        JLabel genreLabel = new JLabel("Genre: " + movie.getMovieInfo().get("movieGenre"));
        JTextArea desLabel = new JTextArea("Description: " + movie.getMovieInfo().get("movieDescription"));
        desLabel.setFocusable(false);
        desLabel.setLineWrap(true);
        desLabel.setWrapStyleWord(true);
        desLabel.setEditable(false);

        JLabel ratingLabel = new JLabel("Rating: " + movie.getMovieInfo().get("movieRating"));
        JLabel runtimeLabel = new JLabel("Runtime: "+movie.getMovieInfo().get("movieRuntime") + " minutes");
        theaterLabel = new JLabel("Theater: Unselected");
        showtimeLabel = new JLabel("Showtime: Unselected");

        titleLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        genreLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        theaterLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        showtimeLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        desLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        ratingLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        runtimeLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));


        posterDescription.add(titleLabel);
        posterDescription.add(blank1);
        posterDescription.add(genreLabel);
        posterDescription.add(theaterLabel);
        posterDescription.add(showtimeLabel);
        posterDescription.add(ratingLabel);
        posterDescription.add(runtimeLabel);

        gbc.gridy = 1;
        gbc.weighty = 0.2;
        under.add(posterDescription);
        under.add(desLabel);
        leftPanel.add(under,gbc);


        // Middle panel - Theater and Showtime buttons
        JPanel middlePanel = new JPanel(new GridBagLayout());
        gbcMiddle = new GridBagConstraints();
        gbcMiddle.gridwidth = GridBagConstraints.REMAINDER;
        gbcMiddle.fill = GridBagConstraints.HORIZONTAL;
        gbcMiddle.insets = new Insets(5, 5, 5, 5);

        ArrayList<JButton> theaterButtons = createTheaterButtons(movie);






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
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private ArrayList<JButton> createTheaterButtons(Movie movie) {
        ArrayList<JButton> buttons = new ArrayList<>();
        List<Showtime> showtime = movie.getShowtimeList();
        List<Theatre> theatre = new ArrayList<>();
        Set<String> theatrenum = new HashSet<>();
        for(Showtime the : showtime) {
            theatre.add((Theatre)the.getShowtimeInfo().get("theatre"));
        }
        for(Theatre theaternum : theatre){
            theatrenum.add(String.valueOf(theaternum.getTheatreInfo().get("theatreNumber")));
        }
        for (String theatrenum1 : theatrenum) {
            JButton button = new JButton(theatrenum1);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showtimePanel.removeAll();
                    showtimePanel.setVisible(false);
                    showtimeButtons = createShowtimeButtons(movie,e.getActionCommand());
                    theaterLabel.setText("Theater: " + (theatrenum1));
                    showtimePanel.setVisible(true);  // Show showtime buttons after selecting a theater
                    showtimeLabel.setText("Showtime: None Selected");  // Reset showtime selection
                    for (JButton button : showtimeButtons) {
                        button.setFont(new Font("SansSerif", Font.PLAIN, 18));

                        GridBagConstraints grid1 = new GridBagConstraints();
                        grid1.gridwidth = GridBagConstraints.REMAINDER;
                        grid1.fill = GridBagConstraints.NONE;
                        grid1.insets = new Insets(5, 5, 5, 5);
                        showtimePanel.add(button, grid1);
                    }

                }
            });
            buttons.add(button);
        }
        return buttons;
    }

    private ArrayList<JButton> createShowtimeButtons(Movie movie, String num) {
        ArrayList<JButton> buttons = new ArrayList<>();
        List<Showtime> showtime = movie.getShowtimeList();
        List<Showtime> sepShowtime = new ArrayList<>();

        for(Showtime times : showtime){
            Theatre spt = (Theatre)times.getShowtimeInfo().get("theatre");
            String numt = String.valueOf(spt.getTheatreInfo().get("theatreNumber"));
            if(Integer.parseInt(numt) == Integer.parseInt(num)){
                sepShowtime.add(times);
            }
        }
        for (Showtime ab: sepShowtime) {
            JButton button = new JButton(String.valueOf(ab.getShowtimeInfo().get("showtimeDateTime")));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showtimeLabel.setText("Showtime: " + ab.getShowtimeInfo().get("showtimeDateTime"));
                    new SeatSelection(ab,movie,account);
                }
            });
            buttons.add(button);
        }
        return buttons;
    }

    public BufferedImage ByteToImage(byte[] b) {
        try {

            ByteArrayInputStream bis = new ByteArrayInputStream(b);
            BufferedImage image = ImageIO.read(bis);
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

//    public Movie frommovie(){
//        try{
//            ResultSet rs = DBquery.getInstance().getSelect("SELECT * FROM Movie WHERE movie_id = 1");
//            while(rs.next()){
//                int movieID = rs.getInt("movie_id");
//                String movieName = rs.getString("movie_name");
//                String movieDescription = rs.getString("movie_description");
//                int movieRuntime = rs.getInt("movie_runtime");
//                String movieGenre = rs.getString("movie_genre");
//                float movierating = rs.getFloat("movie_rating");
//                byte[] moviePoster = rs.getBytes("movie_poster");
//            }
//        }catch(SQLException e){
//
//        }
//    }
//    Movie movie = new Movie(int movieID, String movieName, String movieDescription, int movieRuntime, String movieGenre, float movieRating, byte moviePoster);

