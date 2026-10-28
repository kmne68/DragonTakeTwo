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
    private char[][] tiles;
    private int width;
    private int height;
    private int monsterX;
    private int monsterY;
    private int boxX;
    private int boxY;

    public Map() {
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
        width = random.nextInt(10, 20);
        height = random.nextInt(10, 20);
        tiles = new char[height][width];
        System.out.println("Width: " + width + ", Height: " + height + ", Tiles.length: " + tiles.length);

        // Fill the map
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                tiles[y][x] = '#';
            }
        }

        // Carve the room
        for(int y = 1; y < height - 1; y++) {
            for(int x = 1; x < width - 1; x++) {
                tiles[y][x] = '.';
            }
        }        

        int[] monster = placeOnFloor(3, 3, random); // 3, 3 are the initial player coordinates
        monsterX = monster[0];
        monsterY = monster[1];
        tiles[monster[1]][monster[0]] = 'm';
        int[] box = placeOnFloor(monsterX, monsterY, random);
        boxX = box[0];
        boxY = box[1];
        tiles[box[1]][box[0]] = 'b';
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

    public int[] placeOnFloor(int avoidX, int avoidY, Random random) {
        int x, y;
        do {
            x = random.nextInt(1, width - 1);
            y = random.nextInt(1, height - 1);
        } while ((x == avoidX && y == avoidY) || tiles[y][x] != '.');
        return new int[] { x, y };
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