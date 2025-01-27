//206530552 Moshe Yehely Israel.
package sprite;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gameEnv.Game;
import geometry.Ball;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

import java.awt.Color;
/**
 * This class represents a Paddle.
 * @author Moshe Yehely Israel.
 * @since 27-08-2024.
 */
public class Paddle implements Sprite, Collidable {

    private final Rectangle rectangle;
    private final biuoop.GUI gui;
    private biuoop.KeyboardSensor keyboard;
    private final int width; //of the window
    private final int height; // of the window
    /**
     * constructor.
     * @param game The game.
     */
    public Paddle(Game game) {
        this.gui = game.getGui();
        this.keyboard = this.gui.getKeyboardSensor();
        this.width = game.getWidth();
        this.height = game.getHeight();
        this.rectangle = new Rectangle(new Point((int) (width / 2) - 100, height - 40), 100, 20);
    }

    /**
     * This method moves the paddle to the left.
     */
    public void moveLeft() {
        this.rectangle.changeUpperLeft(-10, 0);
        if (this.rectangle.getUpperLeft().getX() <= -rectangle.getWidth()) {
            this.rectangle.changeUpperLeft(width, 0);
        }
    }
    /**
     * This method moves the paddle to the right.
     */
    public void moveRight() {
        this.rectangle.changeUpperLeft(10, 0);
        if (this.rectangle.getUpperLeft().getX() >= this.width) {
            this.rectangle.changeUpperLeft(-width, 0);
        }
    }

    /**
     * This method alerts that time has passed.
     * Each time unit the method checks whether the user clicked on the right or left and moves the paddle accordingly.
     */
    @Override
    public void timePassed() {
        this.keyboard = this.gui.getKeyboardSensor();
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * This method draws the paddle on the game surface.
     * @param d The surface on which it will be drawn.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.ORANGE);
        d.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
    }

    /**
     * This method returns the shape of the paddle.
     * @return The shape of the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }
    /**
     * This method adds the paddle to the game.
     * @param g The game where need to add the paddle.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * This method calculates the velocity of the ball after hitting the paddle
     * The method divides the paddle into different areas and the velocity changes accordingly.
     * @param ball              The ball that hit.
     * @param collisionPoint    The point where the ball hit the paddle.
     * @param currentVelocity   The velocity of the ball before it hit.
     * @return the new velocity of the ball.
     */
    public Velocity hit(Ball ball, Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint.getX() <= this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() / 5) {
            return Velocity.fromAngleAndSpeed(210, currentVelocity.getSpeed());
        }
        if (collisionPoint.getX() <= this.rectangle.getUpperLeft().getX() + (this.rectangle.getWidth() / 5) * 2) {
            return Velocity.fromAngleAndSpeed(240, currentVelocity.getSpeed());
        }
        if (collisionPoint.getX() <= this.rectangle.getUpperLeft().getX() + (this.rectangle.getWidth() / 5) * 3) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        if (collisionPoint.getX() <= this.rectangle.getUpperLeft().getX() + (this.rectangle.getWidth() / 5) * 4) {
            return Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
        }
        if (collisionPoint.getX() <= this.rectangle.getUpperLeft().getX() + (this.rectangle.getWidth())) {
            return Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
        }
        return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());

    }
}