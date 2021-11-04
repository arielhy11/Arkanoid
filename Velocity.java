/**
 * ID: 313450249
 * Name: Ariel Mantel
 */
/**
 * the velocity of different objects and responsible of moving them.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * constructor with two arguments.
     * @param dx x value.
     * @param dy y value.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     * @param p the location of the object.
     * @return the new location of the object.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * sets the velocity.
     * @param v the velocity to be set to.
     */
    public void setVelocity(Velocity v) {
        this.dx = v.dx;
        this.dy = v.dy;
    }

    /**
     * sets the velocity.
     * @param x the x value of the new velocity.
     * @param y the y value of the new velocity.
     */
    public void setVelocity(double x, double y) {
        this.dx = x;
        this.dy = y;
    }

    /**
     * sends the current velocity.
     * @return the velocity.
     */
    public Velocity getVelocity() {
        return new Velocity(dx, dy);
    }

    /**
     * return the x value of the velocity.
     * @return x value of the velocity.
     */
    public double getDx() {
        return dx;
    }

    /**
     * return the y value of the velocity.
     * @return y value of the velocity.
     */
    public double getDy() {
        return dy;
    }

    /**
     * sets the velocity according to angle and speed.
     * @param angle the angle.
     * @param speed the speed.
     * @return the new velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos(Math.toRadians(angle));
        double dy = -speed * Math.sin(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }
}
