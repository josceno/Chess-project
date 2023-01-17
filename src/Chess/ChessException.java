package src.Chess;

public class ChessException extends RuntimeException {
    private long serialVersionUID = 2L;

    public ChessException(String msg){
        super(msg);
    }
}
