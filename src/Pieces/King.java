package src.Pieces;

import src.Chess.ChessPiece;
import src.Chess.Colors;
import src.boardgame.Board;

public class King extends ChessPiece{
    public King(Board board, Colors color){
        super(board,color);
    }     
    @Override
    public String toString(){
        return "K";
    }
    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        return mat;
    }
}
