package TJ.Model.Pieces;

public class King extends Piece{
    public King(Team team, int row, int col) {
        super(Type.KING, 15, team, row, col); // value 4
    }

    @Override
    public boolean move(int row, int col) {
        return false;
    }

    @Override
    public String toString() {
        if(super.getTeam()==Team.WHITE) return "WK";
        return "BK";
    }
}
