package controllers;

import models.PlayerBulletModel;
import views.GameView;

/**
 * Created by huynq on 2/26/17.
 */
public class PlayerBulletController extends GameController {

    public static final int DEFAULT_WIDTH = 7;
    public static final int DEFAULT_HEIGHT = 15;

    public static final int DEFAULT_HALF_WIDTH = DEFAULT_WIDTH / 2;

    public PlayerBulletController(GameView view, PlayerBulletModel bulletmodel) {
        super(view, bulletmodel);
    }

    public PlayerBulletController(int x, int y) {
        this(
                new GameView("bullet-single.png"),
                new PlayerBulletModel(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT)
        );
    }

    public void run() {
        if (model instanceof PlayerBulletModel) {
            ((PlayerBulletModel) model).fly();
        }
    }
}
