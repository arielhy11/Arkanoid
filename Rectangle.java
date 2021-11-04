/**
 * ID: 313450249
 * Name: Ariel Mantel
 */
import java.util.ArrayList;

/**
 * a rectangle.
 */
public class Rectangle {
    private Point upperLeft;
    private double width, height;
    private Line up, down, right, left;
    private Point upperRight, lowerLeft, lowerRight;

    /**
     * Create a new rectangle with location and width/height.
     * @param upperLeft the upper left point.
     * @param width the width.
     * @param height the height.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.height = height;
        this.width = width;
        // All points.
        this.upperLeft = upperLeft;
        this.upperRight = new Point(this.upperLeft.getX() + width, this.upperLeft.getY());
        this.lowerLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + height);
        this.lowerRight = new Point(this.upperRight.getX(), this.lowerLeft.getY());
        // all lines
        this.up = new Line(this.upperLeft, this.upperRight);
        this.down = new Line(this.lowerLeft, this.lowerRight);
        this.left = new Line(this.upperLeft, this.lowerLeft);
        this.right = new Line(this.upperRight, this.lowerRight);
    }

    /**
     * Return a (possibly empty) List of intersection points
     * with the specified line.
     * @param line the line that maybe intersects with the rectangle.
     * @return a list of intersection points if any, null otherwise.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        ArrayList<Point> intersectionPoints = new ArrayList<Point>();
        if (line.isIntersecting(this.up)) {
            intersectionPoints.add(line.intersectionWith(this.up));
        }
        if (line.isIntersecting(this.down)) {
            intersectionPoints.add(line.intersectionWith(this.down));
        }
        if (line.isIntersecting(this.right)) {
            intersectionPoints.add(line.intersectionWith(this.right));
        }
        if (line.isIntersecting(this.left)) {
            intersectionPoints.add(line.intersectionWith(this.left));
        }
        // if did not find any, return null.
        if (intersectionPoints.size() == 0) {
            return null;
        } else {
            return intersectionPoints;
        }
    }
    /**
     * getter for width.
     * @return width.
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * getter for height.
     * @return height.
     */
    public double getHeight() {
        return this.height;
    }
    /**
     *  Returns the upper-left point of the rectangle.
     * @return upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /**
     *  Returns the lower-left point of the rectangle.
     * @return lower-left point of the rectangle.
     */
    public Point getLowerLeft() {
        return this.lowerLeft;
    }
    /**
     *  Returns the upper-right point of the rectangle.
     * @return upper-right point of the rectangle.
     */
    public Point getUpperRight() {
        return this.upperRight;
    }
    /**
     *  Returns the lower-right point of the rectangle.
     * @return lower-right point of the rectangle.
     */
    public Point getLowerRight() {
        return this.lowerRight;
    }
    /**
     *  Returns the up line of the rectangle.
     * @return up line of the rectangle.
     */
    public Line getUp() {
        return up;
    }
    /**
     *  Returns the down line of the rectangle.
     * @return down line of the rectangle.
     */
    public Line getDown() {
        return down;
    }
    /**
     *  Returns the right line of the rectangle.
     * @return right line of the rectangle.
     */
    public Line getRight() {
        return right;
    }
    /**
     *  Returns the left line of the rectangle.
     * @return left line of the rectangle.
     */
    public Line getLeft() {
        return left;
    }
    /**
     * setter of upper left.
     * @param point new upper left.
     */
    public void setUpperLeft(Point point) {
        upperLeft = point;
    }
    /**
     * setter of lower left.
     * @param point new loewr left.
     */
    public void setLowerLeft(Point point) {
        this.lowerLeft = point;
    }
    /**
     * setter of upper right.
     * @param point new upper right.
     */
    public void setUpperRight(Point point) {
        this.upperRight = point;
    }
    /**
     * setter of lower right.
     * @param point new lower right.
     */
    public void setLowerRight(Point point) {
        this.lowerRight = point;
    }

    /**
     * setter for up.
     * @param line new up.
     */
    public void setUp(Line line) {
        this.up = line;
    }
    /**
     * setter for down.
     * @param line new down.
     */
    public void setDown(Line line) {
        this.down = line;
    }
    /**
     * setter for left.
     * @param line new left.
     */
    public void setLeft(Line line) {
        this.left = line;
    }
    /**
     * setter for right.
     * @param line new right.
     */
    public void setRight(Line line) {
        this.right = line;
    }
}
