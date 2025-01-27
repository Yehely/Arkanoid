//206530552 Moshe Yehely Israel.
import gameEnv.Game;
/**
 * This class is used to actually run the game through its main method.
 * @author Moshe Yehely Israel
 * @since 27-08-2024
 */
public class Ass5Game {
    /**
     * This method creates a new game and then initializes and runs it.
     * @param args not used here.
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
