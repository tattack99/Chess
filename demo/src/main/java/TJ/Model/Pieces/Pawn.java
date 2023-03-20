package TJ.Model.Pieces;

public class Pawn extends Piece{
    public Pawn(Team team, int row, int col) {
        super(Type.PAWN, 1, team, row, col);
    }

    @Override
    public boolean move(int row, int col) {
        System.out.println("Pawn: row: " + row + ", col: " + col);
        super.setRow(row);
        super.setCol(col);

        return true;
    }

    @Override
    public String toString() {
        if(super.getTeam()==Team.WHITE) return "WP";
        return "BP";
    }
}
