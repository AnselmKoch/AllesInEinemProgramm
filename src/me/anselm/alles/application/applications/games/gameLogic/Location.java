package me.anselm.alles.application.applications.games.gameLogic;

public class Location {

    private int x,y;
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void updateLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isEqualTo(float x, float y) {
        if((int)this.x == (int)x) {
            if((int)this.y == (int)y) {
                return true;
            }
        }
        return false;
    }

    public boolean isEqualTo(Location location) {
        if((int)this.x == (int)location.x) {
            if((int)this.y == (int)location.y) {
                return true;
            }
        }
        return false;
    }
    public void updateLocation(float x, float y) {
        this.x = (int)x;
        this.y = (int)y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
