
import java.awt.*;
import javax.swing.JPanel;

public class BlankPanel extends JPanel{
    public BlankPanel(int width, int height, Color cl){
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(cl);
    }
    public BlankPanel(Color cl){
        this.setBackground(cl);
    }
}
