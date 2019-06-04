package algorithm;

import java.util.Random;

public class Creature {
    private double energy;
    private int x, y, prevX, prevY;

    public Creature(int x, int y) {
        this.x = x;
        this.y = y;
        this.energy = 5;
    }

    private void eat(double foodValue) {
        this.energy += foodValue;
    }

    public void tick() {
        Random random = new Random();

        this.energy -= 1;

        this.setPrevPos();
        if(random.nextBoolean()) {
            this.moveHorizontal(random.nextBoolean());
        } else {
            this.moveVertical(random.nextBoolean());
        }
    }

    public void moveVertical(boolean up) {
        if(up) {
            x -= 1;
        } else {
            x += 1;
        }
    }

    public void moveHorizontal(boolean left) {
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

    public void setPrevPos() {
        this.prevX = this.x;
        this.prevY = this.y;
    }

    public void setX(int x) {
        this.setPrevPos();
        this.x = x;
    }

    public void setY(int y) {
        this.setPrevPos();
        this.y = y;
    }

    public double getEnergy() {
        return energy;
    }
}
