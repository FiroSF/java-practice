package jirwe;
import java.util.*;
//*Sanshin_SF의 코드
//hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh
public class jirechatgi {
    static int x, y;

    public static void main(String[] sh){
        //input bangshick jottgatne;;
        //stole input from firo
        Scanner in = new Scanner(System.in);
        int col = in.nextInt();
        int row = in.nextInt();
        String ash = "";

        //map input
        for (int i = 0; i < row; i++) {
            ash += in.next();
        }

        System.out.println(chajara(col,row,ash));
        in.close(); //added by Firo_SF
    }

    public static String chajara(int x1, int y1,String in){
        //sets global variables
        x=x1;
        y=y1;
        //changes string to array
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
                    if (j>=x-(x-1)) {
                        output[j-1]++;
                    }
                    //right
                    if (j-(line)<(x-1)) {
                        output[j+1]++;
                    }

                    //up
                    if (j-x>=0) {
                        output[j-x]++;
                    }
                    //down
                    if ((j+x)<=(x*y)) {
                        output[j+x]++;
                    }

                    //left-up
                    if ((j>=x-(x-1))&&(j-x>=0)){
                        output[j-(x+1)]++;
                    }
                    //right-up
                    if ((j-(line)<(x-1))&&(j-x>=0)){
                        output[j-(x-1)]++;
                    }

                    //left-down
                    if ((j>=x-(x-1))&&(j+x<=x*y)){
                        output[j+(x-1)]++;
                    }
                    //right-down
                    if ((j-(line)<(x-1))&&(j+x<=x*y)){
                        output[j+(x+1)]++;
                    }

                }
            }
            line+=x;
        }
        return output;
    }

    public static String convert(int[] in){
        String output ="";
        //converts the int[] to a string 
        for (int i = 0; i < in.length; i++) {
            if (in[i]<9){
                output+=in[i];
            } else{
                output+="*";
            }
        }
        return output;
    }

}