package cybercycles;

/**
 * Created by Guillaume on 2017-02-25.
 */
public class Early {

    public static boolean calculatePath(boolean[][] map, int inix, int iniy) {
        //8 if de 2 if
        if (iniy == 0 || map[inix][iniy - 1] == true) {
            if ((inix == 0 || iniy == 0 || map[inix - 1][iniy - 1] == true) && (inix == map.length - 1 || iniy == 0 || map[inix + 1][iniy - 1] == true)) {
                return true;
            }
        } else if (iniy == 0 || inix == map.length - 1 || map[inix + 1][iniy - 1] == true) {
            if ((iniy == 0 || map[inix][iniy - 1] == true) && (inix == map.length - 1 || map[inix + 1][iniy] == true)) {
                return true;
            }
        } else if (inix == map.length - 1 || map[inix + 1][iniy] == true) {
            if ((inix == map.length - 1 || iniy == 0 || map[inix + 1][iniy - 1] == true) && (inix == map.length - 1 || iniy == map[0].length - 1 || map[inix + 1][iniy + 1] == true)) {
                return true;
            }
        } else if (inix == map.length - 1 || iniy == map[0].length - 1 || map[inix + 1][iniy + 1] == true) {
            if ((inix == map.length - 1 || map[inix + 1][iniy] == true) && (iniy == map[0].length - 1 || map[inix][iniy + 1] == true)) {
                return true;
            }
        } else if (iniy == map[0].length - 1 || map[inix][iniy + 1] == true) {
            if ((inix == map.length - 1 || iniy == map[0].length - 1 || map[inix + 1][iniy + 1] == true) && (inix == 0 || iniy == map[0].length - 1 || map[inix - 1][iniy + 1] == true)) {
                return true;
            }
        } else if (inix == 0 || iniy == map[0].length - 1 || map[inix - 1][iniy + 1] == true) {
            if ((iniy == map[0].length - 1 || map[inix][iniy + 1] == true) && (inix == 0 || map[inix - 1][iniy] == true)) {
                return true;
            }
        } else if (inix == 0 || map[inix - 1][iniy] == true) {
            if ((inix == 0 || iniy == map[0].length - 1 || map[inix - 1][iniy + 1] == true) && (inix == 0 || iniy == 0 || map[inix - 1][iniy - 1] == true)) {
                return true;
            }
        } else if (inix == 0 || iniy == 0 || map[inix - 1][iniy - 1] == true) {
            if ((inix == 0 || map[inix - 1][iniy] == true) && (iniy == 0 || map[inix][iniy - 1] == true)) {
                return true;
            }
        }
        return false;

    }
}
