package src.Pieces;

import src.Chess.ChessPiece;
import src.Chess.Colors;
import src.boardgame.Board;

public class Rook extends ChessPiece {
    public Rook(Board board, Colors color){
        super(board,color);
    }
    @Override
    public String toString(){
        return "R";

    }
}
