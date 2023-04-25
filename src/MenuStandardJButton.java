import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuStandardJButton extends JButton implements MouseListener {

    String text;

    public MenuStandardJButton(String text, int x, int y, int width, int height)
    {
        super(text);
        this.text = text;
        setBounds(x, y, width, height);
        setOpaque(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        addMouseListener(this);
    }

    @Override
    public void paintComponent(java.awt.Graphics g)
    {
        java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;
        super.paintComponent(g2);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(this.text.equalsIgnoreCase("play")) {

        }
        else if(this.text.equalsIgnoreCase("options")) {
            JOptionPane.showMessageDialog(null, "This is yet to be implemented! :D");
        }
        else {
            System.exit(0);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setForeground(Color.blue);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setForeground(Color.black);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
