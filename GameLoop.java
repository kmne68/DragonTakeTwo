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

public class GameLoop {

    Player player;
    Map map = new Map();
    Scanner scanner = new Scanner(System.in);

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
        // int result = player.move(1, 0);
        int[] direction = getPlayerDirection();
        int result = player.move(direction[0], direction[1]);
        map.print(player.getPositionX(), player.getPositionY());

    //    if (result == -1) {
    //        System.out.println("Invalid move");
    //        return;
    //    }
        System.out.println("Player is at position: " + player.getPositionX() + ", " + player.getPositionY());
        System.out.println("Updating game... turns remaining: " + turns);
    }


    private void spawnPlayer() {
        // int randomX = (int) (Math.random() * map.getWidth());
        // int randomY = (int) (Math.random() * map.getHeight());
        // map.setBox(randomX, randomY);

        player = new Player("Player", 0, 1, 100, 100, 0, 0, 3, 3);
        // player.spawn(map);
        System.out.println("Player spawned at position: " + player.getPositionX() + ", " + player.getPositionY());

    }

    int[] getPlayerDirection() {
        System.out.println("Enter a direction (w, a, s, d): ");
        char direction = scanner.next().charAt(0);

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
}