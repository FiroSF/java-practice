package project.chess.horse.list;

import project.chess.Board;
import project.chess.horse.HorseBase;

/**
 * Pawn
 */

public class Pawn extends HorseBase {

    public Pawn(int x, int y, int team) {
        super(x, y, team);
        this.name = "Pawn";

        if (team == 0) {
            this.displayName = "P ";
        } else {
            this.displayName = "p ";
        }
    }

    @Override
    public void genCanMove(Board b) {
        canMove.clear();

        int temp;

        if (this.team == 0) {
            temp = 1;
        } else {
            temp = -1;
        }

        //front
        if (canMoveThere(b, currentX, currentY + temp) == 1) {
            canMove.add(new int[] { currentX, currentY + temp });

            //front 2
            if (moveCount == 0 && canMoveThere(b, currentX, currentY + temp) == 1) {
                canMove.add(new int[] { currentX, currentY + temp * 2 });
            }
        }

        //right
        if (canMoveThere(b, currentX + 1, currentY + temp) == 2) {
            canMove.add(new int[] { currentX + 1, currentY + temp });
        }

        //left
        if (canMoveThere(b, currentX - 1, currentY + temp) == 2) {
            canMove.add(new int[] { currentX - 1, currentY + temp });
        }



        // if (board[currentX][currentY + temp] == null) {
        //     // go front
        //     canMove.add(new int[] { currentX, currentY + temp });
        //     if (moveCount == 0 && board[currentX][currentY + temp * 2] == null) {
        //         canMove.add(new int[] { currentX, currentY + temp * 2 });
        //     }
        // }

        // if (currentX != 7 && board[currentX + 1][currentY + temp] != null
        //         && board[currentX + 1][currentY + temp].getTeam() != this.team) {
        //     // right
        //     canMove.add(new int[] { currentX + 1, currentY + temp });
        // }

        // if (currentX != 0 && board[currentX - 1][currentY + temp] != null
        //         && board[currentX - 1][currentY + temp].getTeam() != this.team) {
        //     // left
        //     canMove.add(new int[] { currentX - 1, currentY + temp });
        // }
    }
}
