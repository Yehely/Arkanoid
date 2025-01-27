package geometry;
/**
 * This class represents a point.
 * @author Moshe Yehely Israel.
 * @since 27-08-2024.
 */

public class Point {
    private final double x;
    private final double y;

    // constructor

    /**
     * constructor of the class.
     * @param x x value of the point.
     * @param y y value of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * this method calculate the distance from the point to other point.
     * @param other represents other point.
     * @return the distance.
     */
    public double distance(Point other) {
        double xDistance = (other.x - this.x) * (other.x - this.x);
        double yDistance = (other.y - this.y) * (other.y - this.y);
        return Math.sqrt(xDistance + yDistance);
    }

    /**
     * this method check if the point is equal to other point.
     * @param other represents other point.
     * @return true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        return this.x == other.x && this.y == other.y;
    }
    /**
     * return x value.
     * @return x value.
     */
    public double getX() {
        return x;
    }

    /**
     * return y value.
     * @return y value.
     */
    public double getY() {
        return y;
    }
}
