package src.Chess;



import src.boardgame.Board;
import src.boardgame.Piece;
import src.boardgame.Position;

public abstract class ChessPiece extends Piece{
    private Colors color;

    public ChessPiece(){};
    public ChessPiece(Board board, Colors color) {
        super(board);
        this.color = color;
    }

    public ChessPiece(Colors color) {
        this.color = color;
    }

    public Colors getColor(){
        return color;
    }
    protected boolean isThereOpponetPiece(Position position){
        ChessPiece p =(ChessPiece)getBoard().piece(position);
         return p!=null && p.getColor() != color;
    }
}
