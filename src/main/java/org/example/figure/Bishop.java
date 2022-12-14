package org.example.figure;

import org.example.Board;
import org.example.Color;

import static org.example.ColorUtils.getFigureSymbol;

public class Bishop extends Figure {

	public Bishop(Color color, String ID, int startX, int startY) {
		super(color, ID, startX, startY);
	}

	@Override
	public boolean possibleMove(int x, int y) {
		// cannot capture own piece
		if (this.colorEquals(Board.getFigure(x, y))) {
			return false;
		}
		// invalid move for bishop
		if (Math.abs(getX() - x) != Math.abs(getY() - y)) {
			return false;
		}

		return Board.checkAvailable(getX(), getY(), x, y);
	}

	@Override
	public String toString() {
		return getFigureSymbol(this.getColor(), "bishop");
	}

	@Override
	public boolean canMove() {

		int originX = this.getX();
		int originY = this.getY();

		// reset x and y to original position after each while loop
		int x = originX;
		int y = originY;

		// top left
		while ((--x) >= 0 && (--y) >= 0) {
			if (this.testMove(x, y)) {
				return true;
			}
		}
		x = originX;
		y = originY;
		// top right
		while ((++x) <= 7 && (--y) >= 0) {
			if (this.testMove(x, y)) {
				return true;
			}
		}
		x = originX;
		y = originY;
		// bottom left
		while ((--x) >= 0 && (++y) <= 7) {
			if (this.testMove(x, y)) {
				return true;
			}
		}
		x = originX;
		y = originY;
		// bottom right
		while ((++x) <= 7 && (++y) <= 7) {
			if (this.testMove(x, y)) {
				return true;
			}
		}
		return false;
	}

}
