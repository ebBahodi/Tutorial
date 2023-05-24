import Pinball.MyGame;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        MyGame game = new MyGame(screenSize);
        Menu menu = new Menu(screenSize.width, screenSize.height, game);


    }
}