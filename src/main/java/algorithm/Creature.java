package algorithm;

public class Creature {
    private double eatenFood;
    private int x, y, prevX, prevY;

    public Creature(int x, int y) {
        this.x = x;
        this.y = y;
        this.eatenFood = 0;
    }

    public void moveVertical(boolean up) {
        prevX = x;
        prevY = y;
        if(up) {
            x -= 1;
        } else {
            x += 1;
        }
    }

    public void moveHorizontal(boolean left) {
        prevX = x;
        prevY = y;
        if(left) {
            y -= 1;
        } else {
            x += 1;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPrevX() {
        return prevX;
    }

    public int getPrevY() {
        return prevY;
    }
}
