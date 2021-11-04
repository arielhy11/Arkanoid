/**
 * ID: 313450249
 * Name: Ariel Mantel
 */
/**
 * a class, representing a line.
 */
public class Line {
    private Point start, end;

    /**
     * constructor out of two points.
     * @param start starting points.
     * @param end end points.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor out of four numbers.
     * @param x1 x value of first point.
     * @param y1 y value of first point.
     * @param x2 x value of second point.
     * @param y2 y value of second point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }
    /**
     * Return the length of the line.
     * @return distance between two points.
     */
    public double length() {
        return start.distance(end);
    }
    /**
     * Returns the middle point of the line.
     * @return middle point of the line.
     */
    public Point middle() {
        return new Point((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2);
    }
    /**
     * Returns the start point of the line.
     * @return start point of the line.
     */
    public Point start() {
        return start;
    }
    /**
     * Returns the end point of the line.
     * @return end point of the line.
     */
    public Point end() {
        return end;
    }
    /**
     * Returns true if the lines intersect, false otherwise.
     * @param other another line to check.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        //if the lines are the same and on a point.
        if (this.equals(other) && this.start.equals(other.end)) {
            return true;
        }
        // if both lines are points.
        if (this.start.equals(this.end) && other.start().equals(other.end())) {
            return false; // since the previous check was not satisfied.
        }
        // if one of the lines is a point.
        if (this.start.equals(this.end)) {
            return this.start.isBetween(other);
        }
        if (other.start().equals(other.end())) {
            return other.start().isBetween(this);
        }
        // if the lines are the same.
        if (this.equals(other)) {
                    return false;
        }
        // if the inclines of both of the lines are not-defined.
        if (this.start.getX() == this.end.getX() && other.start.getX() == other.end.getX()) {
            /*
            return true only if the lines intersect in one point only and the lines go other
            directions.
             */
            return (start.equals(other.start()) && !end.isBetween(other) && !other.end().isBetween(this))
                    || (end.equals(other.end()) && !start.isBetween(other) && !other.start().isBetween(this))
                    || (start.equals(other.end()) && !end.isBetween(other) && !other.start().isBetween(this))
                    || (end.equals(other.start()) && !start.isBetween(other) && !other.end().isBetween(this));
        }
        // if the incline of one of the lines is not defined.
        if (this.start.getX() == this.end.getX()) {
            double x = this.start.getX();
            double otherIncline = (other.end.getY() - other.start.getY()) / (other.end.getX() - other.start.getX());
            double otherYinter = other.start.getY() - (other.start.getX() * otherIncline);
            double possibleY = (otherIncline * x + otherYinter);
            Point possiblePoint = new Point(x, possibleY);
            return (possiblePoint.isBetween(this) && possiblePoint.isBetween(other));
        }
        if (other.start.getX() == other.end.getX()) {
            double x = other.start.getX();
            double thisIncline = (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
            double thisYinter = this.start.getY() - (this.start.getX() * thisIncline);
            double possibleY = (thisIncline * x + thisYinter);
            Point possiblePoint = new Point(x, possibleY);
            return (possiblePoint.isBetween(this) && possiblePoint.isBetween(other));
        }
        double thisIncline = (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
        double otherIncline = (other.end.getY() - other.start.getY()) / (other.end.getX() - other.start.getX());
        double thisYinter = this.start.getY() - (this.start.getX() * thisIncline);
        double otherYinter = other.start.getY() - (other.start.getX() * otherIncline);
        double possibleX = (otherYinter - thisYinter) / (otherIncline - thisIncline) * -1;
        double possibleY = (thisIncline * possibleX + thisYinter);
        Point possiblePoint = new Point(possibleX, possibleY);
        return (possiblePoint.isBetween(this) && possiblePoint.isBetween(other));
    }
    /**
     * Returns the intersection point if the lines intersect, and null otherwise.
     * @param other the other line to check.
     * @return the intersecting point if exits, if not returns null.
     */
    public Point intersectionWith(Line other) {
        if (this.isIntersecting(other)) {
            // if the lines are the same and on a point, return the point.
            if (this.equals(other) && this.start.equals(other.end)) {
                return this.start;
            }
            // if one line is a point.
            if (this.start.equals(this.end)) {
                return this.start;
            }
            if (other.start().equals(other.end())) {
                return other.start();
            }
            //if a point is the same.
            if (this.start.equals(other.start())) {
                return this.start;
            }
            if (this.start.equals(other.end())) {
                return this.start;
            }
            if (this.end.equals(other.start())) {
                return this.end;
            }
            if (this.end.equals(other.end())) {
                return this.end;
            }
            // if the incline of one of the lines is not defined.
            if (this.start.getX() == this.end.getX()) {
                double x = this.start.getX();
                double otherIncline = (other.end.getY() - other.start.getY()) / (other.end.getX() - other.start.getX());
                double otherYinter = other.start.getY() - (other.start.getX() * otherIncline);
                double possibleY = (otherIncline * x + otherYinter);
                Point possiblePoint = new Point(x, possibleY);
                return (possiblePoint);
            }
            if (other.start.getX() == other.end.getX()) {
                double x = other.start.getX();
                double thisIncline = (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
                double thisYinter = this.start.getY() - (this.start.getX() * thisIncline);
                double possibleY = (thisIncline * x + thisYinter);
                Point possiblePoint = new Point(x, possibleY);
                return (possiblePoint);
            }
            double thisIncline = (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
            double otherIncline = (other.end.getY() - other.start.getY()) / (other.end.getX() - other.start.getX());
            double thisYinter = this.start.getY() - (this.start.getX() * thisIncline);
            double otherYinter = other.start.getY() - (other.start.getX() * otherIncline);
            double possibleX = (otherYinter - thisYinter) / (otherIncline - thisIncline) * -1;
            double possibleY = thisIncline * possibleX + thisYinter;
            Point possiblePoint = new Point(possibleX, possibleY);
            return new Point(possibleX, possibleY);
        } else {
            return null;
        }
    }
    /**
     * equals -- return true is the lines are equal, false otherwise.
     * @param other another line to compare to.
     * @return true if equals, false if not.
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end)
                || this.end.equals(other.start) && this.start.equals(other.end));
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     * @param rect the intersecting shape.
     * @return the closest intersection.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Point closestPoint = null;
        Point tmpPoint;
        double shortestDistance = 99999;
        if (this.isIntersecting(rect.getUp())) {
            closestPoint = this.intersectionWith(rect.getUp());
            shortestDistance = this.start.distance(closestPoint);
        }
        if (this.isIntersecting(rect.getDown())) {
            tmpPoint = this.intersectionWith(rect.getDown());
            if (tmpPoint.distance(this.start) < shortestDistance) {
                closestPoint = tmpPoint;
                shortestDistance = closestPoint.distance(this.start);
            }
        }
        if (this.isIntersecting(rect.getLeft())) {
            tmpPoint = this.intersectionWith(rect.getLeft());
            if (tmpPoint.distance(this.start) < shortestDistance) {
                closestPoint = tmpPoint;
                shortestDistance = closestPoint.distance(this.start);
            }
        }
        if (this.isIntersecting(rect.getRight())) {
            tmpPoint = this.intersectionWith(rect.getRight());
            if (tmpPoint.distance(this.start) < shortestDistance) {
                closestPoint = tmpPoint;
                shortestDistance = closestPoint.distance(this.start);
            }
        }
        if (closestPoint != null) {
            return closestPoint;
        } else {
            return null;
        }
    }
}
