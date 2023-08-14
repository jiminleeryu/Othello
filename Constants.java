package othello;

import javafx.scene.paint.Color;

public class Constants {

    public static final int WHITE = 0;
    public static final int BLACK = 1;
    public static final int SQUARE_WIDTH = 50;
    public static final int SCENE_WIDTH = 775;
    public static final int SCENE_HEIGHT = 700;
    public static final int COL_NUM = 10;
    public static final int ROW_NUM = 10;
    public static final int PIECE_OFFSET = 25;
    public static final int[][] SQUARE_VALUES =
                    {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 200, -70, 30, 25, 25, 30, -70, 200, 0},
                    {0, -70, -100, -10, -10, -10, -10, -100, -70, 0},
                    {0, 30, -10,   2,  2,  2,  2, -10, 30, 0},
                    {0, 25, -10,   2,  2,  2,  2, -10, 25, 0},
                    {0, 25, -10,   2,  2,  2,  2, -10, 25, 0},
                    {0, 30, -10,   2,  2,  2,  2, -10, 30, 0},
                    {0, -70, -100, -10, -10, -10, -10, -100, -70, 0},
                    {0, 200, -70, 30, 25, 25, 30, -70, 200, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
    public static final double DURATION = 0.01;
    public static final int CONTROLS_PANE_WIDTH = 250;
    public static final Color VALID_SQUARE_COLOR = Color.GREY;
    public static final Color BORDER_COLOR = Color.DARKRED;
    public static final Color BOARD_COLOR = Color.DARKGREEN;
}
