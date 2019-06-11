package algorithm.world;

import algorithm.Creature;

public class Field {
    private int x, y;
    private boolean isBlocked;
    private Creature currentCreature;

    /**
     * Between 0 and 1
     */
    private double foodValue;

    public Field(int x, int y, boolean isBlocked, double foodValue) {
        this.x = x;
        this.y = y;
        this.isBlocked = isBlocked;
        this.currentCreature = null;
        this.foodValue = foodValue;
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
