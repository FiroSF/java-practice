package connect4;
import java.util.*;
/**
 * Connect4
 */
public class Connect4 {
    //maps[x][y]
    static int maps[][];
    //true = black, false = white
    static boolean whos = true;
    static String whosStr = "Black";
    //limit
    static int xlim;
    static int ylim;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        //make map
        xlim = in.nextInt();
        ylim = in.nextInt();
        maps = new int[xlim][ylim];

        //first input request
        printMap();
        System.out.printf("%s's turn! : ", whosStr);

        while(!turn(in.nextInt())) {
            //after run 'turn'
            if (whos) {
                whosStr = "White";
            } else {
                whosStr = "Black";
            }
            whos = !whos;

            //input request
            System.out.printf("%s's turn! : ", whosStr);
        }

        System.out.printf("%s has won the game!", whosStr);

        in.close();
    }

    public static boolean turn(int pos) {
        //it returns win or not

        if (pos >= xlim || pos <= -1) {
            printMap();
            System.out.printf("There is no index like %d...", pos);
            System.out.print("\n\n");
            whos = !whos;
            return false;
        }
        
        for (int i = 0; i < ylim; i++) {
            if (maps[pos][i] == 0) {
                //display what happened
                System.out.printf("%s -> %d", whosStr, pos);

                //pos
                if (whos) {
                    maps[pos][i] = 1;
                } else {
                    maps[pos][i] = -1;
                }

                printMap();
                return check(pos, i);
            }
        }
        //if indexout
        printMap();
        System.out.printf("Look, %d is already FULL!", pos);
        System.out.print("\n\n");
        whos = !whos;
        return false;
    }

    public static void printMap() {
        //"1○...●○.○○●..."
        for (int j = ylim - 1; j >= 0; j--) {
            //When the number is over 9, it cant be displayed so print the value of %10.
            System.out.print("\n" + j % 10);

            for (int j2 = 0; j2 < xlim; j2++) {
                if (maps[j2][j] == 0) {
                    //blank
                    System.out.print(".");

                } else if (maps[j2][j] == 1) {
                    //black
                    System.out.print("○");

                } else if (maps[j2][j] == -1) {
                    //white
                    System.out.print("●");
                }
            }
        }

        //" 12345678..."
        System.out.print("\n ");
        for (int j = 0; j < xlim; j++) {
            //When the number is over 9, it cant be displayed so print the value of %10.
            System.out.print(j % 10);
        }
        System.out.print("\n\n");
    }

    public static boolean check(int x, int y) {
        //it returns win or not

        int stack = 0;
        
        for (int i = 0; i < 4; i++) {
            for (int j = -3; j < 4; j++) {
                //0 - up down / 1 - left right / 2 - \ / 3 - /
                if ((i == 0 && detect(x, y - j)) || (i == 1 && detect(x - j, y)) || (i == 2 && detect(x - j, y - j)) || (i == 3 && detect(x + j, y - j))) {
                    stack++;
                } else {
                    stack = 0;
                }

                //win
                if (stack >= 4) {
                    return true;
                }
            }
            stack = 0;
        }
        return false;
    }

    public static boolean detect(int x, int y) {
        //if indexout
        if (x < 0 || x >= xlim || y < 0 || y >= ylim) {
            return false;
        }

        //detect
        if ((whos && maps[x][y] == 1) || (!whos && maps[x][y] == -1)) {
            return true;
        } else {
            return false;
        }
    }
}