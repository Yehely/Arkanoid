package sprite;
import biuoop.DrawSurface;
import geometry.Ball;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

/**
 * This interface is implemented in objects that other objects may collide with.
 */
public interface Collidable {

    /**
     * This method returns the shape of this object.
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * This method draws the object on the surface.
     * @param surface The surface on which it will be drawn.
     */
    void drawOn(DrawSurface surface);

    /**
     * This method returns the velocity of the hitting ball after hitting this object.
     * @param hitter            The ball that hit.
     * @param collisionPoint    The point where the call hit this object.
     * @param currentVelocity   The velocity of the ball before it hit.
     * @return                  The velocity of the ball after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
