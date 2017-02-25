package cybercycles;

import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AI {
    //Allo
    /* Configuration */
    public final String ROOM = "rocketA";
    public final String TEAM = "1";

    /* Déplacement de l'A.I. */
    public final char[] directions = {'u', 'l', 'd', 'r'};
    public char direction;

    //Informations de la map
    public int selfIndice;
    public int[] allyIndice;
    public int[] enemyIndice;
    public boolean[][] map;
    public SnakeObject snakes[];

    Random random = new Random();

    /**
     * Fonction appelée en début de partie.
     *
     * @param config Configuration de la grille de jeu
     * @throws org.json.JSONException
     */
    public void start(JSONObject config) throws JSONException {
        System.out.println("Joueurs : " + config.getJSONArray("players"));

        System.out.println("Obstacles : " + config.getJSONArray("obstacles"));

        System.out.print("Taille de la grille : ");
        System.out.println(config.getInt("w") + " x " + config.getInt("h"));

        System.out.println("Votre identifiant : " + config.getString("me"));

        //Initialisation de la map
        map = new boolean[config.getInt("w")][config.getInt("h")];

        int w, x, h, y;
        for(int i = 0; i < config.getJSONArray("obstacles").length(); i++){
            w = config.getJSONArray("obstacles").getJSONObject(i).getInt("w");
            x = config.getJSONArray("obstacles").getJSONObject(i).getInt("x");
            h = config.getJSONArray("obstacles").getJSONObject(i).getInt("h");
            y = config.getJSONArray("obstacles").getJSONObject(i).getInt("y");

            for(; w >= 0; w--) {
                for(; h >= 0; h--){
                    map[x + w][y + h] = true;
                }
            }
        }

        //Initialisation des snakes
        snakes = new SnakeObject[config.getJSONArray("players").length()];
        for(int i = 0; i < snakes.length; i++){
            snakes[i] = new SnakeObject(config.getJSONArray("players").getJSONObject(i).getInt("x"), config.getJSONArray("players").getJSONObject(i).getInt("y"),
                    config.getJSONArray("players").getJSONObject(i).getString("id"), config.getJSONArray("players").getJSONObject(i).getString("team"));
        }

        for(int i = 0; i < snakes.length; i++){
            if(snakes[i].getID().equals(config.getJSONObject("me"))){
                this.selfIndice = i;
            }
        }

        for(int i = 0; i < snakes.length; i++){
            if(snakes[i].getID() == snakes[selfIndice].getID()){
                continue;
            } else if(snakes[i].getTEAM() == snakes[selfIndice].getTEAM()){
                int[] temp = new int[allyIndice.length + 1];

                for(int j = 0; j < allyIndice.length; j++){
                    temp[j] = allyIndice[j];
                }

                temp[allyIndice.length] = i;

                allyIndice = temp;
            } else {
                int[] temp = new int[enemyID.length + 1];

                for(int j = 0; j < enemyID.length; j++){
                    temp[j] = enemyID[j];
                }

                temp[enemyID.length] = i;

                enemyID = temp;
            }
        }
    }

    /**
     * Fonction appelée à chaque tour de jeu.
     *
     * @param prevMoves Mouvements précédents des joueurs
     * @return Mouvement à effectuer
     * @throws org.json.JSONException
     */
    public char next(JSONArray prevMoves) throws JSONException {
        System.out.print("Mouvements précédents : ");

        for (int i = 0; i < prevMoves.length(); i++) {
            JSONObject prevMove = prevMoves.getJSONObject(i);
            System.out.print(prevMove + " ");
        }

        System.out.print("\n");

        // Choisis une direction au hasard
        direction = directions[random.nextInt(directions.length)];
        System.out.println("Mouvement choisi : " + direction);

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
}
