package cybercycles;

/**
 * Created by Guillaume on 2017-02-25.
 */
public class Early {
    public static char calculatePath(int finalX,int finalY){
        int snakeX = snake[selfIndice].getX();
        int snakeY = snake[selfIndice].getY();

        int deltaX = finalX - snakeX;
        int deltaY = finalY - snakeY;

        if (Math.abs(deltaX) < Math.abs(deltaY)){
            if (deltaY < 0&& getMap()[snakeX][snakeY-1]==false) {
                return 'd';
            }else if (getMap()[snakeX - 1][snakeY] == false){
                return 'l';
            } else if (getMap()[snakeX + 1][snakeY]== false){
                return 'r';
            } else {
                return 'u';
            }
        } else {
            if (deltaX < 0 && getMap()[snakeX-1][snakeY]== false){
                return 'l';
            } else if (getMap()[snakeX][snakeY-1]==false){
                return 'd';
            } else if (getMap()[snakeX][snakeY+1]==false) {
                return 'u';
            } else {
                return 'r';
            }

        }


    }
}
