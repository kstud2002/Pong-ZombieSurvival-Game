/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.games;

import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;

/**
 *
 * @author Kian
 */
public class Player implements GameObject {

    private int health = 100;
    private int points;
    private int x;
    private int y;
    private int NextX = 0;
    private int NextY = 0;
    boolean exit = false;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void action(int keyChar, WSwingConsoleInterface csi) {
        switch (keyChar) {
            case CharKey.UARROW:
                NextY = y - 1;
                break;
            case CharKey.DARROW:
                NextY = y + 1;
                break;
            case CharKey.LARROW:
                NextX = x - 1;
                break;
            case CharKey.RARROW:
                NextX = x + 1;
                break;
            case CharKey.Q:
            case CharKey.q:
                exit = true;
        }
        //Collision
        if (NextX >= 0 && NextY >= 0 && NextX <= 79 && NextY <= 24) {
            if (csi.peekChar(NextX, NextY) == '#') {
                NextX = x;
                NextY = y;
                points--;
            }
        }

        x = NextX;
        y = NextY;
        points++;

        //Sreenbordercollision
        if (NextX == -1) {
            x = 79;
        } else if (NextX == 80) {
            x = 0;
        }
        if (NextY == -1) {
            y = 24;
        } else if (NextY == 25) {
            y = 0;
        }
    }

    public int getHealth() {
        return health;
    }

    public void addHealth(int health) {
        this.health += health;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    @Override
    public void update(WSwingConsoleInterface csi, Player player) {

    }

    @Override
    public String getDrawString() {
        return "P";
    }

    @Override
    public boolean isDead() {
        return getHealth() < 0;
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
        return CSIColor.PUMPKIN;
    }

}
