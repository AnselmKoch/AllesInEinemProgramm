package me.anselm.alles.application.applications.games.shootShooter;

public class Healthbar {

    private float x,y;
    private int width, height = 10;
    private int health;
    private float healthPerPixel;
    private float widthHealth;

    public Healthbar(float x, float y, int width, int health) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.widthHealth = width;
        this.health = health;
        this.healthPerPixel = calculateHealthPerPixel(health);
    }

    public float calculateHealthPerPixel(int health) {
        return (float)width / health;
    }

    public float getHealthPerPixel() {
        return this.healthPerPixel;
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getWidthHealth() {
        return widthHealth;
    }

    public void setWidthHealth(float widthHealth) {
        this.widthHealth = widthHealth;
    }
}
