package models;

import java.awt.*;

/**
 * Created by huynq on 2/28/17.
 */
public class GameModel {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean isAlive = true;

    public GameModel(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public boolean checkContact(GameModel otherGameModel) {
        Rectangle r1 = new Rectangle(x, y, width, height);
        Rectangle r2 = new Rectangle(otherGameModel.x, otherGameModel.y, otherGameModel.width, otherGameModel.height);
        return r1.intersects(r2);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getMidX() {
        return x + (double)width / 2;
    }

    public boolean isAlive() {
        return isAlive;
    }
}
