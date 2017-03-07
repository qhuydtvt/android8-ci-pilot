import controllers.ControllerManager;
import controllers.EnemyControllerManager;
import controllers.PlayerPlaneController;
import models.GameModel;
import utils.Utils;
import views.GameView;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

//struct

// Plane
// x, y, image, speed, width, height
// move, shot

/**
 * Created by huynq on 2/19/17.
 */
public class GameWindow extends Frame {
    private static final int SPEED = 20;
    private static final int SCREEN_WIDTH = 400;
    private static final int SCREEN_HEIGHT = 600;

    private BufferedImage backBufferImage;
    private Graphics backGraphics;

    Image backgroundImage;

    Thread thread;

    ControllerManager playerBulletControllerManager;
    PlayerPlaneController playerPlaneController;
    ControllerManager enemyControllerManager;


    public GameWindow() {

        setUpControllers();

        setVisible(true);
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("windowClosing");
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                System.out.printf("windowClosed");
            }
        });


        // 1: Load image
        backgroundImage = Utils.loadImageFromRes("background.png");



        // 2: Draw image
        repaint();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int keyCode = e.getKeyCode();

                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    playerPlaneController.moveRight();
                } else if (keyCode == KeyEvent.VK_LEFT) {
                    playerPlaneController.moveLeft();
                } else if (keyCode == KeyEvent.VK_UP) {
                    playerPlaneController.moveUp();
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    playerPlaneController.moveDown();
                } else if (keyCode == KeyEvent.VK_SPACE) {
                    playerPlaneController.shoot();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
            }
        });

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(17);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    repaint();


                    enemyControllerManager.run();
                    enemyControllerManager.run();
                    playerPlaneController.run();
                }
            }
        });

        backBufferImage = new BufferedImage(
                SCREEN_WIDTH,
                SCREEN_HEIGHT,
                BufferedImage.TYPE_INT_ARGB);

        backGraphics = backBufferImage.getGraphics();

        thread.start();
    }

    private void setUpControllers() {
        enemyControllerManager = new EnemyControllerManager();
        playerPlaneController = new PlayerPlaneController(
                new GameView("plane3.png"),
                new GameModel(SCREEN_WIDTH / 2, SCREEN_HEIGHT - 10, 70, 50),
                playerBulletControllerManager
        );
    }

    @Override
    public void update(Graphics g) {
        if (backBufferImage != null) {

            backGraphics.drawImage(backgroundImage, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, null);
            playerPlaneController.draw(backGraphics);
            enemyControllerManager.draw(backGraphics);

            g.drawImage(backBufferImage, 0, 0, null);
        }
    }
}
