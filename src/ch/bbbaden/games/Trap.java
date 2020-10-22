/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.games;

import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;

/**
 *
 * @author Kian
 */
public class Trap implements GameObject {
    
    private int x;
    private int y;
    
    public Trap(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public void update(WSwingConsoleInterface csi, Player player) {
        if (player.getX() == x && player.getY() == y) {
            player.addHealth(-5);
        }
    }
    
    @Override
    public String getDrawString() {
        return "X";
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
        return CSIColor.ROSE_TAUPE;
    }
    
}
