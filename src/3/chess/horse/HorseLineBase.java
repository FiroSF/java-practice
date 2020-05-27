package project.chess.horse;

import project.chess.Board;

/**
 * HorseLineBase
 */
public abstract class HorseLineBase extends HorseBase {

    public HorseLineBase(int x, int y, int team) {
        super(x, y, team);
    }

    /**
     * add canMove with examining lines
     * @param b board
     * @param xv represents direction
     * @param yv represents direction
     */
    public void lineExamine(Board b, int xv, int yv) {
        int x = currentX + xv;
        int y = currentY + yv;

        while (true) { //x >= 0 && x < board.length && y >= 0 && y < board[x].length
            int result = canMoveThere(b, x, y);

            if (result == 1) {
                canMove.add(new int[]{x, y});
            } else if (result == 2) {
                canMove.add(new int[]{x, y});
                break;
            } else {
                break;
            }
            
            x += xv;
            y += yv;
        }
    }
}