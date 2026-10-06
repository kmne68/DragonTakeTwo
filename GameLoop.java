public class GameLoop {
    int turns = 10;

    public void run() {
        while (turns > 9) {
            update();
            turns--;
        }
    }

    private void update() {
        spawnPlayer();
        System.out.println("Updating game... turns remaining: " + turns);
    }


    private void spawnPlayer() {
        // int randomX = (int) (Math.random() * map.getWidth());
        // int randomY = (int) (Math.random() * map.getHeight());
        // map.setBox(randomX, randomY);

        Player player = new Player("Player", 0, 1, 100, 100, 0, 0, 3, 3);
        // player.spawn(map);
        System.out.println("Player spawned at position: " + player.getPositionX() + ", " + player.getPositionY());

    }
}