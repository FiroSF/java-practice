package chess.horse;

import java.util.ArrayList;

import chess.Board;

/**
 * Horse
 */
public abstract class HorseBase {

    protected int currentX;
    protected int currentY;
    public int moveCount = 0;

    /**
     * represents team
     * <p>0 = black, 1 = white
     * <p>0 = upper, 1 = lower
     */
    protected int team;

    /**
     * represents name
     * <p>Rook, ...
     */
    protected String name;

    /**
     * represents display name
     * <p>Rook -> R, r
     */
    protected String displayName;

    /**
     * represents position that this can move to
     */
    protected ArrayList<int[]> canMove = new ArrayList<int[]>();

    /**
     * makes canMove
     * @param board current board
     */
    public abstract void genCanMove(Board board);

    /**
     * Horse constructor
     * @param x
     * @param y
     * @param team 0 = upper, 1 = lower
     */
    public HorseBase(int x, int y, int team) {
        setXY(x, y);
        this.team = team;
    }

    /**
     * 
     * @param x to move
     * @param y to move
     * @return can move -> T, can't move -> F
     */
    public boolean canMove(int x, int y) {
        int[] xy = new int[]{x, y};

        if (isContainsXY(canMove, xy)) {
            return true;
        }
        
        return false;
    }


    public ArrayList<int[]> getCanMove() {
        return this.canMove;
    }

    public int getX() {
        return currentX;
    }

    public int getY() {
        return currentY;
    }

    /**
     * get name
     * <p>Rook, ...
     */
    public String getName() {
        return this.name;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    /**
     * 0 = upper, 1 = lower
     */
    public int getTeam() {
        return this.team;
    }

    
    /**
     * set x, y
     * @param x
     * @param y
     */
    public void setXY(int x, int y) {
        this.currentX = x;
        this.currentY = y;
    }


    /**
     * 
     * @param a list
     * @param b xy
     * @return if contains, T if doesn't contain, F
     */
    public boolean isContainsXY(ArrayList<int[]> a, int[] b) {
        for (int[] aa : a) {
            if (aa[0] == b[0] && aa[1] == b[1]) {
                return true;
            }
        }

        return false;
    }

    /**
     * check can move there
     * @param board
     * @param x
     * @param y
     * @return 2 = True and there is enemy's horse, 1 = True, -1 = out of range, -2 = there is our team horse
     */
    public int canMoveThere(Board board, int x, int y) {
        if (!(x >= 0 && x < board.getBoard().length && y >= 0 && y < board.getBoard()[x].length)) {
            return -1;
        }
        HorseBase target = board.getBoard()[x][y];
        
        if (target == null || target.getTeam() != this.team) {

            if (target == null) {
                return 1;
            }

            return 2;
        }

        return -2;
    }

    /**
     * add canMove
     * @param board
     * @param x
     * @param y
     */
    public void cellExamine(Board board, int x, int y) {
        int result = canMoveThere(board, x, y);

        if (!board.getCheckmateWarning() && result == -3) {
            result = 1;
        }

        if (result > 0) {
            canMove.add(new int[]{x, y});
        }
    }
}