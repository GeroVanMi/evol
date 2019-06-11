package algorithm.world;

import java.util.Random;

public class World {
    private Field[][] fields;

    public World(int size) {
        fields = new Field[size][size];

        Random random = new Random();
        for(int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {

                boolean isOnBorder = false;
                double foodValue = 0;

                if(x == 0 || y == 0 || x == size - 1 || y == size - 1) {
                    isOnBorder = true;
                }

                // TODO: Replace this with own proper function and better ways to regulate chance.
                if(random.nextBoolean()) {
                    foodValue = random.nextDouble();
                }
                fields[x][y] = new Field(x, y, isOnBorder,foodValue);
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
