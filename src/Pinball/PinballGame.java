package Pinball;

import Constants.PinballGameConstants;
import Pinball.Ball;
import StateManager.PinballStateManager;

import javax.swing.*;
import javax.swing.AbstractAction;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

public class PinballGame extends JPanel {
    PinballStateManager pinballStateManager;
    private final int width;
    private final int height;

    public PinballGame(Dimension size) {
        super();
        this.width = size.width;
        this.height = size.height;
        this.setSize(width, height);
        this.pinballStateManager = new PinballStateManager(this, size.width, size.height);
        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (g instanceof Graphics2D g2) {
            super.paintComponent(g2);
            pinballStateManager.draw(g2);
        }
    }
}

