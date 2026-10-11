/**
 * Represents a player in the game.
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
import java.util.List;
import java.util.ArrayList;

public class Player {
    private String name;
    private int score;
    private int level;
    private int health;
    private int mana;
    private int gold;
    private int experience;
    private int positionX;
    private int positionY;
    private final List<String> inventory = new ArrayList<>();

    public Player(String name, int score, int level, int health, int mana, int gold, int experience, int positionX, int positionY) {
        this.name = name;
        this.score = score;
        this.level = level;
        this.health = health;
        this.mana = mana;
        this.gold = gold;
        this.experience = 0;
        this.positionX = positionX;
        this.positionY = positionY;
    }


    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public int move(int x, int y) {
        if (this.positionX + x < 1 || this.positionX + x > 10 || this.positionY + y < 1 || this.positionY + y > 10) {
            System.out.println("Invalid move");
            return -1;
        }
        this.positionX += x;
        this.positionY += y;
        return 0;
    }

    public void addItemToInventory(String name) {
        inventory.add(name);
    }

    public void printInventory() {
        for(int i = 0; i < inventory.size(); i++) {
            System.out.println(inventory.get(i) + ", ");
        }
    }
}


