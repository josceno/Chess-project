package src.Chess;



import src.Pieces.King;
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

    private void placeNewPiece(char column, int row,ChessPiece piece){
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }

    public void initalSetup(){
        placeNewPiece('b', 6, new Rook(board, Colors.WHITE));
        placeNewPiece('e', 8, new King(board, Colors.BLACK));
        placeNewPiece('e', 1, new King(board, Colors.WHITE));
    }
   
    

}
