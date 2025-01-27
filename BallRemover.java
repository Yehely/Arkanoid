// 206530552 Moshe Yehely Israel.
package gameEnv;

import geometry.Ball;
import geometry.Block;

/**
 * This class represents an object that removes balls from the game.
 * @author Moshe Yehely Israel
 * @since 27-08-2024
 */
public class BallRemover implements HitListener {
    private final Game game;
    private final Counter remainingBalls;

    /**
     * constructor.
     * @param game              The game.
     * @param remainingBalls    The amount of balls that remained.
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * This method removes the ball from play when the ball hits the specific block.
     * @param beingHit the specific block that hit the ball.
     * @param hitter the ball that hit the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        remainingBalls.decrease(1);
    }
}
