package sprite;
import biuoop.DrawSurface;
/**
 * This interface represents sprite type objects.
 * @author Moshe Yehely Israel.
 * @since 27-08-2024.
 */
public interface Sprite {
    /**
     * This method draw the sprite to the screen.
     * @param d the board of the game.
     */
    void drawOn(DrawSurface d);
    /**
     * This method notify the sprite that time has passed.
     */
    void timePassed();
}
