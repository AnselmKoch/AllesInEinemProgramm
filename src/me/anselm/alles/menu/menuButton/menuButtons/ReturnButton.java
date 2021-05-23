package me.anselm.alles.menu.menuButton.menuButtons;

import me.anselm.alles.menu.MenuType;
import me.anselm.alles.menu.menuButton.ButtonTypes;
import me.anselm.alles.menu.menuButton.MenuButton;

public class ReturnButton extends MenuButton {

    public ReturnButton(MenuType menuType) {
        super(menuType);
    }
    public ReturnButton(String actionCommand, String name) {
      super(actionCommand, name);
    }
    public ReturnButton(ButtonTypes buttonTypes) {super(buttonTypes);}
}
