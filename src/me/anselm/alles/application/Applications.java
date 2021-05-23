package me.anselm.alles.application;

public enum Applications {

    OPEN_GL("Open GL"),DREIDIMENSION("DreiDimension"),SHOOTSHOOTER("ShootShooter"),RAINBOW("Rainbow"), SINUS("Sinus"), RAINDROP("Raindrop");

    private String name;

    Applications(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
