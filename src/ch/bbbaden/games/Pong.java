/*
 */
package ch.bbbaden.games;

import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;

/**
 *
 * @author Michael Schneider (michael.schneider@bbbaden.ch)
 */
public class Pong {

    private final WSwingConsoleInterface csi;

    public Pong() {
        csi = new WSwingConsoleInterface("Basic Game - Event Based");
        System.out.println("X-Dimension: " + csi.getXdim());
        System.out.println("Y-Dimension: " + csi.getYdim());
    }

    public void run() {
        int playerX = 36;
        int playerNextX = 36;
        int playerY = 23;
        int player2X = 36;
        int player2NextX = 36;
        int player2Y = 1;
        int ballX = 20;
        int ballNextX = 20;
        int ballY = 12;
        int ballNextY = 12;
        int pointsP1 = 0;
        int pointsP2 = 0;
        boolean X = true;
        boolean Y = true;

        // Draw static part
        setupGameBoard();
        boolean exit = false;
        while (!exit) {
            // Draw game board
            csi.restore();
            // Points
            csi.print(68, 23, "Points: " + Integer.toString(pointsP1), CSIColor.ATOMIC_TANGERINE);
            csi.print(1, 1, "Points: " + Integer.toString(pointsP2), CSIColor.STEEL_BLUE);
            // Draw dynamic part
            csi.print(playerX, playerY, "--------", CSIColor.ATOMIC_TANGERINE);
            csi.print(player2X, player2Y, "--------", CSIColor.STEEL_BLUE);
            csi.print(ballX, ballY, "O", CSIColor.WHITE);
            // Push to screen
            csi.refresh();

            // Wait for key
            int key = csi.inkey().code;
            switch (key) {
                case CharKey.A:
                case CharKey.a:
                    player2NextX = player2X - 2;
                    break;
                case CharKey.D:
                case CharKey.d:
                    player2NextX = player2X + 2;
                    break;
                case CharKey.LARROW:
                    playerNextX = playerX - 2;
                    break;
                case CharKey.RARROW:
                    playerNextX = playerX + 2;
                    break;
                case CharKey.Q:
                case CharKey.q:
                    exit = true;
            }
            // move player
            playerX = playerNextX;
            player2X = player2NextX;

            // bounce ball on sideborders
            if (ballX == 64) {
                X = false;
            } else if (ballX == 15) {
                X = true;
            }

            if (X) {
                ballNextX++;
            } else {
                ballNextX--;
            }
            if (Y) {
                ballNextY++;
            } else {
                ballNextY--;
            }

            //reset game after points
            if (ballNextY <= 0) {
                ballY = 12;
                ballNextY = 12;
                ballX = 20;
                ballNextX = 20;
                pointsP1++;
                playerX = 36;
                playerNextX = 36;
                player2X = 36;
                player2NextX = 36;
            } else if (ballNextY >= 24) {
                ballY = 12;
                ballNextY = 12;
                ballX = 20;
                ballNextX = 20;
                pointsP2++;
                playerX = 36;
                playerNextX = 36;
                player2X = 36;
                player2NextX = 36;
            }

            // collision with paddles
            if (ballY > 15) {
                if (csi.peekChar(ballNextX + 1, ballNextY + 1) == '-') {
                    Y = false;
                }
            } else if (ballY < 5) {
                if (csi.peekChar(ballNextX - 1, ballNextY - 1) == '-') {
                    Y = true;
                }
            }

            // move ball
            ballX = ballNextX;
            ballY = ballNextY;

            // sreenbordercollision of paddles
            if (playerNextX <= 14) {
                playerX = 15;
            } else if (playerNextX >= 58) {
                playerX = 57;
            }
            if (player2NextX <= 14) {
                player2X = 15;
            } else if (player2NextX >= 58) {
                player2X = 57;
            }

        }
        // quit game
        csi.print(1, 20, "Press space to exit");
        csi.refresh();
        csi.waitKey(CharKey.SPACE);
        System.exit(0);
    }

    private void setupGameBoard() {
        csi.cls();
        csi.print(14, 0, "|", CSIColor.LIGHT_GRAY);
        csi.print(14, 1, "|", CSIColor.LIGHT_GRAY);
        csi.print(14, 2, "|", CSIColor.LIGHT_GRAY);
        csi.print(14, 3, "|", CSIColor.LIGHT_GRAY);
        csi.print(14, 4, "|", CSIColor.LIGHT_GRAY);
        csi.print(14, 5, "|", CSIColor.LIGHT_GRAY);
        csi.print(14, 6, "|", CSIColor.LIGHT_GRAY);
        csi.print(14, 7, "|", CSIColor.LIGHT_GRAY);
        csi.print(14, 8, "|", CSIColor.LIGHT_GRAY);
        csi.print(14, 9, "|", CSIColor.LIGHT_GRAY);
        csi.print(14, 10, "|", CSIColor.LIGHT_GRAY);
        csi.print(14, 11, "|", CSIColor.LIGHT_GRAY);
        csi.print(14, 12, "|", CSIColor.LIGHT_GRAY);
        csi.print(14, 13, "|", CSIColor.LIGHT_GRAY);
        csi.print(14, 14, "|", CSIColor.LIGHT_GRAY);
        csi.print(14, 15, "|", CSIColor.LIGHT_GRAY);
        csi.print(14, 16, "|", CSIColor.LIGHT_GRAY);
        csi.print(14, 17, "|", CSIColor.LIGHT_GRAY);
        csi.print(14, 18, "|", CSIColor.LIGHT_GRAY);
        csi.print(14, 19, "|", CSIColor.LIGHT_GRAY);
        csi.print(14, 20, "|", CSIColor.LIGHT_GRAY);
        csi.print(14, 21, "|", CSIColor.LIGHT_GRAY);
        csi.print(14, 22, "|", CSIColor.LIGHT_GRAY);
        csi.print(14, 23, "|", CSIColor.LIGHT_GRAY);
        csi.print(14, 24, "|", CSIColor.LIGHT_GRAY);
        csi.print(65, 0, "|", CSIColor.LIGHT_GRAY);
        csi.print(65, 1, "|", CSIColor.LIGHT_GRAY);
        csi.print(65, 2, "|", CSIColor.LIGHT_GRAY);
        csi.print(65, 3, "|", CSIColor.LIGHT_GRAY);
        csi.print(65, 4, "|", CSIColor.LIGHT_GRAY);
        csi.print(65, 5, "|", CSIColor.LIGHT_GRAY);
        csi.print(65, 6, "|", CSIColor.LIGHT_GRAY);
        csi.print(65, 7, "|", CSIColor.LIGHT_GRAY);
        csi.print(65, 8, "|", CSIColor.LIGHT_GRAY);
        csi.print(65, 9, "|", CSIColor.LIGHT_GRAY);
        csi.print(65, 10, "|", CSIColor.LIGHT_GRAY);
        csi.print(65, 11, "|", CSIColor.LIGHT_GRAY);
        csi.print(65, 12, "|", CSIColor.LIGHT_GRAY);
        csi.print(65, 13, "|", CSIColor.LIGHT_GRAY);
        csi.print(65, 14, "|", CSIColor.LIGHT_GRAY);
        csi.print(65, 15, "|", CSIColor.LIGHT_GRAY);
        csi.print(65, 16, "|", CSIColor.LIGHT_GRAY);
        csi.print(65, 17, "|", CSIColor.LIGHT_GRAY);
        csi.print(65, 18, "|", CSIColor.LIGHT_GRAY);
        csi.print(65, 19, "|", CSIColor.LIGHT_GRAY);
        csi.print(65, 20, "|", CSIColor.LIGHT_GRAY);
        csi.print(65, 21, "|", CSIColor.LIGHT_GRAY);
        csi.print(65, 22, "|", CSIColor.LIGHT_GRAY);
        csi.print(65, 23, "|", CSIColor.LIGHT_GRAY);
        csi.print(65, 24, "|", CSIColor.LIGHT_GRAY);
        csi.saveBuffer();
    }
}
