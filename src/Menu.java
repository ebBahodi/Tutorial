import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {
    private PinballGame game;
    private final int width;
    private final int height;
    public Menu(int width, int height) {
        super();
        this.width = width;
        this.height = height;
        MenuCanvas menuCanvas = new MenuCanvas(this);
        setSize(new Dimension(width, height));
        menuCanvas.setBounds(0, height / 2, width, height);
        menuCanvas.CreateButtons();
        this.getContentPane().add(menuCanvas);
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
