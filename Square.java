package othello;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square {
    private Rectangle square;
    private Pane gPane;
    private int row;
    private int col;
    private Piece piece;

    /**
     * Square constructor which takes in an x, y, pane, and color for a given player. A square is initialized as
     * a rectangle, and is set to fill with a given color (either grey or dark green). The method then adds the
     * square to the pane.
     */
    public Square(int x, int y, Pane pane, Color color){
        this.row = y;
        this.col = x;
        this.gPane = pane;
        this.square = new Rectangle(x * Constants.SQUARE_WIDTH, y * Constants.SQUARE_WIDTH, Constants.SQUARE_WIDTH, Constants.SQUARE_WIDTH);
        this.square.setFill(color);
        this.square.setStroke(Color.BLACK);
        this.gPane.getChildren().add(this.square);
    }

    /**
     * addPiece method which takes in a color parameter.
     * Then, a new piece is constructed at a given row, col, and color.
     */
    public void addPiece(Color color){
        this.piece = new Piece(this.row, this.col, this.gPane, color);
    }

    /**
     * getRow method which returns the row that a given square exists
     */
    public int getRow(){
        return (int) this.square.getY() / Constants.SQUARE_WIDTH;
    }

    /**
     * getCol method which returns the col that a given square exists
     */
    public int getCol(){
        return (int) this.square.getX() / Constants.SQUARE_WIDTH;
    }

    /**
     * getPiece method which returns the piece instance that a given
     * square has
     */
    public Piece getPiece(){
        return this.piece;
    }

    /**
     * getColorOnSquare method which sees if the square is full, and
     * if so, it will return the color of the piece
     */
    public Color getColorOnSquare(){
        if(!this.isEmpty()){
            return this.piece.getColor();
        }
        return null;
    }

    /**
     * getColorOfSquare method which first checks if it is empty,
     * if so, it will return the color of the square.
     */
    public Color getColorOfSquare(){
        if(this.piece == null) {
            return (Color) this.square.getFill();
        }
        return null;
    }

    /**
     * flipPiece which checks if there is a piece, and if so,
     * it will check if the color of the piece is either white
     * or black and it will flip it to the opposite color.
     */
    public void flipPiece(){
        if(this.piece != null) {
            if (this.piece.getColor() == Color.WHITE) {
                this.piece.setColor(Color.BLACK);
            } else if(this.piece.getColor() == Color.BLACK){
                this.piece.setColor(Color.WHITE);
            }
        }
    }

    /**
     * isEmpty method which checks if a piece at a given square is null, indicating that
     * there hasn't been a piece placed there yet. If it is null, the boolean will
     * return true.
     */
    public Boolean isEmpty(){
        if(this.piece == null){
            return true;
        }else{
            return false;
        }
    }

    /**
     * isBorder method which returns true if the row or column of the board is
     * on the edge.
     */
    public Boolean isBorder(){
        if(this.row == 0 || this.row == 9 || this.col == 0 || this.col == 9){
            return true;
        }else{
            return false;
        }
    }

    /**
     * showValid method which sets the fill of the square to the grey, which
     * is the valid square color.
     */
    public void showValid(){
        this.square.setFill(Constants.VALID_SQUARE_COLOR);
    }

    /**
     * removeColor which sets a given square back to the default board color.
     */
    public void removeColor(){
        this.square.setFill(Constants.BOARD_COLOR);
    }
}
