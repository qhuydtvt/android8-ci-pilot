package controllers;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by huynq on 2/28/17.
 */
public class ControllerManager {

    protected   Vector<GameController> gameControllers;

    public ControllerManager() {
        gameControllers = new Vector<>();
    }

    public void add(GameController gameController) {
        this.gameControllers.add(gameController);
    }

    public void run() {
        removeDeadGameControllers();
        for (GameController gameController: this.gameControllers) {
            gameController.run();
        }
    }

    private void removeDeadGameControllers() {
        Iterator<GameController> gameControllerIterator = this.gameControllers.iterator();
        while(gameControllerIterator.hasNext()) {
            GameController gameController = gameControllerIterator.next();
            if(!gameController.isAlive()) {
                gameControllerIterator.remove();
            }
        }
    }

    public void draw(Graphics g) {
        for (GameController gameController: this.gameControllers) {
            gameController.draw(g);
        }
    }
}
