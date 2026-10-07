/**
 * Represents a map in the game.
 * 
 * @author John Doe
 * @version 1.0
 * @since 1.0
 * @see GameLoop
 * @see Main
 * @see Game
 * @see GameState
 * @see GameStateManager
 * @see GameStateManager
 */

public class Map {
    private final String[] rows;

    public Map() {
        rows = new String[] {
            "############",
            "#..........#",
            "#..........#",
            "#..........#",
            "#..........#",
            "#..........#",
            "#..........#",
            "#..........#",
            "#..........#",
            "#..........#",
            "#..........#",
            "############"
        };
    }

    public void print(int playerX, int playerY) {
    for (int y = 0; y < rows.length; y++) {
        for (int x = 0; x < rows[y].length(); x++) {
            if (x == playerX && y == playerY) {
                System.out.print("@");
            } else {
                System.out.print(rows[y].charAt(x));
            }
        }
        System.out.println();
    }
}
}