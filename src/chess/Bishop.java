package chess;

import java.util.ArrayList;

public class Bishop extends Piece {
	public Bishop(String color) {
		super(color);
		super.setType("Bishop");
	}

	// source for possible moves Bishop
	// https://github.com/shd101wyy/Chess/blob/master/src/piece/Bishop.java
	public ArrayList<Tile> getMoves(Board board, Tile originTile) {
		int originX = originTile.getRow();
		int originY = originTile.getColumn();

		ArrayList<Tile> moves = new ArrayList<Tile>();

		int i, j;
		// go direction of left top
		for (i = originX - 1, j = originY + 1; i >= 0 && j <= 7; i--, j++) {
			if (addToMovesIfValid(moves, i, j, board)) // add to moves if valid; if this return true, then it meets
														// other pieces.
				break;
		}
		// go direction of right top
		for (i = originX + 1, j = originY + 1; i <= 7 && j <= 7; i++, j++) {
			if (addToMovesIfValid(moves, i, j, board)) // add to moves if valid; if this return true, then it meets
														// other pieces.
				break;
		}
		// go direction of left bottom
		for (i = originX - 1, j = originY - 1; i >= 0 && j >= 0; i--, j--) {
			if (addToMovesIfValid(moves, i, j, board)) // add to moves if valid; if this return true, then it meets
														// other pieces.
				break;
		}
		// go direction of right bottom
		for (i = originX + 1, j = originY - 1; i <= 7 && j >= 0; i++, j--) {
			if (addToMovesIfValid(moves, i, j, board)) // add to moves if valid; if this return true, then it meets
														// other pieces.
				break;
		}
		return moves;
	}
}
