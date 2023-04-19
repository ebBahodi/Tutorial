import javax.swing.*;
import javax.swing.AbstractAction;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

public class PinballGame extends JPanel {
    private ArrayList<Rectangle> bricks;
    private Platform platform;
    private Ball ball;
    private boolean run = false;
    private boolean running = false;
    private Timer timer;

    private final int width;
    private final int height;
    private final Menu menu;

    public PinballGame(int width, int height, Menu menu) {
        this.setSize(new Dimension(width, height));
        this.menu = menu;
        this.width = width;
        this.height = height;
        System.out.println(this.width + " --- " + this.height);
        this.createBricks();
        this.createPlatform(width, height);
        this.setVisible(false);
        this.createLeftAndRightArrowBindings();
        this.ball = new Ball();
        this.ball.setBallX((float) (width * 0.5) + this.ball.getBallRadius() + 5);
        this.ball.setBallY((float) (height * 0.7) + this.ball.getBallRadius() + 2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (g instanceof Graphics2D g2) {
            super.paintComponent(g2);
            if (!running) {
                this.collide();
                timer.start();
                this.running = true;
            }

            if (isRun()) {
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                RoundRectangle2D.Double platform;
                if (this.platform.getPlatform() != null)
                    platform = this.platform.getPlatform();
                else {
                    platform = new RoundRectangle2D.Double(this.platform.getX(), this.platform.getY(), this.platform.getSize(), 20, 50, 50);
                    this.platform.setPlatform(platform);
                }

                g2.fill(platform);
                for (Rectangle brick : bricks) {
                    g2.draw(brick);
                    g2.setBackground(Color.red);
                    g2.fill(brick);
                }

                g.fillOval((int) (ball.getBallX() - ball.getBallRadius()), (int) (ball.getBallY() - ball.getBallRadius()),
                        (int) (2 * ball.getBallRadius()), (int) (2 * ball.getBallRadius()));
            }
            else {
                this.setVisible(false);
                this.revalidate();
                this.menu.remove(this);
                this.repaint();
                MenuCanvas canvas = new MenuCanvas(this.menu);
                canvas.setBounds(0, height / 2, width, height);
                canvas.CreateButtons();
                this.menu.getContentPane().add(canvas);
                this.menu.getContentPane().repaint();
            }
            g2.dispose();
        }
    }

    public void createBricks() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight() * 0.5;
        double brickWidth = width / 20;
        double brickHeight = height / 20;
        bricks = new ArrayList<>();
        for (double x = 40; x < width; x += brickWidth + 2) {
            if (x + brickWidth < width) {
                for (double y = 20; y < height; y += brickHeight + 3) {
                    if (y + brickHeight < height) {
                        Rectangle brick = new Rectangle((int) x, (int) y, (int) brickWidth, (int) brickHeight);
                        bricks.add(brick);
                    }
                }
            }
        }
    }

    public void displayFrame() {
        this.setVisible(true);
    }

    public void createPlatform(int width, int height) {
        this.platform = new Platform();
        double platformWidth = this.platform.getSize();
        this.platform.setX((double) ((width / 2) - (platformWidth / 2)));
        this.platform.setY(height * 0.9);
        System.out.println(width + " --- " + height);
    }

    public void createLeftAndRightArrowBindings() {
        InputMap im = getInputMap();
        ActionMap am = getActionMap();

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
                    repaint();
                }
            }
        });

        am.put("RightArrow", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (platform.getX() + platform.getSize() + 15 < getWidth()) {
                    platform.moveXAxis((int) (platform.getX() + 15));
                    repaint();
                }
            }
        });
    }

    public void collide() {
        timer = new Timer(10, e -> {
            if (run) {
                ball.setBallX(ball.getBallSpeedX());
                ball.setBallY(ball.getBallSpeedY());

                if (ball.getBallX() - ball.getBallSpeedX() <= 0) {
                    ball.setBallSpeedX(-ball.getBallSpeedX());
                    ball.setBallX(ball.getBallRadius());
                } else if (ball.getBallX() + ball.getBallRadius() > getWidth()) {
                    ball.setBallSpeedX(-ball.getBallSpeedX());
                }
                if (ball.getBallY() - ball.getBallSpeedY() <= 0) {
                    ball.setBallSpeedY(-ball.getBallSpeedY());
                } else if (ball.getBallY() + ball.getBallRadius() > getHeight()) {
                    ball.setBallSpeedY(-ball.getBallSpeedY());
                }

                if ((platform.getX() <= ball.getBallX() && (platform.getX() + platform.getSize()) >= (ball.getBallX() + ball.getBallRadius())) && (platform.getY() <= (ball.getBallY() + ball.getBallRadius())) && (platform.getY() + 20) > (ball.getBallY() + ball.getBallRadius())) {
                    ball.setBallY(-ball.getBallSpeedY());
                    ball.setBallSpeedY(-ball.getBallSpeedY());
                }

                if ((ball.getBallY() + ball.getBallRadius()) >= getHeight()) {
                    Object[] options = {
                            "Restart", "Title screen"
                    };
                    int n = JOptionPane.showOptionDialog(this, "Game over, you lost. Would you like to restart the game and try again?", "Git gud", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                    if (n == 1) {
                        timer.stop();
                        run = false;
                    }
                    else {
                        this.reset();
                    }
                }

                int index = 1000;
                for (int i = 0; i < bricks.size(); i++) {
                    Rectangle brick = bricks.get(i);
                    //Ball hits a brick with the left side of the ball.
                    boolean left = (brick.getX() + brick.getWidth()) == ball.getBallX() && (brick.getY() <= (ball.getBallY() - ball.getBallRadius()) && brick.getY() + brick.getHeight() >= (ball.getBallY() + ball.getBallRadius()));
                    //Ball hits a brick with the top side of the ball.
                    boolean top = (brick.getX() <= ball.getBallX() && (brick.getX() + brick.getWidth()) > (ball.getBallX() + ball.getBallRadius())) && (brick.getY() + brick.getHeight()) >= (ball.getBallY() - ball.getBallRadius());
                    //Ball hits a brick with the right side of the ball.
                    boolean right = brick.getX() == (ball.getBallX() + ball.getBallRadius()) && (brick.getY() <= ball.getBallY() && brick.getY() + brick.getHeight() >= ball.getBallY());
                    //Ball hits a brick with the bottom side of the ball.
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
                repaint();
            }
        });
    }

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }

    public void reset() {
        ball = new Ball();
        this.ball.setBallX((float) (getWidth() * 0.5) + this.ball.getBallRadius() + 5);
        this.ball.setBallY((float) (getHeight() * 0.7) + this.ball.getBallRadius() + 2);
        this.platform = null;
        this.createBricks();
        this.createPlatform(this.width, this.height);
        timer.restart();
        repaint();
    }
}

