package project.chess.horse.list;

import project.chess.Board;
import project.chess.horse.HorseBase;

/**
 * Knight
 */
public class Knight extends HorseBase {
    
    public Knight(int x, int y, int team) {
        super(x, y, team);
        this.name = "Knight";

        if (team == 0) {
            this.displayName = "N ";
        } else {
            this.displayName = "n ";
        }
    }

    @Override
    public void genCanMove(Board board) {
        canMove.clear();

        int[][] positions = {{1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};
        for (int[] i : positions) {
            cellExamine(board, i[0] + currentX, i[1] + currentY);
        }
    }
}