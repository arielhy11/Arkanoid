/**
 * ID: 313450249
 * Name: Ariel Mantel
 */
import biuoop.DrawSurface;

/**
 *  a game object that can be drawn to the screen (and which is not just a background image).
 */
public interface Sprite {

    /**
     * draw the sprite to the screen.
     * @param d the surface.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}
