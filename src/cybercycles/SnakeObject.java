package cybercycles;

public class SnakeObject {
    private int x, y;
    private final String ID, TEAM;

    public SnakeObject(int x, int y, String ID, String TEAM){
        setX(x);
        setY(y);
        this.ID = ID;
        this.TEAM = TEAM;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getID(){ return ID; }

    public String getTEAM(){ return TEAM; }

    public String toString(){
        return "{x: " + x + "; y: " + y + "; ID: " + ID + "; TEAM: " + TEAM + " }";
    }
}
