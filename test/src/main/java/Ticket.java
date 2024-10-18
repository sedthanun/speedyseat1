import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class Ticket {
    private int ticketID;
    private String ticketStatus;
    private Seat seat;
    private byte[] qrCode;

//    private boolean paymentConfirm;

    public Ticket(Seat seat) {
//        this.paymentConfirm = false;
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
        BufferedImage bImage = null;
        try {
            bImage = ImageIO.read(new File("/Icon/qr_code_payment.png"));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "png", bos );
            byte [] image = bos.toByteArray();
            return image;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
