package gameEnv;
import geometry.Ball;
import geometry.Block;

/**
 * This class is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 * @author Moshe Yehely Israel
 * @since 27-08-2024
 */
public class BlockRemover implements HitListener {
    private final Game game;
    private final Counter remainingBlocks;

    /**
     * constructor.
     * @param game              The game.
     * @param remainingBlocks   The blocks that remain int the game.
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * This method remove block from the game.
     * @param beingHit The block that need to remove.
     * @param hitter   The ball that hit the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.setColor(beingHit.getCollisionRectangle().getColor()); //change ball's color.
        beingHit.removeFromGame(this.game);
        beingHit.removeHitListener(this);
        remainingBlocks.decrease(1);
    }
}
