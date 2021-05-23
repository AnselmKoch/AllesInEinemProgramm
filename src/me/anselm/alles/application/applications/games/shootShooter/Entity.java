package me.anselm.alles.application.applications.games.shootShooter;

import me.anselm.alles.application.applications.games.gameLogic.Direction;
import me.anselm.alles.application.applications.games.gameLogic.Location;

import java.awt.image.BufferedImage;

public abstract class Entity {

    private float x,y;
    private Location location;
    private int width, height;
    private Direction direction = Direction.NONE;
    private Location[] area;
    private int health,damage;
    private float momentum;
    private Healthbar healthbar;
    private BufferedImage bufferedImage;
    private boolean showHealthbar = false;

    public Entity(float x, float y, int width, int height, int health, int damage, float momentum, BufferedImage bufferedImage) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.location = new Location((int)this.x, (int)this.y);
        this.area = new Location[(width * height)];
        this.health = health;
        this.damage = damage;
        this.momentum = momentum;
        this.bufferedImage = bufferedImage;

        this.healthbar = new Healthbar(x ,y + height, width,health);
        this.updateHitbox();
    }

    public void updateHitbox() {
        int tmp = 0;
        for(int i = 0; i < this.width; i++) {
            for(int j = 0; j < this.height; j++) {
                this.area[tmp] = new Location((int)this.x + i,(int)this.y + j);
                tmp++;
            }
        }
    }

    public void move() {
        switch (this.getDirection()) {
            case EAST: this.x += this.getMomentum();break;
            case NORTH: this.y -= this.getMomentum();break;
            case WEST: this.x -= this.getMomentum();break;
            case SOUTH: this.y += this.getMomentum();break;
        }
        this.location.updateLocation(this.x, this.y);
        this.getHealthbar().setX(this.location.getX());
        this.getHealthbar().setY(this.location.getY() + height);
        this.updateHitbox();
    }

    public Location[] getHitbox() {
        return area;
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

    public Direction getDirection() {
        return direction;
    }
    public void damage(int damage) {
        this.setHealth(getHealth() - damage);
        this.healthbar.setHealth(getHealth());
        for(int i = 0; i < damage; i++) {
            this.healthbar.setWidthHealth(healthbar.getWidthHealth() - healthbar.getHealthPerPixel());
        }
    }
    public Healthbar getHealthbar() {
        return healthbar;
    }

    public void setHealthbar(Healthbar healthbar) {
        this.healthbar = healthbar;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getMomentum() {
        return momentum;
    }

    public void setMomentum(float momentum) {
        this.momentum = momentum;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public boolean isShowHealthbar() {
        return showHealthbar;
    }

    public void setShowHealthbar(boolean showHealthbar) {
        this.showHealthbar = showHealthbar;
    }
}
