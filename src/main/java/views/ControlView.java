package views;

import algorithm.Algorithm;
import algorithm.Creature;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class ControlView {


    private Pane pane;
    private Button addCreatureButton;
    private Algorithm algorithm;

    public ControlView(Algorithm algorithm) {
        this.algorithm = algorithm;

        pane = new Pane();
        addCreatureButton = new Button("Add Creature");

        addCreatureButton.setOnAction(this::handleAddCreature);

        pane.getChildren().add(addCreatureButton);

    }

    private void handleAddCreature(ActionEvent actionEvent) {
        double energyCost = 0.3;
        double offspringEnergy = 1;
        double reproductionEnergyNeeded = 1.5;

        double red = Math.exp(-energyCost);
        double green = Math.exp(-offspringEnergy);
        double blue = Math.exp(-reproductionEnergyNeeded);

        Creature creature = new Creature(5, 5, 0.5, 45, energyCost, 0.5,
                offspringEnergy, reproductionEnergyNeeded, red, green, blue, algorithm.getWorld());
        algorithm.addCreature(creature);
    }

    public Pane getPane() {
        return pane;
    }
}
