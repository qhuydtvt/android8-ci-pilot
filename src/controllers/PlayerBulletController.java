package controllers;

import models.PlayerBulletModel;
import utils.Utils;
import views.PlayerBulletView;

import java.awt.*;

/**
 * Created by huynq on 2/26/17.
 */
public class PlayerBulletController {

    private PlayerBulletModel model;
    private PlayerBulletView view;


    public PlayerBulletController(PlayerBulletModel model, PlayerBulletView view) {
        this.model = model;
        this.view = view;
    }

    public PlayerBulletController(int x, int y) {
        this(new PlayerBulletModel(x, y, 13, 30),
                new PlayerBulletView(Utils.loadImageFromRes("bullet.png")));
    }

    public void run() {
        model.fly();
    }

    public void draw(Graphics graphics) {
        view.draw(graphics, model);
    }
}
