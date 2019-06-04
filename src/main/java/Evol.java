import algorithm.Algorithm;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.WorldView;

public class Evol extends Application {

    public void start(Stage primaryStage) throws Exception {
        Algorithm algorithm = new Algorithm(25);

        WorldView worldView = new WorldView(algorithm);

        algorithm.addTickListener(worldView);

        Scene scene = new Scene(worldView.getPane());
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
