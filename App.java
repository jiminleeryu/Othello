package othello;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {


    /**
     * top logic class that creates a new pane organizer and scene, and sets the scene
     */
    @Override
    public void start(Stage stage) {
        // Create top-level object, set up the scene, and show the stage here.
        stage.setTitle("Othello");
        PaneOrganizer organizer = new PaneOrganizer();
        Scene scene = new Scene(organizer.getRoot(), Constants.SCENE_WIDTH, Constants.SCENE_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    /*
    * Here is the mainline! No need to change this.
    */
    public static void main(String[] argv) {
        // launch is a method inherited from Application
        launch(argv);
    }
}
