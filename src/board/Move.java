package board;

public class Move {
    private final int row;
    private final char column;

    public Move(int r, char c) {
        row = r;
        column = c;
    }

    public int getRow() { return row; }
    public char getColumn() { return column; }

    @Override
    public String toString() { return "(" + column + ", " + row + ")"; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Move)) return false;
        Move m = (Move)obj;
        return row == m.getRow() && column == m.getColumn();
    }
}
