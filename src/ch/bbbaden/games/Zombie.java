/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.games;

import java.util.Random;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;

/**
 *
 * @author Kian
 */
public class Zombie implements GameObject {

    private int x;
    private int y;
    private int NextX;
    private int NextY;
    private int direction;
    private boolean hit;

    public Zombie(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void update(WSwingConsoleInterface csi, Player player) {
        if (!hit) {

            if (player.getX() > x) {
                NextX = x + 1;
            } else if (player.getX() < x) {
                NextX = x - 1;
            }
            if (player.getY() > y) {
                NextY = y + 1;
            } else if (player.getY() < y) {
                NextY = y - 1;
            }
            if (NextX >= 0 && NextY >= 0 && NextX <= 79 && NextY <= 24) {
                while (csi.peekChar(NextX, NextY) == '#') {
                    direction = new Random().nextInt(4);
                    NextX = x;
                    NextY = y;
                    switch (direction) {
                        case 0:
                            NextX = x + 1;
                            break;
                        case 1:
                            NextX = x - 1;
                            break;
                        case 2:
                            NextY = y + 1;
                            break;
                        case 3:
                            NextY = y - 1;
                            break;

                        default:
                            throw new AssertionError();
                    }
                }
            }

            x = NextX;
            y = NextY;

            if (player.getX() == x && player.getY() == y) {
                player.addHealth(-10);
                hit = true;
            }
        } else {
            hit = false;
        }
    }

    @Override
    public String getDrawString() {
        return "Z";
    }

    @Override
    public boolean isDead() {
        return false;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public CSIColor getColor() {
        return CSIColor.RED;
    }

}
