package algorithm;

import algorithm.world.World;
import interfaces.TickListener;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;

public class Algorithm {

    private World world;
    private Timeline loop;
    private ArrayList<Creature> creatures, dyingCreatures, bornCreatures;
    private ArrayList<TickListener> tickListeners;
    private int remainingCycles, totalCycles;

    public Algorithm(int worldSize, int ticksPerSeconds, int totalCycles, double growChance) {
        this.world = new World(worldSize, growChance);
        creatures = new ArrayList<>();
        dyingCreatures = new ArrayList<>();
        bornCreatures = new ArrayList<>();
        this.totalCycles = totalCycles;
        this.remainingCycles = totalCycles;

        this.tickListeners = new ArrayList<>();

        this.loop = new Timeline(new KeyFrame(Duration.millis(1000 / ticksPerSeconds), event -> this.tick()));
        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();
    }

    private void tick() {
        for (Creature creature : creatures) {
            creature.tick();

            if(creature.isAlive()) {
                if(creature.getEnergy() > creature.getReproductionEnergyNeeded()) {
                    Creature newCreature = creature.reproduce();

                    if(newCreature != null) {
                        bornCreatures.add(newCreature);
                    }
                }
            } else {
                dyingCreatures.add(creature);
            }
        }

        if(remainingCycles == 0) {
            world.generateFood();
            remainingCycles = totalCycles;

            for (TickListener tickListener : tickListeners) {
                tickListener.updateAll();
            }
        } else {
            remainingCycles--;
        }

        creatures.removeAll(dyingCreatures);
        creatures.addAll(bornCreatures);

        for (TickListener tickListener : tickListeners) {
            tickListener.update();
        }

        /*System.out.println("-------------------------");
        System.out.println("Tick: " );
        System.out.println("Living Creatures: " + creatures.size());
        System.out.println("Born Creatures : " + bornCreatures.size());
        System.out.println("Dying Creatures: " + dyingCreatures.size());
        System.out.println("Remaining Cycles: " + remainingCycles);
        System.out.println("-------------------------");*/

        bornCreatures.clear();
        dyingCreatures.clear();
    }

    public void addCreature(Creature creature) {
        creatures.add(creature);
    }

    public World getWorld() {
        return world;
    }

    public ArrayList<Creature> getCreatures() {
        return creatures;
    }

    public ArrayList<Creature> getDyingCreatures() {
        return dyingCreatures;
    }

    public void addTickListener(TickListener tickListener) {
        tickListeners.add(tickListener);
    }
}
