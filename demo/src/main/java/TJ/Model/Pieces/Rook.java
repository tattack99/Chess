package TJ.Model.Pieces;

public class Rook extends Piece{
    public Rook(Team team, int row, int col) {
        super(Type.ROOK, 5, team, row, col);
    }

    @Override
    public boolean move(int row, int col) {
        return false;
    }

    @Override
    public String toString() {
        if(super.getTeam()==Team.WHITE) return "WR";
        return "BR";
    }
}
