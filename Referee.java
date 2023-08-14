package othello;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Referee {
    private Player black, white, activePlayer;
    private Timeline timeline;
    private Board board;
    private Label scoreLabel, turnLabel;
    private Pane pane;
    private String winner;

    /**
     * Referee constructor, takes in two players, and a board. Initializes
     * the board and the two players, and sets the active player to
     * black to indicate that black makes the first move. Also calls
     * setup timeline.
     */
    public Referee(Player playerWhite, Player playerBlack, Board board, Pane gamePane){
        this.pane = gamePane;
        this.board = board;
        this.black = playerBlack;
        this.white = playerWhite;
        this.activePlayer = this.black;
        this.setUpTimeline();
        this.scoreLabel = new Label("White: " + this.board.getWhiteScore() + " Black: " + this.board.getBlackScore());;
        this.turnLabel = new Label("Black piece turn");
    }

    /**
     * SwitchPlayers method which checks if the active player is currently white or black
     * and depending on which it is, it will reassign the active player to the other color.
     */
    public void switchPlayers(){
        if(this.activePlayer == this.black){
            this.activePlayer = this.white;
        }else{
            this.activePlayer = this.black;
        }
    }

    /**
    setUpTimeline method to create new timeline and call the update method after every
    keyframe duration.
     */
    private void setUpTimeline() {
        KeyFrame kf = new KeyFrame(Duration.seconds(Constants.DURATION),
                (ActionEvent e) -> this.update());
        this.timeline = new Timeline();
        this.timeline.getKeyFrames().add(kf);
        this.timeline.setCycleCount(Animation.INDEFINITE);
        this.timeline.play();
    }

    /**
     * update method which is called by the timeline, which pauses the timeline, and
     * checks if there is a valid move left. If so, the active player makes a turn,
     * the score is updated, and the text is updated to display the current scores.
     */
    private void update(){
        this.timeline.pause();
        if(this.board.isValidLeft(this.activePlayer.getPlayerColor())) {
            this.activePlayer.turn();
            this.board.updateScore();
            this.scoreLabel.setText("White: " + this.board.getWhiteScore() + " Black: " + this.board.getBlackScore());
        }
        this.gameOver();
    }

    /**
     * turnOver method which sets the text to the updated turn label, plays the timeline, and
     * switches the players.
     */
    public void turnOver(){
        this.turnLabel.setText(this.updatedTurnLabel());
        this.timeline.play();
        this.switchPlayers();
    }

    /**
     * gameOver method which checks if the board is full, and if the game is over. If so,
     * it sets the text to game over, and presents the winning player number. It also sets
     * the mouse click to null, and stops the timeline.
     */
    public void gameOver(){
        if(this.board.isFull() || this.board.isGameOver(this.activePlayer.getPlayerColor())){
            this.board.setGameOver("Game Over, " + this.getWinner() + " wins");
            this.pane.setOnMouseClicked(null);
            this.timeline.stop();
        }
    }

    /**
     * getWinner which sees which player's score is higher, and returns as a string
     * who the winning player is. If it is a tie, then the winner is "no one"
     */
    public String getWinner(){
        if(this.board.getWhiteScore() > this.board.getBlackScore()){
            return "white";
        }else if(this.board.getBlackScore() > this.board.getWhiteScore()){
            return "black";
        }
        return "no one";
    }

    /**
     * updatedTurnLabel which updates the turn label depending on whose turn
     * it is and switches the text.
     */
    public String updatedTurnLabel(){
        if(this.turnLabel.getText().equals("Black piece turn")){
            this.turnLabel.setText("White piece turn");
        }else if(this.turnLabel.getText().equals("White piece turn")){
            this.turnLabel.setText("Black piece turn");
        }
        return this.turnLabel.getText();
    }

    /**
     * updateLabels which sets the scores back to the default, and sets the text back
     * to the player who starts first.
     */
    public void updateLabels(){
        this.board.setScores(2, 2);
        if(this.turnLabel.getText().equals("Black piece turn")){
            this.turnLabel.setText("White piece turn");
        }else{
            this.turnLabel.setText("Black piece turn");
        }
    }

    /**
     * getScoreLabel which returns the current instance of the scoreLabel
     */
    public Label getScoreLabel(){
        return this.scoreLabel;
    }

    /**
     * getTurnLabel which returns the current instance of the turn Label
     */
    public Label getTurnLabel(){
        return this.turnLabel;
    }
}
