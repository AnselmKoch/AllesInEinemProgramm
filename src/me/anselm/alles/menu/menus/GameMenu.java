package me.anselm.alles.menu.menus;

import me.anselm.alles.Alles;
import me.anselm.alles.menu.Menu;
import me.anselm.alles.menu.MenuType;
import me.anselm.alles.application.Applications;
import me.anselm.alles.menu.menuButton.ButtonTypes;
import me.anselm.alles.menu.menuButton.menuButtons.ApplicationButton;
import me.anselm.alles.menu.menuButton.menuButtons.ReturnButton;

import java.awt.event.ActionEvent;

public class GameMenu extends Menu {
    public GameMenu(MenuType menuType) {
        super(menuType);
    }

    @Override
    public void addButton() {
        addButtonToPanel(new ApplicationButton(Applications.SHOOTSHOOTER));
        addButtonToPanel(new ApplicationButton(Applications.DREIDIMENSION));
        addButtonToPanel(new ReturnButton(ButtonTypes.RETURNBUTTON));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equalsIgnoreCase("999")) {
            Alles.menuManager.switchToLastMenu();
        }else {
            Alles.menuManager.switchMenu(Alles.menuManager.getApplicaionFromName(e.getActionCommand()));
        }
    }
}
