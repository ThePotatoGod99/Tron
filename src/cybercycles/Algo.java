package cybercycles;

/**
 * Created by Guillaume on 2017-02-25.
 */
public class Algo{
    
    boolean[][] map;
    int x = 0, y = 0;
    
    
    char[] moves;
    int step = 0;
    int movesLenght = 3;
    
    public Algo(boolean[][] map, int x, int y){
        this.map = map;
        this.x = x;
        this.y = y;
        
        moves = new char[10];
        calculatePath(0, x, y);
    }
    
    /*
    1: right
    2: left
    3: top
    4: bot
     */
    
    public int calculatePath(int numberOfSteps, int x, int y){
        System.out.println("ASDF");
        int numberPossibleMove = 0;
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
        for(char j = 0; j < 8; j++){//test all squares surounding future pos
            x = originalX + possibilities[j][0];
            y = originalY + possibilities[j][1];
            
            try{
                if(map[x][y]){//If there is a wall
                }
                else if(numberOfSteps < 3){
                    moves[numberOfSteps] = j;
                    numberPossibleMove += calculatePath(numberOfSteps + 1, x, y);
                }
            }
            catch(ArrayIndexOutOfBoundsException e){
                
            }
        }
        return numberPossibleMove;
        
    }
    
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
        for(int j = 1; j <= 4; j++){//test all squares surounding future pos
    
            switch(j){
                //get future position
                case 1:
                    x++;
                    break;
        
                case 2:
                    x--;
                    break;
                case 3:
                    y--;
                    break;
                case 4:
                    y++;
                    break;
            }
//            x = originalX + possibilities[j][0];
//            y = originalY + possibilities[j][1];
            
            try{
                if(map[x][y]){//If there is a wall
                    result[0] += 1;
                }
                else if(numberOfSteps < 1){
                    result[1] += otherMoveIsPossible(x, y, numberOfSteps + 1, map)[1];
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
    
    public static int calculatePath(boolean[][] map, int posX, int posY){
        Algo algo = new Algo(map, posX, posY);
        
        /*
        int direction = 0;
        int snakeX = posX;
        int snakeY = posY;
        
        int futureX = snakeX, futureY = snakeY;
        
        int[] moveReward = new int[4];
        int bestMove = 0; //Walls
        int bestMove2 = 0; //
        for(int i = 1; i <= 4; i++){//Test chaque direction possible
            futureX = snakeX;
            futureY = snakeY;
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
            
            
            if(futureX >= map.length || futureX < 0 || futureY >= map[futureX].length || futureY < 0 || map[futureX][futureY]){//Move is impossible
                if(direction == 0){
                    direction = -1;
                }
                continue;
            }
            
            
            //calculate if there will be a wall next to future position
            int x = futureX, y = futureY;
            int[] testForOtherMoves = otherMoveIsPossible(x, y, 0, map);
            if(testForOtherMoves[1] >= 5){
           //     System.out.println(i + " i ");
                direction = i;//Convertiir to char
                bestMove = testForOtherMoves[0];
                bestMove2 = testForOtherMoves[1];
            }
        }*/
        
        
        return 0;//direction;
    }
    
    public char getNextMove(){
        char direction = 0;
        
        if(step < moves.length){
            direction = moves[step];
        }
        
        step++;
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
        System.out.println(direction);
        
        
        return direction;
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

