package TJ.Model.Pieces;

public class Knight extends Piece{
    public Knight(Team team, int row, int col) {
        super(Type.KNIGHT, 3, team, row, col);
    }

    @Override
    public boolean move(int row, int col) {
        return false;
    }

    @Override
    public String toString() {
        if(super.getTeam()==Team.WHITE) return "WKn";
        return "BKn";
    }
}
