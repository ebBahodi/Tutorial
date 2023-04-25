import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {
    private PinballGame game;
    private final int width;
    private final int height;
    public Menu(int width, int height, MyGame game) {
        super();
        this.width = width;
        this.height = height;
        setSize(new Dimension(width, height));
        this.getContentPane().removeAll();
        this.getContentPane().add(game);
        this.setBackground(Color.red);
        this.setVisible(true);
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
    }

    public void startGame() {
        this.game = new PinballGame(width, height, this);
        this.getContentPane().removeAll();
        this.getContentPane().add(this.game);
        this.game.displayFrame();
        this.game.setRun(true);
        
    }

    public PinballGame getGame() {
        return game;
    }
}
