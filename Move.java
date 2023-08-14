package othello;

public class Move {
    private int row, col, value;

    /**
     * move constructor which help tracks the value of a square at a given row
     * and column.
     */
    public Move(int row, int col, int value){
        this.row = row;
        this.col = col;
        this.value = value;
    }

    /**
     * getRow which returns the row of a move
     */
    public int getRow() {
        return this.row;
    }
    /**
     * getCol which returns the col of a move
     */
    public int getCol(){
        return this.col;
    }
    /**
     * setValue which sets the value of a square at the value that
     * is passed in.
     */
    public void setValue(int value1){
        this.value = value1;
    }

    /**
     * getValue method which returns the value at a given row and column.
     */
    public int getValue(){
        return this.value;
    }
}
