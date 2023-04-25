import javax.swing.*;
import java.awt.*;

public class MenuCanvas extends JComponent {
    public MenuCanvas() {
        super();
    }

    @Override
    protected void paintComponent(Graphics g) {
        if(g instanceof Graphics2D){
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            this.setVisible(true);
            CreateButtons();
        }
    }

    public void CreateButtons() {
        MenuStandardJButton playButton = new MenuStandardJButton("Play", getWidth() / 2, (int)(getHeight() * 0.75), 120, 40);
        MenuStandardJButton optionsButton = new MenuStandardJButton("Options", getWidth() / 2, (int)(getHeight() * 0.8), 120, 40);
        MenuStandardJButton exitButton = new MenuStandardJButton("Exit", getWidth() / 2, (int)(getHeight() * 0.85), 120, 40);

        getRootPane().getContentPane().add(playButton);
        getRootPane().getContentPane().add(optionsButton);
        getRootPane().getContentPane().add(exitButton);
    }
}
