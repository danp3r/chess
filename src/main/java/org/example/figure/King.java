package org.example.figure;

import org.example.Board;
import org.example.Color;

public class King extends Figure {

	public King(Color color, String ID, int startX, int startY) {
		super(color, ID, startX, startY);
	}

	boolean isFirstMove = true;

	@Override
	public boolean possibleMove(int x, int y) {
		// cannot capture own piece
		if (this.colorEquals(Board.getFigure(x, y))) {
			return false;
		}
		// bishop
		else if (Math.abs(getX() - x) == 1 && Math.abs(getY() - y) == 1) {
			return true;
		}
		// rook
		else if (Math.abs(getX() - x) == 1 && Math.abs(getY() - y) == 0
				|| Math.abs(getX() - x) == 0 && Math.abs(getY() - y) == 1) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		if (this.getColor() == Color.WHITE) {
			return "♔";
		}
		return "♚";
	}

	public int castle(String side) {
		Rook rook = (Rook) Board.getFigure("rook" + side, this.getColor());
		int originX = this.getX();
		int originY = this.getY();

		if (!this.isFirstMove || !rook.isFirstMove) {
			System.out.println("Cannot castle if king or rook has already moved");
			return -1;
		}
		if (!Board.checkAvailable(this.getX(), this.getY(), rook.getX(), rook.getY())) {
			System.out.println("Cannot castle across a line of check");
			return -1;
		}

		if (this.getColor() == Color.WHITE) {

			if (side.equals("K")) {
				// cant castle accross a line of check
				if (this.move(5, 7, null) == 0 && this.move(6, 7, null) == 0) {
					Board.setFigure(rook.getX(), rook.getY(), null);
					Board.setFigure(5, 7, rook);
					return 0;
				} else {
					Board.setFigure(this.getX(), this.getY(), null);
					Board.setFigure(originX, originY, this);
					return -1;
				}
			}

			else if (side.equals("Q")) {
				if (this.move(3, 7, null) == 0 && this.move(2, 7, null) == 0) {
					Board.setFigure(rook.getX(), rook.getY(), null);
					Board.setFigure(3, 7, rook);
					return 0;
				} else {
					Board.setFigure(this.getX(), this.getY(), null);
					Board.setFigure(originX, originY, this);
					return -1;
				}
			}
		}

		if (this.getColor() == Color.BLACK) {
			if (side.equals("K")) {
				if (this.move(5, 0, null) == 0 && this.move(6, 0, null) == 0) {
					Board.setFigure(rook.getX(), rook.getY(), null);
					Board.setFigure(5, 0, rook);
					return 0;
				} else {
					Board.setFigure(this.getX(), this.getY(), null);
					Board.setFigure(originX, originY, this);
					return -1;
				}
			}

			else if (side.equals("Q")) {
				if (this.move(3, 0, null) == 0 && this.move(2, 0, null) == 0) {
					Board.setFigure(rook.getX(), rook.getY(), null);
					Board.setFigure(3, 0, rook);
					return 0;
				} else {
					Board.setFigure(this.getX(), this.getY(), null);
					Board.setFigure(originX, originY, this);
					return -1;
				}
			}
		}
		return -1;
	}

	@Override
	public boolean canMove() {
		int x = this.getX();
		int y = this.getY();

		// bishop
		// top left
		if (this.testMove(x - 1, y - 1)) {
			return true;
		}
		// top right
		if (this.testMove(x + 1, y - 1)) {
			return true;
		}
		// bottom left
		if (this.testMove(x - 1, y + 1)) {
			return true;
		}
		// bottom right
		if (this.testMove(x + 1, y + 1)) {
			return true;
		}

		// rook
		// left
		if (this.testMove(x - 1, y)) {
			return true;
		}
		// right
		if (this.testMove(x + 1, y)) {
			return true;
		}
		// down
		if (this.testMove(x, y + 1)) {
			return true;
		}
		// up
		return this.testMove(x, y - 1);
	}

}
