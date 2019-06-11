package algorithm;

import interfaces.TickListener;

public class Statskeeper implements TickListener {
    private Algorithm algorithm;
    private int totalCreatures;
    private int passedTicks;

    public Statskeeper(Algorithm algorithm) {
        this.algorithm = algorithm;
        totalCreatures = 0;
        passedTicks = 0;
    }

    @Override
    public void update() {
        totalCreatures += algorithm.getCreatures().size();
        passedTicks++;

        double averageAmountCreatures = totalCreatures / passedTicks;

        System.out.println("Average amount of creatures: " + averageAmountCreatures);
    }

    @Override
    public void updateAll() {

    }
}
