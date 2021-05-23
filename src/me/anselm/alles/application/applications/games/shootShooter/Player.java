package me.anselm.alles.application.applications.games.shootShooter;

import me.anselm.alles.application.applications.games.gameLogic.Direction;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity{

    private int shotCooldown = 0;
    private Shot[] shots= new Shot[100];
    private Direction direction = Direction.NONE;
    private BufferedImage bulletImage = ImageIO.read(new File("img/ShootShooter/bulletImage.png"));

    public Player(float x, float y, int width, int height, int health, int damage, float momentum, BufferedImage bufferedImage) throws IOException {
        super(x, y, width, height, health, damage, momentum, bufferedImage);
    }


    public void shoot() {
        for(int i = 0; i < shots.length; i++) {
            if(shots[i] != null) {
                Shot shot = shots[i];
                if (shot.getY() < 0) {
                    shots[i] = null;
                }else{
                    shot.move();
                }
            }
        }
        if(shotCooldown < 0) {
            shotCooldown = 1;
            for(int i = 0; i < shots.length; i++) {
                if (shots[i] == null) {
                    Shot newShot = new Shot(this.getX() + 25, this.getY(), 5, 5, 1, getDamage(), 3F, bulletImage);
                    newShot.setDirection(Direction.NORTH);
                    shots[i] = newShot;
                    break;
                }
            }
        }else{
            shotCooldown--;
        }
    }

    public int getShotCooldown() {
        return this.shotCooldown;
    }

    public Shot[] getShots() {
        return this.shots;
    }

}
