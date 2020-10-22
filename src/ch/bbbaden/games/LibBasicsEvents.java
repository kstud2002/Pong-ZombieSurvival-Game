/*
 */
package ch.bbbaden.games;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Random;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;

/**
 *
 * @author Michael Schneider (michael.schneider@bbbaden.ch)
 */
public class LibBasicsEvents {

    private final WSwingConsoleInterface csi;
    List<GameObject> gameObjects = new ArrayList<>();
    Player player = new Player(0, 0);
    Random x = new Random();
    Random y = new Random();

    public LibBasicsEvents() {
        csi = new WSwingConsoleInterface("Basic Game - Event Based");
        System.out.println("X-Dimension: " + csi.getXdim());
        System.out.println("Y-Dimension: " + csi.getYdim());
    }

    public void run() {
        // Draw static part
        setupGameBoard();
        setupGameObjects();

        while (!player.exit && !player.isDead()) {
            // Draw game board
            csi.restore();

            // Points & Health
            csi.print(1, 1, "Points: " + player.getPoints());
            csi.print(1, 2, "Health: " + player.getHealth());
            try {
                for (GameObject o : gameObjects) {
                    if (o.isDead()) {
                        gameObjects.remove(o);
                    } else {
                        int ox = o.getX();
                        int oy = o.getY();
                        if (csi.peekChar(ox, oy) == '#') {
                            ox = x.nextInt(80);
                            oy = y.nextInt(24);
                        }
                        csi.print(ox, oy, o.getDrawString(), o.getColor());
                    }
                }
            } catch (ConcurrentModificationException e) {
            }
            csi.print(15, 20, "OO", CSIColor.ELECTRIC_GREEN);
            csi.print(14, 21, "OOOO", CSIColor.ELECTRIC_GREEN);
            csi.print(15, 22, "##", CSIColor.DARK_BROWN);

            csi.print(35, 10, "OO", CSIColor.ELECTRIC_GREEN);
            csi.print(34, 11, "OOOO", CSIColor.ELECTRIC_GREEN);
            csi.print(35, 12, "##", CSIColor.DARK_BROWN);

            csi.print(45, 4, "OO", CSIColor.ELECTRIC_GREEN);
            csi.print(44, 5, "OOOO", CSIColor.ELECTRIC_GREEN);
            csi.print(45, 6, "##", CSIColor.DARK_BROWN);

            csi.print(55, 14, "OO", CSIColor.ELECTRIC_GREEN);
            csi.print(54, 15, "OOOO", CSIColor.ELECTRIC_GREEN);
            csi.print(55, 16, "##", CSIColor.DARK_BROWN);

            player.action(csi.inkey().code, csi);

            for (GameObject o : gameObjects) {
                o.update(csi, player);
            }

            // Push to screen
            csi.refresh();

        }
        csi.print(1, 20, "Press space to exit");
        csi.refresh();
        csi.waitKey(CharKey.SPACE);
        System.exit(0);
    }

    private void setupGameBoard() {
        csi.cls();
        csi.print(10, 9, "##############", CSIColor.LIGHT_GRAY);
        csi.print(10, 10, "#            #", CSIColor.LIGHT_GRAY);
        csi.print(10, 11, "#     #      #", CSIColor.LIGHT_GRAY);
        csi.print(10, 12, "###   #      #", CSIColor.LIGHT_GRAY);
        csi.print(10, 13, "#     #      #", CSIColor.LIGHT_GRAY);
        csi.print(10, 14, "###   ########", CSIColor.LIGHT_GRAY);
        csi.saveBuffer();
    }

    private void setupGameObjects() {

        gameObjects.add(player);
        for (int i = 0; i < 10; i++) {
            gameObjects.add(new Trap(x.nextInt(80), y.nextInt(24)));

        }
        for (int i = 0; i < 20; i++) {
            gameObjects.add(new Medikit(x.nextInt(80), y.nextInt(24)));

        }
        for (int i = 0; i < 10; i++) {
            gameObjects.add(new Zombie(x.nextInt(80), y.nextInt(24)));

        }

    }
}
