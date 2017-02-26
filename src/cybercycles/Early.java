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
            if (snakeY != 0 && deltaY < 0 && map[snakeX][snakeY-1]==false) {
                return 'u';
            } else if (snakeX != 0 && map[snakeX - 1][snakeY] == false){
                return 'l';
            } else if (snakeX != map.length-1 && map[snakeX + 1][snakeY]== false){
                return 'r';
            } else {
                return 'd';
            }
        } else {
            if (deltaX < 0 && snakeX != 0 && map[snakeX-1][snakeY]== false){
                return 'l';
            } else if (snakeY != 0 && map[snakeX][snakeY-1]==false){
                return 'u';
            } else if (snakeY != map[0].length - 1 && map[snakeX][snakeY+1]==false) {
                return 'd';
            } else {
                return 'r';
            }
        }
    }
}
