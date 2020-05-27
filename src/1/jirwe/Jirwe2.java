package jirwe;
import java.util.*;
//* It's minesweeper!... Umm... First, you can input col and row. like 3 3! and, you can input the map. '.' is blank, '*' is mine. after this, you can get the map with numbers!
class Jirwe2{
    static int sizeX, sizeY;
    public static void main(String[] s){
        Scanner in = new Scanner(System.in);
        int col = in.nextInt();
        int row = in.nextInt();
        String map[][] = new String[col][row];

        //map input
        for (int i = 0; i < row; i++) {
            String input = in.next();
            String temp[] = new String[col];
            for (int j = 0; j < col; j++) {
                temp[j] = input.substring(j, j+1);
            }
            map[i] = temp;
        }

        //makes map
        String realMap[][] = makeMap(map, col, row);

        //output
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(realMap[i][j]);
            }
            System.out.println();
        }
        in.close();
    }

    public static String[][] makeMap(String map1[][], int col1, int row1) {
        //global variables
        sizeX = col1;
        sizeY = row1;

        //we'll make map
        String newMap[][] = new String[sizeY][sizeX];

        //generate cordi
        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                //get number
                newMap[i][j] = findJirwe(map1, j, i);
            }
        }

        //output
        return newMap;
    }

    public static String findJirwe(String map2[][], int x, int y) {
        //variable
        int jirweCount = 0;

        if (map2[y][x].equals("*")){return "*";}

        //generate cordi
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                //find Jirwe
                jirweCount += isJirwe(map2, i, j);
            }
        }
        return "" + jirweCount;
    }
    
    public static int isJirwe(String map3[][], int x, int y) {
        //is there Jirwe??
        if ((0 > x || x > sizeX - 1) || (0 > y || y > sizeY - 1)) {
            return 0;
        } else if (map3[y][x].equals("*")) {
            return 1;
        } else {
            return 0;
        }
    }
}