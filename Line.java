//206530552 Moshe Yehely Israel.
package geometry;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a Line.
 * @author Moshe Yehely Israel.
 * @since 27-08-2024.
 */

public class Line {
    private final Point start;
    private final Point end;

    /**
     * constructor of the class using start and end points.
     *
     * @param start the start point of the line.
     * @param end   the end point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor of the class using x,y values of start and end points.
     *
     * @param x1 x value of start point
     * @param y1 y value of start point
     * @param x2 x value of end point
     * @param y2 y value of end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * this method calculate the length of the line.
     *
     * @return line's length.
     */
    public double length() {
        return start.distance(end);
    }

    // Returns the middle point of the line

    /**
     * this method calculate the middle point of the line.
     *
     * @return the middle point of the line.
     */
    public Point middle() {
        double newX = (start.getX() + end.getX()) / 2;
        double newY = (start.getY() + end.getY()) / 2;
        return new Point(newX, newY);
    }

    // Returns the start point of the line

    /**
     * return the start point of the line.
     *
     * @return the start point
     */
    public Point start() {
        return this.start;
    }

    // Returns the end point of the line

    /**
     * return the end point of the line.
     *
     * @return the end point
     */
    public Point end() {
        return this.end;
    }

    // Returns the incline of specific line

    /**
     * calculate the incline of line.
     *
     * @param line the line on which incline is calculate.
     * @return the incline.
     */
    public double getIncline(Line line) {
        return (line.start.getY() - line.end.getY()) / (line.start.getX() - line.end.getX());
    }

    /**
     * this method calculate the intersection point between the line and other line.
     *
     * @param other the other line
     * @return the intersection point
     */
    public Point intersectionPoint(Line other) {
        //I named n to the variables because the formula: y = mx + n
        double otherN = other.start.getY() - (getIncline(other) * other.start.getX());
        double thisN = this.start.getY() - (getIncline(this) * this.start.getX());
        double x = (thisN - otherN) / (getIncline(other) - getIncline(this));
        double y = (getIncline(this) * x) + thisN;
        if (other.start.getX() == other.end.getX()) {
            x = other.start.getX();
            y = (getIncline(this) * x + thisN);
        }
        return new Point(x, y);
    }

    /**
     * check if value1 is between value2 and value3.
     *
     * @param value1 the number
     * @param value2 cut broder 1
     * @param value3 cut broder 2
     * @return true if value1 is between value2 and value3, false otherwise
     */
    public boolean isBetween(double value1, double value2, double value3) {
        return value1 <= Math.max(value2, value3) && value1 >= Math.min(value2, value3);
    }

    /**
     * this method check if the lines intersect.
     *
     * @param other the line that compare with this line.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        return intersecting(other);
    }

    /**
     * check if this 2 lines intersect with this line.
     *
     * @param other1 one of the lines that compare with this line.
     * @param other2 one of the lines that compare with this line.
     * @return true if this 2 lines intersect with this line, false otherwise
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return intersecting(other1) && intersecting(other2);
    }

    /**
     * check if intersection point is exist between this and other line.
     *
     * @param other the other line.
     * @return the intersection point if it exists, null otherwise.
     */
    public Point intersectionWith(Line other) {
        if (!isIntersecting(other)) {
            return null;
        } else {
            return intersectionPoint(other);
        }
    }

    /**
     * this method check if the line is equal to other line.
     *
     * @param other the other line
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end) && this.end == other.start);
    }

    /**
     * this method check if the lines intersect.
     *
     * @param other the line that compare with this line.
     * @return true if the lines intersect, false otherwise.
     */
    // I used this method because I have 2 methods with same name (isIntersecting) and the compiler complicated with it.
    // it seems that I used an unnecessary method, but that's the only way I was able to make it work.
    private boolean intersecting(Line other) {
        double otherIncline = getIncline(other);
        double thisIncline = getIncline(this);
        if (otherIncline == thisIncline) {
            double totalLength = this.length() + other.length();
            return !(this.start.distance(other.start) > totalLength) && !(this.start.distance(other.end) > totalLength)
                    && !(this.end.distance(other.start) > totalLength) && !(this.end.distance(other.end) > totalLength);
        }
        Point intersection = intersectionPoint(other);
        return isBetween(intersection.getX(), this.start.getX(), this.end.getX())
                && isBetween(intersection.getY(), this.start.getY(), this.end.getY())
                && isBetween(intersection.getX(), other.start.getX(), other.end.getX())
                && isBetween(intersection.getY(), other.start.getY(), other.end.getY());
    }

    /**
     * This method returns the intersection point closest to the starting point between the line and a given rectangle.
     *
     * @param rect The Rectangle.
     * @return The closest intersection to the start point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Point upperRight = new Point(rect.getUpperLeft().getX() + rect.getWidth(), rect.getUpperLeft().getY());
        Point lowerLeft = new Point(rect.getUpperLeft().getX(), rect.getUpperLeft().getY() + rect.getHeight());
        Point lowerRight = new Point(rect.getUpperLeft().getX() + rect.getWidth(),
                rect.getUpperLeft().getY() + rect.getHeight());
        List<Point> intersectionPoints = new ArrayList<>();
        intersectionPoints.add(intersectionWith(new Line(rect.getUpperLeft(), upperRight)));
        intersectionPoints.add(intersectionWith(new Line(rect.getUpperLeft(), lowerLeft)));
        intersectionPoints.add(intersectionWith(new Line(upperRight, lowerRight)));
        intersectionPoints.add(intersectionWith(new Line(lowerRight, lowerLeft)));
        return closestToStartOfPointsList(intersectionPoints);

    }

    /**
     * This method returns the closest point to the beginning of the line from a list of points.
     * @param points The list of points.
     * @return the closest point to the beginning of the line from the list.
     */
    public Point closestToStartOfPointsList(List<Point> points) {
        double minDistance = Double.MAX_VALUE;
        Point closest = null;

        for (Point point : points) {
            if (point != null && point.distance(this.start) < minDistance) {
                minDistance = point.distance(this.start);
                closest = point;
            }
        }
        return closest;
    }

    /**
     * this method return line from end to start.
     *
     * @return the revered line.
     */
    public Line reverse() {
        return new Line(this.end, this.start);
    }
}