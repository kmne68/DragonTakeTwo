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
    private Tile [][] tiles;
    private int canvasWidth;
    private int canvasLength;
    private int minWidth, minLength, minHeight;
    private int maxWidth, maxLength, maxHeight;
    private int width;
    private int length;
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
        boolean special = random.nextInt(100) < 10;
        canvasWidth = 100;        
        canvasLength = 100;
        if(special) {
            minWidth = 25; maxWidth = 50;
            minLength = 25; maxLength = 50;
            minHeight = 15; maxHeight = 40;
            System.out.println("Special room: large, vaulted, or other.");
        }
        else {
            minWidth = 10; maxWidth = 20;
            minLength = 10; maxLength = 20;
            minHeight = 8; maxHeight = 10;
        }
        width = random.nextInt(minWidth, maxWidth);
        length = random.nextInt(minLength, maxLength);
        height = random.nextInt(minHeight, maxHeight);
        tiles = new Tile[length][width];
        System.out.println("Width: " + width + ", length: " + length + ", Tiles.length: " + tiles.length);

        int doorWall = random.nextInt(4);
        Tile doorTile = null;
        switch(doorWall) {
            // North wall
            case 0:
                doorTile = tiles[0][random.nextInt(1, width - 1)];
                break;
            // South wall
            case 1:
                doorTile = tiles[length - 1][random.nextInt(1, width - 1)];
                break;
            // West wall
            case 2:
                doorTile = tiles[random.nextInt(1, length - 1)][0];
                break;
            // East wall
            case 3:
                doorTile = tiles[random.nextInt(1, length - 1)][width - 1];
                break;
        // Fill the map
        for(int y = 0; y < length; y++) {
            for(int x = 0; x < width; x++) {
                tiles[y][x] = new Tile('#', height);
            }
        }

        // Carve the room
        for(int y = 1; y < length - 1; y++) {
            for(int x = 1; x < width - 1; x++) {
                tiles[y][x].setSymbol('.');
            }
        }     

        int[] monster = placeOnFloor(3, 3, random); // 3, 3 are the initial player coordinates
        monsterX = monster[0];
        monsterY = monster[1];
        tiles[monster[1]][monster[0]].setSymbol('m');
        int[] box = placeOnFloor(monsterX, monsterY, random);
        boxX = box[0];
        boxY = box[1];
        tiles[box[1]][box[0]].setSymbol('b');

        if(width > 30 || length > 30) {
            placePillars();
        }
    }

    public void print(int playerX, int playerY) {
        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[y].length; x++) {
                if (x == playerX && y == playerY) {
                    System.out.print("@");
                } 
                else {
                    System.out.print(tiles[y][x].getSymbol());
                }
            }
            System.out.println();

        }
        if (tileAt(playerX, playerY).getSymbol() == 'b')
        {
            System.out.println("It's a box!");
        }
        System.out.println("Ceiling: " + tiles[playerY][playerX].getHeight());
    }

    public int[] placeOnFloor(int avoidX, int avoidY, Random random) {
        int x, y;
        do {
            x = random.nextInt(1, width - 1);
            y = random.nextInt(1, length - 1);
        } while ((x == avoidX && y == avoidY) || tiles[y][x].getSymbol() != '.');
        return new int[] { x, y };
    }

    public Tile tileAt(int x, int y) {
        return tiles[y][x];
    }

    public void setTile(int x, int y, char value) {
        tiles[y][x].setSymbol(value);
    }

    public int getMonsterX() {
        return this.monsterX;
    }

    public int getMonsterY() {
        return this.monsterY;
    }

    private void placePillars() {
        if (width <= 30) return;

        int gap = 8;

        int startX = pillarStart(width, gap);
        int startY = pillarStart(height, gap);
        if (startX < 1 || startY < 1) return;

        for (int x = startX; x < width - 1; x += gap) {
            for (int y = startY; y < height - 1; y += gap) {
                if (tiles[y][x].getSymbol() == '.') {
                    tiles[y][x].setSymbol('o');
                }
            }
        }
    }

   private int pillarStart(int size, int gap) {
    int runs = size / gap;
    int span = runs * (gap - 1);
    int endSum = size - span;
    int topGap = endSum / 2;
    System.out.println("Runs: " + runs + ", Span: " + span + ", EndSum: " + endSum + ", TopGap: " + topGap);
    return topGap;
   }
   
}