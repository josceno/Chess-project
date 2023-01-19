package src.Chess;



import src.Pieces.King;
import src.Pieces.Rook;
import src.boardgame.Board;
import src.boardgame.Piece;
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
    public boolean[][] possibleMoves(ChessPosition sourcePosition){
       Position position = sourcePosition.toPosition();
       validateSourcePosition(position);
       return board.piece(position).possibleMoves();
    }
    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargertPosition(source,target);
        Piece capturedPiece = makeMove(source, target);
        return (ChessPiece)capturedPiece;
    }

    

    private Piece makeMove(Position source, Position target) {
        Piece p = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p,target);
        return capturedPiece;
    }

    public void validateSourcePosition(Position source) {
        if(!board.thereIsAPiece(source)){
            throw new ChessException("there is no piece in source position");
        }
        if(!board.piece(source).isThereAnyPossibleMoves()){
            throw new ChessException("Theres no possible moves to the chosen piece");
        }
    }
    public void validateTargertPosition(Position source, Position target) {
        if(!board.piece(source).possibleMove(target)){
            throw new ChessException("unvalid moviment for this piece") ;
        }
    }

    private void placeNewPiece(char column, int row,ChessPiece piece){
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }

    public void initalSetup(){
        placeNewPiece('c', 1, new Rook(board, Colors.WHITE));
        placeNewPiece('c', 2, new Rook(board, Colors.WHITE));
        placeNewPiece('d', 2, new Rook(board, Colors.WHITE));
        placeNewPiece('e', 2, new Rook(board, Colors.WHITE));
        placeNewPiece('e', 1, new Rook(board, Colors.WHITE));
        placeNewPiece('d', 1, new King(board, Colors.WHITE));

        placeNewPiece('c', 7, new Rook(board, Colors.BLACK));
        placeNewPiece('c', 8, new Rook(board, Colors.BLACK));
        placeNewPiece('d', 7, new Rook(board, Colors.BLACK));
        placeNewPiece('e', 7, new Rook(board, Colors.BLACK));
        placeNewPiece('e', 8, new Rook(board, Colors.BLACK));
        placeNewPiece('d', 8, new King(board, Colors.BLACK));
    }
   
    

}
