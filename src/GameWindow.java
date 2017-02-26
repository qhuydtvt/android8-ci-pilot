import controllers.PlayerBulletController;
import models.PlayerBulletModel;
import utils.Utils;
import views.PlayerBulletView;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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

    Image enemyImage;

    int enemyX = SCREEN_WIDTH / 2;
    int enemyY = 0;

    Thread thread;

    ArrayList<PlayerBullet> playerBullets = new ArrayList<>();

    PlayerPlane plane;

    PlayerBulletController playerBulletController;

    public GameWindow() {
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

        playerBulletController = new PlayerBulletController(300, 300);

        plane = new PlayerPlane(
                (SCREEN_WIDTH - 35) / 2,
                SCREEN_HEIGHT - 25,
                Utils.loadImageFromRes("plane3.png"),
                35,
                30
        );


        // 1: Load image
        backgroundImage = Utils.loadImageFromRes("background.png");
        enemyImage = Utils.loadImageFromRes("plane1.png");


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
                    plane.moveRight();
                } else if (keyCode == KeyEvent.VK_LEFT) {
                    plane.moveLeft();
                } else if (keyCode == KeyEvent.VK_UP) {
                    plane.moveUp();
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    plane.moveDown();
                } else if (keyCode == KeyEvent.VK_SPACE) {
                    PlayerBullet playerBullet = new PlayerBullet();
                    playerBullet.image = Utils.loadImageFromRes("bullet.png");

                    playerBullet.x = plane.getX();
                    playerBullet.y = plane.getY();

                    playerBullets.add(playerBullet);
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

                    enemyY += 1;

                    playerBulletController.run();

                    for (PlayerBullet playerBullet : playerBullets) {
                        playerBullet.run();
                    }

                    plane.run();
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


    @Override
    public void update(Graphics g) {
        if (backBufferImage != null) {

            backGraphics.drawImage(backgroundImage, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, null);
            plane.draw(backGraphics);
            backGraphics.drawImage(enemyImage, enemyX, enemyY, 35, 30, null);

            playerBulletController.draw(backGraphics);

            for (PlayerBullet playerBullet : playerBullets) {
                backGraphics.drawImage(playerBullet.image, playerBullet.x, playerBullet.y, 13, 30, null);
            }

            g.drawImage(backBufferImage, 0, 0, null);
        }
    }
}
