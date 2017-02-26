import java.awt.*;

/**
 * Created by huynq on 2/21/17.
 */
public class PlayerBullet {
    public Image image;
    public int x;
    public int y;
    public static final int SPEED = 5;

    public void run() {
        y -= SPEED;
    }
}
