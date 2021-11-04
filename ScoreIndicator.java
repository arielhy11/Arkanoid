import biuoop.DrawSurface;

import java.awt.Color;

/**
 * a board that shows the score.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private Rectangle rect;

    /**
     * constructor.
     * @param rect the shape of the board.
     * @param score the score that is written on the board.
     */
    public ScoreIndicator(Rectangle rect, Counter score) {
        this.rect = rect;
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
        d.setColor(Color.DARK_GRAY);
        d.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
        d.setColor(Color.WHITE);
        d.drawText((int) rect.getDown().middle().getX(), (int) rect.getLowerLeft().getY(),
                "Score: " + this.score.getValue(), 18);
    }

    @Override
    public void timePassed() {

    }

    /**
     * add the indicator to the game.
     * @param g the game.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}
