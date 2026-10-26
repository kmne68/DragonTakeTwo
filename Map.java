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
import java.util.Random;

public class Map {
    private final String[] rows;
    private final char[][] tiles;
    private int monsterX;
    private int monsterY;

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

    /**
     * Fill every cell with #.
     * Carve a floor rectangle inside the border (the current 10×10 of .).
     * Place one b on a floor cell that is not the player start (3, 3).
     * Place one m on a different floor cell.
     * Use new Random(seed) so the same seed always places the box and monster in the same spots.
     */
    public void generate(long seed) {
        Random random = new Random(seed);

        int width = random.nextInt(10, 20);
        int height = random.nextInt(10, 20);
        monsterX = random.nextInt(11, 19);
        monsterY = random.nextInt(11, 19);

        for(int x = 0; x <= width; x++) {
            for(int y = 0; y <= height; y++) {
                System.out.println("#");
            }
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
        tiles[y][x] = value;
    }

    public int getMonsterX() {
        return this.monsterX;
    }

    public int getMonsterY() {
        return this.monsterY;
    }

}