package me.anselm.alles.application.applications.misc;

import me.anselm.alles.Alles;
import me.anselm.alles.application.Application;
import me.anselm.alles.application.ApplicationType;

import java.awt.*;
import java.awt.event.ActionEvent;

public class Rainbow extends Application {

    private final int red = 0,green = 175,blue = 255;
    private int[] colors = {red, green, blue};
    private boolean[] goUpColor = {true, true, true};
    private int schrittGroeße = 1;

    public Rainbow(ApplicationType applicationType, String name) {
        super(applicationType, name);
    }

    @Override
    public void actionToPerfom() {
        setBackground(new Color(colors[0],colors[1],colors[2]));
        for (int i = 0; i < colors.length; i++) {
            if (goUpColor[i]) {
                if (colors[i] > (255 - schrittGroeße)) {
                    colors[i] -= schrittGroeße;
                    goUpColor[i] = false;
                } else {
                    colors[i] += schrittGroeße;
                }
            } else if (!goUpColor[i]) {
                if (colors[i] < (0 + schrittGroeße)) {
                    colors[i] += schrittGroeße;
                    goUpColor[i] = true;
                } else {
                    colors[i] -= schrittGroeße;
                }
            }
        }
    }

    @Override
    public void render(Graphics graphics) {

    }

    @Override
    public void drawPixel() {

    }

    @Override
    public void restoreDefault() {
        colors[0] = red;
        colors[1] = green;
        colors[2] = blue;
        goUpColor[0] = true;
        goUpColor[1] = true;
        goUpColor[2] = true;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equalsIgnoreCase("999")) {
            Alles.menuManager.switchToLastMenu();
        }
    }

    @Override
    public void run() {

        doLoop();

    }
}
