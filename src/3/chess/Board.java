package project.chess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import project.chess.horse.HorseBase;
import project.chess.horse.list.*;

/**
 * Board
 */
@SuppressWarnings("unchecked")  //remove Type safety: Unchecked cast ... warning
public class Board{
    
    Scanner in = new Scanner(System.in);

    private boolean checkmateWarning = true;

    /**
     * represents current board
     */
    private HorseBase[][] horses = new HorseBase[8][8];

    private int count = 1;

    /**
     * 0 = upper, 1 = lower
     */
    private int whos = 0;

    /**
     * upper, lower
     */
    private String displayWhos;

    /**
     * 0 = upper, 1 = lower
     */
    private int win = -1;

    private ArrayList<HashMap<String, Object>> players = new ArrayList<HashMap<String, Object>>();
    
    public Board() {
        for (int i = 0; i < 2; i++) {
            players.add(new HashMap<String, Object>());
            players.get(i).put("got", new ArrayList<String>());

            System.out.printf("\nPlayer %d's name: ", i + 1);
            players.get(i).put("name", in.next());
        }

        for (int i = 0; i < horses.length; i++) {
            for (int j = 0; j < horses[i].length; j++) {
                this.horses[i][j] = horses[i][j];
            }
        }

        //pawn
        for (int i = 0; i < horses.length; i++) {
            assignHorse(i, 1, 0, 5);
            assignHorse(i, 6, 1, 5);
        }

        //king
        assignHorse(4, 0, 0, 0);
        assignHorse(4, 7, 1, 0);

        //queen
        assignHorse(3, 0, 0, 1);
        assignHorse(3, 7, 1, 1);

        //rook
        assignHorse(0, 0, 0, 2);
        assignHorse(0, 7, 1, 2);
        assignHorse(7, 0, 0, 2);
        assignHorse(7, 7, 1, 2);

        //bishop
        assignHorse(2, 0, 0, 3);
        assignHorse(2, 7, 1, 3);
        assignHorse(5, 0, 0, 3);
        assignHorse(5, 7, 1, 3);

        //knight
        assignHorse(1, 0, 0, 4);
        assignHorse(1, 7, 1, 4);
        assignHorse(6, 0, 0, 4);
        assignHorse(6, 7, 1, 4);
    }

    public Board(HorseBase[][] horses) {
        this.horses = horses;
    }


    /**
     * checkmate warning.
     * @return True = warning on
     */
    public boolean getCheckmateWarning() {
        return checkmateWarning;
    }
    
    /**
     * get board
     * @return HorseBase[][]
     */
    public HorseBase[][] getBoard() {
        return horses;
    }


    /**
     * set whos and displayWhos, no inputs. just calculate
     * 0 = upper, 1 = lower
     */
    public void setWhos() {
        this.whos = (count - 1) % 2;
        String name = (String) players.get(whos).get("name");

        switch (whos) {
            case 0:
                displayWhos = name + "(upper)";
                break;
            case 1:
                displayWhos = name + "(lower)";
                break;
            default:
                break;
        }
    }


    
    /**
     * assign horse
     * @param x
     * @param y
     * @param team 0 = upper, 1 = lower
     * @param horse 0 = King, 1 = Queen, 2 = Rook, 3 = Bishop, 4 = Night, 5 = Pawn
     */
    public void assignHorse(int x, int y, int team, int horse) {
        switch (horse) {
            case 0:
                horses[x][y] = new King(x, y, team);
                break;
            case 1:
                horses[x][y] = new Queen(x, y, team);
                break;
            case 2:
                horses[x][y] = new Rook(x, y, team);
                break;
            case 3:
                horses[x][y] = new Bishop(x, y, team);
                break;
            case 4:
                horses[x][y] = new Knight(x, y, team);
                break;
            case 5:
                horses[x][y] = new Pawn(x, y, team);
                break;
            default:
                break;
        }
    }

    /**
     * represents checkmate
     * @param team team to check
     * @return True = checkmate
     */
    public boolean isCheck(int team) {
        int[] king = {};
        ArrayList<int[]> canMoveList = new ArrayList<int[]>();

        //examine cells
        for (int i = 0; i < horses.length; i++) {
            for (int j = 0; j < horses[i].length; j++) {
                HorseBase c = horses[i][j];

                //add canMove
                if (c instanceof HorseBase && c.getTeam() != team) {
                    c.genCanMove(this);

                    for (int[] ks : c.getCanMove()) {
                        canMoveList.add(ks);
                    }

                //find king
                } else if (c instanceof King && c.getTeam() == team) {
                    int[] temp = {c.getX(), c.getY()};
                    king = temp;
                }
            }
        }

        for (int[] i : canMoveList) {
            if (i[0] == king[0] && i[1] == king[1]) {
                return true;
            }
        }

        return false;
    }

    /**
     * move
     * @param selected selected horse
     * @param xMove destination
     * @param yMove destination
     */
    public void move(HorseBase selected, int xMove, int yMove) {
        HorseBase target = horses[xMove][yMove];
        int x = selected.getX();
        int y = selected.getY();
        
        if (target != null) {
            ((ArrayList<String>) (players.get(whos).get("got"))).add(target.getName()); //holy
            System.out.printf("\n%s got enemy's %s!\n", displayWhos, target.getName());

            //if caught the king
            if (target instanceof King) {
                win = selected.getTeam();
            }
        }
        
        selected.moveCount++;
        horses[xMove][yMove] = selected;
        selected.setXY(xMove, yMove);
        horses[x][y] = null;

        if (win > -1) {
            return;
        }

        //pawn revive
        if (selected.getY() == 7 - 7 * selected.getTeam() && selected instanceof Pawn) {
            System.out.print("Pawn can be change into another horse! please select horse to change into");
            System.out.print("\n1 = Queen, 2 = Rook, 3 = Bishop, 4 = Night, 5 = Pawn: ");

            boolean pass = false;

            while (!pass) {
                int horseToChange = in.nextInt();

                if (horseToChange > 5 || horseToChange < 1) {
                    System.out.print("What did you wrote... please select again!: ");
                } else {
                    revive(selected, horseToChange);
                    pass = true;
                }
            }
        }
    }

    /**
     * revive method for pawn
     * @param selected pawn
     * @param horseToChange
     */
    private void revive(HorseBase selected, int horseToChange) {
        assignHorse(selected.getX(), selected.getY(), selected.getTeam(), horseToChange);
    }

    /**
     * print current board
     */
    public void printBoard() {
        System.out.println();
        for (int i = horses.length - 1; i >= 0; i--) {
            System.out.print(i + " ");

            for (int j = 0; j < horses[i].length; j++) {
                HorseBase currenthorse = horses[j][i];

                if (currenthorse == null) {
                    if ((i + j) % 2 == 1) {
                        System.out.print("■ ");
                    } else {
                        System.out.print("□ ");
                    }
                } else {
                    System.out.print(currenthorse.getDisplayName());
                }
            }

            System.out.println();
        }

        System.out.print("  ");

        for (int i = 0; i < horses.length; i++) {
            System.out.print(i + " ");
        }
    }



    /**
     * run chess
     */
    public void run() {
        while (win == -1) {
            turn();
        }

        printBoard();
        System.out.printf("\n\nFinally %d, %s has won the game!", count, displayWhos);
    }

    /**
     * Proceed.
     */
    public void turn() {
        setWhos();
        printBoard();
        if (isCheck(whos)) {
            System.out.printf("\n\nCheck!");
        }

        System.out.printf("\n\n%d, %s's turn! please select horse to move: ", count, displayWhos);

        int x = -1;
        int y = -1;
        HorseBase selected = null;

        boolean pass = false;
        while (!pass) {
            x = in.nextInt();
            y = in.nextInt();
    
            if (horses[x][y] == null) {
                System.out.print("There is no horse. please select again!: ");
            } else if (horses[x][y].getTeam() != whos) {
                System.out.print("That horse is enemy's. please select again!: ");
            } else {
                selected = horses[x][y];
                selected.genCanMove(this);

                if (selected.getCanMove().size() == 0) {
                    System.out.print("This horse can't move! please select again!: ");
                    //! Test requires
                } else {
                    pass = true;
                }
            }
        }

        System.out.printf("You've selected (%d, %d), %s! please select the position to move(-1 is reselect): ", selected.getX(), selected.getY(), selected.getName());
        
        pass = false;
        while (!pass) {
            int xMove = in.nextInt();
            if (xMove == -1) {
                return;
            }
            int yMove = in.nextInt();

            if (selected.canMove(xMove, yMove)) {
                move(selected, xMove, yMove);
                pass = true;
            } else {
                System.out.print("This horse can't go to there! please select the position again!: ");
            }
        }

        if (win > -1) {
            return;
        }

        count++;
    }
}