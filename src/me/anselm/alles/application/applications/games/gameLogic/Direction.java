package me.anselm.alles.application.applications.games.gameLogic;

public enum Direction {
    NORTH('N'), EAST('E'), SOUTH('S'), WEST('W'), NONE('0');

    Direction(Character direction) {
        this.direction = direction;
    }

    private Character direction;

    public Character getDirection() {
        return direction;
    }
}
