package cybercycles;

import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AI {
    //Allo
    /* Configuration */
    public final String ROOM = "rocket";
    public final String TEAM = "1";

    /* Déplacement de l'A.I. */
    public final char[] directions = {'u', 'l', 'd', 'r'};
    public char direction;

    //Informations de la map
    public final int SELF = 0, ALLY = 1, ENEMY1 = 2, ENEMY2 = 3;
    public final int X = 0, Y = 1;
    public boolean[][] map;
    public int snakePosition[][] = new int[4][2];

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

        //Initialisation des snakes
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
