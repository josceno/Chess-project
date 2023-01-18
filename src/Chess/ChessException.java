package src.Chess;

import src.boardgame.BoardExeption;

public class ChessException extends BoardExeption {
    private long serialVersionUID = 2L;

    public ChessException(String msg){
        super(msg);
    }
}
