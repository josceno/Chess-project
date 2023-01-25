package src.Chess;



import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import src.Pieces.Bishop;
import src.Pieces.King;
import src.Pieces.Knight;
import src.Pieces.Pawn;
import src.Pieces.Queen;
import src.Pieces.Rook;
import src.boardgame.Board;
import src.boardgame.Piece;
import src.boardgame.Position;

public class ChessMatch {
    private int turn;
    private Colors currentPlayer;
    private Board board;
    private boolean check;
    private boolean checkMate;
    List<Piece> piecesOnTheBoard = new ArrayList<>();
    List<Piece> capturedPieces = new ArrayList<>();

   public ChessMatch() {
        this.board = new Board(8,8);
        turn = 1;
        currentPlayer = Colors.WHITE;
        initalSetup();
    }
    
    
    public int getTurn() {
    return turn;
    }


    public Colors getCurrentPlayer() {
        return currentPlayer;
    }


    public Board getBoard() {
        return board;
    }


    public boolean getCheck() {
        return check;
    }
    

    public boolean getCheckMate() {
        return checkMate;
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
        if(testCheck(currentPlayer)){
            undoMove(source, target, capturedPiece);
            throw new ChessException("you cannot put yourself on check");
        }
        check = (testCheck(opponent(currentPlayer))) ? true : false; 
        if(testCheckMate(opponent(currentPlayer))){
            checkMate = true;
        }else{
            nextTurn();
        }
        return (ChessPiece)capturedPiece;
    }

    

    private Piece makeMove(Position source, Position target) {
        ChessPiece p = (ChessPiece)board.removePiece(source);
        p.increaseMoveCount();
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p,target);
        

        if(capturedPiece != null){
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }
        return capturedPiece;
        
    }
    private void undoMove(Position source, Position target, Piece capturedPiece){
        ChessPiece p = (ChessPiece)board.removePiece(target);
        p.decreaseMoveCount();
        board.placePiece(p, source);
        if(capturedPiece != null){
            board.placePiece(capturedPiece, target);
            capturedPiece.remove(capturedPiece);
            piecesOnTheBoard.add(capturedPiece);
        }
    }

    private void validateSourcePosition(Position source) {
        if(!board.thereIsAPiece(source)){
            throw new ChessException("there is no piece in source position");
        }
        if(currentPlayer != ((ChessPiece)board.piece(source)).getColor()){
            throw new ChessException("this piece is not yours");
        }
        if(!board.piece(source).isThereAnyPossibleMoves()){
            throw new ChessException("Theres no possible moves to the chosen piece");
        }
    }
    private void validateTargertPosition(Position source, Position target) {
        if(!board.piece(source).possibleMove(target)){
            throw new ChessException("unvalid moviment for this piece") ;
        }
    }
    private void nextTurn(){
        turn++;
        currentPlayer = (currentPlayer == Colors.WHITE)? Colors.BLACK : Colors.WHITE;
    }
    private Colors opponent(Colors colors){
        return (colors == Colors.WHITE) ? Colors.BLACK : Colors.WHITE;
    }
    private ChessPiece king(Colors colors){
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == colors).collect(Collectors.toList());
        for (Piece piece : list) {
            if(piece instanceof King){
                return (ChessPiece) piece;
            }
        }
        throw new IllegalStateException("There is no "+colors +" king in chessBoard");
    }
    private boolean testCheck(Colors colors){
        Position kingPosition = king(colors).getChessPosition().toPosition();
        List<Piece> opponenntPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(colors)).collect(Collectors.toList());
        for (Piece p : opponenntPieces) {
            boolean[][] mat = p.possibleMoves();
            if(mat[kingPosition.getRow()][kingPosition.getColumn()]){
                return true;
            } 
        }
        return false;
        
        
    }
    private boolean testCheckMate(Colors colors){
        if(!testCheck(colors)){
            return false;
        }
        List<Piece> list =  piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == colors).collect(Collectors.toList());
        for (Piece p : list) {
            boolean[][] mat = p.possibleMoves();
            for (int i = 0; i < board.getRows(); i++) {
                for (int j = 0; j < board.getColumns(); j++) {
                    if(mat[i][j]){
                        Position source = ((ChessPiece)p).getChessPosition().toPosition();
                        Position target = new Position(i,j);
                        Piece capturedPiece = makeMove(source, target);
                        boolean testCheck = testCheck(colors);
                        undoMove(source, target, capturedPiece);
                        if(!testCheck){
                            return false;
                        }
                    }
                    
                }
                
            }
        }
        return true;
    }
    private void placeNewPiece(char column, int row,ChessPiece piece){
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        piecesOnTheBoard.add(piece);
    }

    public void initalSetup(){
        placeNewPiece('a',1, new Rook(board,Colors.WHITE));
        placeNewPiece('b',1, new Knight(board,Colors.WHITE));
        placeNewPiece('e',1, new King(board,Colors.WHITE));
        placeNewPiece('g',1, new Knight(board,Colors.WHITE));
        placeNewPiece('h',1, new Rook(board,Colors.WHITE));
        placeNewPiece('c',1, new Bishop(board,Colors.WHITE));
        placeNewPiece('d',1, new Queen(board,Colors.WHITE));
        placeNewPiece('a',2, new Pawn(board,Colors.WHITE));
        placeNewPiece('b',2, new Pawn(board,Colors.WHITE));
        placeNewPiece('f',1, new Bishop(board,Colors.WHITE));
        placeNewPiece('c',2, new Pawn(board,Colors.WHITE));
        placeNewPiece('d',2, new Pawn(board,Colors.WHITE));
        placeNewPiece('e',2, new Pawn(board,Colors.WHITE));
        placeNewPiece('f',2, new Pawn(board,Colors.WHITE));
        placeNewPiece('g',2, new Pawn(board,Colors.WHITE));
        placeNewPiece('h',2, new Pawn(board,Colors.WHITE));

        placeNewPiece('a',8, new Rook(board,Colors.BLACK));
        placeNewPiece('b',8, new Knight(board,Colors.BLACK));
        placeNewPiece('c',8, new Bishop(board,Colors.BLACK));
        placeNewPiece('d',8, new Queen(board,Colors.BLACK));
        placeNewPiece('e',8, new King(board,Colors.BLACK));
        placeNewPiece('g',8, new King(board,Colors.BLACK));
        placeNewPiece('h',8, new Rook(board,Colors.BLACK));
        
        placeNewPiece('f',8, new Bishop(board,Colors.BLACK));
        placeNewPiece('a',7, new Pawn(board,Colors.BLACK));
        placeNewPiece('b',7, new Pawn(board,Colors.BLACK));
        placeNewPiece('c',7, new Pawn(board,Colors.BLACK));
        placeNewPiece('d',7, new Pawn(board,Colors.BLACK));
        placeNewPiece('e',7, new Pawn(board,Colors.BLACK));
        placeNewPiece('f',7, new Pawn(board,Colors.BLACK));
        placeNewPiece('g',7, new Pawn(board,Colors.BLACK));
        placeNewPiece('h',7, new Pawn(board,Colors.BLACK));
    }
   
    

}
