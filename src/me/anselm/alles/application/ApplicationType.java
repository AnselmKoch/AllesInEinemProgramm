package me.anselm.alles.application;

public enum ApplicationType {


    MISC("MISC"), GAMES("GAMES"), MATHE("MATHE");

    private String name;


    ApplicationType(String type) {
        this.name = type;
    }

    public String getName() {
        return name;
    }

}
