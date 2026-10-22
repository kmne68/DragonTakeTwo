/**
 * Represents monsters of type Goblin
 */

import java.util.concurrent.ThreadLocalRandom;
import java.awt.Point;

public class Goblin implements Monster {
    
    private String name;
    private int level;
    private int health;
    private int treaure;
    private int positionX;
    private int positionY;

    public Goblin(String name, int level, int health, int treasure, int positionX, int positionY) {
        this.name = name;
        this.level = level;
        this.health = health;
        this.treaure = treasure;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Point move(int x, int y) {
        positionX += x;
        positionY += y;
        Point newLocation = new Point(positionX, positionY);
        return newLocation;

    }

    public int attack() {
        int damage = ThreadLocalRandom.current().nextInt(0, 4);
        System.out.println("Damage dealt: " + damage);
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int damage) {
        health = Math.max(0, health - damage);
        if(health == 0) {
            System.out.println("The monster has died");
        }
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }
}