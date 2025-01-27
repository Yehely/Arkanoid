//206530552 Moshe Yehely Israel
package geometry;
/**
 * This method represents ball velocity in the game.
 * @author Moshe Yehely Israel.
 * @since 27-08-2024.
 */
public class Velocity {

    private final double dx;
    private final double dy;

    /**
     * Constructor.
     * @param dx The change of X value of ball's velocity.
     * @param dy The change of Y value of ball's velocity.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Constructor.
     * @param angle The angle of ball's velocity.
     * @param speed The speed of ball's velocity.
     * @return The velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radAngle = Math.toRadians(angle);
        double dx = speed * Math.cos(radAngle);
        double dy = speed * Math.sin(radAngle);
        return new Velocity(dx, dy);
    }

    /**
     * This method returns the change in the X value of the ball's center.
     * @return The change in the X value of the ball's center.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * This method returns the change in the Y value of the ball's center.
     * @return The change in the Y value of the ball's center.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * This method calculate the speed from this velocity.
     * @return The speed.
     */
    public double getSpeed() {
        return Math.sqrt((this.dx * this.dx) + (this.dy * this.dy));
    }

    /**
     * This method calculates the values of the newly created point using velocity and a given point.
     * @param p The given point.
     * @return  The new point.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

}