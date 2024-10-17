package Payments;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.*;
import com.formdev.flatlaf.themes.*;

/**
 *
 * @author USER
 */

public class PaymentUI extends JFrame {

    /**
     * Creates new form PaymentUI
     */

    public PaymentUI() {
        initComponents();

        setTitle("SpeedySeat");

        qrCodePanel = new qrCodePanel();
        onlinePaymentPanel = new onlinePaymentPanel();
        payAtCounterPanel = new payAtCounterPanel();

        cardLayout = new CardLayout();

        mainPanel = new JPanel(cardLayout);

        mainPanel.add(qrCodePanel, "qrCodePanel");
        mainPanel.add(onlinePaymentPanel, "onlinePaymentPanel");
        mainPanel.add(payAtCounterPanel, "payAtCounterPanel");

        methodPanel.add(mainPanel, BorderLayout.CENTER);

        qrCode.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        onlineBanking.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        payAtCounter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        qrCode.setOpaque(true);
        qrCode.setBackground(new Color(65,65,65));
        currentCard = "qrCodePanel";

        setLocationRelativeTo(null);
        setResizable(false);

        ImageIcon icon = new ImageIcon(getClass().getResource("/Icon/Icon_App_Test.png")); // เปลี่ยน path_to_your_icon.png เป็นที่อยู่ของไอคอน
        setIconImage(icon.getImage());
    }

//    public PaymentUI(Movie movie) {
//        this();
//
//        byte[] moviePos = (byte[]) movie.getMovieInfo().get("moviePoster");
//        jLabel1.setIcon(new ImageIcon(moviePos));
//
//        dateShowtime.setText("   |  ");
//        movieName.setText(movie.getMovieInfo().get("movieName"));
//        movieGenre.setText(movie.getMovieInfo().get("movieGenre"));
//        runtime.setText(movie.getMovieInfo().get("movieRuntime"));
//        theatre.setText(movie.getMovieInfo().get("movieTheatre"));
//        sound.setText(movie.getMovieInfo().get("movieSound"));
//        subtitle.setText(movie.getMovieInfo().get("movieSubtitle"));
//        jLabel5.setText(movie.getMovieInfo().get("movieSceenformat"));
//        seat.setText(movie.getMovieInfo().get("seat"));
//        price.setText(movie.getMovieInfo().get("moviePrice"));
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel = new JPanel();
        jPanel1 = new JPanel();
        roundedPanel1 = new RoundedPanel();
        jLabel1 = new JLabel();
        dateShowtime = new JLabel();
        movieName = new JLabel();
        movieGenre = new JLabel();
        runtime = new JLabel();
        theatre = new JLabel();
        sound = new JLabel();
        subtitle = new JLabel();
        jLabel3 = new JLabel();
        seat = new JLabel();
        price = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        roundedPanel2 = new RoundedPanel();
        qrCode = new JLabel();
        onlineBanking = new JLabel();
        payAtCounter = new JLabel();
        methodPanel = new RoundedPanel();
        roundedPanel3 = new RoundedPanel();
        roundedPanel4 = new RoundedPanel();
        pay = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

//        jPanel1.setBackground(new java.awt.Color(30, 30, 30));

        roundedPanel1.setBackground(new Color(35, 35, 35));
        roundedPanel1.setPreferredSize(new Dimension(1162, 327));

        URL imgURL = getClass().getResource("/Icon/Test_Poster.jpg");
        if (imgURL != null) {
            jLabel1.setIcon(new ImageIcon(imgURL));
        } else {
            System.err.println("Image not found: " + "/Icon/Test_Poster.jpg");
        }

        try {
            jLabel1.setIcon(new ImageIcon(getClass().getResource("/Icon/Test_Poster.jpg")));
        } catch (NullPointerException e) {
            System.err.println("Image not found: " + e.getMessage());
        }


        jLabel1.setIcon(new ImageIcon(getClass().getResource("/Icon/Test_Poster.jpg")));// C:\Users\USER\Desktop\ISAD\speedyseat1\test\src\main\Icon
        jLabel1.setText("jLabel1");

        dateShowtime.setFont(new Font("Segue UI", 0, 14)); // NOI18N
        dateShowtime.setForeground(new Color(153, 204, 255));
        dateShowtime.setText("17 October 2024   |  18:00");

        movieName.setFont(new Font("Segoe UI", 1, 24)); // NOI18N
        movieName.setForeground(new Color(255, 255, 255));
        movieName.setText("Look Back");

        movieGenre.setBackground(new Color(255, 255, 255));
        movieGenre.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        movieGenre.setForeground(new Color(255, 255, 255));
        movieGenre.setText("Action, Drama, Romance");

        runtime.setForeground(new Color(255, 255, 255));
        runtime.setText("60 min");

        theatre.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        theatre.setForeground(new Color(255, 255, 255));
        theatre.setText("Theatre 7");

        sound.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        sound.setForeground(new Color(255, 255, 255));
        sound.setIcon(new ImageIcon(getClass().getResource("/Icon/sound.png"))); // NOI18N
        sound.setText("ENG");

        subtitle.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        subtitle.setForeground(new Color(255, 255, 255));
        subtitle.setIcon(new ImageIcon(getClass().getResource("/Icon/subtitle.png"))); // NOI18N
        subtitle.setText("TH");

        jLabel3.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("|");

        seat.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        seat.setForeground(new Color(255, 255, 255));
        seat.setIcon(new ImageIcon(getClass().getResource("/Icon/seat.png"))); // NOI18N
        seat.setText("M7");

        price.setFont(new Font("Segoe UI", 1, 14)); // NOI18N
        price.setForeground(new Color(255, 255, 255));
        price.setText("Price: 160 ฿");

        jLabel4.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("|");

        jLabel5.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new Color(255, 255, 255));
        jLabel5.setIcon(new ImageIcon(getClass().getResource("/Icon/display.png"))); // NOI18N
        jLabel5.setText("IMAX");

        GroupLayout roundedPanel1Layout = new GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
                roundedPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(roundedPanel1Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addGroup(roundedPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(roundedPanel1Layout.createSequentialGroup()
                                                .addGroup(roundedPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(dateShowtime)
                                                        .addComponent(movieName)
                                                        .addComponent(movieGenre)
                                                        .addComponent(runtime)
                                                        .addComponent(theatre)
                                                        .addGroup(roundedPanel1Layout.createSequentialGroup()
                                                                .addComponent(sound)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jLabel3)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(subtitle)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jLabel4)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jLabel5)))
                                                .addContainerGap(229, Short.MAX_VALUE))
                                        .addGroup(roundedPanel1Layout.createSequentialGroup()
                                                .addComponent(seat)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(price)
                                                .addGap(74, 74, 74))))
        );
        roundedPanel1Layout.setVerticalGroup(
                roundedPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(roundedPanel1Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(roundedPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addGroup(roundedPanel1Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(dateShowtime)
                                                .addGap(18, 18, 18)
                                                .addComponent(movieName)
                                                .addGap(18, 18, 18)
                                                .addComponent(movieGenre)
                                                .addGap(18, 18, 18)
                                                .addComponent(runtime)
                                                .addGap(18, 18, 18)
                                                .addComponent(theatre)
                                                .addGap(18, 18, 18)
                                                .addGroup(roundedPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(sound)
                                                        .addComponent(subtitle)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jLabel4)
                                                        .addComponent(jLabel5))
                                                .addGap(18, 18, 18)
                                                .addGroup(roundedPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(seat)
                                                        .addComponent(price))))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        roundedPanel2.setBackground(new Color(35, 35, 35));
        roundedPanel2.setPreferredSize(new Dimension(200, 200));

        qrCode.setBackground(new Color(102, 102, 102));
        qrCode.setFont(new Font("Segoe UI", 0, 24)); // NOI18N
        qrCode.setForeground(new Color(255, 255, 255));
        qrCode.setIcon(new ImageIcon(getClass().getResource("/Icon/qr-code.png"))); // NOI18N
        qrCode.setText("QR Code");
        qrCode.setIconTextGap(30);
        qrCode.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                qrCodeMouseClicked(evt);
            }
            public void mouseExited(MouseEvent evt) {
                qrCodeMouseExited(evt);
            }
            public void mousePressed(MouseEvent evt) {
                qrCodeMousePressed(evt);
            }
        });

        onlineBanking.setFont(new Font("Segoe UI", 0, 24)); // NOI18N
        onlineBanking.setForeground(new Color(255, 255, 255));
        onlineBanking.setIcon(new ImageIcon(getClass().getResource("/Icon/banking.png"))); // NOI18N
        onlineBanking.setText("Online Banking");
        onlineBanking.setIconTextGap(30);
        onlineBanking.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                onlineBankingMouseClicked(evt);
            }
        });

        payAtCounter.setFont(new Font("Segoe UI", 0, 24)); // NOI18N
        payAtCounter.setForeground(new Color(255, 255, 255));
        payAtCounter.setIcon(new ImageIcon(getClass().getResource("/Icon/counter.png"))); // NOI18N
        payAtCounter.setText("Pay at Counter");
        payAtCounter.setIconTextGap(30);
        payAtCounter.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                payAtCounterMouseClicked(evt);
            }
        });

        GroupLayout roundedPanel2Layout = new GroupLayout(roundedPanel2);
        roundedPanel2.setLayout(roundedPanel2Layout);
        roundedPanel2Layout.setHorizontalGroup(
                roundedPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, roundedPanel2Layout.createSequentialGroup()
                                .addContainerGap(57, Short.MAX_VALUE)
                                .addGroup(roundedPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(onlineBanking, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(qrCode, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(payAtCounter, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE))
                                .addGap(54, 54, 54))
        );
        roundedPanel2Layout.setVerticalGroup(
                roundedPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(roundedPanel2Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(qrCode)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(onlineBanking)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(payAtCounter, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(17, Short.MAX_VALUE))
        );

        methodPanel.setBackground(new Color(35, 35, 35));
        methodPanel.setMinimumSize(new Dimension(300, 700));
        methodPanel.setPreferredSize(new Dimension(300, 700));
        methodPanel.setLayout(new BorderLayout());

        roundedPanel3.setBackground(new Color(35, 35, 35));

        GroupLayout roundedPanel3Layout = new GroupLayout(roundedPanel3);
        roundedPanel3.setLayout(roundedPanel3Layout);
        roundedPanel3Layout.setHorizontalGroup(
                roundedPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 200, Short.MAX_VALUE)
        );
        roundedPanel3Layout.setVerticalGroup(
                roundedPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 363, Short.MAX_VALUE)
        );

        methodPanel.add(roundedPanel3, BorderLayout.LINE_START);

        roundedPanel4.setBackground(new Color(35, 35, 35));

        pay.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        pay.setText("Pay");
        pay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                payActionPerformed(evt);
            }
        });

        GroupLayout roundedPanel4Layout = new GroupLayout(roundedPanel4);
        roundedPanel4.setLayout(roundedPanel4Layout);
        roundedPanel4Layout.setHorizontalGroup(
                roundedPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(roundedPanel4Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(pay)
                                .addContainerGap(112, Short.MAX_VALUE))
        );
        roundedPanel4Layout.setVerticalGroup(
                roundedPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(roundedPanel4Layout.createSequentialGroup()
                                .addGap(158, 158, 158)
                                .addComponent(pay)
                                .addContainerGap(178, Short.MAX_VALUE))
        );

        methodPanel.add(roundedPanel4, BorderLayout.LINE_END);

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(roundedPanel1, GroupLayout.PREFERRED_SIZE, 711, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(roundedPanel2, GroupLayout.PREFERRED_SIZE, 418, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(methodPanel, GroupLayout.PREFERRED_SIZE, 1147, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(96, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(roundedPanel1, GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                                        .addComponent(roundedPanel2, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(methodPanel, GroupLayout.PREFERRED_SIZE, 363, Short.MAX_VALUE)
                                .addGap(32, 32, 32))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>

    private void qrCodeMousePressed(MouseEvent evt) {
        // TODO add your handling code here:

    }

    private void qrCodeMouseExited(MouseEvent evt) {
        // TODO add your handling code here:

    }

    private void qrCodeMouseClicked(MouseEvent evt) {
        // TODO add your handling code here:
        qrCode.setOpaque(true);
        qrCode.setBackground(new Color(65, 65, 65));

        onlineBanking.setOpaque(true);
        onlineBanking.setBackground(new Color(35, 35, 35));

        payAtCounter.setOpaque(true);
        payAtCounter.setBackground(new Color(35, 35, 35));

        cardLayout.show(mainPanel, "qrCodePanel");
        currentCard = "qrCodePanel";
    }

    private void onlineBankingMouseClicked(MouseEvent evt) {
        // TODO add your handling code here:
        onlineBanking.setOpaque(true);
        onlineBanking.setBackground(new Color(65,65,65));

        qrCode.setOpaque(true);
        qrCode.setBackground(new Color(35, 35, 35));

        payAtCounter.setOpaque(true);
        payAtCounter.setBackground(new Color(35, 35, 35));

        cardLayout.show(mainPanel, "onlinePaymentPanel");
        currentCard = "onlinePaymentPanel";
    }

    private void payAtCounterMouseClicked(MouseEvent evt) {
        // TODO add your handling code here:
        payAtCounter.setOpaque(true);
        payAtCounter.setBackground(new Color(65,65,65));

        qrCode.setOpaque(true);
        qrCode.setBackground(new Color(35, 35, 35));

        onlineBanking.setOpaque(true);
        onlineBanking.setBackground(new Color(35, 35, 35));

        cardLayout.show(mainPanel, "payAtCounterPanel");
        currentCard = "payAtCounterPanel";
    }

    private void payActionPerformed(ActionEvent evt) {
        // ดึงข้อความจาก JPasswordField
        char[] password = onlinePaymentPanel.getpassword();

        // ตรวจสอบว่ารหัสผ่านมีจำนวนไม่เท่ากับ 10 ตัวอักษร
        if (currentCard == "onlinePaymentPanel") {
            if (password.length < 1) {
                // แสดงแจ้งเตือนว่าต้องใช้รหัสผ่าน 10 ตัวอักษร
                JOptionPane.showMessageDialog(this, "Please enter your card number.", "Payment failed", JOptionPane.WARNING_MESSAGE);
            } else if (password.length >= 1 && password.length != 10) {
                // แสดงแจ้งเตือนว่าต้องใช้รหัสผ่าน 10 ตัวอักษร
                JOptionPane.showMessageDialog(this, "Wrong card number.", "Payment failed", JOptionPane.WARNING_MESSAGE);
            } else {
                // โค้ดอื่น ๆ สำหรับการดำเนินการหลังจากป้อนรหัสผ่านถูกต้อง
                System.out.println("Confirm");
            }
        } else {
            System.out.println("Confirm");
        }
    }

    private String getCurrentCard() {
        return currentCard;
    }

    /**
     * @param args the command line arguments
     */

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        //</editor-fold>

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PaymentUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private JLabel dateShowtime;
    private JLabel jLabel1;
    private JLabel price;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JPanel jPanel;
    private JPanel jPanel1;
    private RoundedPanel methodPanel;
    private JLabel movieGenre;
    private JLabel movieName;
    private JLabel onlineBanking;
    private JLabel payAtCounter;
    private JLabel qrCode;
    private RoundedPanel roundedPanel1;
    private RoundedPanel roundedPanel2;
    private RoundedPanel roundedPanel3;
    private RoundedPanel roundedPanel4;
    private JLabel runtime;
    private JLabel seat;
    private JLabel sound;
    private JLabel subtitle;
    private JLabel theatre;
    // End of variables declaration

    private CardLayout cardLayout;
    private JPanel qrCodePanel;
    private onlinePaymentPanel onlinePaymentPanel;
    private JPanel payAtCounterPanel;
    private JPanel mainPanel;
    private JButton pay;
    private String currentCard;
}
