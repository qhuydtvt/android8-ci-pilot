package controllers;

import models.GameModel;
import views.GameView;

import java.awt.*;

/**
 * Created by huynq on 2/28/17.
 */
public class GameController {

    protected GameView view;
    protected GameModel model;

    public GameController(GameView view, GameModel model) {
        this.view = view;
        this.model = model;
    }

    public boolean isAlive() {
        return model.isAlive();
    }

    public void run() {

    }

    public void draw(Graphics g) {
        view.draw(g, model);
    }
}
