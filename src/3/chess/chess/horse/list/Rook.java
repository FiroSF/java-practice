package chess.horse.list;

import chess.Board;
import chess.horse.HorseLineBase;

/**
 * Rook
 */
public class Rook extends HorseLineBase {
    
    public Rook(int x, int y, int team) {
        super(x, y, team);
        this.name = "Rook";

        if (team == 0) {
            this.displayName = "R ";
        } else {
            this.displayName = "r ";
        }
    }

    @Override
    public void genCanMove(Board board) {
        canMove.clear();
        lineExamine(board, 1, 0);
        lineExamine(board, 0, 1);
        lineExamine(board, -1, 0);
        lineExamine(board, 0, -1);
    }
}