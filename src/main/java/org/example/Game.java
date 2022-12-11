package org.example;

import java.util.Scanner;

public class Game {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			Board.startGame();

			int turns = 0;
			Color color;

			while (true) {
				Board.printBoard();
				if (turns % 2 == 0) {
					color = Color.WHITE;
				} else
					color = Color.BLACK;
				if (Board.checkForCheck(color)) {
					if (Board.mate(color)) {
						System.out.printf("Checkmate, %s wins \n", color == Color.WHITE ? "Black" : "White");
						break;
					}
					System.out.printf("%s is in Check! \n", color == Color.WHITE ? "White" : "Black");
				}

				System.out.printf("%s's turn \n", color == Color.WHITE ? "White" : "Black");

				String move = scanner.nextLine();

				if (Board.processMove(move, color) == 0) {
					turns++;
				} else {
					System.out.println("illegal move");
				}
			}
			System.out.println("would you like to play again? y/n");
			if (!scanner.next().equals("y"))
				System.exit(0);
		}
	}

}
