package project.chess.horse.list;

import project.chess.Board;
import project.chess.horse.HorseLineBase;

/**
 * Queen
 */
public class Queen extends HorseLineBase {
    
    public Queen(int x, int y, int team) {
        super(x, y, team);
        this.name = "Queen";

        if (team == 0) {
            this.displayName = "Q ";
        } else {
            this.displayName = "q ";
        }
    }

    @Override
    public void genCanMove(Board board) {
        canMove.clear();
        lineExamine(board, 1, 0);
        lineExamine(board, 0, 1);
        lineExamine(board, -1, 0);
        lineExamine(board, 0, -1);
        lineExamine(board, 1, 1);
        lineExamine(board, -1, 1);
        lineExamine(board, -1, -1);
        lineExamine(board, 1, -1);
    }
}