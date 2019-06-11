package algorithm.world;

import algorithm.Creature;

import java.util.Random;

public class Field {
    private int x, y;
    private boolean isBlocked;
    private Creature currentCreature;

    /**
     * Between 0 and 1
     */
    private double foodValue;

    public Field(int x, int y, boolean isBlocked) {
        this.x = x;
        this.y = y;
        this.isBlocked = isBlocked;
        this.currentCreature = null;
        this.foodValue = 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getFoodValue() {
        return foodValue;
    }

    public double takeFood() {
        double food = foodValue;
        foodValue = 0;
        return food;
    }

    public void growFood() {
        if(foodValue < 1) {
            double grownFood = new Random().nextDouble();
            if(foodValue + grownFood > 1) {
                foodValue = 1;
            } else {
                foodValue += grownFood;
            }
        }
    }

    public void setFoodValue(double foodValue) {
        this.foodValue = foodValue;
    }

    public void creatureArrives(Creature creature) {
        this.currentCreature = creature;
        this.isBlocked = true;
    }

    public void creatureLeaves() {
        this.currentCreature = null;
        this.isBlocked = false;
    }

    public boolean isBlocked() {
        return isBlocked;
    }
}
