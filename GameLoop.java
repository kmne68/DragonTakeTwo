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

    Player player;
    Map map = new Map();
    Scanner scanner = new Scanner(System.in);
    public record Point(int x, int y) {};
    int monsterHealth = 10;

    int turns = 10;

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
        if(command == 'f')
        {
            while(monsterHealth > 0 && !player.isDead()) {
                Point[] surroundingTiles = {
                    new Point(player.getPositionX() + 1, player.getPositionY()),
                    new Point(player.getPositionX(), player.getPositionY() + 1),
                    new Point(player.getPositionX() - 1, player.getPositionY()),
                    new Point(player.getPositionX(), player.getPositionY() - 1)
                };
                for(int i = 0; i <= surroundingTiles.length - 1; i++) {
                    if(map.tileAt(surroundingTiles[i].x, surroundingTiles[i].y) == 'm') {
                        melee();
                        if(testForDead() == 1) {
                            System.out.println("Game over, man! Game over!");
                        }
                        else if(testForDead() == 2) {
                            map.setTile(surroundingTiles[i].y, surroundingTiles[i].x, '.');
                            System.out.println("You have slain the M");
                        } else if(testForDead() == 0) {
                            continue;
                        }
                    }
                }
            }
        }
/*                else if(map.tileAt(surroundingTiles[1].x, surroundingTiles[1].y) == 'm') {
                //    melee();
                //    if(testForDead() = 1)
                    map.setTile(surroundingTiles[1].y, surroundingTiles[1].x, '.');
                    System.out.println("You have slain the N");
                }
                else if(map.tileAt(surroundingTiles[2].x, surroundingTiles[2].y) == 'm') {
                    map.setTile(surroundingTiles[2].y, surroundingTiles[2].x, '.');
                    System.out.println("You have slain the O");
                }
                else if(map.tileAt(surroundingTiles[3].x, surroundingTiles[3].y) == 'm') {
                    map.setTile(surroundingTiles[3].y, surroundingTiles[3].x, '.');
                    System.out.println("You have slain the P");
                }
            } */
        
        if(command == 'p' && !player.inventory.isEmpty()) {
            put();
        } 
        if(command == 'p' && player.inventory.isEmpty()) {
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
        // int randomX = (int) (Math.random() * map.getWidth());
        // int randomY = (int) (Math.random() * map.getHeight());
        // map.setBox(randomX, randomY);

        player = new Player("Player", 0, 1, 10, 100, 0, 0, 3, 3);
        // player.spawn(map);
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
        if(map.tileAt(y, x) == '.') {
            map.setTile(y, x, 'b');
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
            monsterHealth -= attack();
            System.out.println("Monster health: " + monsterHealth);
        }
    }

    // If true is returned, the player attacks first. Otherwise, the monster does
    private boolean rollInitiative() {
        boolean whoseTurn = ThreadLocalRandom.current().nextBoolean();
        return whoseTurn;
    }

    private int testForDead() {
        if(player.isDead()) {
            System.out.println("RIP player.");
            return 1;
        } else if(monsterHealth <= 0) {
            System.out.println("You have slain the Jabberwocky!");
            return 2;
        }else {
            System.out.println("The struggle continues...");
            return 0;
        }
    }
}