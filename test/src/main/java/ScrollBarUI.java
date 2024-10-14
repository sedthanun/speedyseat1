import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class ScrollBarUI extends BasicScrollBarUI{
    private JButton blankBtn;
    public ScrollBarUI(){
        blankBtn = new JButton();
        blankBtn.setPreferredSize(new Dimension(0,0));
    }
    @Override
    protected void configureScrollBarColors() {
        this.thumbColor = new Color(35, 35, 35);
    }
    @Override
    protected JButton createDecreaseButton(int orientation) {
        return blankBtn;
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return blankBtn;
    }
}