package othello;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class Board {
    private Square[][] squareArray;
    private Pane boardPane;
    private int white, black;
    private Label gameOver;

    /**
     * Board constructor, which takes in a pane, and creates a new squareArray,
     * creates the game board, and sets up the starting pieces.
     */
    public Board(Pane bPane){
        this.boardPane = bPane;
        this.squareArray = new Square[Constants.ROW_NUM][Constants.COL_NUM];
        this.createGameBoard();
        this.setupPieces();
    }

    /**
     * Copy constructor which takes in a board constructor, and creates a new
     * pane and square array, which creates a new board and adds pieces to
     * the current board.
     */
    public Board(Board board){
        Square[][] currentBoard = board.getBoardArray();
        this.boardPane = new Pane();
        this.squareArray = new Square[Constants.ROW_NUM][Constants.COL_NUM];
        for (int row = 1; row < 9; row++) {
            for (int col = 1; col < 9; col++) {
                this.squareArray[row][col] = new Square(row, col, this.boardPane, Constants.BOARD_COLOR);
                if(!(currentBoard[row][col].isEmpty())){
                    this.squareArray[row][col].addPiece(currentBoard[row][col].getColorOnSquare());
                }
            }
        }
    }

    /**
     * CreateGameBoard which runs through a nested for loop that creates the outline border and
     * the playable game board inside.
     */
    public void createGameBoard(){
        for(int r = 0; r < Constants.ROW_NUM; r++){
            for(int c = 0; c < Constants.COL_NUM; c++){
                if(r == 0 || r == 9 || c == 0 || c == 9){
                    this.squareArray[r][c] = new Square(c, r, this.boardPane, Constants.BORDER_COLOR);
                }else{
                    this.squareArray[r][c] = new Square(c, r, this.boardPane, Constants.BOARD_COLOR);
                }
            }
        }
    }

    /**
     * setUpPieces method which adds the starting 4 pieces to the board, each that are
     * alternating between white and black.
     */
    public void setupPieces(){
        this.squareArray[4][4].addPiece(Color.BLACK);
        this.squareArray[5][5].addPiece(Color.BLACK);
        this.squareArray[4][5].addPiece(Color.WHITE);
        this.squareArray[5][4].addPiece(Color.WHITE);
    }

    /**
     * addPiece method which takes in a row, col, and color, and adds a piece to that
     * square at a given color.
     */
    public void addPiece(int row, int col, Color color){
        this.squareArray[row][col].addPiece(color);
    }

    /**
     * flipPieces method which takes in a row, col, and color, which then goes through each
     * direction of the flipPieces and checks the line for each direction. Flipped is set
     * to true once the checkLine method finds a piece that is the same color as the
     * player.
     */
    public void flipPieces(int row, int col, Color color){
        for(int rowDir = -1; rowDir < 2; rowDir++) {
            for (int colDir = -1; colDir < 2; colDir++) {
                if(!(rowDir == 0 && colDir == 0)) {
                    if (this.checkLine(row, col, rowDir, colDir, color, false, false)) {
                        this.checkLine(row, col, rowDir, colDir, color, false, true);
                    }
                }
            }
        }
    }

    /**
     * isGrey method that takes in a row and col, and checks if the color of the square is
     * equal to a grey square. If yes, then it returns true, else it will return false.
     */
    public Boolean isGrey(int row, int col){
        if(this.squareArray[row][col].getColorOfSquare() == Constants.VALID_SQUARE_COLOR){
            return true;
        }else
            return false;
    }

    /**
     * isEmpty method which takes in a row and col and checks if the square has no piece
     * on it. If so, it will return true, if not, it returns false.
     */
    public Boolean isEmpty(int row, int col){
        if(this.squareArray[row][col].isEmpty()){
            return true;
        }else
            return false;
    }

    /**
     * isFull method which iterates through the entire board until it finds a piece
     * that is null, which will then return false, indicating that there is still
     * a space on the board.
     */
    public Boolean isFull(){
        for (int row = 1; row < 9; row++) {
            for (int col = 1; col < 9; col++) {
                if(this.squareArray[row][col].getPiece() == null){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * getArrayList method which iterates through the entire board, and if the
     * method finds a square that is valid, it will add it to its valid square
     * arrayList which will then be used by the miniMax.
     */
    public ArrayList <Square> getArrayList(Color color){
        ArrayList<Square> validSquares = new ArrayList<>();
        for (int row = 1; row < Constants.ROW_NUM - 1; row++) {
            for (int col = 1; col < Constants.COL_NUM - 1; col++) {
                if(this.squareArray[row][col].isEmpty() && isSandwich(row, col,color)){
                    validSquares.add(this.squareArray[row][col]);
                }
            }
        }
        return validSquares;
    }

    /**
     * doWin method which takes in a color parameter, and checks if the given color
     * wins the game.
     */
    public Boolean doWin(Color playerColor){
        if(playerColor == Color.WHITE && this.white > this.black){
            return true;
        }else if(playerColor == Color.BLACK && this.black > this.white){
                return true;
        }
        return false;
    }

    /**
     * isTie method which returns true when the score of the white and black player
     * are the same.
     */
    public Boolean isTie(){
        if(this.white == this.black){
            return true;
        }
        return false;
    }

    /**
     * checkLine method which takes in a row, col, row direction, col direction, a color, and two
     * booleans, seenOpponent and flipped. The method starts with the given square checking whether
     * it's empty or if it's a border square. If the next square over is not the same color as the
     * player, then the checkLine method is recursively called again until the square color is the
     * same as the player and that the opposing square has been seen.
     */
    public Boolean checkLine(int row, int col, int rowDir, int colDir, Color playerColor, Boolean seenOpponent, Boolean flipped){
            if (flipped) {
                if (this.squareArray[row + rowDir][col + colDir].getColorOnSquare() != playerColor) {
                    this.squareArray[row + rowDir][col + colDir].flipPiece();
                    this.checkLine(row + rowDir, col + colDir, rowDir, colDir, playerColor, true, true);
                }
            } else {
                if (this.squareArray[row][col].isBorder() || this.squareArray[row + rowDir][col + colDir].isEmpty()) {
                    return false;
                }
                if (this.squareArray[row + rowDir][col + colDir].getColorOnSquare() != playerColor) {
                    return this.checkLine(row + rowDir, col + colDir, rowDir, colDir, playerColor, true, false);
                }
                if (this.squareArray[row + rowDir][col + colDir].getColorOnSquare() == playerColor && seenOpponent) {
                    return true;
                }
                return false;
            }
        return false;
    }

    /**
     * isSandwich method which iterates through the line and returns true if there is a valid line, of
     * sandwiched pieces.
     */
    public Boolean isSandwich(int row, int col, Color playerColor){
        for(int rowDir = -1; rowDir < 2; rowDir++){
            for (int colDir = -1; colDir < 2; colDir++) {
                if(!(rowDir == 0 && colDir == 0)) {
                    if (this.checkLine(row, col, rowDir, colDir, playerColor, false, false)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * showValidSquares method which takes in a color of the player, and iterates through the board
     * and checks if the square is empty and not a border. If so, it will check if the square is
     * empty, not a border, and if it can be sandwiched. If so, it will show that square as valid.
     */
    public void showValidSquares(Color playerColor){
        for (int row = 1; row < Constants.ROW_NUM -1; row++) {
            for (int col = 1; col < Constants.COL_NUM -1; col++) {
                if(this.squareArray[row][col].isEmpty() && !this.squareArray[row][col].isBorder()
                        && this.isSandwich(row, col, playerColor)){
                    this.squareArray[row][col].showValid();
                }
            }
        }
    }

    /**
     * removeValidSquares which iterates through the square array and removes
     * the color from any valid square.
     */
    public void removeValidSquares(){
        for(int row = 1; row < Constants.ROW_NUM -1; row++){
            for (int col = 1; col < Constants.COL_NUM -1; col++) {
                this.squareArray[row][col].removeColor();
            }
        }
    }

    /**
     * updateScore which updates the black and white player scores for each iteration
     * through the entire board. If there is a black piece, the black score will be
     * added by one, and same applies to white pieces.
     */
    public void updateScore(){
        int white1 = 0;
        int black1 = 0;
        for (int row = 1; row < Constants.ROW_NUM -1; row++) {
            for (int col = 1; col < Constants.COL_NUM -1; col++) {
                if(this.squareArray[row][col].getColorOnSquare() == Color.WHITE && !this.squareArray[row][col].isEmpty()){
                    white1++;
                }else if(this.squareArray[row][col].getColorOnSquare() == Color.BLACK && !this.squareArray[row][col].isEmpty()){
                    black1++;
                }
            }
        }
        this.white = white1;
        this.black = black1;
    }

    /**
     * isValidLeft which takes in a color parameter, and returns true if a given colored piece can be sandwiched
     */
    public Boolean isValidLeft(Color color){
        for (int row = 1; row < Constants.ROW_NUM - 1; row++) {
            for (int col = 1; col < Constants.COL_NUM - 1; col++) {
                if(this.isSandwich(row, col, color)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * isGameOver method which returns false if there is no grey squares left on the
     * board.
     */
    public Boolean isGameOver(Color playerColor){
        for (int row = 1; row < Constants.ROW_NUM -1; row++) {
            for (int col = 1; col < Constants.COL_NUM -1; col++) {
                if(this.isSandwich(row, col, playerColor)){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * setGameOver method which takes in a string of text and sets the game over
     * label to that text.
     */
    public void setGameOver(String text){
        this.gameOver = new Label(text);
        this.gameOver.setFont(new Font(40));
        this.boardPane.getChildren().add(this.gameOver);
    }

    /**
     * getColorOnBoard which takes in a row and column and returns the color
     * of a piece on the square.
     */
    public Color getColorOnBoard(int row, int col){
        return this.squareArray[row][col].getColorOnSquare();
    }

    /**
     * getBoardArray which returns the board array as a double array of
     * Squares
     */
    public Square[][] getBoardArray(){
        return this.squareArray;
    }

    /**
     * getWhiteScore which returns the score of the white player
     */
    public int getWhiteScore(){
        return this.white;
    }

    /**
     * getBlackScore which returns the score of the black player
     */
    public int getBlackScore(){
        return this.black;
    }

    /**
     * setScores which take in the white and black scores and sets the score
     * instance variables to the numbers that are passed in.
     */
    public void setScores(int scoreWhite, int scoreBlack){
        this.white = scoreWhite;
        this.black = scoreBlack;
    }
}