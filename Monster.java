/**
 * Represents a monster in the game
 * 
 * 
 */
import java.awt.Point;

public interface Monster {

    Point move(int x, int y);
    int attack();
}