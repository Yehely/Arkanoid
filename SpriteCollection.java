//206530552 Moshe Yehely Israel
package sprite;
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a list of Sprite type objects.
 * @author Moshe Yehely Israel.
 * @since 27-08-2024.
 */
public class SpriteCollection {

    private final List<Sprite> spritesList;

    /**
     * Constructor.
     */
    public SpriteCollection() {
        this.spritesList = new ArrayList<>();
    }

    /**
     * This method add sprite to the list.
     * @param s The sprite that need to add.
     */
    public void addSprite(Sprite s) {
        this.spritesList.add(s);
    }

    /**
     * This method remove sprite from the list.
     * @param s The sprite that need to remove.
     */
    public void removeSprite(Sprite s) {
        this.spritesList.remove(s);
    }

    /**
     * This method call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (Sprite sprite : new ArrayList<>(spritesList)) {
            sprite.timePassed();
        }
    }

    /**
     * This method call drawOn(d) on all sprites.
     * @param d The surface on which the sprites will be drawn.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : new ArrayList<>(this.spritesList)) {
            sprite.drawOn(d);
        }
    }
}