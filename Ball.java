/**
 * ID: 313450249
 * Name: Ariel Mantel
 */
import biuoop.DrawSurface;

/**
 * a class, representing a ball.
 */
public class Ball implements Sprite {

    // the balls fields.
    private Point center;
    private int r;
    private Velocity v;
    private java.awt.Color color;
    private GameEnvironment environment;


    /**
     * constructor.
     * @param center the starting position of the ball.
     * @param r the radios of the ball.
     * @param color the color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.v = new Velocity(0, 0);
        this.environment = new GameEnvironment();
    }

    /**
     * constructor.
     * @param x the x of starting position of the ball.
     * @param y the y of starting position of the ball.
     * @param i the radios of the ball.
     * @param color the color of the ball.
     */
    public Ball(double x, double y, int i, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = i;
        this.color = color;
        this.v = new Velocity(0, 0);
        this.environment = new GameEnvironment();
    }

    /**
     * send the x value of ball location.
     * @return the x value of ball location.
     */
    public int getX() {
        return (int) center.getX();
    }
    /**
     * send the y value of ball location.
     * @return the y value of ball location.
     */
    public int getY() {
        return (int) center.getY();
    }
    /**
     * send the size value of ball.
     * @return the size value of ball.
     */
    public int getSize() {
        return r;
    }
    /**
     * send the color value of ball.
     * @return the y value of ball.
     */
    public java.awt.Color getColor() {
        return color;
    }
    /**
     * set the velocity value of ball.
     * @param velocity the velocity value of ball.
     */
    public void setVelocity(Velocity velocity) {
        this.v.setVelocity(velocity);
    }
    /**
     * set the velocity value of ball.
     * @param dx the x velocity value of ball.
     * @param dy the y velocity value of ball.
     */
    public void setVelocity(double dx, double dy) {
        this.v.setVelocity(dx, dy);
    }

    /**
     * send the velocity value of ball.
     * @return the velocity value of ball.
     */
    public Velocity getVelocity() {
        return this.v.getVelocity();
    }

    /**
     * getter for environment.
     * @return balls environment.
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * setter for environment.
     * @param otherEnvironment new environment.
     */
    public void setEnvironment(GameEnvironment otherEnvironment) {
        this.environment = otherEnvironment;
    }

    /**
     * moving the ball, while checking the borders to see if the ball is passing them.
     */
    public void moveOneStep() {

        // detects what direction the ball moves and switches it.
        double xDirection, yDirection;
        if (v.getDx() < 0) {
            xDirection = -r;
        } else {
            xDirection = r;
        }
        if (v.getDy() < 0) {
            yDirection = -r;
        } else {
            yDirection = r;
        }
        // if the ball is in the paddle because they move towards each other, the ball moves to the top of the paddle.
        Rectangle danger;
        // finding the paddle.
        for (Collidable c: environment.getCollidables()) {
            if (c.getCollisionRectangle().getUpperLeft().getY() == 550) {
                danger = c.getCollisionRectangle();
                // moving the ball up if its in the paddle.
                if (center.getY() > danger.getUpperLeft().getY()
                        && center.getX() > danger.getUpperLeft().getX()
                        && center.getX() < danger.getUpperRight().getX()) {
                    this.center = new Point(center.getX(), danger.getUpperLeft().getY() - r);
                }
            }
        }
        Line yTrajectory = new Line(center.getX(), center.getY() + yDirection,
                center.getX() + v.getDx(),  center.getY() + yDirection + v.getDy());
        Line xTrajectory = new Line(center.getX() + xDirection, center.getY(),
                center.getX() + v.getDx() + xDirection,  center.getY() + v.getDy());
        // the collision is on the y field.
        if (environment.getClosestCollision(yTrajectory) != null) {
            Collidable bangedCollidable = environment.getClosestCollision(yTrajectory).collisionObject();
            Point bangedPoint = environment.getClosestCollision(yTrajectory).collisionPoint();
            this.setVelocity(bangedCollidable.hit(this, bangedPoint, this.v));
            this.center = new Point(this.center.getX(),
                    bangedPoint.getY() - yDirection - v.getDy());
        }
        // the collision is on the x field.
        if (environment.getClosestCollision(xTrajectory) != null) {
            Collidable bangedCollidable = environment.getClosestCollision(xTrajectory).collisionObject();
            Point bangedPoint = environment.getClosestCollision(xTrajectory).collisionPoint();
            this.setVelocity(bangedCollidable.hit(this, bangedPoint, this.v));
            this.center = new Point(bangedPoint.getX() - xDirection - v.getDx(),
                    this.center.getY());
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillCircle((int) this.center.getX(), (int) this.center.getY(), r);
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * adding the ball to the game.
     * @param g the game.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * remove the ball from the game.
     * @param g the game.
     */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
    }
}
