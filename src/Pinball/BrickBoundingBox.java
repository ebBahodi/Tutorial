package Pinball;

import Constants.PinballGameConstants;

public class BrickBoundingBox {
    private int xTopLeft;
    private int yTopLeft;
    private int width;
    private int height;

    public BrickBoundingBox(int xTopLeft, int yTopLeft) {
        this.width = PinballGameConstants.BRICK_WIDTH;
        this.height = PinballGameConstants.BRICK_HEIGHT;
        this.xTopLeft = xTopLeft;
        this.yTopLeft = yTopLeft;
    }

    public int getXTopLeft() {
        return xTopLeft;
    }

    public void setXTopLeft(int xTopLeft) {
        this.xTopLeft = xTopLeft;
    }

    public int getYTopLeft() {
        return yTopLeft;
    }

    public void setYTopLeft(int yTopLeft) {
        this.yTopLeft = yTopLeft;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
