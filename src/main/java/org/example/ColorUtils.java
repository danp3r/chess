package org.example;

public class ColorUtils {
    public static String getFigureSymbol(Color color, String figureName) {
        return switch (figureName) {
            case "pawn" -> color.equals(Color.WHITE) ? "♙" : "♟";
            case "bishop" -> color.equals(Color.WHITE) ? "♗" : "♝";
            case "king" -> color.equals(Color.WHITE) ? "♔" : "♚";
            case "knight" -> color.equals(Color.WHITE) ? "♘" : "♞";
            case "queen" -> color.equals(Color.WHITE) ? "♕" : "♛";
            case "rook" -> color.equals(Color.WHITE) ? "♖" : "♜";
            default -> " ";
        };
    }
}
