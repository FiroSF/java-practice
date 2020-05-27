package jirwe;
import java.util.*;
//*Sanshin_SF의 코드
public class jirwechatgiSh {
    //the (x,y) values of the minefield
    static int x, y;

    public static void main(String[] sh){
        //input
        Scanner in = new Scanner(System.in);
        int col = in.nextInt();
        int row = in.nextInt();
        String ash = "";
        for (int i = 0; i < row; i++) {
            ash += in.next();
        }

        System.out.println();
        System.out.println(chajara(col,row,ash));
        in.close();
    }

    public static String chajara(int x1, int y1,String in){
        //sets global variables (x,y)
        x=x1;
        y=y1;
        //converts string to array
        String[] input = new String[x*y];
        for (int i = 0; i < input.length; i++) {
            input[i]=in.substring(i,i+1);
        }

        return convert(calculate(input));
    }

    //returns int[] with significant numbers of mapped mines
    public static int[] calculate(String[] in){
        int[] output = new int[in.length];
        //(int)line is added the value of x every y line is calculated
        int line =0;
        for (int i = 0; i < y; i++) {
            //repeat for length of x
            for (int j = 0+line; j < x+line; j++) {
                //If a mine is found
                if (in[j].equals("*")){
                    output[j]=9;
                    //left
                    if (j>=1+(line)) {
                        output[j-1]++;
                        //up
                        if (j-x>=0) {
                            output[j-(x+1)]++;
                        }
                        //down
                        if ((j+x)<(x*y)) {
                            output[j+(x-1)]++;
                        }
                    }
                    //right
                    if (j-(line)<(x-1)) {
                        output[j+1]++;
                        //up
                        if (j-x>=0) {
                            output[j-(x-1)]++;
                        }
                        //down
                        if ((j+x)<(x*y)) {
                            output[j+(x+1)]++;
                        }
                    }

                    //up
                    if (j-x>=0) {
                        output[j-x]++;
                    }
                    //down
                    if ((j+x)<(x*y)) {
                        output[j+x]++;
                    }

                }
            }
            line+=x;
        }
        return output;
    }

    public static String convert(int[] in){
        String temp="",output ="";
        //converts the int[] to a string 
        for (int i = 0; i < in.length; i++) {
            if (in[i]<9){
                temp+=in[i];
            } else{
                temp+="*";
            }
        }

        //adds "\n" on output
        for (int i = x; i <= temp.length(); i+=x) {
            output+=temp.substring(i-x,i)+"\n";
        }
        return output;
    }
}