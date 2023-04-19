import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Menu menu = new Menu(screenSize.width, screenSize.height);
        menu.setVisible(true);

    }
}