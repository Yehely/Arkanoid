// 206530552 Moshe Yehely Israel.
package geometry;
import gameEnv.Game;
import gameEnv.HitListener;
import gameEnv.HitNotifier;
import sprite.Sprite;
import sprite.Collidable;
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * This class represents a block in the game.
 * The block is represented by a rectangle. And, the block holds a list of listeners who listen to hits.
 * @author Moshe Yehely Israel.
 * @since 27-08-2024.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final Rectangle collisionRectangle;
    private final List<HitListener> hitListeners;

    /**
     * constructor.
     * @param upperLeft The upper-left point of the block.
     * @param width     The width of the block.
     * @param height    The height of the block.
     */
    public Block(Point upperLeft, double width, double height) {
        this.collisionRectangle = new Rectangle(upperLeft, width, height);
        this.hitListeners = new ArrayList<>();
    }

    /**
     * This method returns the collusion shape of the block.
     * @return the collusion shape.
     */
    public Rectangle getCollisionRectangle() {
        return collisionRectangle;
    }

    /**
     * This method returns the new velocity of a certain ball, after the ball hit this block.
     * @param hitter            The ball that hit this block.
     * @param collisionPoint    The point where the ball hit the block.
     * @param currentVelocity   The velocity of the ball before the hit.
     * @return                  The updated velocity of the ball "hitter".
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //If the ball hit a block of a different color than it, the method updates the list of listeners:
        if (!ballColorMatch(hitter)) {
            this.notifyHit(hitter);
        }

        //The following conditions check from which direction in relation to the block the hit was made
        // and change the velocity of the ball accordingly.
        if (this.collisionRectangle.isVertex(collisionPoint)) {
            return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
        }
        if (this.collisionRectangle.isHorizontal(collisionPoint)) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
    }

    /**
     * This method draws the block on the surface.
     * @param surface The surface on which the block will be drawn.
     */
    @Override
   public void drawOn(DrawSurface surface) {
        surface.setColor(this.collisionRectangle.getColor());
        surface.fillRectangle((int) this.collisionRectangle.getUpperLeft().getX(),
                (int) this.collisionRectangle.getUpperLeft().getY(),
                (int) this.collisionRectangle.getWidth(),
                (int) this.collisionRectangle.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.collisionRectangle.getUpperLeft().getX(),
                (int) this.collisionRectangle.getUpperLeft().getY(),
                (int) this.collisionRectangle.getWidth(),
                (int) this.collisionRectangle.getHeight());
    }

    /**
     *Unused method but necessary because the class is implements sprite.
     */
    @Override
    public void timePassed() {
    }

    /**
     * This method adds the block to the game.
     * @param game The game to which the block will be added.
     */
    public void addToGame(Game game) {
        game.addCollidable(this);
        game.addSprite(this);
    }

    /**
     * This method checks whether the color of a certain ball is equal to the color of the block.
     * @param ball The certain ball.
     * @return true if the color is equal, false otherwise.
     */
    public boolean ballColorMatch(Ball ball) {
        return ball.getColor().equals(this.collisionRectangle.getColor());
    }

    /**
     * This method removes the block from the game.
     * @param game The game from which the block will be removed.
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * This method adds a listener to the list of listeners for the block.
     * @param hl The listener to be added to the list.
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * This method remove a listener from the list of listeners for the block.
     * @param hl The listener to be removed from the list.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * This method notify all listeners that a violation has been hit.
     * @param hitter The ball that hit the block
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }


}
