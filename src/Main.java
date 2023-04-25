import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        MyGame game = new MyGame(screenSize);
        game.setBounds(0, screenSize.height / 2, screenSize.width, screenSize.height);
        Menu menu = new Menu(screenSize.width, screenSize.height, game);
        menu.setVisible(true);
        game.setVisible(true);


    }
}