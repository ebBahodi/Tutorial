import javax.swing.*;
import java.awt.*;

public class MyGame extends JComponent implements Runnable {

    GameState state = GameState.MENU;

    public MyGame(Dimension screenSize) {
        super();
//        setDoubleBuffered(true);
//        new Thread(this).start();
        setBounds(0, 0, screenSize.width, screenSize.height);
    }


    @Override
    public void paintComponent(Graphics g) {
//        super.paintComponents(g);
        update();
    }

    public void update() {
        switch (state) {
            case MENU -> {
                MenuCanvas canvas = new MenuCanvas();
                canvas.setBounds(0, getSize().height / 2, getSize().width, getSize().height);
                getRootPane().getContentPane().add(canvas);
                repaint();
            }
            case START -> {

            }
        }
    }

    @Override
    public void run() {

    }
}
