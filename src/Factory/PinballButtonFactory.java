package Factory;

import Buttons.MenuStandardJButton;
import Constants.PinballMenuButtonConstants;
public class PinballButtonFactory {
    public static MenuStandardJButton getButtonByType(String name, Runnable onTrigger){
        return createButton(name, onTrigger);
    }

    private static MenuStandardJButton createButton(String name, Runnable onTrigger) {
        return new MenuStandardJButton(name, PinballMenuButtonConstants.PINBALL_MENU_BUTTON_WIDTH,
                PinballMenuButtonConstants.PINBALL_MENU_BUTTON_HEIGHT, onTrigger);
    }
}
