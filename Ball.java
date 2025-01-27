// 206530552 Moshe Yehely Israel.
package geometry;
import biuoop.DrawSurface;
import gameEnv.Game;
import gameEnv.GameEnvironment;
import sprite.CollisionInfo;
import sprite.Sprite;

/**
 * This class represents a ball in the game.
 * The ball is represented using a center point, radius, color, and velocity.
 * The class also holds the switch and board width and the game environment.
 * @author Moshe Yehely Israel
 * @since 27-08-2024
 */
public class Ball implements Sprite {

    private Point center;
    private final int r;
    private java.awt.Color color;
    private Velocity v;
    private int width;
    private int height;
    private GameEnvironment environment;


    /**
     * constructor.
     * @param center    The ball's center point.
     * @param r         The radius of the ball.
     * @param color     The color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        fixStartPoint();
        this.color = color;
        this.height = 200; // This is the default size of board.
        this.width = 200;
        this.v = new Velocity(1, 2); // This is the default value of velocity.
        environment = new GameEnvironment();
    }

    /**
     * constructor.
     * @param x     X value of ball's center point.
     * @param y     Y value of Ball's center point.
     * @param r     The radius of the ball.
     * @param color The color of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        fixStartPoint();
        this.color = color;
        this.height = 200; // This is the default size of board.
        this.width = 200;
        this.v = new Velocity(1, 2); //This is the default value of velocity.
        environment = new GameEnvironment();
    }

    /**
     * Get the X value of the center point of the ball.
     * @return the X value.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Get the Y value of the center point of the ball.
     * @return the Y value.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Get the radius of the center point of the ball.
     * @return the radius.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Get the color of the center point of the ball.
     * @return the color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Set color of the center point of the ball.
     * @param color The new color of the ball.
     */
    public void setColor(java.awt.Color color) {
        this.color = color;
    }

    /**
     * Set velocity of the ball.
     * @param v The new velocity of the ball.
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }
    /**
     * Set velocity of the ball.
     * @param dx The change of X value in one step.
     * @param dy The change of Y value in one step.
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * Get the velocity of the ball.
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * This method defines new height and width values.
     * @param width The new width.
     * @param height The new height.
     */
    public void setSurfaceSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Draw the ball on the given DrawSurface.
     * @param surface The DrawSurface on which it will be drawn.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle(this.getX(), this.getY(), this.r);
        setSurfaceSize(surface.getWidth(), surface.getHeight());
    }

    /**
     * This method updates the position of the ball after a "step".
     */
    public void moveOneStep() {

        // The next two conditions check if the ball has reached the edge of the screen.
        // If so, the ball will move in the opposite direction.
        if (this.getX() > this.width - this.r || this.getX() < this.r) {
            setVelocity(-this.v.getDx(), this.v.getDy());
        }
        if (this.getY() < this.r) {
            setVelocity(this.v.getDx(), -this.v.getDy());
        }

        CollisionInfo closestCollision = environment.getClosestCollision(getDirectionVector());
        //This condition checks whether a collision exists.
        if (closestCollision != null) {
            //Update ball's velocity:
            this.setVelocity(closestCollision.collisionObject().hit(this, closestCollision.collisionPoint(), v));
        }
        //Update ball's location:
        this.center = this.v.applyToPoint(this.center);
    }

    /**
     * This method produces a vector represented by a line.
     * The vector starts at the center of the ball
     * and ends at the point where the ball is expected to be in the next step + radius.
     * @return The direction vector.
     */
    public Line getDirectionVector() {
        double distance = Math.sqrt(v.getDx() * v.getDx() + v.getDy() * v.getDy());
        double ratio = this.r / distance;
        double newX = center.getX() + ratio * (v.getDx());
        double newY = center.getY() + ratio * (v.getDy());
        return new Line(center, new Point(newX, newY));
    }


    /**
     * This method activates the moveOneStep method when invoked.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * This method adds the ball to the game.
     * @param game The game to which the ball is added.
     */
    public void addToGame(Game game) {
        game.addSprite(this);
        this.environment = game.getEnvironment();
    }

    /**
     * This method removes the ball from the game.
     * @param game The game to which the ball is removed.
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }

    /**
     * This method corrects the position of the ball at the beginning of the game
     * if it was set in a problematic position.
     */
    private void fixStartPoint() {
        double x = center.getX();
        double y = center.getY();
        if (r >= center.getX()) {
            x = r;
        }
        if (r >= center.getY()) {
            y = r;
        }
        this.center = new Point(x, y);

    }


}