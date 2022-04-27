package chess;

import java.util.ArrayList;

public class Rook extends Piece {
	public Rook(String color) {
		super(color);
		super.setType("Rook");
	}

	// source for possible moves Rook
	// https://github.com/shd101wyy/Chess/blob/master/src/piece/Rook.java
	public ArrayList<Tile> getMoves(Board board, Tile originTile) {
		int originX = originTile.getRow();
		int originY = originTile.getColumn();

		ArrayList<Tile> moves = new ArrayList<Tile>();

		// check left
		for (int i = originX - 1; i >= 0; i--) {
			if (addToMovesIfValid(moves, i, originY, board)) // add to moves if valid; if this return true, then it
																// meets other pieces.
				break;
		}
		// check right
		for (int i = originX + 1; i <= 7; i++) {
			if (addToMovesIfValid(moves, i, originY, board)) // add to moves if valid; if this return true, then it
																// meets other pieces.
				break;
		}
		// check above
		for (int i = originY + 1; i <= 7; i++) {
			if (addToMovesIfValid(moves, originX, i, board)) // add to moves if valid; if this return true, then it
																// meets other pieces.
				break;
		}
		// check below
		for (int i = originY - 1; i >= 0; i--) {
			if (addToMovesIfValid(moves, originX, i, board)) // add to moves if valid; if this return true, then it
																// meets other pieces.
				break;
		}
		return moves;
	}
}
