package org.example.figure;

import org.example.Board;
import org.example.Color;

import static org.example.ColorUtils.getFigureSymbol;

public class Knight extends Figure {

	public Knight(Color color, String ID, int startX, int startY) {
		super(color, ID, startX, startY);
	}

	@Override
	public boolean possibleMove(int x, int y) {
		// cannot capture own piece
		if (this.colorEquals(Board.getFigure(x, y))) {
			return false;
		}

		return Math.abs(this.getY() - y) == 2 && Math.abs(this.getX() - x) == 1
				|| Math.abs(this.getY() - y) == 1 && Math.abs(this.getX() - x) == 2;
	}

	@Override
	public String toString() {
		return getFigureSymbol(this.getColor(), "knight");
	}

	@Override
	public boolean canMove() {

		int x = this.getX();
		int y = this.getY();

		// left & up
		if (this.testMove(x - 2, y - 1)) {
			return true;
		}
		if (this.testMove(x - 1, y - 2)) {
			return true;
		}

		// right & up
		if (this.testMove(x + 2, y - 1)) {
			return true;
		}
		if (this.testMove(x + 1, y - 2)) {
			return true;
		}

		// left & down
		if (this.testMove(x - 2, y + 1)) {
			return true;
		}
		if (this.testMove(x - 1, y + 2)) {
			return true;
		}

		// right & down
		if (this.testMove(x + 2, y + 1)) {
			return true;
		}
		return this.testMove(x + 1, y + 2);
	}

}
