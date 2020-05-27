package jirwe;
import java.util.*;
//*초기 코드입니다. Jirwe2 파일이 최신 파일이에요!
class Jirwe{
    public static void main(String[] s){
        Scanner in = new Scanner(System.in);
        int col = in.nextInt();
        int row = in.nextInt();
        String map[] = new String[col];

        //map input
        for (int i = 0; i < row; i++) {
            map[i] = in.next() + " ";
        }

        //makes map
        String realMap[] = makeMap(map, col, row);

        //output
        for (int i = 0; i < row; i++) {
            System.out.println(realMap[i]);
        }

        in.close();
    }

    public static String[] makeMap(String map1[], int col1, int row1) {
        String newMap[] = new String[col1];
        for (int i = 0; i < row1; i++) {
            String line = "";

            for (int j = 0; j < col1; j++) {
                //get numbers
                line += findJirwe(map1, j, i, col1, row1);
            }

            //append line
            newMap[i] = line;
        }

        return newMap;
    }

    public static String findJirwe(String map2[], int x, int y, int col2, int row2) {
        //variable
        int jirweCount = 0;
        if (map2[y].substring(x, x + 1).equals("*")){return "*";}


        //line1
        if (y - 1 >= 0) {
            for (int i = x - 1; i <= x + 1; i++) {
    
                //check index, find Jirwe
                if ((0 <= i && i <= col2 &&map2[y - 1].substring(i, i + 1).equals("*"))) {
                    jirweCount++;
                }
            }
        }

        //line2
        for (int i = x - 1; i <= x + 1; i++) {
            //check index, find Jirwe
            if ((0 <= i && i <= col2 && map2[y].substring(i, i + 1).equals("*"))) {
                jirweCount++;
            }
        }

        //line3
        if (y + 1 < row2) {
            for (int i = x - 1; i <= x + 1; i++) {
    
                //check index, find Jirwe
                if ((0 <= i && i <= col2 && map2[y + 1].substring(i, i + 1).equals("*"))) {
                    jirweCount++;
                }
            }
        }

        return "" + jirweCount;
    }
}