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
public interface GameObject {

    public void update(WSwingConsoleInterface csi, Player player);

    public String getDrawString();

    public boolean isDead();

    public int getX();

    public int getY();

    public CSIColor getColor();
}
