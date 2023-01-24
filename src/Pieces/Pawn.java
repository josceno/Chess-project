package src.Pieces;

import src.Chess.ChessPiece;
import src.Chess.Colors;
import src.boardgame.Board;

public class Pawn extends ChessPiece {
    public Pawn(Board board, Colors color){
        super(board,color);
    }
    public String toString(){
        return "k";
    }

    @Override
    public boolean[][] possibleMoves() {
        return null;
    }
}
