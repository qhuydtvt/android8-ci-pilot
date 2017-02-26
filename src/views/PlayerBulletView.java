package views;

import models.PlayerBulletModel;

import java.awt.*;

/**
 * Created by huynq on 2/26/17.
 */
public class PlayerBulletView {
    private Image image;

    public PlayerBulletView(Image image) {
        this.image = image;
    }

    public void draw(Graphics graphics, PlayerBulletModel model) {
        graphics.drawImage(
                image,
                model.getX(),
                model.getY(),
                model.getWidth(),
                model.getHeight(),
                null);
    }
}
