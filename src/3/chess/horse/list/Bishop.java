package project.chess.horse.list;

import project.chess.Board;
import project.chess.horse.HorseLineBase;

/**
 * Bishop
 */
public class Bishop extends HorseLineBase {
    
    public Bishop(int x, int y, int team) {
        super(x, y, team);
        this.name = "Bishop";

        if (team == 0) {
            this.displayName = "B ";
        } else {
            this.displayName = "b ";
        }
    }

    @Override
    public void genCanMove(Board board) {
        canMove.clear();
        lineExamine(board, 1, 1);
        lineExamine(board, -1, 1);
        lineExamine(board, -1, -1);
        lineExamine(board, 1, -1);
    }
}