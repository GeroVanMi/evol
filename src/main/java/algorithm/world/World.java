package algorithm.world;

import java.util.Random;

public class World {
    private Field[][] fields;
    private int size;
    private double growChance;

    public World(int size, double growChance) {
        this.size = size;
        this.growChance = growChance;
        fields = new Field[size][size];

        for(int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {

                boolean isOnBorder = false;

                if(x == 0 || y == 0 || x == size - 1 || y == size - 1) {
                    isOnBorder = true;
                }

                fields[x][y] = new Field(x, y, isOnBorder);
            }
        }

        this.generateFood();
    }

    public void generateFood() {
        for(int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Field currentField = fields[x][y];
                if(!currentField.isBlocked() && new Random().nextDouble() > growChance) {
                    currentField.growFood();
                }
            }
        }
    }

    public Field[][] getFields() {
        return fields;
    }

    public int getSize() {
        return fields.length;
    }
}
