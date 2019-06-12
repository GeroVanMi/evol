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

        System.out.println("Passed ticks: " + passedTicks);
        System.out.println("Total amount of creatures: " + totalCreatures);
        System.out.println("Current amount of creatures: " + algorithm.getCreatures().size());
        System.out.println("Average amount of creatures: " + averageAmountCreatures);
        System.out.println("--------------------------------------------");
    }

    @Override
    public void updateAll() {

    }
}
