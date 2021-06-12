package board;

public class Move {
    private final char column;
    private final int row;

    public Move(char c, int r) {
        column = c;
        row = r;
    }

    public int getRow() { return row; }
    public char getColumn() { return column; }
}
