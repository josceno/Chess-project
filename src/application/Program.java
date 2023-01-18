package src.application;

import java.util.InputMismatchException;
import java.util.Scanner;

import src.Chess.ChessException;
import src.Chess.ChessMatch;
import src.Chess.ChessPiece;
import src.Chess.ChessPosition;
import src.boardgame.Position;

public class Program {
    public static void main(String[] args) {
       
        Scanner input = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        
        while(true){
            try{
                Ui.clearScreen();
                Ui.printBoard(chessMatch.getPiece());
                System.out.println();
                System.out.print("Source: ");
                ChessPosition source = Ui.readPosition(input);
    
                System.out.println();
                System.out.print("Target: ");
                ChessPosition target = Ui.readPosition(input);
    
                ChessPiece chessPiece = chessMatch.performChessMove(source, target);
            }catch(ChessException e){
                System.out.print(e.getMessage());
                input.nextLine();
            }catch(InputMismatchException e){
                System.out.print(e.getMessage());
                input.nextLine();
            }
           
        }
        



    }

 
}
