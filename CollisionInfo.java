package sprite;

import geometry.Point;

/**
 * This class holds information about a certain object
 * and at the point where another object collided with it.
 * @author Moshe Yehely Israel
 * @since 27-08-2024
 */
public class CollisionInfo {

    private final Point thePoint;
    private final Collidable c;

    /**
     * constructor.
     * @param point The point where the collision was.
     * @param c     The object that experienced a collision.
     */
    public CollisionInfo(Point point, Collidable c) {
        this.thePoint = point;
        this.c = c;
    }

    /**
     * This method returns the collision point.
     * @return The collision point.
     */
    public Point collisionPoint() {
        return thePoint;
    }

    /**
     * This method returns the object that experienced a collision.
     * @return The object that experienced a collision.
     */
    public Collidable collisionObject() {
        return c;
    }
}
