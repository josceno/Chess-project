package src.Pieces;

import src.Chess.ChessMatch;
import src.Chess.ChessPiece;
import src.Chess.Colors;
import src.boardgame.Board;
import src.boardgame.Position;

public class Pawn extends ChessPiece {
    private ChessMatch chessMatch;

    public Pawn(Board board, Colors color,ChessMatch chessMatch){
        super(board,color);
        this.chessMatch = chessMatch;
    }
    public String toString(){
        return "P";
    }


    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new  boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0,0);

        if(getColor()==Colors.WHITE){
            p.setValues(position.getRow()-1,position.getColumn());
            if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow()-2,position.getColumn());
            Position p2 = new Position(position.getRow()-1, position.getColumn());
            if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMovesCount()==0){
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow()-1,position.getColumn()-1);
            if(getBoard().positionExists(p) && isThereOpponetPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow()-1,position.getColumn()+1);
            if(getBoard().positionExists(p) && isThereOpponetPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            if(position.getRow()== 3){
                Position left = new Position(position.getRow(),position.getColumn()-1);
                if(getBoard().positionExists(left) && isThereOpponetPiece(left) && getBoard().piece(left) == chessMatch.getEnPessantVunrable()){
                    mat[left.getRow()-1][left.getColumn()]= true;
                }
                Position right = new Position(position.getRow(),position.getColumn()+1);
                if(getBoard().positionExists(right) && isThereOpponetPiece(right) && getBoard().piece(right) == chessMatch.getEnPessantVunrable()){
                    mat[right.getRow()-1][right.getColumn()]= true;
                }
            }
        }else {
            p.setValues(position.getRow()+1,position.getColumn());
            if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow()+2,position.getColumn());
            Position p2 = new Position(position.getRow()+1, position.getColumn());
            if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMovesCount()==0){
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow()+1,position.getColumn()+1);
            if(getBoard().positionExists(p) && isThereOpponetPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow()+1,position.getColumn()-1);
            if(getBoard().positionExists(p) && isThereOpponetPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            if(position.getRow()== 4){
                Position left = new Position(position.getRow(),position.getColumn()-1);
                if(getBoard().positionExists(left) && isThereOpponetPiece(left) && getBoard().piece(left) == chessMatch.getEnPessantVunrable()){
                    mat[left.getRow()+1][left.getColumn()]= true;
                }
                Position right = new Position(position.getRow(),position.getColumn()+1);
                if(getBoard().positionExists(right) && isThereOpponetPiece(right) && getBoard().piece(right) == chessMatch.getEnPessantVunrable()){
                    mat[right.getRow()+1][right.getColumn()]= true;
                }
            }
        }
        return mat;
    }
}
