package me.anselm.alles.menu.menuButton;

import me.anselm.alles.menu.MenuType;
import me.anselm.alles.application.Applications;

import javax.swing.*;

public abstract class MenuButton extends JButton {

    final private String actionCommand, name;


    public MenuButton(ButtonTypes buttonTypes) {
        this.name = buttonTypes.getText();
        this.actionCommand = buttonTypes.getActionCommand();

        this.setText(name);
        this.setActionCommand(actionCommand);
    }

    public MenuButton(Applications applicationType) {
        this.name = applicationType.getName();
        this.actionCommand = applicationType.getName();

        this.setText(name);
        this.setActionCommand(actionCommand);
    }

    public MenuButton(String actionCommand, String name) {
        this.actionCommand = actionCommand;
        this.name = name;

        this.setText(name);
        this.setActionCommand(actionCommand);
    }

    public MenuButton(MenuType menuType) {
        this.actionCommand = String.valueOf(menuType.getMenuID());
        this.name = menuType.getMenuName();

        setText(name);
        setActionCommand(actionCommand);
    }

    public String getActionCommand() {
        return actionCommand;
    }


    public String getName() {
        return name;
    }


}
