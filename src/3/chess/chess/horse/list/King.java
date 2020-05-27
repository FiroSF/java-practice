package chess.horse.list;

import chess.Board;
import chess.horse.HorseBase;

/**
 * King
 */
public class King extends HorseBase {
    
    public King(int x, int y, int team) {
        super(x, y, team);
        this.name = "King";

        if (team == 0) {
            this.displayName = "K ";
        } else {
            this.displayName = "k ";
        }
    }

    @Override
    public void genCanMove(Board board) {
        canMove.clear();

        int[][] positions = {{1, 1}, {1, 0}, {1, -1}, {0, 1}, {0, -1}, {-1, 1}, {-1, 0}, {-1, -1}};
        for (int[] i : positions) {
            cellExamine(board, i[0] + currentX, i[1] + currentY);
        }
    }
}