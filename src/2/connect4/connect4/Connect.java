//아진짜 무섭네
//이걸 할수가있냐
//*Sanshin_SF의 코드, 최신 버전은 ConnectSh입니다!
package connect4;
import java.util.*;
public class Connect {
    static int player, x, y, temp;
    static int[][] arena;

    public static void main(String[] cheese) {
        Scanner in = new Scanner(System.in);
        int lenX = in.nextInt();
        int lenY = in.nextInt();
        x = lenX;
        y = lenY;
        arena = new int[lenY][lenX];
        
        while (!search4(temp)) {
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
        display();
        System.out.println("PLAYER "+player+" WINS");
        in.close();
    }

    //adds the discs to the arena
    //returns true if more than 4 is connected
    public static boolean search4(int currentX){
        int currentY = 0;

        for (int i = 0; i < y; i++) {
            //full
            if(arena[i][currentX]!=0){
                System.out.println("빡ㅌ통?");
                next();
                break;
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
                //System.out.print(arena[i][j]);
            }
            System.out.println();
        }
    }

    //finds any 4 lines
    public static boolean scan4(int currentY,int currentX){
        int down=0, l=0,lD=0,lU=0, r=0,rU=0,rD=0;
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
        for (int i = currentX-1; i >= 0; i--) {
            if(i<=0){
                break;
            }else if(arena[currentY][i]==player){
                l++;
            }else{
                break;
            }
        }
        //System.out.println("\nLeftCount: "+l);

        //right
        for (int i = currentX+1; i <= x; i++) {
            if(i>=x){
                break;
            }else if(arena[currentY][i]==player){
                r++;
            }else{
                break;
            }
        }
        //System.out.println("\nRightCount: "+r);

        //left-down temp value shibal
        int tempLDV, tempLUV, tempRDV, tempRUV;
        //left-down
        if(currentX>=y-1-currentY)
            tempLDV=y-1-currentY;
        else
            tempLDV=currentX;
        for (int i = 1; i <= tempLDV; i++) {
            if(arena[currentY+i][currentX-i]==player)
                lD++;
            else
                break;
        }

        //left-up
        if(currentX>=currentY)
            tempLUV=currentY;
        else
            tempLUV=currentX;
        for (int i = 1; i <= tempLUV; i++) {
            if(arena[currentY-i][currentX-i]==player)
                lU++;
            else
                break;
        }

        //right-down
        if(x-1-currentX>=y-1-currentY)
            tempRDV=y-1-currentY;
        else
            tempRDV=x-1-currentX;
        for (int i = 1; i <= tempRDV; i++) {
            if(arena[currentY+i][currentX+i]==player)
                rD++;
            else
                break;
        }

        //right-up
        if(x-1-currentX>=currentY)
            tempRUV=currentY;
        else
            tempRUV=x-1-currentX;
        for (int i = 1; i <= tempRUV; i++) {
            if(arena[currentY-i][currentX+i]==player)
                rU++;
            else
                break;
        }

        
        //System.out.println("\nlDCount: "+lD);
        //System.out.println("\nlUCount: "+lU);

        if((1+l+r>=4)||(1+down>=4)||(1+lD+rU>=4)||(1+lU+rD>=4))
            return true;
        return false;
    }

}
//2hard
//tlqkf dashi he ya ji