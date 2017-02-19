import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by huynq on 2/19/17.
 */
public class GameWindow extends Frame {
    public GameWindow() {
        setVisible(true);
        setSize(400, 600);

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
    }
}
