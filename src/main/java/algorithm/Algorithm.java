package algorithm;

import algorithm.world.Field;
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

    public Algorithm(int worldSize) {
        this.world = new World(worldSize);
        creatures = new ArrayList<>();
        dyingCreatures = new ArrayList<>();
        bornCreatures = new ArrayList<>();

        this.tickListeners = new ArrayList<>();

        this.loop = new Timeline(new KeyFrame(Duration.millis(20), event -> this.tick()));
        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();
    }

    private void tick() {

        for (Creature creature : creatures) {
            creature.tick(world);
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
