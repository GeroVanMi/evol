package algorithm;

import algorithm.world.World;
import interfaces.TickListener;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Random;

public class Algorithm {

    private World world;
    private Timeline loop;
    private ArrayList<Creature> creatures;
    private ArrayList<TickListener> tickListeners;

    public Algorithm(int worldSize) {
        this.world = new World(worldSize);
        creatures = new ArrayList<>();
        creatures.add(new Creature(5, 5));

        this.tickListeners = new ArrayList<>();

        this.loop = new Timeline(new KeyFrame(Duration.millis(200), event -> this.tick()));
        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();
    }

    private void tick() {
        Random random = new Random();
        for (Creature creature : creatures) {

            int x = creature.getX(), y = creature.getY();
            if (x > world.getSize() - 1) {
                creature.setX(x % world.getSize());
            } else if (x < 0) {
                creature.setX(world.getSize() - 1);
            }
            if (y > (world.getSize() - 1)) {
                creature.setY(y % (world.getSize()));
            } else if (y < 0) {
                creature.setY(world.getSize() - 1);
            }

            if(random.nextBoolean()) {
                creature.moveHorizontal(random.nextBoolean());
            } else {
                creature.moveVertical(random.nextBoolean());
            }

        }
        for (TickListener tickListener : tickListeners) {
            tickListener.update();
        }
    }

    public World getWorld() {
        return world;
    }

    public ArrayList<Creature> getCreatures() {
        return creatures;
    }

    public void addTickListener(TickListener tickListener) {
        tickListeners.add(tickListener);
    }
}
