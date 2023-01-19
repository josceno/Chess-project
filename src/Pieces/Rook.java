package src.Pieces;



import src.Chess.ChessPiece;
import src.Chess.Colors;
import src.boardgame.Board;
import src.boardgame.Position;

public class Rook extends ChessPiece {
    public Rook(Board board, Colors color){
        super(board,color);
    }
    @Override
    public String toString(){
        return "R";

    }
    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0,0);
        //above
        p.setValues(position.getRow()-1, position.getColumn());

        while(getBoard().positionExists(p)&&!getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow()-1);

        }
        if(getBoard().positionExists(p) && isThereOpponetPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        
        // left
        p.setValues(position.getRow(), position.getColumn()-1);

        while(getBoard().positionExists(p)&&!getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn()-1);

        }
        if(getBoard().positionExists(p) && isThereOpponetPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //right
        p.setValues(position.getRow(), position.getColumn()+1);

        while(getBoard().positionExists(p)&&!getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn()+1);

        }
        if(getBoard().positionExists(p) && isThereOpponetPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //below
        p.setValues(position.getRow(), position.getColumn()+1);

        while(getBoard().positionExists(p)&&!getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow()+1);

        }
        if(getBoard().positionExists(p) && isThereOpponetPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        
        return mat;
    }

    
}
