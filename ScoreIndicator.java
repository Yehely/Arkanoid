//206530552 Moshe Yehely Israel
package gameEnv;
import sprite.Sprite;
import biuoop.DrawSurface;
/**
 * This class displays the score to the user.
 * @author Moshe Yehely Israel.
 * @since 27-08-2024.
 */
public class ScoreIndicator implements Sprite {

    private final Counter scoreCounter;

    /**
     * Constructor.
     * @param scoreCounter The Score.
     */
    public ScoreIndicator(Counter scoreCounter) {
        this.scoreCounter = scoreCounter;
    }

    /**
     * This method add the score indicator to the game.
     * @param g the game.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * This method draw the score on the game surface.
     * @param d The game surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(350, 25, "Score: " + this.scoreCounter.getValue(), 30);
    }

    /**
     * This method notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
    }
}
