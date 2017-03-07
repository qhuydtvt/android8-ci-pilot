package views;

import models.GameModel;
import utils.Utils;

import java.awt.*;

/**
 * Created by huynq on 2/28/17.
 */
public class GameView {

    private Image image;

    public GameView(Image image) {
        this.image = image;
    }

    public GameView(String url) {
        this(Utils.loadImageFromRes(url));
    }

    public void draw(Graphics graphics, GameModel model) {
        graphics.drawImage(
                image,
                model.getX(),
                model.getY(),
                model.getWidth(),
                model.getHeight(),
                null);
    }
}
