/**
 * ID: 313450249
 * Name: Ariel Mantel
 */
/**
 * the collidables are obstacles the ball cannot pass.
 */
public interface Collidable {

    /**
     * Return the "collision shape" of the object.
     * @return getter for the shape of the collidable.
     */
    Rectangle getCollisionRectangle();
    /**
     * Notify the object that we collided with it at collisionPoint with
     * given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     * @param hitter the hitting ball.
     * @param collisionPoint the point where the collision occurs.
     * @param currentVelocity the current velocity of the ball.
     * @return new velocity of ball, according to the previous data.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
