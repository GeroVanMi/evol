import algorithm.Algorithm;
import algorithm.Statskeeper;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.ControlView;
import views.WorldView;

public class Evol extends Application {

    public void start(Stage primaryStage) throws Exception {
        Algorithm algorithm = new Algorithm(100, 5, 10, 0.75);
        Statskeeper statskeeper = new Statskeeper(algorithm);

        WorldView worldView = new WorldView(algorithm);

        algorithm.addTickListener(worldView);
        algorithm.addTickListener(statskeeper);

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
