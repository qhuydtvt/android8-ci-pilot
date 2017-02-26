import java.awt.*;

/**
 * Created by huynq on 2/26/17.
 */
public class PlayerPlane {

    public static final int SPEED = 10;

    private int x;// read-only
    private int y;// = SCREEN_HEIGHT - 25;

    private Image image;

    private int width;
    private int height;

    public PlayerPlane(int x, int y, Image image, int width, int height) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.width = width;
        this.height = height;
    }

    public int getX() { //getter
        return x;
    }

    public void moveLeft() {
        x -= SPEED;
    }

    public void moveRight() {
        x += SPEED;
    }

    public void moveUp() {
        y -= SPEED;
    }

    public void moveDown() {
        y += SPEED;
    }

    public int getY() {
        return y;
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(image, x, y, width, height, null);
    }

    // Update dinh ky
    public void run() {

    }
}
