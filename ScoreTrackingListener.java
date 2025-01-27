package sprite;

import gameEnv.Counter;
import gameEnv.HitListener;
import geometry.Ball;
import geometry.Block;

/**
 * This class calculate the score of the game.
 * @author Moshe Yehely Israel.
 * @since 27-08-2024.
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;

    /**
     * Constructor.
     * @param scoreCounter The score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method return the score.
     * @return The score.
     */
    public int getScore() {
        return this.currentScore.getValue();
    }

    /**
     * This method add 5 points to the score.
     * @param beingHit The block that being hit.
     * @param hitter   The ball that hit the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}
