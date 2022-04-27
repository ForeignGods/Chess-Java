package chess;

import java.util.ArrayList;

public class King extends Piece {
	public King(String color) {
		super(color);
		super.setType("King");
	}

	// source for possible moves King
	// https://github.com/shd101wyy/Chess/blob/master/src/piece/King.java
	public ArrayList<Tile> getMoves(Board board, Tile originTile) {
		int originX = originTile.getRow();
		int originY = originTile.getColumn();

		ArrayList<Tile> moves = new ArrayList<Tile>();

		// check left top
		addToMovesIfValid(moves, originX - 1, originY + 1, board); // add to moves if valid.

		// check top
		addToMovesIfValid(moves, originX, originY + 1, board); // add to moves if valid.

		// check right top
		addToMovesIfValid(moves, originX + 1, originY + 1, board); // add to moves if valid.

		// check left
		addToMovesIfValid(moves, originX - 1, originY, board); // add to moves if valid.

		// check right
		addToMovesIfValid(moves, originX + 1, originY, board); // add to moves if valid.

		// check left bottom
		addToMovesIfValid(moves, originX - 1, originY - 1, board); // add to moves if valid.

		// check bottom
		addToMovesIfValid(moves, originX, originY - 1, board); // add to moves if valid.

		// check right bottom
		addToMovesIfValid(moves, originX + 1, originY - 1, board); // add to moves if valid.

		return moves;
	}
}
