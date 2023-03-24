package TJ.Model;

import TJ.Model.Pieces.*;

public class Board {

    private Cell[][] board;


    public Board() {
        int BOARD_SIZE = 8;
        board = new Cell[BOARD_SIZE][BOARD_SIZE];
    }

    public void printBoard(){
        printBoard(this.board);
    }

    public Cell getCell(int row, int col){
        return board[row][col];
    }

    public Cell[][] init(){
        return startPosition(board);
    }
    private Cell[][] startPosition(Cell[][] board){
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board.length;j++){
                board[i][j] = new Cell(null);
                Team team = Team.BLACK;
                if(i >= 6){team = Team.WHITE;}

                if(i == 1 || i == 6){
                    board[i][j].setPiece(new Pawn(team,i,j));
                }
                else if(i == 0 || i==7){
                    if(j == 0 || j == 7) board[i][j].setPiece(new Rook(team,i,j));
                    if(j == 1 || j == 6) board[i][j].setPiece(new Knight(team,i,j));
                    if(j == 2 || j == 5) board[i][j].setPiece(new Bishop(team,i,j));
                    if(j == 3) board[i][j].setPiece(new Queen(team,i,j));
                    if(j == 4) board[i][j].setPiece(new King(team,i,j));
                }
            }
        }
        return board;
    }

    public boolean move(int row, int col, int newRow, int newCol){
        if(board[row][col].getPiece()==null) return false;

        Piece p = board[row][col].getPiece();
        if(p.move(newRow,newCol)){
            board[p.getRow()][p.getCol()].setPiece(p);
            board[row][col].setPiece(null);
            return true;
        }

        return false;
    }

    private boolean empty(Cell[][] board){
        for (Cell[] ints : board) {
            for (Cell j : ints) {
                if(j.getPiece()!=null) return false;
            }
            System.out.println();
        }
        return true;
    }

    private void printBoard(Cell[][] board){
        for (Cell[] ints : board) {
            for (Cell j : ints) {
                System.out.print(" " + j.getPiece() + " ");
            }
            System.out.println();
        }
    }
}
