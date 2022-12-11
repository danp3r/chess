package org.example.figure;

import org.example.Board;
import org.example.Color;

import static org.example.ColorUtils.getFigureSymbol;

public class Rook extends Figure {

	public Rook(Color color, String ID, int startX, int startY) {
		super(color, ID, startX, startY);
	}

	boolean isFirstMove = true;


	@Override
	public boolean possibleMove(int x, int y) {
		// cannot capture own piece
		if (this.colorEquals(Board.getFigure(x, y))) {
			return false;
		}
		// invalid move for rook
		if (Math.abs(getX() - x) != 0 && Math.abs(getY() - y) != 0) {
			return false;
		}

		return Board.checkAvailable(getX(), getY(), x, y);
	}

	@Override
	public String toString() {
		return getFigureSymbol(this.getColor(), "rook");
	}

	@Override
	public boolean canMove() {
		int x = this.getX();
		int y = this.getY();

		// left
		while ((--x) >= 0 && y >= 0) {
			if (this.testMove(x, y)) {
				return true;
			}
		}
		x = this.getX();
		y = this.getY();
		// right
		while ((++x) <= 7 && y >= 0) {
			if (this.testMove(x, y)) {
				return true;
			}
		}
		x = this.getX();
		y = this.getY();
		// down
		while (x >= 0 && (++y) <= 7) {
			if (this.testMove(x, y)) {
				return true;
			}
		}
		x = this.getX();
		y = this.getY();
		// up
		while (x <= 7 && (--y) >= 0) {
			if (this.testMove(x, y)) {
				return true;
			}
		}

		return false;
	}

}
