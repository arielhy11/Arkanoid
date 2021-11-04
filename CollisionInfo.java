/**
 * ID: 313450249
 * Name: Ariel Mantel
 */
/**
 * class that contains the information of the collision.
 */
public class CollisionInfo {

    private Point point;
    private Collidable object;

    /**
     * constructor.
     * @param point the point of the collision.
     * @param object the object the ball collides with.
     */
    public CollisionInfo(Point point, Collidable object) {
        this.point = point;
        this.object = object;
    }

    /**
     * the point at which the collision occurs.
     * @return the point of the collision.
     */
    public Point collisionPoint() {
        return point;
    }

    /**
     * the collidable object involved in the collision.
     * @return the object the ball collides with.
     */
    public Collidable collisionObject() {
        return object;
    }
}
