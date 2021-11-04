/**
 * ID: 313450249
 * Name: Ariel Mantel
 */
import java.util.ArrayList;

/**
 * contains all collidables.
 */
public class GameEnvironment {
    private ArrayList<Collidable> collidables;

    /**
     * constructor.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * add the given collidable to the environment.
     * @param c the collidable.
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * remove the given collidable from the environment.
     * @param c the collidable.
     */
    public void delCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * getter for the list.
     * @return list of collidables.
     */
    public ArrayList<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     * @param trajectory line that is the balls path.
     * @return the CollisionInfo, or null if no collision.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closestPoint = null;
        Point tmpPoint;
        Collidable collid = null;
        double shortestDistance = 99999999;
        for (int i = 0; i < collidables.size(); i++) {
            if (trajectory.closestIntersectionToStartOfLine(collidables.get(i).getCollisionRectangle()) != null) {
                tmpPoint = trajectory.closestIntersectionToStartOfLine(collidables.get(i).getCollisionRectangle());
                if (tmpPoint.distance(trajectory.start()) < shortestDistance) {
                    closestPoint = tmpPoint;
                    shortestDistance = tmpPoint.distance(trajectory.start());
                    collid = collidables.get(i);
                }
            }
        }
        if (closestPoint == null) {
            return null;
        } else {
            return new CollisionInfo(closestPoint, collid);
        }
    }
}
