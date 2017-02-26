package cybercycles;

/**
 * Created by Guillaume on 2017-02-25.
 */
public class Contourner{
    
    
    public Contourner(){
        
    }
    
    /*
    1: right
    2: left
    3: top
    4: bot
     */
    public static boolean otherMoveIsPossible(int x, int y, boolean[][] map){//Has wall next to it; has other possible move
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
        boolean result = false;
        
        int numberWall = 0;
        int[][] possibleMove = new int[5][];
        for(int j = 0; j < 8; j++){//test all squares surounding future pos
            x = originalX + possibilities[j][0];
            y = originalY + possibilities[j][1];
            
            try{
                if(map[x][y]){//Is a wall next to x y
                    result = true;
                    numberWall++;
                }
            }
            catch(ArrayIndexOutOfBoundsException e){
            }
        }
        if(numberWall == 8){
            result = false;
        }
        
        if(originalX == 7 && originalY == 6){
              //  System.out.println(" AS DFASD FASDF A DSFFASD FA ADS FASD FASDF ASD FADSF " + result + " : " + numberWall + " m" + map[8][6]);
        }
        return result;
        
    }
    
    
    public static double distance(int posX, int posY, int desX, int desY){
        return Math.sqrt(Math.pow(desX - posX, 2) + Math.pow(desY - posY, 2));
    }
    
    public static int calculateFutureMove(boolean[][] map, int posX, int posY, int desX, int desY, int stepNumber, int oldX, int oldY, double oldDistance){
        //imprimerMap(map, posX, posY);
        // System.out.println("X: " + posX + " y: " + posY);
        System.out.println("FutureMove1 " + stepNumber + " " + posX + " : " + posY);
        
        int direction = 0;
        
        int futureX = posX, futureY = posY;
        
        int[] moveReward = new int[4];
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
            
            double distance = distance(futureX, futureY, desX, desY);
            if(oldDistance == 0){
                oldDistance = distance;
            }
            
            if(futureX == 7 && futureY == 6){
                System.out.print(" ALLO " );
                 System.out.print("DIST " + distance + " : " +oldDistance + " : " + otherMoveIsPossible(futureX, futureY, map));
                 System.out.println("futureMove2 " + stepNumber + " " + posX + " : " + posY + " : " + futureX + " " + futureY + map[futureX][futureY]);
            }
           // System.out.println("futureMove2 " + stepNumber + " " + posX + " : " + posY + " : " + futureX + " " + futureY + map[futureX][futureY]);
           // System.out.println("DIST " + distance + " : " +oldDistance + " : " + otherMoveIsPossible(futureX, futureY, map));
            if(futureX == desX && futureY == desY){
                
                stepNumber++;
                return stepNumber;
                //break;
            }
            else if((futureX == oldX && futureY == oldY) || futureX >= map.length || futureX < 0 || futureY >= map[futureX].length || futureY < 0 || map[futureX][futureY]){//Move is impossible
                
                //if move possible
                
                
                // System.out.println("Move impossible " + i);
                if(direction == 0){
                    direction = -1;
                }
                continue;
            }
            else if(otherMoveIsPossible(futureX, futureY, map) && distance < oldDistance){
                
                boolean[][] hola = map.clone();
                stepNumber = calculateFutureMove(hola, futureX, futureY, desX, desY, stepNumber + 1, posX, posY, distance);
                break;
            }
        }
        System.out.println("futureMove3 " + stepNumber + " " + posX + " : " + posY);
        return stepNumber;
        
        
    }
    
    public static char calculatePath(boolean[][] map, int posX, int posY, int desX, int desY){
        if(posX == desX && posY == desY){
            return 'z';
        }
        long startTime = System.currentTimeMillis();
        int direction = 0;
            
        int futureX = posX, futureY = posY;
        
        int[] moveReward = new int[4];
        int bestMove = 0;
        
        double bestDistance = 0;
        for(int i = 1; i <= 4; i++){//Test chaque direction possible   4
            int stepNumber = 0;
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
            if(futureX == desX && futureY == desY){
               // System.out.println(" X " + futureX + " : " + futureY + " ALLO");//"ASDF " + stepsNumber);
                direction = i;
                return convert(direction);
            }
            if(futureX >= map.length || futureX < 0 || futureY >= map[futureX].length || futureY < 0 || map[futureX][futureY]){//Move is impossible
                //if move possible
                
                
                // System.out.println("Move impossible " + i);
                if(direction == 0){
                    direction = -1;
                }
                continue;
            }
            
            
            //calculate if there will be a wall next to future position
            int x = futureX, y = futureY;
            
            
          //  System.out.println(" X " + x + " : " + y + " : " + futureX + futureY);//"ASDF " + stepsNumber);
            boolean testForOtherMoves = otherMoveIsPossible(x, y, map);
            if(testForOtherMoves){
                
                boolean[][] hola = map.clone();
                //stepNumber = calculateFutureMove(hola, x, y, desX, desY, 0, x, y, distance(x, y, desX, desY));
                
                
              //  if(stepNumber >= bestMove){
                
                double distance = distance(x, y, desX, desY);
              //  System.out.println(" ASDF A DSFASD FSD FAD FADS FADS FASD ASD " + bestDistance + " : " + distance + " : " + x + y);
                if(distance <= bestDistance || bestDistance == 0){
                    bestDistance = distance;
                    bestMove += stepNumber;
                    direction = i;
                }
                //toRemove
            }
            //Calculate if you will be able to move after this move
            //   System.out.println(i + " : " + testForOtherMoves[0] + " : " + testForOtherMoves[1] + " : " + bestMove);
        //    direction = i;
            
        }
       
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        //System.out.println(totalTime + " ASDF ADGF AD FADS F " + direction);
        
        

        return convert(direction);
    }

    public static int convertToInt(char direction){
        int result = direction;
        switch(result){
            case 'r':
                result = 1;
                break;
            case 'l':
                result = 2;
                break;
            case 'u':
                result = 3;
                break;
            case 'd':
                result = 4;
                break;
        }
        return result;
    }
    
    public static char convert(int direction){
        switch(direction){
            case 1:
                direction = 'r';
                break;
            case 2:
                direction = 'l';
                break;
            case 3:
                direction = 'u';
                break;
            case 4:
                direction = 'd';
                break;
        }
        return (char)direction;
    }
    public static int numberOfFreeTile(boolean[][] map, int x, int y){
        return 0;
    }
    
    public static void main(String[] args){
//        TODO remove main
        
        
        int posX = 2, posY = 8;
        boolean[][] map = new boolean[10][];
        boolean[] line;
        for(int x = 0; x < 10; x++){
            
            line = new boolean[10];
            for(int y = 0; y < 10; y++){
                line[y] = false;
                if(x == 0 || x == 9 || y == 0 || y == 9){
                    line[y] = true;
                }
                else if(x == y && x < 7){
                    line[y] = true;
                }
            }
            map[x] = line;
        }
        
        //line = new boolean[6];
        //  for(int a = 0; a <= 3; a++){
        ////     line[a] = true;
        //  }
        // map[3] = line;
        
        boolean condition = true;
        while(condition){
            int direction = 0;
            
            imprimerMap(map, posX, posY);
            // System.out.println("\n");
            direction = calculatePath(map, posX, posY, 8, 1);
            System.out.println(direction);
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
                    else if(i == 8 && j == 1){
                        System.out.print("Z");
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

