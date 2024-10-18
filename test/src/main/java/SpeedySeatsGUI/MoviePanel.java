package SpeedySeatsGUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Movie.*;

public class MoviePanel extends JPanel implements MouseListener{
    private JLabel imgLabel, nameLabel, ratingLabel;
    private JPanel imgPanel, detailPanel, namePanel, ratingPanel, nPanel, wPanel, ePanel, sPanel, w2Panel;
    private Movie movie;
    public MoviePanel(Movie movie){
        this.movie = movie;
        nameLabel = new JLabel((String)movie.getMovieInfo().get("movieName"));
        ratingLabel = new JLabel(String.format("      Rating: %.02f", (Float)movie.getMovieInfo().get("movieRating")));
        imgPanel = new JPanel();
        detailPanel = new JPanel();
        namePanel = new JPanel();
        ratingPanel = new JPanel();
        nPanel = new BlankPanel(16, 16, new Color(35, 35, 35));
        wPanel = new BlankPanel(16, 16, new Color(35, 35, 35));
        ePanel = new BlankPanel(16, 16, new Color(35, 35, 35));
        sPanel = new BlankPanel(16, 7, new Color(35, 35, 35));
        w2Panel = new BlankPanel(18, 18, new Color(35, 35, 35));

        this.addMouseListener(this);

        Image image = new ImageIcon((byte[])movie.getMovieInfo().get("moviePoster")).getImage();
//        ImageIcon icon = new ImageIcon(image.getScaledInstance(224, 126,  java.awt.Image.SCALE_SMOOTH));
        ImageIcon icon = new ImageIcon(image.getScaledInstance(168, 224,  java.awt.Image.SCALE_SMOOTH));
        imgLabel = new JLabel(icon);

        imgPanel.setLayout(new BorderLayout());
        imgPanel.add(nPanel, BorderLayout.NORTH);
        imgPanel.add(wPanel, BorderLayout.WEST);
        imgPanel.add(imgLabel);
        imgPanel.add(ePanel, BorderLayout.EAST);
        imgPanel.add(sPanel, BorderLayout.SOUTH);

        namePanel.setLayout(new BorderLayout());
        nameLabel.setFont(nameLabel.getFont().deriveFont(16f));
        namePanel.add(w2Panel, BorderLayout.WEST);
        namePanel.add(nameLabel);



        ratingLabel.setHorizontalTextPosition(JLabel.LEFT);
        Image starImg = new ImageIcon("./pics/star.png").getImage();
        ImageIcon starIcon = new ImageIcon(starImg.getScaledInstance(12, 12,  java.awt.Image.SCALE_SMOOTH));
        ratingLabel.setIcon(starIcon);

        detailPanel.setLayout(new GridLayout(2, 1));
        ratingPanel.setLayout(new GridLayout(1, 2));
        ratingPanel.add(ratingLabel);
        ratingPanel.setPreferredSize(new Dimension(224, 40));

        detailPanel.add(namePanel);
        detailPanel.add(ratingPanel);

        imgPanel.setBackground(new Color(35, 35, 35));
        namePanel.setBackground(new Color(35, 35, 35));
        ratingPanel.setBackground(new Color(35, 35, 35));

        nameLabel.setForeground(Color.white);
        ratingLabel.setForeground(new Color(255, 255, 255));


//        this.setPreferredSize(new Dimension(224, 256));
        this.setPreferredSize(new Dimension(225, 300));
        this.setBackground(new Color(35, 35, 35));
        this.setLayout(new BorderLayout());
        this.add(imgPanel);
        this.add(detailPanel, BorderLayout.SOUTH);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    public Movie getSelectedMovie(){
        return this.movie;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource().equals(this)){
            namePanel.setBackground(new Color(61, 61, 61));
            ratingPanel.setBackground(new Color(61, 61, 61));
            nPanel.setBackground(new Color(61, 61, 61));
            ePanel.setBackground(new Color(61, 61, 61));
            wPanel.setBackground(new Color(61, 61, 61));
            sPanel.setBackground(new Color(61, 61, 61));
            w2Panel.setBackground(new Color(61, 61, 61));
            imgPanel.setBackground(new Color(61, 61, 61));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().equals(this)){
            namePanel.setBackground(new Color(35, 35, 35));
            ratingPanel.setBackground(new Color(35, 35, 35));
            nPanel.setBackground(new Color(35, 35, 35));
            ePanel.setBackground(new Color(35, 35, 35));
            sPanel.setBackground(new Color(35, 35, 35));
            wPanel.setBackground(new Color(35, 35, 35));
            w2Panel.setBackground(new Color(35, 35, 35));
            imgPanel.setBackground(new Color(35, 35, 35));
        }
    }
}
