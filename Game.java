// 206530552 Moshe Yehely Israel.
package gameEnv;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import geometry.Ball;
import geometry.Block;
import geometry.Point;
import sprite.Collidable;
import sprite.ScoreTrackingListener;
import sprite.Sprite;
import sprite.SpriteCollection;
import sprite.Paddle;

import java.awt.Color;
/**
 * This class represents the flow of the game.
 * @author Moshe Yehely Israel
 * @since 27-08-2024
 */
public class Game {
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final GUI gui;
    private final int width;
    private final int height;
    private final Counter countOfBlocks;
    private final Counter countOfBalls;
    private final Counter countOfscore;

    /**
     * constructor.
     */
    public Game() {
        this.width = 800;
        this.height = 600;
        this.gui = new GUI("Game!!", width, height);
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.countOfBlocks = new Counter();
        this.countOfBalls = new Counter();
        this.countOfscore = new Counter();
    }

    /**
     * This method adds a new Collidable to the game.
     * @param c The Collidable that add to the game.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * This method removes a Collidable from the game.
     * @param c The Collidable that need to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }
    /**
     * This method adds a new Sprite to the game.
     * @param s The Sprite that add to the game.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }
    /**
     * This method removes a Sprite from the game.
     * @param s The Sprite that need to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * This method returns the GUI of the game.
     * @return The GUI if the game.
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * This method returns the width of the game board.
     * @return The width of the game board.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * This method returns the height of the game board.
     * @return The height of the game board.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * This method return the environment of the game.
     * @return The environment of the game.
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * This method adds the lower edge of the game board.
     * This edge removes balls from the game board that hit with each other.
     * @param ballRemover listener to the lower edge. Its job is to detect when a ball hits the lower edge.
     */
    private void addDeathRegion(BallRemover ballRemover) {
        Block death = new Block(new Point(0, this.height), this.width, 20);
        death.addToGame(this);
        death.addHitListener(ballRemover);
    }

    /**
     * This method adds the edges of the game board from the top and sides.
     */
    private void addEdges() {
        Block up = new Block(new Point(0, 30), this.width, 20);
        Block left = new Block(new Point(0, 50), 20, this.height - 40);
        Block right = new Block(new Point(this.width - 20, 50), 20, this.height - 20);
        up.addToGame(this);
        left.addToGame(this);
        right.addToGame(this);
    }
    /**
     * This method initializes the game and creates: blocks, balls and a paddle.
     */
    public void initialize() {
        //These lines create listeners for removing blocks and balls, as well as a listener for counting the score.
        BlockRemover blockRemover = new BlockRemover(this, this.countOfBlocks);
        BallRemover ballRemover = new BallRemover(this, this.countOfBalls);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.countOfscore);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.countOfscore);
        scoreIndicator.addToGame(this);
        addEdges(); //Create an edges for the game board.
        addDeathRegion(ballRemover); //Create a lower edge.
        //Create a paddle.
        Paddle paddle = new Paddle(this);
        paddle.addToGame(this);
        //This loop generates balls and updates the amount of balls in the game:
        for (int i = 1; i <= 3; i++) {
            Ball ball = new Ball(400, 300, 10, Color.BLACK);
            ball.setVelocity(i, 4 - i);
            ball.addToGame(this);
            countOfBalls.increase(1);
        }
        //This loop generates blocks and increases the amount of blocks in the game:
        for (int i = 0; i < 6; i++) {
            for (int j = 1; j <= 12 - i; j++) {
                Block block = new Block(new Point(width - 20 - (60 * j), 60 + i * 20), 60, 20);
                block.getCollisionRectangle().setColor(new Color(i * 40, 255 / (i + 1), i * 10));
                block.addToGame(this);
                countOfBlocks.increase(1);
                block.addHitListener(blockRemover);
                block.addHitListener(scoreTrackingListener);
            }
        }
    }

    /**
     * This method actually runs the actual game.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        //The loop ran as long as the game was not yet finished:
        while (countOfBalls.getValue() > 0 && countOfBlocks.getValue() > 0) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        //When the game ends and the blocks on the board run out - 100 points are added and
        // a corresponding message is printed:
        if (countOfBlocks.getValue() == 0) {
            countOfscore.increase(100);
        }
        System.out.println("Game Over, your score is: " + countOfscore.getValue());
        this.gui.close();

    }
}
