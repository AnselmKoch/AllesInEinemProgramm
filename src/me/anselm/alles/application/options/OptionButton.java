package me.anselm.alles.application.options;

import me.anselm.alles.application.Application;

import javax.swing.*;

public class OptionButton extends JButton {

    private final String name;

    public OptionButton(String name, Application application) {
        this.name = name;
        this.setText(name);
        this.setActionCommand("option");
        this.addActionListener(application);
    }

    @Override
    public String getName() {
        return name;
    }
}
