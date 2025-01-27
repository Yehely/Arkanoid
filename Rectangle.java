//206530552 Moshe Yehely Israel
package geometry;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a rectangle.
 * @author Moshe Yehely Israel.
 * @since 27-08-2024.
 */
public class Rectangle {

    private Point upperLeft;
    private final double width;
    private final double height;
    private Color color;

    /**
     * Constructor.
     *
     * @param upperLeft The point of upper-left of the rectangle.
     * @param width     The width of the rectangle.
     * @param height    The height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = Color.GRAY;
    }

    /**
     * this method create a list of the intersection points between the rectangle to specific line.
     *
     * @param line the specific line.
     * @return the list of intersection points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPointsList = new ArrayList<>();
        intersectionPointsList.add(line.closestIntersectionToStartOfLine(this));
        intersectionPointsList.add(line.reverse().closestIntersectionToStartOfLine(this));
        if (intersectionPointsList.get(0) == intersectionPointsList.get(1)) {
            intersectionPointsList.remove(1);
        }
        return intersectionPointsList;
    }

    /**
     * This method return the width of the rectangle.
     *
     * @return The width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * This method return the height of the rectangle.
     *
     * @return The height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * This method return the upper-left point of the rectangle.
     *
     * @return The upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * This method set a new color to the rectangle.
     * @param color The new color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * This method return the color of the rectangle.
     * @return The color of the rectangle.
     */
    public Color getColor() {
        return this.color;
    }
    /**
     * This method change the upper-left point of the rectangle.
     *
     * @param dx The change of X value of the new upper left point.
     * @param dy The change of Y value of the new upper left point.
     */
    public void changeUpperLeft(double dx, double dy) {
        this.upperLeft = new Point(this.upperLeft.getX() + dx, this.upperLeft.getY() + dy);
    }

    /**
     * This method checks if a certain point is on the vertex of the rectangle.
     *
     * @param point The certain point.
     * @return True if the point is on the vertex of the rectangle. false otherwise.
     */
    public boolean isVertex(Point point) {
        return (point.getX() == upperLeft.getX() && point.getY() == upperLeft.getY())
                || (point.getX() == upperLeft.getX() + this.width && point.getY() == upperLeft.getY())
                || (point.getX() == upperLeft.getX() && point.getY() == upperLeft.getY() + this.height)
                || (point.getX() == upperLeft.getX() + this.width && point.getY() == upperLeft.getY() + this.height);
    }

    /**
     * This method checks if a certain point is horizontal to the rectangle.
     *
     * @param point The certain point.
     * @return True if the point is horizontal to the rectangle. false otherwise.
     */
    public boolean isHorizontal(Point point) {
        if (this.upperLeft.getX() == point.getX()) {
            return false;
        }
        if (this.upperLeft.getY() == point.getY()) {
            return true;
        }
        Point lowerRight = new Point(this.width + upperLeft.getX(), this.height + upperLeft.getY());
        return lowerRight.getX() != point.getX();
    }
}
