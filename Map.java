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
    private final char[][] tiles;

    public Map() {
        rows = new String[] {
            "############",
            "#..........#",
            "#..........#",
            "#..........#",
            "#..........#",
            "#....b.....#",
            "#..........#",
            "#..........#",
            "#..........#",
            "#..........#",
            "#..........#",
            "############"
        };

        tiles = new char[rows.length][rows[0].length()];
        for(int i = 0; i < rows[0].length(); i++) {
            tiles[i] = rows[i].toCharArray();
        }
    }

    public void print(int playerX, int playerY) {
        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[y].length; x++) {
                if (x == playerX && y == playerY) {
                    System.out.print("@");
                } 
                else {
                    System.out.print(tiles[y][x]);
                }
            }
            System.out.println();

        }
        if (tileAt(playerX, playerY) == 'b')
        {
            System.out.println("It's a box!");
        }
    }

    public char tileAt(int x, int y) {
        return tiles[y][x];
    }

    public void setTile(int x, int y, char value) {
        tiles[x][y] = value;
    }
}