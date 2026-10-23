/**
 * Represents a monster in the game
 * 
 * 
 */

public interface Monster {

    void move(int x, int y);
    int attack();
    void takeDamage(int damage);
    int getHealth();
}