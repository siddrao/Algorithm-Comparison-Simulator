package wls;


import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 */
public class Main extends Application {

    private Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage startStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("scenes/dijkstra.fxml"));
		Parent root = loader.load();
		Simulator viewController = loader.getController();
		startStage = new Stage();        
		startStage.setScene(new Scene(root));
		viewController.setStage(startStage);
    }    
}
