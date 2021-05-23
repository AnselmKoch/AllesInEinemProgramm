package me.anselm.alles.menu;

import me.anselm.alles.Alles;
import me.anselm.alles.menu.menuButton.MenuButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class Menu extends MenuPanel implements ActionListener {

    private final int ID;
    private boolean needReload = false;
    private final String name;
    private JPanel headerPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JLabel header = new JLabel();
    private ArrayList<MenuButton> buttons = new ArrayList<>();

    public Menu(MenuType menuType) {
        this.ID = menuType.getMenuID();
        this.name = menuType.getMenuName();
        this.header.setText(name);
        this.header.setFont(new Font("Monospaced", Font.BOLD, 30));

        this.renderContent();
        if(buttons.size() != 0) {
            renderButtons();
        }
        this.setVisible(true);
        this.addButton();
    }

    public void renderButtons() {
        for(MenuButton menuButton : this.buttons) {
            menuButton.setAlignmentX(CENTER_ALIGNMENT);
            this.buttonPanel.add(menuButton);
            this.add(buttonPanel);
        }
    }

    public void renderContent() {
        this.setSize(new Dimension(Alles.WIDTH, Alles.HEIGHT));
        this.headerPanel.setAlignmentX(CENTER_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.headerPanel.add(header);
        this.add(headerPanel);
    }

    public void setLayout(LayoutStyle layout)  {
        setLayout(layout);
    }

    public void addButtonToPanel(MenuButton menuButton) {
        buttons.add(menuButton);
        renderButtons();
        menuButton.addActionListener(this);
    }

    public abstract void addButton();

    public ArrayList<MenuButton> getButtons() {
        return buttons;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public JLabel getHeader() {
        return header;
    }

    public boolean isNeedReload() {
        return needReload;
    }

    public void setNeedReload(boolean needReload) {
        this.needReload = needReload;
    }
}
