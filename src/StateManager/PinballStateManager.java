package StateManager;

import Constants.PinballGameConstants;
import Pinball.Ball;
import Pinball.Brick;
import Pinball.PinballGame;
import Pinball.Platform;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;


public class PinballStateManager {
    PinballGame pinballGame;

    private ArrayList<Brick> bricks;
    private Platform platform;
    private Ball ball;
    private boolean paused = true;
    private boolean running = false;
    private Timer timer;


    public PinballStateManager(PinballGame pinballGame, int width, int height) {
        this.pinballGame = pinballGame;
        this.ball = new Ball();
        this.ball.setBallX((float) (width * 0.5) + this.ball.getBallRadius() + 5);
        this.ball.setBallY((float) (height * 0.7) + this.ball.getBallRadius() + 2);
        this.createBricks();
        this.createPlatform(width, height);
        this.createLeftAndRightArrowBindings();
        setPaused(false);
    }

    public void draw(Graphics2D g) {
        if (!running) {
            this.collide();
            timer.start();
            this.running = true;
        }

        if (!isPaused()) {
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            RoundRectangle2D.Double platform;
            if (this.platform.getPlatform() != null)
                platform = this.platform.getPlatform();
            else {
                platform = new RoundRectangle2D.Double(this.platform.getX(), this.platform.getY(), this.platform.getSize(), 20, 50, 50);
                this.platform.setPlatform(platform);
            }

            g.fill(platform);
            for (Brick brick : bricks) {
                g.draw(brick);
                g.setBackground(Color.red);
                g.fill(brick);
            }

            g.fillOval((int) (ball.getBallX() - ball.getBallRadius()), (int) (ball.getBallY() - ball.getBallRadius()),
                    (int) (2 * ball.getBallRadius()), (int) (2 * ball.getBallRadius()));
        }
        g.dispose();
    }

    public void createBricks() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight() * 0.5;
        double brickWidth = PinballGameConstants.BRICK_WIDTH;
        double brickHeight = PinballGameConstants.BRICK_HEIGHT;
        bricks = new ArrayList<>();
        for (int x = 40; x < width; x += brickWidth + 2) {
            if (x + brickWidth < width) {
                for (int y = 20; y < height; y += brickHeight + 3) {
                    if (y + brickHeight < height) {
                        Brick brick = new Brick(x, y);
                        bricks.add(brick);
                    }
                }
            }
        }
    }

    public void createPlatform(int width, int height) {
        this.platform = new Platform();
        double platformWidth = this.platform.getSize();
        this.platform.setX((float) ((width / 2) - (platformWidth / 2)));
        this.platform.setY(height * 0.9);
        System.out.println(width + " --- " + height);
    }

    public void createLeftAndRightArrowBindings() {
        InputMap im = pinballGame.getInputMap();
        ActionMap am = pinballGame.getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "LeftArrow");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "RightArrow");
        createBindings(am);
    }

    public void createBindings(ActionMap am) {
        am.put("LeftArrow", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (platform.getX() - 15 > 0) {
                    double x = platform.getX() - 15;
                    platform.moveXAxis(x);
                    pinballGame.repaint();
                }
            }
        });

        am.put("RightArrow", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (platform.getX() + platform.getSize() + 15 < pinballGame.getWidth()) {
                    platform.moveXAxis((int) (platform.getX() + 15));
                    pinballGame.repaint();
                }
            }
        });
    }

    public void collide() {
        timer = new Timer(10, e -> {
            if (!paused) {
                ball.setBallX(ball.getBallSpeedX());
                ball.setBallY(ball.getBallSpeedY());

                if (ball.getBallX() - ball.getBallSpeedX() <= 0) {
                    ball.setBallSpeedX(-ball.getBallSpeedX());
                    ball.setBallX(ball.getBallRadius());
                } else if (ball.getBallX() + ball.getBallRadius() > pinballGame.getWidth()) {
                    ball.setBallSpeedX(-ball.getBallSpeedX());
                }
                if (ball.getBallY() - ball.getBallSpeedY() <= 0) {
                    ball.setBallSpeedY(-ball.getBallSpeedY());
                } else if (ball.getBallY() + ball.getBallRadius() > pinballGame.getHeight()) {
                    ball.setBallSpeedY(-ball.getBallSpeedY());
                }

                if ((platform.getX() <= ball.getBallX() && (platform.getX() + platform.getSize()) >= (ball.getBallX() + ball.getBallRadius())) && (platform.getY() <= (ball.getBallY() + ball.getBallRadius())) && (platform.getY() + 20) > (ball.getBallY() + ball.getBallRadius())) {
                    ball.setBallY(-ball.getBallSpeedY());
                    ball.setBallSpeedY(-ball.getBallSpeedY());
                }

                if ((ball.getBallY() + ball.getBallRadius()) >= pinballGame.getHeight()) {
                    Object[] options = {
                            "Restart", "Title screen"
                    };
                    int n = JOptionPane.showOptionDialog(pinballGame, "Game over, you lost. Would you like to restart the game and try again?", "Git gud", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                    if (n == 1) {
                        timer.stop();
                        paused = true;
                    }
                    else {
                        this.reset();
                    }
                }

                int index = 1000;
                for (int i = 0; i < bricks.size(); i++) {
                    Rectangle brick = bricks.get(i);
                    //Pinball.Ball hits a brick with the left side of the ball.
                    boolean left = (brick.getX() + brick.getWidth()) == ball.getBallX() && (brick.getY() <= (ball.getBallY() - ball.getBallRadius()) && brick.getY() + brick.getHeight() >= (ball.getBallY() + ball.getBallRadius()));
                    //Pinball.Ball hits a brick with the top side of the ball.
                    boolean top = (brick.getX() <= ball.getBallX() && (brick.getX() + brick.getWidth()) > (ball.getBallX() + ball.getBallRadius())) && (brick.getY() + brick.getHeight()) >= (ball.getBallY() - ball.getBallRadius());
                    //Pinball.Ball hits a brick with the right side of the ball.
                    boolean right = brick.getX() == (ball.getBallX() + ball.getBallRadius()) && (brick.getY() <= ball.getBallY() && brick.getY() + brick.getHeight() >= ball.getBallY());
                    //Pinball.Ball hits a brick with the bottom side of the ball.
                    boolean bottom = (brick.getX() <= ball.getBallX() && (brick.getX() + brick.getWidth()) > ball.getBallX()) && brick.getY() == (ball.getBallY() + ball.getBallRadius());
                    if (left) {
                        index = i;
                        ball.setBallSpeedX(-ball.getBallSpeedX());
                        ball.setBallX(ball.getBallSpeedX());
                        break;
                    } else if (top) {
                        index = i;
                        ball.setBallSpeedY(-ball.getBallSpeedY());
                        ball.setBallY(ball.getBallSpeedY());
                        break;
                    } else if (right) {
                        index = i;
                        ball.setBallSpeedX(-ball.getBallSpeedX());
                        ball.setBallX(ball.getBallSpeedX());
                        break;
                    } else if (bottom) {
                        index = i;
                        ball.setBallSpeedY(-ball.getBallSpeedY());
                        ball.setBallY(-ball.getBallSpeedY());
                        break;
                    }
                }

                if (index != 1000) {
                    bricks.remove(index);
                }
                pinballGame.repaint();
            }
        });
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public void reset() {
        ball = new Ball();
        this.ball.setBallX((float) (pinballGame.getWidth() * 0.5) + this.ball.getBallRadius() + 5);
        this.ball.setBallY((float) (pinballGame.getHeight() * 0.7) + this.ball.getBallRadius() + 2);
        this.platform = null;
        this.createBricks();
        this.createPlatform(pinballGame.getWidth(), pinballGame.getHeight());
        timer.restart();
        pinballGame.repaint();
    }
}
