package othello;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class PaneOrganizer {
    private BorderPane root;
    private SetupGame othello;
    private Pane gPane;

    /**
     * PaneOrganizer constructor which creates the board pane and a new instance of the othello game.
     * Also adds controls
     */
    public PaneOrganizer(){
        this.root = new BorderPane();
        createBoardPane();
        this.othello = new SetupGame(this.gPane);
        addControls();
    }

    /**
     * addControls which creates a new instance of controls and sets it to the right
     * of the screen.
     */
    public void addControls(){
        Controls controls = new Controls(this.othello);
        this.root.setRight(controls.getPane());
    }

    /**
     * createBoardPane which creates a new graphical pane and sets it to the center.
     */
    private void createBoardPane(){
        this.gPane = new Pane();
        this.root.setCenter(this.gPane);
    }

    /**
     * getRoot which returns the BorderPane root
     */
    public BorderPane getRoot(){
        return root;
    }
}
