package me.anselm.alles.menu.menus;

import me.anselm.alles.Alles;
import me.anselm.alles.menu.Menu;
import me.anselm.alles.menu.MenuType;
import me.anselm.alles.menu.menuButton.menuButtons.GamesButton;
import me.anselm.alles.menu.menuButton.menuButtons.MatheButton;
import me.anselm.alles.menu.menuButton.menuButtons.MiscButton;
import me.anselm.alles.menu.menuButton.menuButtons.TestButton;

import java.awt.event.ActionEvent;

public class MainMenu extends Menu {


    public MainMenu(MenuType menuType) {
        super(menuType);
    }

    @Override
    public void addButton() {
        addButtonToPanel(new MiscButton(MenuType.MISC_MENU));
        addButtonToPanel(new GamesButton(MenuType.GAMES_MENU));
        addButtonToPanel(new MatheButton(MenuType.MATH_MENU));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Alles.menuManager.switchMenu(Alles.menuManager.getMenuFromID(Integer.valueOf(e.getActionCommand())));
    }
}
