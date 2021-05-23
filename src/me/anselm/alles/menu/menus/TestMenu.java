package me.anselm.alles.menu.menus;

import me.anselm.alles.Alles;
import me.anselm.alles.menu.Menu;
import me.anselm.alles.menu.MenuType;
import me.anselm.alles.menu.menuButton.ButtonTypes;
import me.anselm.alles.menu.menuButton.menuButtons.ReturnButton;
import me.anselm.alles.menu.menuButton.menuButtons.TestButton;

import java.awt.event.ActionEvent;

public class TestMenu extends Menu {

    public TestMenu(MenuType menuType) {
        super(menuType);
    }

    @Override
    public void addButton() {
        addButtonToPanel(new TestButton(MenuType.TEST_MENU));
        addButtonToPanel(new TestButton(MenuType.TEST_MENU));
        addButtonToPanel(new ReturnButton(ButtonTypes.RETURNBUTTON));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equalsIgnoreCase("999")) {
            Alles.menuManager.switchToLastMenu();
        }else {
            Alles.menuManager.switchMenu(Alles.menuManager.getMenuFromID(Integer.valueOf(e.getActionCommand())));
        }
    }
}
