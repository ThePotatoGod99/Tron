package cybercycles;

/**
 * Created by Guillaume on 2017-02-25.
 */
public class Early {
    public static char calculatePath(boolean[][] map, int iniX, int iniY, int finalX,int finalY){
        int snakeX = iniX;
        int snakeY = iniY;

        int deltaX = finalX - snakeX;
        int deltaY = finalY - snakeY;

        if (Math.abs(deltaX) < Math.abs(deltaY)){
            if (deltaY < 0 && map[snakeX][snakeY-1]==false) {
                return 'd';
            }else if (map[snakeX - 1][snakeY] == false){
                return 'l';
            } else if (map[snakeX + 1][snakeY]== false){
                return 'r';
            } else {
                return 'u';
            }
        } else {
            if (deltaX < 0 && map[snakeX-1][snakeY]== false){
                return 'l';
            } else if (map[snakeX][snakeY-1]==false){
                return 'd';
            } else if (map[snakeX][snakeY+1]==false) {
                return 'u';
            } else {
                return 'r';
            }

        }


    }
}
