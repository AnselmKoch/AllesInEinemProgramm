package me.anselm.alles.application.applications.misc.raindrop;

import java.awt.*;

public class Drop {

    private float x,y;
    private Color color;

    public Drop(float x, Color color) {
        this.x = x;
        this.y = 0;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
