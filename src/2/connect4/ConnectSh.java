//z
//*Sanshin_SF의 코드
package connect4;
import java.util.*;
public class ConnectSh {
    static int player, x, y, temp;
    static int[][] arena;

    public static void main(String[] cheese) {
        Scanner in = new Scanner(System.in);
        x = in.nextInt();
        y = in.nextInt();
        arena = new int[y][x];
        
        while (!addDisc(temp)) {
            next();
            display();
            String playerIcon = "●";
            if(player==1){
                playerIcon="○";
            }
            System.out.print("Player "+player+"("+playerIcon+"):");
            temp = in.nextInt();
            while(temp>=x||temp<0){
                System.out.println("빡통?");
                temp = in.nextInt();
            }
            
        }
        System.out.println();
        display();
        System.out.println("PLAYER "+player+" WINS");
        in.close();
    }

    //adds the discs to the arena
    //returns true if more than 4 is connected
    public static boolean addDisc(int currentX){
        int currentY = 0;

        for (int i = 0; i < y; i++) {
            //full
            if(arena[i][currentX]!=0){
                System.out.println("빡ㅌ통?");
                next();
                return false;
            }
            //no discs
            if(i==y-1){
                currentY=y-1;
                arena[currentY][currentX]=player;
                break;
            }
            //add disk above
            if(arena[i+1][currentX]!=0){
                currentY=i;
                arena[currentY][currentX]=player;
                break;
            }
        }
        return scan4(currentY,currentX);
    }

    //switch turns
    public static void next(){
        if(player==1)
            player=2;
        else
            player=1;
        System.out.println();
    }

    //display board
    public static void display(){
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if(arena[i][j]==0){
                    System.out.print('.');
                }else if (arena[i][j]==1){
                    System.out.print('○');
                }else {
                    System.out.print('●');
                }
            }
            System.out.println();
        }
    }

    //finds any 4 lines
    public static boolean scan4(int currentY,int currentX){
        int down=0,lr=0,ldUr=0,luRd=0;

        if(player==0)
            return false;

        //down
        for (int i = currentY+1; i < y; i++) {
            if(arena[i][currentX]==player)
                down++;
            else
                break;
        }

        //left
        for (int i = currentX-1; i > -1; i--) {
            if(arena[currentY][i]==player){
                lr++;
            }else{
                break;
            }
        }

        //right
        for (int i = currentX+1; i < x; i++) {
            if(arena[currentY][i]==player){
                lr++;
            }else{
                break;
            }
        }

        //left-down
        for (int i = 1; i <= Math.min(currentX,y-1-currentY); i++) {
            if(arena[currentY+i][currentX-i]==player)
                ldUr++;
            else
                break;
        }

        //left-up
        for (int i = 1; i <= Math.min(currentX,currentY); i++) {
            if(arena[currentY-i][currentX-i]==player)
                luRd++;
            else
                break;
        }

        //right-down
        for (int i = 1; i <= Math.min(x-1-currentX,y-1-currentY); i++) {
            if(arena[currentY+i][currentX+i]==player)
                luRd++;
            else
                break;
        }

        //right-up
        for (int i = 1; i <= Math.min(x-1-currentX,currentY); i++) {
            if(arena[currentY-i][currentX+i]==player)
                ldUr++;
            else
                break;
        }
        // System.out.println("\nLRCount: "+lr);
        // System.out.println("downCount: "+down);
        // System.out.println("ldUrCount: "+ldUr);
        // System.out.println("luRdCount: "+luRd);

        if((1+lr>=4)||(1+down>=4)||(1+ldUr>=4)||(1+luRd>=4))
            return true;
        return false;
    }
}