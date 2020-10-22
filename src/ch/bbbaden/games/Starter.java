/*
 */
package ch.bbbaden.games;

import javax.swing.JOptionPane;

/**
 *
 * @author Michael Schneider (michael.schneider@bbbaden.ch)
 */
public class Starter {
    
    public static void main(String[] args) {
        
        String[] options = {"Zombapocalypse", "Pong"};
        int x = JOptionPane.showOptionDialog(null, "Welches Spiel möchten Sie spielen?",
                "Spielmenü",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (x == 0) {
            new LibBasicsEvents().run();
        } else if (x == 1) {
            new Pong().run();
        }
        
    }
}
