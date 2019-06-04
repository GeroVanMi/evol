package algorithm.world;

import java.util.Random;

public class World {
    private Field[][] fields;

    public World(int size) {
        fields = new Field[size][size];

        Random random = new Random();
        for(int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                fields[x][y] = new Field(x, y);

                // TODO: Replace this with own proper function and better ways to regulate chance.
                if(random.nextBoolean()) {
                    fields[x][y].setFoodValue(random.nextDouble());
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
