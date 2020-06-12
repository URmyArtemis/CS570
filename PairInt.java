package Maze;
public class PairInt {
	// Member variables
    private int x;
    private int y;
    // constructor
    public PairInt(int x, int y) {
        this.x = x;
        this.y = y;
    }
    // x getter method
    public int getX() {
        return x;
    }
    // y getter method
    public int getY() {
        return y;
    }
    // x setter method
    public void setX(int x) {
        this.x = x;
    }
    // y setter method
    public void setY(int y) {
        this.y = y;
    }
    // equal method
    public boolean equals(Object p) {
        if ( p == null) {
            return false;
        }
        PairInt pairInt = (PairInt) p;
        return (this.x == pairInt.x && this.y == pairInt.y);
    }
    // toString 
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
    // copy should return a new copy of a PairInt
    public PairInt copy() {
        return new PairInt(x, y);
    }
}