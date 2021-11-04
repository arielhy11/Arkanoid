/**
 * ID: 313450249
 * Name: Ariel Mantel
 */

import biuoop.GUI;

/**
 * a class that runs the game.
 */
public class Ass6Game {
    /**
     * the main of the class.
     * @param args null.
     */
    public static void main(String[] args) {
        // the board is created, by the size of 800X600.
        GUI gui = new GUI("Game!", 800, 600);
        AnimationRunner runner = new AnimationRunner(gui);
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        Game game = new Game(gui, keyboard, runner);
        game.initialize();
        game.run();
    }
}
