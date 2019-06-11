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

    public Algorithm(int worldSize, int ticksPerSeconds, int totalCycles) {
        this.world = new World(worldSize);
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
            if(creature.isAlive()) {
                creature.tick(world);
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

        for (TickListener tickListener : tickListeners) {
            tickListener.update();
        }

        creatures.addAll(bornCreatures);
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
