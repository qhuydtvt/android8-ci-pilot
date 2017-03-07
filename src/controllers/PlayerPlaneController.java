package controllers;

import models.GameModel;
import models.PlayerPlaneModel;
import views.GameView;

import java.util.Vector;

/**
 * Created by huynq on 2/28/17.
 */
public class PlayerPlaneController extends GameController {

    private ControllerManager playerBulletManager;

    public PlayerPlaneController(GameView view, GameModel model, ControllerManager bulletControllerManager) {
        super(view, model);
        this.playerBulletManager = bulletControllerManager;
    }

    public PlayerPlaneController(int x, int y, ControllerManager bulletControllerManager) {

        this(
                new GameView("plane3.png"),
                new PlayerPlaneModel(x, y, 70, 50),
                bulletControllerManager
        );
    }

    public void move(int dx, int dy) {
        model.move(dx, dy);
    }

    public void shoot() {
        // Create new bullet
        PlayerBulletController playerBulletController = new PlayerBulletController((int)model.getMidX() -
                PlayerBulletController.DEFAULT_HALF_WIDTH, model.getY());

        // Add to array list
        playerBulletManager.add(playerBulletController);
    }

    public void moveRight() {
        model.move(10, 0);
    }

    public void moveLeft() {
        model.move(-10, 0);
    }

    public void moveUp() {
        model.move(0, -10);
    }

    public void moveDown() {
        model.move(0, 10);
    }
}
