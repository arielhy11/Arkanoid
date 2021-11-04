/**
 * ID: 313450249
 * Name: Ariel Mantel
 */
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.ArrayList;

/**
 * running the game.
 */
public class Game implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter scoreCounter;
    private AnimationRunner runner;
    private boolean running;
    private GUI gui;
    private biuoop.KeyboardSensor keyboard;

    /**
     * constructor.
     */
    public Game(GUI gui, biuoop.KeyboardSensor keyboard, AnimationRunner runner) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = gui;
        this.keyboard = keyboard;
        this.runner = runner;
    }

    /**
     * adds a collidable to the games private environment.
     * @param c the collidable.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * adds a sprite to the games private sprites.
     * @param s the sprite.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        // counter and remover of blocks.
        this.blockCounter = new Counter();
        BlockRemover blockRemover = new BlockRemover(this, blockCounter);
        // counter of balls.
        this.ballCounter = new Counter();
        //counter of score.
        this.scoreCounter = new Counter();
        ScoreTrackingListener scoreListner = new ScoreTrackingListener(scoreCounter);
        // creating the fist ball
        Ball aBall = new Ball(50, 100, 5, Color.BLACK);
        aBall.setVelocity(new Velocity(4, 4));
        aBall.addToGame(this);
        this.ballCounter.increase(1);

        // creating the second ball.
        Ball bBall = new Ball(50, 50, 5, Color.CYAN);
        bBall.setVelocity(new Velocity(4, 4));
        bBall.addToGame(this);
        this.ballCounter.increase(1);

        // creating the third ball.
        Ball cBall = new Ball(100, 50, 5, Color.YELLOW);
        cBall.setVelocity(new Velocity(4, 4));
        cBall.addToGame(this);
        this.ballCounter.increase(1);

        // creating array containing colors, to be used by the rows of the blocks.
        ArrayList<Color> colors = new ArrayList();
        colors.add(Color.BLUE);
        colors.add(Color.GREEN);
        colors.add(Color.PINK);

        // creating row of blocks.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5 - i; j++) {
                Block block = new Block(new Rectangle(new Point(680 - j * 100, 200 + 30 * i),
                        100, 30), colors.get(i));
                block.addToGame(this);
                block.addHitListener(blockRemover);
                block.addHitListener(scoreListner);
                blockCounter.increase(1);
            }
        }
        //creating the blocks that surround the board.
        Block leftBorder = new Block(new Rectangle(new Point(0, 20),
                20, 600), Color.GRAY);
        Block upBorder = new Block(new Rectangle(new Point(0, 20),
                800, 20), Color.GRAY);
        Block rightBorder = new Block(new Rectangle(new Point(780, 20),
                20, 600), Color.GRAY);
        leftBorder.addToGame(this);
        upBorder.addToGame(this);
        rightBorder.addToGame(this);

        //death block.
        Block deathBlock = new Block(new Rectangle(new Point(0, 600),
                800, 20), Color.GRAY);
        deathBlock.addToGame(this);
        //adding ballremover to the deathblock.
        BallRemover ballRemover = new BallRemover(this, ballCounter);
        deathBlock.addHitListener(ballRemover);

        // setting the environments of balls so they will know all collidables.
        aBall.setEnvironment(environment);
        bBall.setEnvironment(environment);
        cBall.setEnvironment(environment);

        // creating the score indicator.
        ScoreIndicator scoreIndicator = new ScoreIndicator((new Rectangle(
                new Point(0, 0), 800, 20)), scoreCounter);
        scoreIndicator.addToGame(this);

        // creating the paddle.
        Paddle paddle = new Paddle(new Rectangle(new Point(380, 550), 150, 30),
                this.keyboard, Color.magenta, environment);
        paddle.addToGame(this);
        paddle.setEnvironment(environment);
    }

    /**
     * removes a collidable from the game.
     * @param c the collidable to be removed.
     */
    public void removeCollidable(Collidable c) {
        this.environment.delCollidable(c);
    }

    /**
     * removes a sprite from the game.
     * @param s the sprite to be removed.
     */
    public void removeSprite(Sprite s) {
        this.sprites.delSprite(s);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        this.initialize();
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);


    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn;
        // the logic from the previous run method goes here.
        // the `return` or `break` statements should be replaced with
        // this.running = false;


        if (this.blockCounter.getValue() == 0) {
            scoreCounter.increase(100);
            this.running = false;
        }

        // if no balls left the player lost, close the game.
        if (this.ballCounter.getValue() == 0) {
            this.running = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
