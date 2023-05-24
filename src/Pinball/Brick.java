package Pinball;

import java.awt.*;
public class Brick extends Rectangle {

    private int index;
    private final BrickBoundingBox boundingBox;

    public Brick(int xTopLeft, int yTopLeft) {
        super();
        this.boundingBox = new BrickBoundingBox(xTopLeft, yTopLeft);
    }

    public void setBounds(BrickBoundingBox boundingBox) {
        Rectangle rectangle = new Rectangle(
                boundingBox.getXTopLeft(),
                boundingBox.getYTopLeft(),
                boundingBox.getWidth(),
                boundingBox.getHeight()
        );
        super.setBounds(rectangle);
    }

    public BrickBoundingBox getBoundingBox(){
        return this.boundingBox;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
