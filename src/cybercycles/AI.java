package cybercycles;

import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AI {
    //Allo
    /* Configuration */
    public final String ROOM = "teamRocketdfgsdfgsd";
    public String TEAM = "1";

    /* Déplacement de l'A.I. */
    public final char[] directions = {'u', 'l', 'd', 'r'};
    public char direction;

    //Informations de la map
    public int selfIndice = 0;
    public int[] allyIndice = new int[0];
    public int[] enemyIndice = new int[0];
    public boolean[][] map;
    public SnakeObject snakes[];

    Random random = new Random();

    public AI(int i) {
        if (i == 0) {

        } else if (i <= 1) {
            TEAM = 1 + "";
        } else {
            TEAM = 2 + "";
        }
        System.out.println(TEAM);
    }

    public void start(JSONObject config) throws JSONException {
        //Initialisation de la map
        map = new boolean[config.getInt("w")][config.getInt("h")];

        int w, x, h, y;
        for (int i = 0; i < config.getJSONArray("obstacles").length(); i++) {
            w = config.getJSONArray("obstacles").getJSONObject(i).getInt("w") - 1;
            x = config.getJSONArray("obstacles").getJSONObject(i).getInt("x");
            h = config.getJSONArray("obstacles").getJSONObject(i).getInt("h") - 1;
            y = config.getJSONArray("obstacles").getJSONObject(i).getInt("y");

            for (int tw = w; tw >= 0; tw--) {
                for (int th = h; th >= 0; th--) {
                    if (x + tw >= 0 && y + th >= 0 && x + tw < map.length && y + th < map[0].length) {
                        map[x + tw][y + th] = true;
                    }
                }
            }
        }

        //Initialisation des snakes
        snakes = new SnakeObject[config.getJSONArray("players").length()];
        for (int i = 0; i < snakes.length; i++) {
            int temp = Integer.valueOf(config.getJSONArray("players").getJSONObject(i).getString("id")) - 1;
            snakes[temp] = new SnakeObject(config.getJSONArray("players").getJSONObject(i).getInt("x"), config.getJSONArray("players").getJSONObject(i).getInt("y"),
                    config.getJSONArray("players").getJSONObject(i).getString("id"), config.getJSONArray("players").getJSONObject(i).getString("team"));
        }

        for (int i = 0; i < snakes.length; i++) {
            if (snakes[i].getID().equals(config.getString("me"))) {
                this.selfIndice = i;
                break;
            }
        }

        for (int i = 0; i < snakes.length; i++) {
            if (snakes[i].getID().equals(snakes[selfIndice].getID())) {
                continue;
            } else if (snakes[i].getTEAM().equals(snakes[selfIndice].getTEAM())) {
                int[] temp = new int[allyIndice.length + 1];

                for (int j = 0; j < allyIndice.length; j++) {
                    temp[j] = allyIndice[j];
                }

                temp[allyIndice.length] = i;

                allyIndice = temp;
            } else {
                int[] temp = new int[enemyIndice.length + 1];

                for (int j = 0; j < enemyIndice.length; j++) {
                    temp[j] = enemyIndice[j];
                }

                temp[enemyIndice.length] = i;

                enemyIndice = temp;
            }
        }

        for (int i = 0; i < snakes.length; i++) {
            map[snakes[i].getX()][snakes[i].getY()] = true;
        }
    }

    public char next(JSONArray prevMoves) throws JSONException {
        direction = 'z';

        //Update la map et la position des snakes
        for (int i = 0; i < prevMoves.length(); i++) {
            int index = Integer.valueOf(prevMoves.getJSONObject(i).getString("id"));

            switch (prevMoves.getJSONObject(i).getString("direction").charAt(0)) {
                case 'u':
                    snakes[index - 1].setY(snakes[index - 1].getY() - 1);
                    break;
                case 'l':
                    snakes[index - 1].setX(snakes[index - 1].getX() - 1);
                    break;
                case 'd':
                    snakes[index - 1].setY(snakes[index - 1].getY() + 1);
                    break;
                case 'r':
                    snakes[index - 1].setX(snakes[index - 1].getX() + 1);
                    break;
            }

            if (!snakes[i].isDead() && (snakes[i].getX() < 0 || snakes[i].getX() > map.length || snakes[i].getY() < 0 || snakes[i].getY() > map.length || map[snakes[i].getX()][snakes[i].getY()])) {
                snakes[i].setDead();
            }

            if (snakes[index - 1].getX() >= 0 && snakes[index - 1].getX() < map.length && snakes[index - 1].getY() >= 0 && snakes[index - 1].getY() < map[0].length) {
                map[snakes[index - 1].getX()][snakes[index - 1].getY()] = true;
            }
        }

        //Calcul pour voir si on est toujours dans le mid game

        if (!snakes[enemyIndice[0]].isDead() && (isCloser(enemyIndice[0]) || snakes[enemyIndice[1]].isDead())) {
            direction = Contourner.calculatePath(map, snakes[selfIndice].getX(), snakes[selfIndice].getY(), snakes[enemyIndice[0]].getX(), snakes[enemyIndice[0]].getY());
        } else if(!snakes[enemyIndice[1]].isDead()){
            direction = Contourner.calculatePath(map, snakes[selfIndice].getX(), snakes[selfIndice].getY(), snakes[enemyIndice[1]].getX(), snakes[enemyIndice[1]].getY());
        }

        //Late game
        if (direction == 'z') {
            for (int i = 0; i < allyIndice.length; i++) {
                if (!snakes[i].isDead()) {
                    if (Contourner.calculatePath(map, snakes[selfIndice].getX(), snakes[selfIndice].getY(), snakes[allyIndice[i]].getX(), snakes[allyIndice[i]].getY()) == 'z') {
                        direction = (char) Survival.calculatePath(map, snakes[selfIndice].getX(), snakes[selfIndice].getY());
                    } else {
                        direction = (char) Survival.calculatePath(map, snakes[selfIndice].getX(), snakes[selfIndice].getY());
                    }
                }
            }
        } else {
            //Mid game
            if (!snakes[enemyIndice[0]].isDead() && (isCloser(enemyIndice[0]) || snakes[enemyIndice[1]].isDead())) {
                if(snakes[enemyIndice[0]].getX() == snakes[selfIndice].getX() || snakes[enemyIndice[0]].getY() == snakes[selfIndice].getY()){
                    if(snakes[selfIndice].getX() - snakes[enemyIndice[0]].getX() == 2){
                        direction = 'l';
                    } else if(snakes[selfIndice].getX() - snakes[enemyIndice[0]].getX() == -2){
                        direction = 'r';
                    } else if(snakes[selfIndice].getY() - snakes[enemyIndice[0]].getY() == 2){
                        direction = 'u';
                    } else if(snakes[selfIndice].getY() - snakes[enemyIndice[0]].getY() == -2){
                        direction = 'd';
                    }
                }
            } else if(!snakes[enemyIndice[1]].isDead()){
                if(snakes[enemyIndice[1]].getX() == snakes[selfIndice].getX() || snakes[enemyIndice[1]].getY() == snakes[selfIndice].getY()){
                    if(snakes[selfIndice].getX() - snakes[enemyIndice[1]].getX() == 2){
                        direction = 'l';
                    } else if(snakes[selfIndice].getX() - snakes[enemyIndice[1]].getX() == -2){
                        direction = 'r';
                    } else if(snakes[selfIndice].getY() - snakes[enemyIndice[1]].getY() == 2){
                        direction = 'u';
                    } else if(snakes[selfIndice].getY() - snakes[enemyIndice[1]].getY() == -2){
                        direction = 'd';
                    }
                }
            }
        }

        imprimerMap();
        

        return direction;
    }

    /**
     * Fonction appelée en fin de partie.
     *
     * @param winnerID ID de l'équipe gagnante
     */
    public void end(String winnerID) {
        System.out.println("Équipe gagnante : " + winnerID);
    }

    public boolean[][] getMap() {
        return map;
    }

    public void imprimerMap() {
        for (int j = 0; j < map[0].length; j++) {
            System.out.println();
            for (int i = 0; i < map.length; i++) {
                boolean flag = false;
                for (int z = 0; z < snakes.length; z++) {
                    if (snakes[z].getX() == i && snakes[z].getY() == j) {
                        System.out.print(snakes[z].getTEAM().charAt(0));
                        flag = true;
                    }
                }

                if (!flag) {
                    if (map[i][j]) {
                        System.out.print("X");
                    } else {
                        System.out.print("O");
                    }
                }
            }
        }
    }

    private boolean isCloser(int enemyIndice){
        double distance1 = Math.sqrt((snakes[selfIndice].getX() - snakes[enemyIndice].getX()) * (snakes[selfIndice].getX() - snakes[enemyIndice].getX()) + (snakes[selfIndice].getY() - snakes[enemyIndice].getY()) * (snakes[selfIndice].getY() - snakes[enemyIndice].getY()));
        double distance2 = Math.sqrt((snakes[selfIndice].getX() - snakes[enemyIndice].getX()) * (snakes[selfIndice].getX() - snakes[enemyIndice].getX()) + (snakes[selfIndice].getY() - snakes[enemyIndice].getY()) * (snakes[selfIndice].getY() - snakes[enemyIndice].getY()));

        return (distance1 >= distance2);
    }
}
