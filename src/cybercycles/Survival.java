package cybercycles;

import com.sun.xml.internal.bind.v2.TODO;

/**
 * Created by Guillaume on 2017-02-25.
 */
public class Survival {
    public Survival(){
        
    }
    
    /*
    1: right
    2: left
    3: top
    4: bot
     */
    public static int[] otherMoveIsPossible(int x, int y, int numberOfSteps, boolean[][] map){//Has wall next to it; has other possible move
        int[][] possibilities = {
                {1, 0},
                {0, 1},
                {-1, 0},
                {0, -1},
                {1, 1},
                {-1, 1},
                {-1, -1},
                {1, -1}
        };
        int originalX = x, originalY = y;
        int[] result = {0, 0};
        for(int j = 0; j < 8; j++){//test all squares surounding future pos
            x = originalX + possibilities[j][0];
            y = originalY + possibilities[j][1];
            
            try{
                if(map[x][y]){
                    result[0] += 1;
                }
                else if(numberOfSteps < 1){
                    //System.out.println(numberOfSteps);
                    result[1] += otherMoveIsPossible(x, y, numberOfSteps + 1, map)[1];
                    //  System.out.println(result[1] + " :ASDF");
                }
                else{
                    result[1] += 1;
                }
                
            }
            catch(ArrayIndexOutOfBoundsException e){
                
            }
        }
        
        return result;
        
    }
    
    public static char calculatePath(boolean[][] map, int posX, int posY){
        int direction = 0;
        int futureX = posX, futureY = posY;
        
        int bestMove = 0;
        for(int i = 1; i <= 4; i++){//Test chaque direction possible
            futureX = posX;
            futureY = posY;
            //Calculate if move is possible
            //  int i = 2;
            switch(i){
                //get future position
                case 1:
                    futureX++;
                    break;
                
                case 2:
                    futureX--;
                    break;
                case 3:
                    futureY--;
                    break;
                case 4:
                    futureY++;
                    break;
            }
            
            
            //System.out.println(" ASDF " + futureX + " : " + futureY);
            if(futureX >= map.length || futureX < 0 || futureY >= map[futureX].length || futureY < 0 || map[futureX][futureY]){//Move is impossible
                // System.out.println("Move impossible " + i);
                if(direction == 0){
                    direction = -1;
                }
                continue;
            }
            
            
            //calculate if there will be a wall next to future position
            int x = futureX, y = futureY;
            int[] testForOtherMoves = otherMoveIsPossible(x, y, 0, map);
            //Calculate if you will be able to move after this move
            //   System.out.println(i + " : " + testForOtherMoves[0] + " : " + testForOtherMoves[1] + " : " + bestMove);
            if(testForOtherMoves[0] >= bestMove && testForOtherMoves[1] >= 5){
           //     System.out.println(i + " i ");
                direction = i;//Convertiir to char
                bestMove = testForOtherMoves[0];
            }
        }
        return Contourner.convert(direction);
    }
    
    public static int numberOfFreeTile(boolean[][] map, int x, int y){
        return 0;
    }
    
    public static void main(String[] args){
//        TODO remove main
        
        
        int posX = 2, posY = 1;
        boolean[][] map = new boolean[6][];
        boolean[] line;
        for(int x = 0; x < 6; x++){
            
            line = new boolean[6];
            for(int y = 0; y < 6; y++){
                line[y] = false;
                if(x == 0 || x == 5 || y == 0 || y == 5){
                    line[y] = true;
                }
            }
            map[x] = line;
        }
        
        line = new boolean[6];
        for(int a = 0; a <= 3; a++){
            line[a] = true;
        }
        map[3] = line;
        
        boolean condition = true;
        while(condition){
            int direction = 0;
            
            imprimerMap(map, posX, posY);
             System.out.println("\n");
            direction = calculatePath(map, posX, posY);
            map[posX][posY] = true;
            switch(direction){
                case 1:
                    posX++;
                    break;
                
                case 2:
                    posX--;
                    break;
                case 3:
                    posY--;
                    break;
                case 4:
                    posY++;
                    break;
                case -1:
                    condition = false;
                    break;
            }
            
        }
        
        
    }
    
    
    public static void imprimerMap(boolean[][] map, int x, int y){
        for(int j = 0; j < map[0].length; j++){
            System.out.println();
            for(int i = 0; i < map.length; i++){
                boolean flag = false;
              /*  for (int z = 0; z < snakes.length; z++) {
                    if (snakes[z].getX() == i && snakes[z].getY() == j) {
                        System.out.print(snakes[z].getTEAM().charAt(0));
                        flag = true;
                    }
                }*/
                
                if(!flag){
                    if(map[i][j]){
                        System.out.print("X");
                    }
                    else if(i == x && j == y){
                        System.out.print("A");
                    }
                    else{
                        System.out.print("O");
                    }
                }
            }
        }
    }
}

