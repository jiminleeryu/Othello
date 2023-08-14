package othello;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;

public class HumanPlayer implements Player{
    private Referee humanRef;
    private Board board;
    private Pane pane;
    private Color playerColor;
    private int row1, col1;

    /**
     * HumanPlayer constructor which takes in a color, board, and pane, and sets the
     * various instance variables to these parameters. This constructor also sets up
     * the handle mouse press.
     */
    public HumanPlayer(Color color, Board board1, Pane pane1){
        this.playerColor = color;
        this.board = board1;
        this.pane = pane1;
        this.pane.setOnMouseClicked((MouseEvent e) -> this.handleMousePress(e));
    }

    /**
     * Turn method for human player where the board will show all the valid squares
     * and set the handle mouse press event.
     */
    @Override
    public void turn() {
        this.board.showValidSquares(this.playerColor);
        this.pane.setOnMouseClicked((MouseEvent e) -> this.handleMousePress(e));
    }

    /**
     * setRef method which is called by setupGame and sets the human ref to the
     * referee that was created in setupGame.
     */
    @Override
    public void setRef(Referee referee){
        this.humanRef = referee;
    }

    /**
     * getPlayerColor which returns the current instance of the player's color
     */
    @Override
    public Color getPlayerColor() {
        return this.playerColor;
    }

    /**
     * handleMousePress which sets the row and col to the x and y inputs of the mouse,
     * and then checks the board if the square that the player selects is grey and
     * can sandwich. If so, it will add a piece to the board
     */
    public void handleMousePress(MouseEvent e){
        this.row1 = (int) (e.getY() / Constants.SQUARE_WIDTH);
        this.col1 = (int) (e.getX() / Constants.SQUARE_WIDTH);
        if(this.board.isGrey(this.row1, this.col1) && this.board.isSandwich(this.row1, this.col1, this.playerColor)){
            this.board.addPiece(this.row1, this.col1, this.playerColor);
            this.board.flipPieces(this.row1, this.col1, this.playerColor);
            this.board.removeValidSquares();
            this.pane.setOnMouseClicked(null);
            this.humanRef.turnOver();
        }else
            this.humanRef.turnOver();
    }
}
