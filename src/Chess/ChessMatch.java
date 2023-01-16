package src.Chess;



import src.Pieces.Rook;
import src.boardgame.Board;
import src.boardgame.Position;

public class ChessMatch {
    private Board board;

   public ChessMatch() {
        this.board = new Board(8,8);
        initalSetup();
    }
    
    public ChessPiece[][] getPiece(){
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i = 0; i < mat.length; i++) {
            for(int j =0; j<mat.length;j++){
                mat[i][j] = (ChessPiece) board.piece(i,j);
            }
        }
        return mat;
    }
    public void initalSetup(){
        board.placePiece(new Rook(board,Colors.WHITE), new Position(2,1));
    }
   
    

}
