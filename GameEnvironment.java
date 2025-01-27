package gameEnv;
import geometry.Line;
import geometry.Point;
import sprite.Collidable;
import sprite.CollisionInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * This class organizes the game environment.
 * The class contains a list of all collidable components in the game
 * @author Moshe Yehely Israel
 * @since 27-08-2024
 */

public class GameEnvironment {

    private final List<Collidable> collidableList;

    /**
     * constructor.
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<>();
    }

    /**
     * This method adds a collidable to the list.
     * @param c The collidable that need to add.
     */
    public void addCollidable(Collidable c) {
        this.collidableList.add(c);
    }

    /**
     * This method returns the CollisionInfo of the closest collision point to a certain line.
     * @param trajectory The certain line.
     * @return The CollisionInfo of the closest collision point to a certain line
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        for (Collidable collidable : collidableList) {
            Point closest = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (closest != null) {
                return new CollisionInfo(closest, collidable);
            }
        }
        return null;
    }

    /**
     * This method remove Collidablle from the list.
     * @param c The Collidable need to remove.
     */
    public void removeCollidable(Collidable c) {
        collidableList.remove(c);

    }

}
