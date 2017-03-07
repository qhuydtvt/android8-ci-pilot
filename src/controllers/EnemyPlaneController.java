package controllers;

import models.GameModel;
import views.GameView;

/**
 * Created by huynq on 2/28/17.
 */
public class EnemyPlaneController extends GameController {

    public EnemyPlaneController(GameView view, GameModel model) {
        super(view, model);
    }

    public EnemyPlaneController(int x, int y) {
        this(new GameView("plane1.png"),
                new GameModel(x, y, 35, 25));
    }

    @Override
    public void run() {
        model.move(0, 2);
    }
}
