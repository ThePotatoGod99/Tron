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
    
    public void calculatePath(int xInit, int yInit,){
        int
        
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
