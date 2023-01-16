package src.application;

import src.Chess.ChessMatch;
import src.boardgame.Position;

public class Program {
    public static void main(String[] args) {
       ChessMatch chessMatch = new ChessMatch();
       Ui.printBoard(chessMatch.getPiece());
    }

 
}
