package views;

import algorithm.Algorithm;
import algorithm.Creature;
import algorithm.world.Field;
import algorithm.world.World;
import interfaces.TickListener;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class WorldView implements TickListener {
    private Algorithm algorithm;
    private World world;
    private Pane pane;
    private GraphicsContext brush;
    private double fieldWidth, fieldLength;

    private Color BORDER = new Color(0.25, 0.25, 0.25, 1);
    private Color BACKGROUND = new Color(0.75, 0.75, 0.75, 1);
    private Color CREATURE = new Color(0.25, 0.75, 0.25, 1);
    private Color FOOD = new Color(0.75, 0.25, 0.25, 1);

    public WorldView(Algorithm algorithm) {
        this.algorithm = algorithm;
        this.world = algorithm.getWorld();

        Canvas canvas = new Canvas(820, 820);

        this.brush = canvas.getGraphicsContext2D();
        this.pane = new Pane(canvas);

        this.fieldWidth = canvas.getWidth() / world.getSize();
        this.fieldLength = canvas.getHeight() / world.getSize();

        this.renderFullWorld();
    }

    private void renderFullWorld() {
        for (int x = 0; x < world.getSize(); x++) {
            for (int y = 0; y < world.getSize(); y++) {
                drawField(x, y);
            }
        }
    }

    private void drawField(int x, int y) {
        brush.setFill(BACKGROUND);
        brush.fillRect(x * fieldWidth, y * fieldLength, fieldWidth, fieldLength);

        brush.setStroke(BORDER);
        brush.strokeRect(x * fieldWidth, y * fieldLength, fieldWidth, fieldLength);

        Field currentField = world.getFields()[x][y];
        drawFood(x, y, currentField.getFoodValue());
    }

    private void drawFood(int x, int y, double foodvalue) {
        brush.setFill(FOOD);

        double foodWidth = fieldWidth * foodvalue / 2;
        double foodLength = fieldLength * foodvalue / 2;
        double centerW = fieldWidth / 2 - foodWidth / 2;
        double centerL = fieldLength / 2 - foodLength / 2;

        brush.fillRect(x * fieldWidth + centerW, y * fieldLength + centerL, foodWidth, foodLength);
    }

    private void drawCreature(Creature creature) {
        brush.setFill(CREATURE);
        brush.fillOval(creature.getX() * fieldWidth, creature.getY() * fieldLength, fieldWidth, fieldLength);
    }

    private void clear(int x, int y) {
        brush.clearRect(x * fieldWidth, y * fieldLength, fieldWidth, fieldLength);
    }

    public Pane getPane() {
        return pane;
    }

    @Override
    public void update() {
        for (Creature creature : algorithm.getCreatures()) {
            drawCreature(creature);
            clear(creature.getPrevX(), creature.getPrevY());
            drawField(creature.getPrevX(), creature.getPrevY());
        }

        for (Creature dyingCreature : algorithm.getDyingCreatures()) {
            clear(dyingCreature.getX(), dyingCreature.getY());
            drawField(dyingCreature.getX(), dyingCreature.getY());
        }
    }
}
