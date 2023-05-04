package Pinball;

import javax.swing.*;
import java.awt.*;

public class MyGame extends JComponent {
    public MyGame(Dimension screenSize) {
        super();
        setBounds(0, 0, screenSize.width, screenSize.height);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setVisible(true);
        init();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void init() {
        MenuCanvas canvas = new MenuCanvas(getSize().height / 2, getSize().width, getSize().height);
        this.add(canvas, BorderLayout.CENTER);
    }
}
