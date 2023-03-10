package src.Pieces;


import src.Chess.ChessMatch;
import src.Chess.ChessPiece;
import src.Chess.Colors;
import src.boardgame.Board;
import src.boardgame.Position;

public class King extends ChessPiece{

    private ChessMatch chessMatch;

    public King(Board board, Colors color, ChessMatch chessMatch){
        super(board,color);
        this.chessMatch = chessMatch;
    }     
    @Override
    public String toString(){
        return "K";
    }
    private boolean canMove(Position position){
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        return p == null || p.getColor() != getColor();
    }
    private boolean testRookCastling(Position position){
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        return p != null  && p instanceof Rook && p.getColor() == getColor() && p.getMovesCount() == 0;
    }
    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        
        Position p = new Position(0,0);
        
        //above
        p.setValues(position.getRow()-1,position.getColumn());
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()]= true;
        }
        //below
        p.setValues(position.getRow()+1,position.getColumn());
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()]= true;
        }
        //left
        p.setValues(position.getRow(),position.getColumn()-1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()]= true;
        }
        //Right
        p.setValues(position.getRow(),position.getColumn()+1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()]= true;
        }
        p.setValues(position.getRow()-1,position.getColumn()+1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()]= true;
        }
        p.setValues(position.getRow()-1,position.getColumn()-1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()]= true;
        }
        p.setValues(position.getRow()+1,position.getColumn()-1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()]= true;
        }
        p.setValues(position.getRow()+1,position.getColumn()+1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()]= true;
        }

        //#specialMove castling
        if(getMovesCount()==0 && !chessMatch.getCheck()){
            //Kings side castling
            Position post1 = new Position(position.getRow(), position.getColumn()+3);
            if(testRookCastling(post1)){
                Position p1 = new Position(position.getRow(),position.getColumn()+1);
                Position p2 = new Position(position.getRow(),position.getColumn()+2);
                if(getBoard().piece(p1)== null && getBoard().piece(p2)== null){
                    mat[position.getRow()][position.getColumn()+2] = true;

                }
            }
             //Kings side castling
             Position post2 = new Position(position.getRow(), position.getColumn()-4);
             if(testRookCastling(post2)){
                 Position p1 = new Position(position.getRow(),position.getColumn()-1);
                 Position p2 = new Position(position.getRow(),position.getColumn()-2);
                 Position p3 = new Position(position.getRow(),position.getColumn()-3);
                 if(getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null){
                     mat[position.getRow()][position.getColumn()-2] = true;
 
                 }
             }
        }
        return mat;
    }
}
