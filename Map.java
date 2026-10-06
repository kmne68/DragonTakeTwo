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
    private int width;
    private int height;
    private int[][] map;
    private String[] mapString;

    private String[] mapString = {
        "############",
        "#..........#",
        "#..........#",
        "#..........#",
        "#..........#",
        "#....b.....#", // box on the map
        "#..........#",
        "#..........#",
        "#..........#",
        "#..........#",
        "#..........#",
        "############"
    };

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new int[width][height];
        this.mapString = new String[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.map[i][j] = 0;
                this.mapString[i][j] = " ";
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public String[]
}