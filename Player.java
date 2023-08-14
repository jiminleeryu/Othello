package othello;

import javafx.scene.paint.Color;

public interface Player {
    /**
     * turn method which is called by the active player
     */
    void turn();

    /**
     * setRef method which is called in setupGame, sets the referee to the parameter
     */
    void setRef(Referee referee);

    /**
     * getPlayerColor which returns the player's color
     */
    Color getPlayerColor();
}
