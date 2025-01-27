// 206530552 Moshe Yehely Israel.
package gameEnv;

import geometry.Ball;
import geometry.Block;

/**
 * This interface represents hits listeners.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit The block that being hit.
     * @param hitter   The ball that hit the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
