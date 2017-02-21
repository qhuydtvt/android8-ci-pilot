import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
    Image planeImage;
    Image enemyImage;
    private int planeX = (SCREEN_WIDTH - 35) / 2;
    private int planeY = SCREEN_HEIGHT - 25;

    int enemyX = SCREEN_WIDTH / 2;
    int enemyY = 0;

    Thread thread;

    PlayerBullet playerBullet;

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

        // 1: Load image
        backgroundImage = loadImageFromRes("background.png");
        planeImage = loadImageFromRes("plane3.png");
        enemyImage = loadImageFromRes("plane1.png");



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
                    planeX += SPEED;
                } else if (keyCode == KeyEvent.VK_LEFT) {
                    planeX -= SPEED;
                } else if (keyCode == KeyEvent.VK_UP) {
                    planeY -= SPEED;
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    planeY += SPEED;
                } else if (keyCode == KeyEvent.VK_SPACE) {
                    playerBullet = new PlayerBullet();
                    playerBullet.image = loadImageFromRes("bullet.png");
                    playerBullet.x = planeX;
                    playerBullet.y = planeY;
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
                while(true) {
                    try {
                        Thread.sleep(17);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    repaint();

                    enemyY += 1;
                    if (playerBullet != null)
                        playerBullet.y -= 1;
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

    private Image loadImageFromRes(String url) {
        try {
            Image image = ImageIO.read(new File("resources/" + url));
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(Graphics g) {
        if (backBufferImage != null) {

            backGraphics.drawImage(backgroundImage, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, null);
            backGraphics.drawImage(planeImage, planeX, planeY, 35, 25, null);
            backGraphics.drawImage(enemyImage, enemyX, enemyY, 35, 30, null);
            if (playerBullet != null)
                backGraphics.drawImage(playerBullet.image, playerBullet.x, playerBullet.y, 13, 30, null);

            g.drawImage(backBufferImage, 0, 0, null);
        }
    }
}
