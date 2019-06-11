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
    private double offspringEnergy;
    private double red, green, blue;
    private double reproductionEnergyNeeded;

    public Creature(int x, int y, double initialEnergy, int direction, double energyCost, double turnChance,
                    double offspringEnergy, double reproductionEnergyNeeded, double red, double green, double blue) {
        this.x = x;
        this.y = y;
        this.energy = initialEnergy;
        this.energyCost = energyCost;
        this.direction = direction;
        this.alive = true;
        this.turnChance = turnChance;
        this.reproductionEnergyNeeded = reproductionEnergyNeeded;
        this.offspringEnergy = offspringEnergy;

        // Set the color of the Creature
        this.red = red; this.green = green; this.blue = blue;
    }

    public void eat(double foodValue) {
        this.energy += foodValue;
    }

    public void tick(World world) {
        setPrevPos();

        move(world, 0);
        energy -= energyCost;

        Field field = world.getFields()[x][y];
        eat(field.takeFood());

        if (energy <= 0) {
            die();
        }
    }

    private void die() {
        alive = false;
    }

    private void turn() {
        Random random = new Random();

        if (random.nextBoolean()) {
            direction -= 45;
            if (direction < 0) {
                direction = 315;
            }
        } else {
            direction += 45;
            if (direction == 360) {
                direction = 0;
            }
        }
    }

    public void move(World world, int recursiveCounter) {

        Random random = new Random();
        if (random.nextDouble() > turnChance) {
            this.turn();
        }


        int newY = y;
        int newX = x;

        switch (direction) {
            case 0:
                newY--;
                break;

            case 45:
                newX++;
                newY--;

            case 90:
                newX++;
                break;

            case 135:
                newX++;
                newY++;

            case 180:
                newY++;
                break;

            case 225:
                newX--;
                newY++;

            case 270:
                newX--;
                break;

            case 315:
                newX--;
                newY--;
        }

        world.getFields()[x][y].creatureLeaves();

        if(world.getFields()[newX][newY].isBlocked()) {
            this.turn();
            if(recursiveCounter < 8) {
                this.move(world, recursiveCounter + 1);
            }
        } else {
            x = newX;
            y = newY;
        }

        world.getFields()[x][y].creatureArrives(this);
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

    public Creature reproduce(World world) {
        // Mutatable values
        double childOffspringEnergy = this.offspringEnergy;
        double childReproductionEnergyNeeded = this.reproductionEnergyNeeded;
        double childEnergyCost = this.energyCost;

        // Mutate
        Random mutation = new Random();

        if(mutation.nextDouble() > 0.95) {
            double mutationValue = mutation.nextDouble() - 0.5 / 2;
            if(childOffspringEnergy + mutationValue > 0) {
                childOffspringEnergy += mutationValue;
            }
        }
        if(mutation.nextDouble() > 0.95) {
            double mutationValue = mutation.nextDouble() - 0.5 / 2;
            if(childReproductionEnergyNeeded + mutationValue > 0) {
                childReproductionEnergyNeeded += mutationValue;
            }
        }

        if(mutation.nextDouble() > 0.95) {
            double mutationValue = mutation.nextDouble() - 0.5 / 2;
            if(energyCost + mutationValue > 0) {
                childEnergyCost +=  mutationValue;
            }
        }

        boolean hasFreeSpace = false;

        int childX = -1;
        int childY = -1;

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if(!world.getFields()[x - i][y - j].isBlocked()) {
                    childX = x - i;
                    childY = y - j;
                    hasFreeSpace = true;
                    break;
                }
            }
            if(hasFreeSpace) {
                break;
            }
        }

        if(hasFreeSpace) {
            // Create child and reduce energy of parent
            Creature child = new Creature(childX, childY, offspringEnergy, direction, childEnergyCost, turnChance,
                    childOffspringEnergy, childReproductionEnergyNeeded, childEnergyCost / 5,
                    childOffspringEnergy / 5, childReproductionEnergyNeeded / 5);
            energy -= offspringEnergy;
            return child;
        }
        return null;
    }

    public void setPrevPos() {
        this.prevX = this.x;
        this.prevY = this.y;
    }

    public boolean isAlive() {
        return alive;
    }

    public double getEnergy() {
        return energy;
    }

    public double getRed() {
        return red;
    }

    public double getGreen() {
        return green;
    }

    public double getBlue() {
        return blue;
    }

    public double getReproductionEnergyNeeded() {
        return reproductionEnergyNeeded;
    }
}
