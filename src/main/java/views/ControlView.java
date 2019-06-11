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
        double energyCost = 0.1;
        double offspringEnergy = 1;
        double reproductionEnergyNeeded = 1.5;

        Creature creature = new Creature(10, 10, 0.5, 45, energyCost, 0.5,
                offspringEnergy, reproductionEnergyNeeded, energyCost / 5, offspringEnergy / 5,
                reproductionEnergyNeeded / 5, algorithm.getWorld());
        algorithm.addCreature(creature);
    }

    public Pane getPane() {
        return pane;
    }
}
