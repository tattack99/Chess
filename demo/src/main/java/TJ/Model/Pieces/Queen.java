package TJ.Model.Pieces;

public class Queen extends Piece{
    public Queen(Team team, int row, int col) {
        super(Type.QUEEN, 9, team, row, col);
    }

    @Override
    public boolean move(int row, int col) {
        return false;
    }

    @Override
    public String toString() {
        if(super.getTeam()==Team.WHITE) return "WQ";
        return "BQ";
    }
}
