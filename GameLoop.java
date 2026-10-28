/**
 * Represents the game loop.
 * 
 * @author John Doe
 * @version 1.0
 * @since 1.0
 * @see Player
 * @see Map
 */
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class GameLoop {

    public final long seed;
    public final Map map;
    public final Scanner scanner;
    
    public record Point(int x, int y) {};
    int turns = 10;
    private Player player;
    private Goblin goblin;

    public GameLoop(long seed) {
        this.scanner = new Scanner(System.in);
        this.seed = seed;
        this.map = new Map();
        map.generate(seed);
        this.goblin = new Goblin("Goober", 1, 10, 0, map.getMonsterX(), map.getMonsterY());
    }
    


    public void run() {
        spawnPlayer();
        map.print(player.getPositionX(), player.getPositionY());
        while (turns > 0) {
            update();
            turns--;
        }
    }

    private void update() {
        System.out.println("Enter a direction (w, a, s, d) to move");
        System.out.println("or t take an object, i to print inventory");
        System.out.println("or p to put down an object:");
        char command = scanner.next().charAt(0);
        if(command == 't') {
            take();
        }
        if(command == 'i') {
            player.printInventory();
        } 
        else if(command == 'f')
        {
            fight:
            while(goblin.getHealth() > 0 && !player.isDead()) {
                Point[] surroundingTiles = {
                    new Point(player.getPositionX() + 1, player.getPositionY()),
                    new Point(player.getPositionX(), player.getPositionY() + 1),
                    new Point(player.getPositionX() - 1, player.getPositionY()),
                    new Point(player.getPositionX(), player.getPositionY() - 1)
                };
                for(int i = 0; i <= surroundingTiles.length - 1; i++) {
                    if(map.tileAt(surroundingTiles[i].x, surroundingTiles[i].y) == 'm') {
                        melee();
                        int meleeResult = testForDead();
                        if(meleeResult == 1) {
                            System.out.println("Game over, man! Game over!");
                            break fight;
                        }
                        else if(meleeResult == 2) {
                            System.out.println("You have slain the M");
                            map.setTile(surroundingTiles[i].x, surroundingTiles[i].y, '.');
                            break fight;
                        } else if(meleeResult == 0) {
                            continue;
                        }
                    }
                }
            }
        }
        
        else if(command == 'p' && !player.inventory.isEmpty()) {
            put();
        } 
        else if(command == 'p' && player.inventory.isEmpty()) {
            System.out.println("There is nothing in your inventory.");
        } else {
            int[] direction = directionFor(command);
            int prospectiveX = player.getPositionX() + direction[0];
            int prospectiveY = player.getPositionY() + direction[1];
            if(map.tileAt(prospectiveX, prospectiveY) == '#') {
                System.out.println("You've hit a wall. Try another direction.");
            } else if(map.tileAt(prospectiveX, prospectiveY) == 'm') {
                System.out.println("You've encountered a monster! Run away!");
            } else {
                player.move(direction[0], direction[1]);
            }
        }
        map.print(player.getPositionX(), player.getPositionY());

        System.out.println("Player is at position: " + player.getPositionX() + ", " + player.getPositionY());
        System.out.println("Updating game... turns remaining: " + (turns - 1));
    }


    private void spawnPlayer() {
        player = new Player("Player", 0, 1, 10, 100, 0, 0, 3, 3);
        System.out.println("Player spawned at position: " + player.getPositionX() + ", " + player.getPositionY());
    }

    int[] directionFor(char direction) {
        switch (direction) {
            case 'w':
                return new int[] {0, -1};
            case 'a':
                return new int[] {-1, 0};
            case 's':
                return new int[] {0, 1};
            case 'd':
                return new int[] {1, 0};
            default:
                return new int[] {0, 0};
        }
    }

    private void take() {
        int x = player.getPositionX();
        int y = player.getPositionY();
        if( map.tileAt(x, y) == 'b') {
            map.setTile(x, y, '.');
            System.out.println("You pick up the box");
            player.addItemToInventory("box");
        } else {
            System.out.println("Nothing to take");
        }
    }

    private void put() {
        int x = player.getPositionX();
        int y = player.getPositionY();
        if(map.tileAt(x, y) == '.') {
            map.setTile(x, y, 'b');
            System.out.println("You put down the box");
            player.removeItemFromInventory("box");
        }
    }

    private int attack() {
        int damage = ThreadLocalRandom.current().nextInt(0, 4);
        System.out.println("Damage dealt: " + damage);
        return damage;
    }

    private void melee() {
        if (rollInitiative() == false) {
            player.takeDamage(attack());
            System.out.println("Player health: " + player.getHealth());
        } else {
            goblin.takeDamage(attack());
            System.out.println("Monster health: " + goblin.getHealth());
        }
    }

    // If true is returned, the player attacks first. Otherwise, the monster does
    private boolean rollInitiative() {
        boolean whoseTurn = ThreadLocalRandom.current().nextBoolean();
        return whoseTurn;
    }

    private int testForDead() {
        if(player.isDead()) {
            return 1;
        } else if(goblin.getHealth() <= 0) {
            return 2;
        }else {
            return 0;
        }
    }
}