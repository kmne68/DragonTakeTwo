/**
 * Represents the game loop.
 * 
 * @author John Doe
 * @version 1.0
 * @since 1.0
 * @see Player
 * @see Map
 */
public class GameLoop {

    Player player;
    Map map = new Map();

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
        // spawnPlayer();
        int result = player.move(1, 0);
        map.print(player.getPositionX(), player.getPositionY());
        if (result == -1) {
            System.out.println("Invalid move");
            return;
        }
        System.out.println("Player moved to position: " + player.getPositionX() + ", " + player.getPositionY());
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
}