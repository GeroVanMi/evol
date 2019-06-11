package algorithm;

import algorithm.world.Field;
import algorithm.world.World;

import java.util.Random;

public class Creature {
    private double energy, energyCost;
    private int x, y, prevX, prevY;
    private int direction;
    private boolean alive;
    private double turnChance;

    public Creature(int x, int y, double initalEnergy, int direction, double energyCost, double turnChance) {
        this.x = x;
        this.y = y;
        this.energy = initalEnergy;
        this.energyCost = energyCost;
        this.direction = direction;
        this.alive = true;
        this.turnChance = turnChance;
    }

    public void eat(double foodValue) {
        this.energy += foodValue;
    }

    public void tick(World world) {
        if(alive) {
            setPrevPos();

            move(world);
            energy -= energyCost;

            Field field = world.getFields()[x][y];
            eat(field.takeFood());

            if(energy == 0) {
                die();
            }
        }
    }

    private void die() {
        alive = false;
    }

    private void turn() {
        Random random = new Random();

        if(random.nextBoolean()) {
            direction -= 90;
            if(direction < 0) {
                direction = 270;
            }
        } else {
            direction += 90;
            if(direction == 360) {
                direction = 0;
            }
        }
    }

    public void move(World world) {

        Random random = new Random();
        if(random.nextDouble() > turnChance) {
            this.turn();
        }

        switch (direction) {
            case 0:
                if(world.getFields()[x][y - 1].isBlocked()) {
                    this.turn();
                } else {
                    y--;
                }
                break;

            case 90:
                if(world.getFields()[x + 1][y].isBlocked()) {
                    this.turn();
                } else {
                    x++;
                }
                break;

            case 180:
                if(world.getFields()[x][y + 1].isBlocked()) {
                    this.turn();
                } else {
                    y++;
                }
                break;

            case 270:
                if(world.getFields()[x - 1][y].isBlocked()) {
                    this.turn();
                } else {
                    x--;
                }
                break;
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

    public void reproduce() {
        this.energy -= 1;
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

    public boolean isAlive() {
        return alive;
    }
}
