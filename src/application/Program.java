package src.application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import src.Chess.ChessException;
import src.Chess.ChessMatch;
import src.Chess.ChessPiece;
import src.Chess.ChessPosition;

public class Program {
    public static void main(String[] args) {
       
        Scanner input = new Scanner(System.in);
        ChessMatch chessMatch = new
                ChessMatch();
        List<ChessPiece> captured = new ArrayList<>();
        
        while(!chessMatch.getCheckMate()){
            try{
                Ui.clearScreen();
                Ui.printMatch(chessMatch,captured);
                System.out.println();
                System.out.print("Source: ");
                ChessPosition source = Ui.readPosition(input);
                
                boolean[][] possibleMoves = chessMatch.possibleMoves(source);
                Ui.clearScreen();
                Ui.printBoard(chessMatch.getPiece(),possibleMoves);
                System.out.println();
                System.out.print("Target: ");
                ChessPosition target = Ui.readPosition(input);
    
                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);

                if(capturedPiece != null){
                    captured.add(capturedPiece);
                }

            }catch(ChessException e){
                System.out.print(e.getMessage());
                input.nextLine();
            }catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                input.nextLine();
            }
           
        }
        Ui.clearScreen();
        Ui.printMatch(chessMatch, captured);
    }

 
}
