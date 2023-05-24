package Pinball;

import StateManager.GameMenuStateManager;

import javax.swing.*;
import java.awt.*;

public class MenuCanvas extends JComponent {
    GameMenuStateManager menuStateManager;
    public MenuCanvas(int y, int width, int height) {
        super();
        this.setVisible(true);
        menuStateManager = new GameMenuStateManager(this);
        this.setSize(width, height);
        this.setLayout(new GridBagLayout());
        CreateButtons();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        menuStateManager.draw(g);

    }

    public void CreateButtons() {
        menuStateManager.createButtons();
    }
}
