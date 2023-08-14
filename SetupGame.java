package othello;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class SetupGame {
    private Board board;
    private Pane gPane;
    private Player white, black;
    private Referee referee;

    /**
     * setupGame constructor which takes in a game pane, and sets the pane
     * instance variable to the pane, and creates a new board.
     */
    public SetupGame(Pane gamePane){
        this.gPane = gamePane;
        this.board = new Board(this.gPane);
    }

    /**
     * startPlayers method which takes in the two modes of the black and white players,
     * and sets up the human or computer players based on the numbers that are passed in
     * and sets the referees to the players.
     */
    public void startPlayers(int whitePlayerMode, int blackPlayerMode){
        if(whitePlayerMode == 0){
            this.white = new HumanPlayer(Color.WHITE, this.board, this.gPane);
        }else{
            this.white = new CompPlayer(Color.WHITE, this.board, whitePlayerMode);
        }
        if(blackPlayerMode == 0){
            this.black = new HumanPlayer(Color.BLACK, this.board, this.gPane);
        }else{
            this.black = new CompPlayer(Color.BLACK, this.board, blackPlayerMode);
        }
        this.referee = new Referee(this.white, this.black, this.board, this.gPane);
        this.black.setRef(this.referee);
        this.white.setRef(this.referee);
    }

    /**
     * getScoreLabel which returns the score label from the referee
     */
    public Label getScoreLabel(){
        return this.referee.getScoreLabel();
    }

    /**
     * getTurnLabel which returns the turn label from the referee
     */
    public Label getTurnLabel(){
        return this.referee.getTurnLabel();
    }

    /**
     * restart method which restarts the game and sets the board, referee,
     * and players to null, and creates a new board.
     */
    public void restart(){
        this.board = null;
        this.referee.updateLabels();
        this.referee = null;
        this.white = null;
        this.black = null;
        this.board = new Board(this.gPane);
    }
}
