package othello;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Piece {
    private Circle piece;

    /**
     * Piece constructor which takes in a row, col, pane, and color. A circle is then
     * created with the center and fill are all set to given values.
     */
    public Piece(int row, int col, Pane gPane, Color color){
        this.piece = new Circle(20);
        this.piece.setCenterX(col * Constants.SQUARE_WIDTH + Constants.PIECE_OFFSET);
        this.piece.setCenterY(row * Constants.SQUARE_WIDTH + Constants.PIECE_OFFSET);
        this.piece.setFill(color);
        gPane.getChildren().add(this.piece);
    }

    /**
     * getColor method which returns the color of the piece.
     */
    public Color getColor(){
        return (Color) this.piece.getFill();
    }

    /**
     * set color method which takes in the color parameter and sets the
     * color of the piece to it
     */
    public void setColor(Color color){
        this.piece.setFill(color);
    }
}
