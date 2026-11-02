public class Tile {

    private char symbol;
    private int ceilingHeight;

    public Tile (char symbol, int ceilingHeight) {
        this.symbol = symbol;
        this.ceilingHeight = ceilingHeight;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public int getHeight() {
        return this.ceilingHeight;
    }


}