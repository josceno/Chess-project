package src.application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import src.Chess.ChessException;
import src.Chess.ChessMatch;
import src.Chess.ChessPiece;
import src.Chess.ChessPosition;
import src.Chess.Colors;
import src.boardgame.Piece;

public class Ui {
    public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
   
    
    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        
    }
    public static ChessPosition readPosition(Scanner input){
            try{
                String s = input.nextLine();
                char column = s.charAt(0);
                int row = Integer.parseInt(s.substring(1));
                return new ChessPosition(column, row);
            }catch(ChessException e){
                throw new InputMismatchException("invalid values, the values must be between a1 and h8 "); 
            }   
    }

    public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured){
        printBoard(chessMatch.getPiece());
        System.out.println();
        printCapturedPieces(captured);
        System.out.println();
        System.out.println("trun: " +chessMatch.getTurn());
        System.out.println("Waiting player: "+chessMatch.getCurrentPlayer());
        if(chessMatch.getCheck()){
            System.out.println("CHECK");
        }
    }
    public static void printBoard(ChessPiece[][] piece, boolean[][] possibleMoves) {
        for (int i = 0; i < piece.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < piece.length; j++) {
                printPiece(piece[i][j], possibleMoves[i][j]);
            }
            System.out.println();
        }
    }
    public static void printBoard(ChessPiece[][] piece) {
        for(int i = 0; i<piece.length;i++){
            System.out.print((8-i)+" ");
            for (int j = 0; j < piece.length; j++) {
                printPiece(piece[i][j],false);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }
    private static void printPiece(ChessPiece piece,boolean backgorund) {
    	if(backgorund){
            System.out.print(ANSI_BLUE_BACKGROUND);
        }
        if (piece == null) {
            System.out.print("-"+ANSI_RESET);
        }
        else {
            if (piece.getColor() == Colors.WHITE) {
                System.out.print(ANSI_WHITE + piece + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
            }
        }
        System.out.print(" ");
	}
    private static void printCapturedPieces(List<ChessPiece> captured){
        
        List<Piece> white = captured.stream().filter(x -> x.getColor() == Colors.WHITE).collect(Collectors.toList());
        List<Piece> black = captured.stream().filter(x -> x.getColor() == Colors.BLACK).collect(Collectors.toList());
        
        System.out.print("White: ");
        System.out.print(ANSI_WHITE);
        System.out.println(Arrays.toString(white.toArray()));
        System.out.print(ANSI_RESET);
        System.out.print("Black: ");
        System.out.println(ANSI_YELLOW);
        System.out.print(Arrays.toString(black.toArray()));
        System.out.print(ANSI_RESET);
    }
}
