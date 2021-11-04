/**
 * ID: 313450249
 * Name: Ariel Mantel
 */
/**
 * a class representing a ball.
 */
public class Point {
    private double x;
    private double y;
    /**
     * constructor.
     * @param x the x value of the point.
     * @param y the y value of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * checking if a point is on a line.
     * @param line the line to check if the ball is on.
     * @return true if the point is on the line, false if not.
     */
    public boolean isBetween(Line line) {
        if (line.start().getX() == line.end().getX()) { // in case one incline is not defined.
            return (line.start().getX() == this.getX()
                    && (line.start().getY() <= this.getY() && line.end().getY() >= this.getY())
                    || (line.start().getY() >= this.getY() && line.end().getY() <= this.getY()));
        }
        double incline = (line.end().getY() - line.start().getY()) / (line.end().getX() - line.start().getX());
        double yInter = line.start().getY() - (line.start().getX() * incline);
        double epsi = 0.000001; // if two numbers are in this range, they will be considered equal.
        return (this.y + epsi > incline * this.x + yInter && this.y - epsi < incline * this.x + yInter
                && ((this.getX() <= line.start().getX() && this.getX() >= line.end().getX())
                || (this.getX() >= line.start().getX() && this.getX() <= line.end().getX())));
    }
    /**
     * distance -- return the distance of this point to the other point.
     * @param other other point to check how far it is.
     * @return the distance.
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow((this.x - other.getX()), 2) + Math.pow((this.y - other.getY()), 2));
    }
    /**
     * equals -- return true is the points are equal, false otherwise.
     * @param other other point to compare to.
     * @return true if equals, false if not.
     */
    public boolean equals(Point other) {
        return (this.x == other.getX() && this.y == other.getY());
    }
    /**
     * Return the x value of the point.
     * @return x value of the point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Return the y value of the point.
     * @return y value of the point.
     */
    public double getY() {
        return this.y;
    }
}
