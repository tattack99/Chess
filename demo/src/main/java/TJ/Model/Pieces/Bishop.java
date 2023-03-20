package TJ.Model.Pieces;

public class Bishop extends Piece{
    public Bishop(Team team, int row, int col) {
        super(Type.BISHOP, 3, team, row, col);
    }

    @Override
    public boolean move(int row, int col) {
        return false;
    }

    @Override
    public String toString() {
        if(super.getTeam()==Team.WHITE) return "WB";
        return "BB";
    }
}
