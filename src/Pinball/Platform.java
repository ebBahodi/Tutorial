package Pinball;

import java.awt.geom.RoundRectangle2D;

public class Platform {
    private double x;
    private double y;
    private RoundRectangle2D.Double platform;

    public Platform() {
    }

    public double getSize(){
        return 200;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public RoundRectangle2D.Double getPlatform() {
        return platform;
    }

    public void setPlatform(RoundRectangle2D.Double platform) {
        this.platform = platform;
    }

    public void moveXAxis(double x) {
        this.setX(x);
        this.platform.setRoundRect(x, this.platform.getY(), this.getSize(), 20, 50, 50);
    }
}
