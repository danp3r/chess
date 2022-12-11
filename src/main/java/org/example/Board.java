package org.example;

import org.example.figure.Bishop;
import org.example.figure.King;
import org.example.figure.Knight;
import org.example.figure.Pawn;
import org.example.figure.Figure;
import org.example.figure.Queen;
import org.example.figure.Rook;

import java.util.ArrayList;

public class Board {
    public static ArrayList<Figure> black = new ArrayList<>();
    public static ArrayList<Figure> white = new ArrayList<>();

    static Figure[][] board = new Figure[8][8];

    static void printBoard() {
        System.out.println("    a   b   c   d   e   f   g   h");

        System.out.println("  --------------------------------------");
        int count = 8;
        for (int i = 0; i < 8; i++) {
            System.out.print(count + " ");
            System.out.print("| ");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print("  | ");
                } else {
                    System.out.print(board[i][j] + " | ");
                }
            }
            System.out.print(count);
            count--;
            System.out.println();
            System.out.println("  --------------------------------------");
        }
        System.out.println("    a   b   c   d   e   f   g   h");
        System.out.println();
    }

    static void startGame() {
        System.out.println("Как играть:");
        System.out.println("Для пешек, введи \"pawn\" с указанием буквы. Например, \"pawnA\"");
        System.out.println("Для слонов, ладей и коней необходимо добавить \"Q\" или \"K\" для уточнения с какой стороны находится фигура, Queen сторона или King сторона");
        System.out.println("далее необходимо поставить пробел и ввести адрес ячейки на которую переместить фигуру. Например, \"bishopK c4\"");

        init();
    }

    private static void init() {
        // black
        new Rook(Color.BLACK, "rookQ", 0, 0);
        new Knight(Color.BLACK, "knightQ", 1, 0);
        new Bishop(Color.BLACK, "bishopQ", 2, 0);
        new Queen(Color.BLACK, "queen", 3, 0);
        new King(Color.BLACK, "king", 4, 0);
        new Bishop(Color.BLACK, "bishopK", 5, 0);
        new Knight(Color.BLACK, "knightK", 6, 0);
        new Rook(Color.BLACK, "rookK", 7, 0);

        new Pawn(Color.BLACK, "pawnA", 0, 1);
        new Pawn(Color.BLACK, "pawnB", 1, 1);
        new Pawn(Color.BLACK, "pawnC", 2, 1);
        new Pawn(Color.BLACK, "pawnD", 3, 1);
        new Pawn(Color.BLACK, "pawnE", 4, 1);
        new Pawn(Color.BLACK, "pawnF", 5, 1);
        new Pawn(Color.BLACK, "pawnG", 6, 1);
        new Pawn(Color.BLACK, "pawnH", 7, 1);

        // white
        new Rook(Color.WHITE, "rookQ", 0, 7);
        new Knight(Color.WHITE, "knightQ", 1, 7);
        new Bishop(Color.WHITE, "bishopQ", 2, 7);
        new Queen(Color.WHITE, "queen", 3, 7);
        new King(Color.WHITE, "king", 4, 7);
        new Bishop(Color.WHITE, "bishopK", 5, 7);
        new Knight(Color.WHITE, "knightK", 6, 7);
        new Rook(Color.WHITE, "rookK", 7, 7);

        new Pawn(Color.WHITE, "pawnA", 0, 6);
        new Pawn(Color.WHITE, "pawnB", 1, 6);
        new Pawn(Color.WHITE, "pawnC", 2, 6);
        new Pawn(Color.WHITE, "pawnD", 3, 6);
        new Pawn(Color.WHITE, "pawnE", 4, 6);
        new Pawn(Color.WHITE, "pawnF", 5, 6);
        new Pawn(Color.WHITE, "pawnG", 6, 6);
        new Pawn(Color.WHITE, "pawnH", 7, 6);
    }

    public static void setFigure(int x, int y, Figure figure) {
        if (figure != null) {
            figure.setX(x);
            figure.setY(y);
        }
        board[y][x] = figure;
    }

    public static Figure getFigure(int x, int y) {
        return board[y][x];
    }

    public static Figure getFigure(String piece, Color color) {
        if (color == Color.WHITE) {
            return white.stream().filter(p -> p.equals(piece)).findFirst().orElse(null);
        } else if (color == Color.BLACK) {
            return black.stream().filter(p -> p.equals(piece)).findFirst().orElse(null);
        }
        return null;
    }

    public static boolean checkAvailable(int x1, int y1, int x2, int y2) {
        int xDistance = x2 - x1;
        int yDistance = y2 - y1;
        int xDir = 0;
        int yDir = 0;
        int size;

        if (xDistance < 0) {
            xDir = -1;
        } else if (xDistance > 0) {
            xDir = 1;
        }

        if (yDistance < 0) {
            yDir = -1;
        } else if (yDistance > 0) {
            yDir = 1;
        }

        if (xDistance != 0) {
            size = Math.abs(xDistance) - 1;
        } else {
            size = Math.abs(yDistance) - 1;
        }
        for (int i = 0; i < size; i++) {
            x1 += xDir; y1 += yDir;
            if (getFigure(x1, y1) != null) {
                return false;
            }
        }
        return true;

    }

    static int processMove(String move, Color color) {

        String[] splitStr = move.split(" ");
        String piece = splitStr[0];

        if (piece.equals("castle")) {
            King king = (King) getFigure("king", color);
            if (king == null) {
                return -1;
            }
            return king.castle(splitStr[1]);
        }

        Figure p = getFigure(piece, color);
        if (p == null) {
            System.out.println("invalid piece, please type in piece to move it.");
            return -1;
        }

        String coordinates = splitStr[1];
        if (coordinates.length() != 2) {
            System.out.println("Invalid Tile please try again");
            return -1;
        }

        int x = coordinates.charAt(0) - 'a';
        int y = 7 - (coordinates.charAt(1) - '1');

        if (y < 0 || y > 7 || x < 0 || x > 7) {
            System.out.println("Invalid Tile please try again");
            return -1;
        }

        return p.move(x, y, getFigure(x, y));

    }

    public static boolean checkForCheck(Color color) {
        Figure king = getFigure("king", color);
        if (king == null) {
            return false;
        }
        if (color == Color.WHITE) {
            return black.stream().anyMatch(p -> p.possibleMove(king.getX(), king.getY()));
        } else if (color == Color.BLACK) {
            return white.stream().anyMatch(p -> p.possibleMove(king.getX(), king.getY()));
        }
        return false;
    }

    public static boolean mate(Color color) {
        if (color == Color.WHITE) {
            return white.stream().noneMatch(Figure::canMove);
        } else if (color == Color.BLACK) {
            return black.stream().noneMatch(Figure::canMove);
        }
        return true;
    }

}
