package me.anselm.alles.application.applications.games;

import me.anselm.alles.Alles;
import me.anselm.alles.application.Application;
import me.anselm.alles.application.ApplicationType;
import me.anselm.alles.application.applications.games.gameLogic.CollitionDetector;
import me.anselm.alles.application.applications.games.gameLogic.Direction;
import me.anselm.alles.application.applications.games.gameLogic.KeyInputHandler;
import me.anselm.alles.application.applications.games.shootShooter.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class ShootShooter extends Application {

    private Entity[] aliveEntities;
    private Player player;
    private KeyInputHandler keyInputHandler;
    private CollitionDetector collitionDetector;
    private Image skyBackGroundImage = ImageIO.read(new File("img/ShootShooter/background.png"));
    private BufferedImage backGroundImage = new BufferedImage(Alles.WIDTH, Alles.HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);

    private BufferedImage playerImage = ImageIO.read(new File("img/ShootShooter/playerImage.png"));
    private BufferedImage enemyImage = ImageIO.read(new File("img/ShootShooter/enemyImage.png"));
    private BufferedImage bulletImage = ImageIO.read(new File("img/ShootShooter/bulletImage.png"));


    public ShootShooter(ApplicationType applicationType, String name) throws IOException {
        super(applicationType, name);
        collitionDetector = new CollitionDetector(this);
        for (int i = 0; i < Alles.WIDTH; i++) {
            for (int j = 0; j < Alles.HEIGHT; j++) {
                if ((i % 100 == 0) && (j % 50 == 0)) {
                    backGroundImage.getGraphics().drawImage(skyBackGroundImage, i, j, this);
                }
            }
        }
    }

    @Override
    public void actionToPerfom() {
        for(int i = 0; i < aliveEntities.length; i++) {
            if(aliveEntities[i] != null) {
                aliveEntities[i].move();
            }else{
                aliveEntities[i] = randomEnemy();
            }
        }
        handleKeyInput();
        player.shoot();
        collitionDetector.checkHitbox();
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(backGroundImage, 0, 0, this);

        for (Shot shot : player.getShots()) {
            if (shot != null) {
                graphics.drawImage(bulletImage, (int) shot.getX(), (int) shot.getY(), this);
            }
        }

        for (Entity entity : aliveEntities) {
            if (entity != null) {
                graphics.drawImage(entity.getBufferedImage(), (int) entity.getX(), (int) entity.getY(), entity.getWidth(), entity.getHeight(), this);
                if (entity.isShowHealthbar()) {
                    Healthbar healthbar = entity.getHealthbar();
                    graphics.setColor(Color.RED);
                    graphics.fillRect((int) healthbar.getX(), (int) healthbar.getY(), healthbar.getWidth(), healthbar.getHeight());
                    graphics.setColor(Color.GREEN);
                    graphics.fillRect((int) healthbar.getX(), (int) healthbar.getY(), (int) healthbar.getWidthHealth(), healthbar.getHeight());
                }
            }
        }
    }


    private void setUpEntities() throws IOException {
        aliveEntities = new Entity[3];
        player = new Player(Alles.WIDTH / 2 - 50, Alles.HEIGHT - 100,50,50,50,25,3F, playerImage);
        aliveEntities[0] = player;
        for(int i = 1; i < aliveEntities.length; i++) {
            aliveEntities[i] = randomEnemy();
        }
    }

    public void killEntity(Entity entity) {
        for(int i = 0; i < aliveEntities.length; i++) {
            if(entity == aliveEntities[i]) {
                aliveEntities[i] = null;
            }
        }
    }
    @Override
    public void drawPixel() {
    }

    @Override
    public void restoreDefault() {
        this.keyInputHandler = null;
        this.player = null;
        this.aliveEntities[0] = null;
     }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "999") {
            Alles.menuManager.switchToLastMenu();
        }
    }


    @Override
    public void run() {
        if(keyInputHandler == null) {
            this.keyInputHandler = new KeyInputHandler();
            addKeyListener(keyInputHandler);
        }
        try {
            setUpEntities();
        } catch (IOException e) {
            e.printStackTrace();
        }
            doLoop();

    }

    private void handleKeyInput() {
       int crKey = keyInputHandler.getCurrentKey();
       if(keyInputHandler.getPressedKeys().size() != 0) {
           if (crKey == 37) {
               player.setDirection(Direction.WEST);
           }
           if (crKey == 39) {
               player.setDirection(Direction.EAST);
           }
       }else{
           player.setDirection(Direction.NONE);
       }
    }

    public Enemy randomEnemy() {
        Random random = new Random();
        int randomX = random.nextInt(Alles.WIDTH);
        if(randomX > Alles.WIDTH-75) {
            randomX -= 75;
        }
        Enemy returnEnemy = new Enemy(randomX, 0,50,50, 1000, 5,0.5F, enemyImage);
        returnEnemy.setDirection(Direction.SOUTH);
        return returnEnemy;
    }

    public Entity[] getEntities() {
        return  this.aliveEntities;
    }
    public Player getPlayer() {
        return player;
    }

    public Entity[] getAliveEntities() {
        return aliveEntities;
    }

}
