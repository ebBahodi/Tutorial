package StateManager;

import Buttons.MenuStandardJButton;
import Factory.PinballButtonFactory;
import Pinball.MenuCanvas;
import Pinball.PinballGame;

import javax.swing.*;
import java.awt.*;

public class GameMenuStateManager {

    private final MenuCanvas canvas;

    public GameMenuStateManager(MenuCanvas canvas) {
        this.canvas = canvas;
    }

    public void draw(Graphics g) {
        if(g instanceof Graphics2D){
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
    }

    private void play() {
        canvas.removeAll();
        PinballGame pinballGame = new PinballGame(canvas.getSize());
        canvas.add(pinballGame);
        canvas.repaint();
    }

    private void options() {
        JOptionPane.showMessageDialog(canvas, "To be implemented!", "Options", JOptionPane.INFORMATION_MESSAGE);
    }

    private void backToMenu() {
        System.out.println("I am going back to the main menu");
    }

    public void createButtons() {
        createAndAddButton("Play", 0, 1, this::play);
        createAndAddButton("Options", 0, 2, this::options);
        createAndAddButton("Return", 0, 3, this::backToMenu);
    }

    private void createAndAddButton(String buttonText, int xConstraint, int yConstraint, Runnable onTrigger) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = xConstraint;
        gbc.gridy = yConstraint;

        MenuStandardJButton button = PinballButtonFactory.getButtonByType(buttonText, onTrigger);

        canvas.add(button, gbc);
    }
}
