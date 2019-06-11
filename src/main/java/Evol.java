import algorithm.Algorithm;
import algorithm.Creature;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.ControlView;
import views.WorldView;

public class Evol extends Application {

    public void start(Stage primaryStage) throws Exception {
        Algorithm algorithm = new Algorithm(25, 1, 15, 0.6);

        WorldView worldView = new WorldView(algorithm);

        algorithm.addTickListener(worldView);

        Scene scene = new Scene(worldView.getPane());
        primaryStage.setScene(scene);

        primaryStage.show();

        // Secondary Control Window

        ControlView controlView = new ControlView(algorithm);
        Scene controlScene = new Scene(controlView.getPane());
        Stage controlStage = new Stage();
        controlStage.setScene(controlScene);
        controlStage.show();
    }
}
