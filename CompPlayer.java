package othello;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class CompPlayer implements Player {
    private Referee compRef;
    private Board board;
    private int playerMode;
    private Color playerColor, opponentColor;
    private Move move;

    /**
     * compPlayer constructor which takes in a color, board, and intelligence mode
     */
    public CompPlayer(Color color, Board board, int mode) {
        this.playerMode = mode;
        this.board = board;
        this.playerColor = color;
    }

    /**
     * turn method for compPlayer, which takes a move from the miniMax, and adds a
     * piece to the board based on the values that it received from the method.
     * It will then flip the pieces and the comp ref will call turn over much
     * like the human player.
     */
    @Override
    public void turn() {
        this.move = this.miniMax(this.board, this.playerColor, this.playerMode);
        if(this.board.isSandwich(this.move.getRow(), this.move.getCol(), this.playerColor)) {
            this.board.addPiece(this.move.getRow(), this.move.getCol(), this.playerColor);
            this.board.flipPieces(this.move.getRow(), this.move.getCol(), this.playerColor);
            this.board.removeValidSquares();
            this.compRef.turnOver();
        }else
            this.compRef.turnOver();
    }

    /**
     * setRef method which takes in a referee, and sets the compRef to the referee that
     * gets passed in.
     */
    @Override
    public void setRef(Referee referee) {
        this.compRef = referee;
    }

    /**
     * getPlayerColor which returns the color of the player
     */
    @Override
    public Color getPlayerColor() {
        return this.playerColor;
    }

    /**
     * getOpponentColor which checks if the player color is either white or black,
     * and sets the opponent's color to the opposite color and returns it.
     */
    public Color getOpponentColor() {
        if (this.playerColor == Color.WHITE) {
            this.opponentColor = Color.BLACK;
        } else if (this.playerColor == Color.BLACK) {
            this.opponentColor = Color.WHITE;
        }
        return this.opponentColor;
    }

    /**
     * evalBoard which takes in a color and board, and iterates through the
     * entire board for a given player color, and returns how likely
     * that player will win based on the current conditions, as
     * represented by the sum - sub value.
     */
    public int evalBoard(Color color, Board board) {
        int sum = 0;
        int sub = 0;
        for (int row = 1; row < 9; row++) {
            for (int col = 1; col < 9; col++) {
                if (!board.isEmpty(row, col)) {
                    if (board.getColorOnBoard(row, col) == color) {
                        sum += Constants.SQUARE_VALUES[row][col];
                    } else if (board.getColorOnBoard(row, col) != color) {
                        sub += Constants.SQUARE_VALUES[row][col];
                    }
                }
            }
        }
        return sum - sub;
    }

    /**
     * Given the current board state (locations/numbers of each player's pieces), the
     * computer creates every move it could possibly make. For each of these potential
     * plays, the computer player tries it out on a temporary board to see what moves the
     * opponent could make from there. The number of iterations in this recursive process
     * is limited by the level of intellect of the computer player. The computer player
     * decides which final board state is preferable by examining the final board states that
     * result from each of its hypothetical moves. The move that corresponds to that board
     * state is made by the computer player.
     */
    public Move miniMax(Board board, Color color, int intel) {
        if (board.isFull()) {
            if (board.doWin(color)) {
                return new Move(0, 0, 100000);
            } else if (!board.doWin(color)) {
                return new Move(1, 0, -100000);
            } else if (board.isTie()) {
                return new Move(2, 0, 0);
            }
        }

        ArrayList<Square> validSquares = this.board.getArrayList(color);

        if (validSquares.size() == 0) {
            if (intel == 1) {
                return new Move(3, 0, -100000);
            } else {
                Move oppoMove;
                oppoMove = miniMax(board, this.getOpponentColor(), intel - 1);
                oppoMove.setValue((-1) * oppoMove.getValue());
                return oppoMove;
            }
        } else {
            Move bestMove = new Move(4, 0, -100000);
            for (Square square : validSquares) {
                Board tempBoard = new Board(board);
                tempBoard.addPiece(square.getRow(), square.getCol(), color);
                Move currentMove = new Move(square.getRow(), square.getCol(), -100000);

                if (intel == 1) {
                    currentMove.setValue(this.evalBoard(color, tempBoard));
                } else {
                    currentMove.setValue((-1) * this.miniMax(tempBoard, this.getOpponentColor(), intel - 1).getValue());
                }

                if (bestMove.getValue() <= currentMove.getValue()) {
                    bestMove = currentMove;
                }
            }
            return bestMove;
        }
    }

}
