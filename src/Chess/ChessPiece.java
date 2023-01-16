package src.Chess;

import src.boardgame.Board;
import src.boardgame.Piece;

public class ChessPiece extends Piece{
    private Colors color;

    public ChessPiece(){};
    public ChessPiece(Board board, Colors color) {
        super(board);
        this.color = color;
    }

    public ChessPiece(Colors color) {
        this.color = color;
    }

    
}
