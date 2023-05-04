package Pinball;

public class Ball {
    private float ballRadius = 10;
    private float ballX;
    private float ballY;
    private float ballSpeedX = 3;
    private float ballSpeedY = 1;

    public float getBallRadius() {
        return ballRadius;

    }

    public float getBallX() {
        return ballX;
    }

    public void setBallX(float ballX) {
        this.ballX += ballX;
    }

    public float getBallY() {
        return ballY;
    }

    public void setBallY(float ballY) {
        this.ballY += ballY;
    }

    public float getBallSpeedX() {
        return ballSpeedX;
    }

    public float getBallSpeedY() {
        return ballSpeedY;
    }

    public void setBallSpeedX(float ballSpeedX) {
        this.ballSpeedX = ballSpeedX;
    }

    public void setBallSpeedY(float ballSpeedY) {
        this.ballSpeedY = ballSpeedY;
    }
}
