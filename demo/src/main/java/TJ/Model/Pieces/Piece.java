package TJ.Model.Pieces;

public abstract class Piece {
    private Type type;
    private int value;
    private Team team;
    private int row;
    private int col;

    public Piece(Type type, int value, Team team, int row, int col) {
        this.type = type;
        this.value = value;
        this.team = team;
        this.row = row;
        this.col = col;
    }

    public int getValue() {
        return value;
    }

    public Team getTeam() {
        return team;
    }

    public Type getType() {
        return type;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public abstract boolean move(int row, int col);
    public abstract String toString();
}
