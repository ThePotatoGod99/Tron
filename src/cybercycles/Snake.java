package cybercycles;

/**
 * Created by simon on 25/02/17.
 */
public class Snake{
    
    
    private Point destination;
    
    private Point position;
    
    private int step = 0;
    
    private char[] path;
    /*
    1: right
    2: left
    3: top
    4: bot
     */
    
    public Snake(){
        destination = new Point(0, 0);
        position = new Point(0, 0);
    }
    public boolean calculatePath(int xInit, int yInit, int xFinal, int yFinal, boolean[][] jeu){
    
        int w = jeu.length;
        int h = jeu[0].length;
        boolean possible = false;
        
        if (xInit >= w || xInit < 0 || yInit  >= h || yInit < 0){
            possible = false;
        } else {
            if(xInit == xFinal && yInit == yFinal){
                possible = true;
            } else {
                if(jeu[xInit][yInit] == true){
                    possible = false;
                }
                else{
                    jeu[xInit][yInit] = true;
                    
                    if (calculatePath(xInit,yInit + 1,xFinal,yFinal, jeu) || calculatePath(xInit,yInit - 1,xFinal,yFinal, jeu) || calculatePath(xInit + 1,yInit,xFinal,yFinal, jeu) || calculatePath(xInit,yInit + 1,xFinal,yFinal, jeu)) {
                        possible = true;
                    }
                }
            }
//
        }
        return possible;
    }
    
    public char getNextMove(){
        return path[step];
    }
    
    public void setPosition(int x, int y){
        position.set(x, y);
    }
    
    public void setDestination(int x, int y){
        destination.set(x, y);
    }
    
    public class Point{
        public int x, y;
        
        public Point(int x, int y){
            this.set(x, y);
        }
        
        public void set(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
