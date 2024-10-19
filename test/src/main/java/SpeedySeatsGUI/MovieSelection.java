package SpeedySeatsGUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

import Booking.Account;
import com.formdev.flatlaf.themes.*;
import Movie.*;
public class MovieSelection implements MouseListener, ActionListener, WindowListener{
    private JFrame homeframe;
    private JPanel homepanel;
    private JPanel top;
    private JPanel left;
    private JPanel right;
    private JPanel b1, b2;
    private JScrollPane homeScrollPane;
    private JLabel loading, pageName;

    private ArrayList<MoviePanel> moviePanels;

    public MovieSelection(){
        homeframe = new JFrame("SpeadySeats");
        homepanel = new JPanel();
        top = new JPanel();
        left = new JPanel();
        right = new JPanel();
        b1 = new BlankPanel(1920, 5, new Color(30, 30, 30));
        b2 = new BlankPanel(1920, 5, new Color(30, 30, 30));
        homeScrollPane = new JScrollPane(homepanel);
        loading = new JLabel("Loading...");
        pageName = new JLabel("Select Movie");

        homeframe.addWindowListener(this);


        left.setPreferredSize(new Dimension(100, 720));
        right.setPreferredSize(new Dimension(100, 720));

        ((FlowLayout)homepanel.getLayout()).setHgap(24);
        ((FlowLayout)homepanel.getLayout()).setVgap(24);

        pageName.setForeground(Color.WHITE);
        pageName.setFont(pageName.getFont().deriveFont(16f));
        top.setPreferredSize(new Dimension(1920, 50));
        top.add(b1, BorderLayout.NORTH);
        top.add(pageName, BorderLayout.CENTER);
        top.add(b2, BorderLayout.SOUTH);

        loading.setForeground(Color.WHITE);
        homepanel.add(loading);

        homeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        homeScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        homeScrollPane.getVerticalScrollBar().setUnitIncrement(15);
        homeScrollPane.getVerticalScrollBar().setBackground(new Color(30, 30, 30));
        homeScrollPane.getVerticalScrollBar().setUI(new ScrollBarUI());
        homeScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(8, 1));
        homeScrollPane.setBorder(BorderFactory.createEmptyBorder());

        homeframe.setLayout(new BorderLayout());
        homeframe.add(top, BorderLayout.NORTH);
        homeframe.add(homeScrollPane, BorderLayout.CENTER);
        homeframe.add(left, BorderLayout.WEST);
        homeframe.add(right, BorderLayout.EAST);

        homeframe.setBackground(new Color(30, 30, 30));
        top.setBackground(new Color(30, 30, 30));
        left.setBackground(new Color(30, 30, 30));
        right.setBackground(new Color(30, 30, 30));
        homepanel.setBackground(new Color(30, 30, 30));




        homeframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeframe.setSize(1080,720);
        homeframe.setVisible(true);
    }

    public JFrame getFrame(){
        return this.homeframe;
    }

    public void refresh(){
        Movie.setMovieListFromDB();
        Movie.setShowtimeListFromDB();
        loading.setVisible(false);
    }

    public void mouseClicked(MouseEvent e) {
        for( MoviePanel moviePanels : moviePanels){
            if (e.getSource().equals(moviePanels)){
                System.out.println("movieID:"+moviePanels.getSelectedMovie().getMovieInfo().get("movieID"));
                new ShowtimeGUI(moviePanels.getSelectedMovie(), new Account("John", 1)).setVisible(true);
            }
        }
    }
    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }

    public void setSize(Dimension size) {
        homeframe.setSize(size);
    }

    public void setLocation(Point location) {
        homeframe.setLocation(location);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void windowOpened(WindowEvent e) {

        this.refresh();

        moviePanels = new ArrayList<MoviePanel>();
        for (Movie movie : Movie.getMovieList()){
            moviePanels.add(new MoviePanel(movie));
        }
        for( MoviePanel mPanel : moviePanels){
            homepanel.add(mPanel);
            mPanel.addMouseListener(this);
        }
//        homepanel.setPreferredSize(new Dimension(880, 310*((int)(moviePanels.size()/3)+1)));
        if (moviePanels.size()/3.0==(int)(moviePanels.size()/3)){
            homepanel.setPreferredSize(new Dimension(880, 332*((int)(moviePanels.size()/3))));
        }else{
            homepanel.setPreferredSize(new Dimension(880, 332*((int)(moviePanels.size()/3)+1)));
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {}

    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}

    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        new MovieSelection();
    }
}

