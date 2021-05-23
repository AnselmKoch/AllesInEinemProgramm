package me.anselm.alles;

import me.anselm.alles.menu.MenuManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Alles extends JFrame {

    public static final int WIDTH =  2000, HEIGHT = 1000;
    private static Alles instance;
    public static MenuManager menuManager;

    public static void main(String[] args) throws IOException {
        instance = new Alles();
        menuManager = new MenuManager();
    }

    public Alles() {
        setSize(new Dimension(WIDTH, HEIGHT));
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static Alles getInstance() {
        return instance;
    }

    public static void setInstance(Alles instance) {
        Alles.instance = instance;
    }
}
