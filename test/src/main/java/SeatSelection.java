import javax.swing.*; // สำหรับใช้ JFrame, JButton, JOptionPane, JLabel
import java.awt.*; // สำหรับจัดการ Layout เช่น GridBagLayout, Dimension
import java.awt.event.*; // สำหรับใช้ ActionListener
import java.util.ArrayList; // สำหรับใช้ ArrayList ในการเก็บข้อมูลที่นั่ง
import com.formdev.flatlaf.themes.*;

public class SeatSelection extends JFrame {
    private final ArrayList<String> selectedSeats = new ArrayList<>(); // เก็บที่นั่งที่เลือก
    private final JButton[][] seatButtons = new JButton[5][5]; // เก็บปุ่มที่นั่ง

    public SeatSelection() {
        setTitle("Seat Selection");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // ปิดหน้าต่างเมื่อคลิกปุ่มปิด
        setSize(500, 400); // กำหนดขนาดหน้าต่าง
        setLocationRelativeTo(null); // จัดให้หน้าต่างอยู่ตรงกลางหน้าจอ

        setLayout(new GridBagLayout()); // ใช้ GridBagLayout เพื่อจัดการ layout ของปุ่ม eiei
        GridBagConstraints gbc = new GridBagConstraints();

        // ชื่อแถวและประเภทที่นั่ง
        String[][] seatRows = {
                {"E", "Standard"},
                {"D"},
                {"C", "Premium"},
                {"B"},
                {"A", "Honeymoon"}
        };

        // วนลูปแถวที่นั่งและเพิ่มปุ่มที่นั่ง
        for (int i = 0; i < seatRows.length; i++) {
            gbc.gridy = i; // ตั้งค่าลำดับแถว
            gbc.gridx = 0; // ตั้งค่าลำดับคอลัมน์

            JLabel seatRowLabel = new JLabel(seatRows[i][0]); // ชื่อแถว
            gbc.insets = new Insets(10, 10, 10, 10); // เพิ่มช่องว่างรอบ ๆ ปุ่ม
            add(seatRowLabel, gbc); // เพิ่มชื่อแถว

            // เพิ่มประเภทที่นั่ง (ถ้ามี)
            if (seatRows[i][0].equals("A") || seatRows[i][0].equals("C") || seatRows[i][0].equals("E")) {
                JLabel seatTypeLabel = new JLabel("(" + seatRows[i][1] + ")"); // ประเภทที่นั่ง
                gbc.gridx = 1;
                add(seatTypeLabel, gbc); // เพิ่มประเภทที่นั่ง
            } else {
                gbc.gridx = 1;
                add(new JLabel(" "), gbc); // ใส่ label ว่างถ้าไม่มีประเภทที่นั่ง
            }

            // เพิ่มปุ่มที่นั่ง
            for (int j = 0; j < 5; j++) {
                gbc.gridx = j + 2; // เริ่มที่คอลัมน์ที่ 2

                String seatName = seatRows[i][0] + (1+j); // กำหนดชื่อปุ่มที่นั่ง เช่น E5, E4, ...
                JButton seatButton = new JButton(seatName);
                seatButton.setPreferredSize(new Dimension(50, 30)); // กำหนดขนาดปุ่ม
                seatButtons[i][j] = seatButton; // เก็บปุ่มที่นั่งใน array

                seatButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (selectedSeats.contains(seatName)) { // ถ้าเลือกแล้ว
                            selectedSeats.remove(seatName); // ยกเลิกการเลือก
                            seatButton.setEnabled(true); // เปิดปุ่มที่นั่ง
                            JOptionPane.showMessageDialog(SeatSelection.this,
                                    "Seat " + seatName + " has been deselected",
                                    "Seat Selection",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else if (selectedSeats.size() < 5) { // จำกัดให้เลือกได้สูงสุด 3 ที่นั่ง
                            selectedSeats.add(seatName); // เลือกที่นั่ง
                            seatButton.setEnabled(false); // ปิดปุ่มที่นั่ง
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
                    }
                });

                add(seatButton, gbc); // เพิ่มปุ่มที่นั่งในหน้าต่าง
            }
        }

        setVisible(true); // ให้หน้าต่างมองเห็นได้
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        new SeatSelection(); // เรียกคลาส SeatSelection เพื่อสร้างหน้าต่างที่นั่ง
    }
}
