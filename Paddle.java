/**
 * ID: 313450249
 * Name: Ariel Mantel
 */
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;

/**
 * the paddle, used by the user, can move arround and hit the ball.
 */
public class Paddle implements Sprite, Collidable {
    private Rectangle rect;
    private java.awt.Color color;
    private GameEnvironment environment;
    private biuoop.KeyboardSensor keyboard;

    /**
     * constructor.
     * @param rect the shape of the paddle.
     * @param keyboard the keyboard that controls the paddle.
     * @param color the color of the paddle.
     * @param environment the environment of the paddle, containing all other collidables.
     */
    public Paddle(Rectangle rect, KeyboardSensor keyboard, Color color, GameEnvironment environment) {
        this.rect = rect;
        this.keyboard = keyboard;
        this.color = color;
        this.environment = environment;
    }

    /**
     * ordering the paddle left, if authorised.
     */
    public void moveLeft() {
        // getting all the the collidables in order to iterate through them.
        ArrayList<Collidable> collidables = environment.getCollidables();
        // ordering the paddle not to move if it can't.
        boolean dontMove = false;
        // iterating through all collidables, if one is in the paddles way, it shouldn't move.
        for (int i = 0; i < collidables.size(); i++) {
            if (rect.getLeft().start().isBetween(collidables.get(i).getCollisionRectangle().getRight())
            && collidables.get(i).getCollisionRectangle().getUpperLeft().getY() != rect.getUpperLeft().getY()) {
                dontMove = true;
            }
        }
        // if authorised, ordering the paddle left.
        if (!dontMove) {
            move(-1);
        }
    }

    /**
     * ordering the paddle left, if authorised.
     */
    public void moveRight() {
        // getting all the the collidables in order to iterate through them.
        ArrayList<Collidable> collidables = environment.getCollidables();
        // ordering the paddle not to move if it can't.
        boolean dontMove = false;
        // iterating through all collidables, if one is in the paddles way, it shouldn't move.
        for (int i = 0; i < collidables.size(); i++) {
            if (rect.getRight().start().isBetween(collidables.get(i).getCollisionRectangle().getLeft())
                    && collidables.get(i).getCollisionRectangle().getUpperLeft().getY() != rect.getUpperLeft().getY()) {
                dontMove = true;
            }
        }
        // if authorised, ordering the paddle right.
        if (!dontMove) {
            move(1);
        }
    }

    /**
     * moving the paddle to the direction chose by user.
     * @param direction the direction.
     */
    public void move(double direction) {
        //moving first all points of paddle.
        this.rect.setUpperLeft(new Point(rect.getUpperLeft().getX() + 5 * direction, rect.getUpperLeft().getY()));
        this.rect.setLowerLeft(new Point(rect.getLowerLeft().getX() + 5 * direction, rect.getLowerLeft().getY()));
        this.rect.setUpperRight(new Point(rect.getUpperRight().getX() + 5 * direction, rect.getUpperRight().getY()));
        this.rect.setLowerRight(new Point(rect.getLowerRight().getX() + 5 * direction, rect.getLowerRight().getY()));
        //moving first all lines of paddle.
        this.rect.setUp(new Line(rect.getUpperRight(), rect.getUpperLeft()));
        this.rect.setDown(new Line(rect.getLowerLeft(), rect.getLowerRight()));
        this.rect.setRight(new Line(rect.getUpperRight(), rect.getLowerRight()));
        this.rect.setLeft(new Line(rect.getLowerLeft(), rect.getUpperLeft()));
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
    }

    /**
     * notifies the paddle that time passed.
     */
    public void timePassed() {
        // because time passed, paddle checks again if user pressed left or right so it acts accordingly.
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }
    /**
     * getter for the shape of paddle.
     * @return the shape.
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * setter for paddles environment.
     * @param otherEnvironment the new environment.
     */
    public void setEnvironment(GameEnvironment otherEnvironment) {
        this.environment = otherEnvironment;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // array that contains all five parts of upper line of paddle.
        ArrayList<Line> parts = new ArrayList<Line>();
        // dividing the upper line of the paddle to five.
        for (int i = 0; i < 5; i++) {
            Point left = new Point(rect.getUpperLeft().getX() + i
                    * rect.getUpperLeft().distance(rect.getUpperRight()) / 5, rect.getUpperLeft().getY());
            Point right = new Point(rect.getUpperLeft().getX() + (i + 1)
                    * rect.getUpperLeft().distance(rect.getUpperRight()) / 5, rect.getUpperLeft().getY());
            parts.add(new Line(left, right));
        }
        // if the ball hits the first (most left) part of the paddle, it gets a velocity to the extreme left.
        // accordingly, at the rest of the parts it will move. except for the middle, where it will remain its
        // velocity besides going up.
        if (collisionPoint.isBetween(parts.get(0))) {
            Velocity velocity = Velocity.fromAngleAndSpeed(150, 5);
            return velocity;
        } else if (collisionPoint.isBetween(parts.get(1))) {
            Velocity velocity = Velocity.fromAngleAndSpeed(135, 5);
            return velocity;
        } else if (collisionPoint.isBetween(parts.get(2))) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        } else if (collisionPoint.isBetween(parts.get(3))) {
            Velocity velocity = Velocity.fromAngleAndSpeed(45, 5);
            return velocity;
        } else if (collisionPoint.isBetween(parts.get(4))) {
            Velocity velocity = Velocity.fromAngleAndSpeed(30, 5);
            return velocity;
        } else {
            // if the ball hits the side of the paddles.
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
    }

    /**
     * Add this paddle to the game.
     * @param g the game.
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}