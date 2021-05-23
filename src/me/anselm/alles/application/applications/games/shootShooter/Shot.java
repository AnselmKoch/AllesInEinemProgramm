package me.anselm.alles.application.applications.games.shootShooter;

import java.awt.image.BufferedImage;

public class Shot extends Entity{


    public Shot(float x, float y, int width, int height, int health, int damage, float momentum, BufferedImage bufferedImage) {
        super(x, y, width, height, health, damage, momentum, bufferedImage);
    }
}
