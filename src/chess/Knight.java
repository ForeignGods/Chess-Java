package chess;

import java.util.ArrayList;

public class Knight extends Piece {
	public Knight(String color) {
		super(color);
		super.setType("Knight");
	}

	// source for possible moves Knight
	// https://github.com/shd101wyy/Chess/blob/master/src/piece/Knight.java
	public ArrayList<Tile> getMoves(Board board, Tile originTile) {
		int originX = originTile.getRow();
		int originY = originTile.getColumn();

		ArrayList<Tile> moves = new ArrayList<Tile>();
		int x, y;
		/*
		 * several cases 2 3 1 4 5 8 6 7
		 */
		// case1
		x = originX - 2;
		y = originY + 1;
		if (x >= 0 && y <= 7) {
			addToMovesIfValid(moves, x, y, board); // add to moves if the piece can move to that coordinate
		}
		// case2
		x = originX - 1;
		y = originY + 2;
		if (x >= 0 && y <= 7) {
			addToMovesIfValid(moves, x, y, board); // add to moves if the piece can move to that coordinate
		}
		// case3
		x = originX + 1;
		y = originY + 2;
		if (x <= 7 && y <= 7) {
			addToMovesIfValid(moves, x, y, board); // add to moves if the piece can move to that coordinate
		}
		// case4
		x = originX + 2;
		y = originY + 1;
		if (x <= 7 && y <= 7) {
			addToMovesIfValid(moves, x, y, board); // add to moves if the piece can move to that coordinate
		}
		// case5
		x = originX - 2;
		y = originY - 1;
		if (x >= 0 && y >= 0) {
			addToMovesIfValid(moves, x, y, board); // add to moves if the piece can move to that coordinate
		}
		// case6
		x = originX - 1;
		y = originY - 2;
		if (x >= 0 && y >= 0) {
			addToMovesIfValid(moves, x, y, board); // add to moves if the piece can move to that coordinate
		}
		// case7
		x = originX + 1;
		y = originY - 2;
		if (x <= 7 && y >= 0) {
			addToMovesIfValid(moves, x, y, board); // add to moves if the piece can move to that coordinate
		}
		// case1
		x = originX + 2;
		y = originY - 1;
		if (x <= 7 && y >= 0) {
			addToMovesIfValid(moves, x, y, board); // add to moves if the piece can move to that coordinate
		}
		return moves;
	}
}
