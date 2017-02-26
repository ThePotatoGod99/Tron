package cybercycles;

/**
 * Created by Guillaume on 2017-02-25.
 */
public class Early {
    public static boolean calculatePath(boolean[][] map, int inix, int iniy){
        //8 if de 2 if
        if (map[inix][iniy-1]== true){
            if (map[inix-1][iniy-1]==true&&map[inix+1][iniy-1]==true){
                return true;
            }
        } else if (map[inix+1][iniy-1]== true){
            if (map[inix][iniy-1]== true && map[inix+1][iniy]== true) {
                return true;
            }
        } else if (map[inix+1][iniy]== true ){
            if (map[inix+1][iniy-1]== true && map[inix+1][iniy+1]== true){
                return true;
            }
        } else if (map[inix+1][iniy+1]== true) {
            if (map[inix][iniy]== true && map[inix][iniy+1]== true) {
                return true;
            }
        } else if (map[inix][iniy+1]== true){
            if (map[inix+1][iniy+1]== true && map[inix-1][iniy+1]== true){
                return true;
            }
        } else if (map[inix-1][iniy+1]== true){
            if (map[inix][iniy+1]== true && map[inix-1][iniy]== true){
                return true;
            }
        } else if (map[inix-1][iniy]== true){
            if (map[inix-1][iniy+1]== true && map[inix-1][iniy-1]== true){
                return true;
            }
        } else if (map[inix-1][iniy-1]== true) {
            if (map[inix - 1][iniy] == true && map[inix][iniy - 1] == true) {
                return true;
            }
        }
            return false;

    }
}
