package controllers;

import models.GameModel;
import views.GameView;

/**
 * Created by huynq on 3/7/17.
 */
public class EnemyControllerManager extends ControllerManager {

    private int timerCount = 0;

    @Override
    public void run() {
        super.run();
        spawnEnemies();
    }

    private void spawnEnemies() {
        timerCount++;
        if (timerCount > 60) {
            timerCount = 0;
            EnemyPlaneController enemyPlaneController = new EnemyPlaneController(
                    new GameView("plane1.png"),
                    new GameModel(10, 10, 34, 30)
            );
            this.gameControllers.add(enemyPlaneController);
        }
    }
}
