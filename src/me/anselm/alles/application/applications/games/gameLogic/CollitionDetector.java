package me.anselm.alles.application.applications.games.gameLogic;

import me.anselm.alles.application.applications.games.ShootShooter;
import me.anselm.alles.application.applications.games.shootShooter.Enemy;
import me.anselm.alles.application.applications.games.shootShooter.Entity;
import me.anselm.alles.application.applications.games.shootShooter.Shot;

public class CollitionDetector {

    private ShootShooter shootShooter;
    public CollitionDetector(ShootShooter shootShooter) {
        this.shootShooter = shootShooter;
    }

    public synchronized void checkHitbox() {
        Shot[] shots = shootShooter.getPlayer().getShots();
        for(Entity entity : shootShooter.getEntities()) {
            if(entity instanceof Enemy) {
                Enemy enemy = (Enemy) entity;
                for (Location location : enemy.getHitbox()) {
                    for (int i = 0; i < shots.length; i++) {
                        if (shots[i] != null) {
                            for (Location location1 : shots[i].getHitbox()) {
                                if (location.isEqualTo(location1)) {
                                    enemy.damage(shots[i].getDamage());
                                    if (enemy.getHealth() <= 0) {
                                        shootShooter.killEntity(entity);
                                    }
                                    shots[i] = null;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
                }
            }
}
