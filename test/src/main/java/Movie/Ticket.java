package Movie;
import Booking.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Dictionary;
import java.util.Hashtable;

public class Ticket {
    private int ticketID;
    private String ticketStatus;
    private Seat seat;
    private byte[] qrCode;

//    private boolean paymentConfirm;

    public Ticket(Seat seat) {
        this.seat = seat;
//        this.paymentConfirm = false;
    }

    public Dictionary<String, Object> getTicketInfo() {
        Dictionary<String, Object> ticketInfo = new Hashtable<>();

        ticketInfo.put("ticketID", ticketID);
        ticketInfo.put("ticketStatus", ticketStatus);
        ticketInfo.put("seat", seat);
        ticketInfo.put("qrCode", qrCode);

        return ticketInfo;
    }

    public static Ticket createTicket(Seat seat){
        Ticket ticket = new Ticket(seat);
        byte[] qrCode = ticket.createQRCode();
        ticket.setQRCode(qrCode);

        return ticket;
    }

    public void setQRCode(byte[] qrCode){
        this.qrCode = qrCode;
    }

    public byte[] createQRCode(){

//        BufferedImage bImage = null;
//        try {
//            bImage = ImageIO.read(new File("/Icon/qr_code_payment.png"));
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            ImageIO.write(bImage, "png", bos );
//            byte [] image = bos.toByteArray();
//            return image;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        File file = new File("test/src/main/resources/Icon/qr_code_payment.png");
        byte[] imageBytes = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            int bytesRead;
            while ((bytesRead = fis.read(imageBytes)) != -1) {
                bos.write(imageBytes, 0, bytesRead);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
