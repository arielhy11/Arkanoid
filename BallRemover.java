/**
 * a listener that is in-charge of tracking the collision of the ball with the death block
 * and of removing the ball from the game.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * constructor.
     * @param game the game.
     * @param removedBalls a counter.
     */
    public BallRemover(Game game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    /**
     * Balls that hit the death block should be removed
     * from the game. remove this listener from the block
     * that is being removed from the game.
     * @param beingHit the deathblock.
     * @param hitter the ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        this.remainingBalls.decrease(1);
    }
}
