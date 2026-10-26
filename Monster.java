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
    int getPositionX();
    int getPositionY();
    void setPositionX(int x);
    void setPositionY(int y);
}