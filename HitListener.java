/**
 * an interface that is resembling tracking of hit events.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit a block that was hit.
     * @param hitter the ball that hit.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
