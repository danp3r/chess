package org.example.figure;

import org.example.Board;
import org.example.Color;

public class Pawn extends Figure {

	public Pawn(Color color, String ID, int startX, int startY) {
		super(color, ID, startX, startY);
	}

	boolean isFirstMove = true;

	@Override
	public boolean possibleMove(int x, int y) {
		if (this.getColor() == Color.WHITE) {

			// 2 spaces forward
			if (this.isFirstMove && this.getY() - y == 2 && this.getX() - x == 0
					&& Board.checkAvailable(getX(), getY(), x, y) && Board.getFigure(x, y) == null) {
				return true;
			}
			// 1 space forward
			if (this.getY() - y == 1 && this.getX() - x == 0 && Board.getFigure(x, y) == null) {
				return true;
			}

			// diagonal
			if (this.getY() - y == 1 && Math.abs(this.getX() - x) == 1 && Board.getFigure(x, y) != null
					&& !this.colorEquals(Board.getFigure(x, y))) {
				return true;
			}
		}

		if (this.getColor() == Color.BLACK) {
			// 2 spaces forward
			if (this.isFirstMove && this.getY() - y == -2 && this.getX() - x == 0
					&& Board.checkAvailable(getX(), getY(), x, y) && Board.getFigure(x, y) == null) {
				return true;
			}
			// 1 space forward
			if (this.getY() - y == -1 && this.getX() - x == 0 && Board.getFigure(x, y) == null) {
				return true;
			}

			// diagonal
			return this.getY() - y == -1 && Math.abs(this.getX() - x) == 1 && Board.getFigure(x, y) != null
					&& !this.colorEquals(Board.getFigure(x, y));
		}
		return false;
	}

	@Override
	public String toString() {
		if (this.getColor() == Color.WHITE) {
			return "♙";
		}
		return "♟";
	}

	@Override
	public boolean canMove() {
		int x = this.getX();
		int y = this.getY();

		if (this.getColor() == Color.WHITE) {

			if (this.testMove(x, y - 1)) {
				return true;
			}

			if (this.testMove(x, y - 2)) {
				return true;
			}

			if (this.testMove(x - 1, y - 1)) {
				return true;
			}

			if (this.testMove(x + 1, y - 1)) {
				return true;
			}

		}
		if (this.getColor() == Color.BLACK) {

			if (this.testMove(x, y + 1)) {
				return true;
			}

			if (this.testMove(x, y + 2)) {
				return true;
			}

			if (this.testMove(x - 1, y - 1)) {
				return true;
			}

			return this.testMove(x + 1, y + 1);
		}

		return false;
	}

}
