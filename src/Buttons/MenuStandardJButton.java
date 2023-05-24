package Buttons;

import Constants.PinballMenuButtonConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuStandardJButton extends JButton implements MouseListener {
    String text;
    Runnable onTrigger;
//    private
    public MenuStandardJButton(String text, int width, int height, Runnable onTrigger)
    {
        super(text);
        this.onTrigger = onTrigger;
        this.text = text;
        setSize(width, height);
        setOpaque(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        addMouseListener(this);
        setFont(new Font("Arial", Font.PLAIN, PinballMenuButtonConstants.BUTTON_FONT_SIZE));
    }

    @Override
    public void paintComponent(java.awt.Graphics g)
    {
        java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;
        super.paintComponent(g2);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.onTrigger.run();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setForeground(PinballMenuButtonConstants.FOREGROUND_COLOR_ENTERED);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setForeground(PinballMenuButtonConstants.FOREGROUND_COLOR_EXITED);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
