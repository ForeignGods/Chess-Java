package chess;

/**
 *
 * @author Pascal
 *
 */
public class Bishop extends Piece {
	/**
	 *
	 * @param color
	 */
	public Bishop(String color) {
		super(color);
		super.setType("Bishop");
	}

	// Inspiration for possible moves Bishop
	// https://github.com/shd101wyy/Chess/blob/master/src/piece/Bishop.java
	/**
	 * @return Tile[][]
	 */
	@Override
	public Tile[][] getMoves(Board board, Tile originTile) {
		int originX = originTile.getRow();
		int originY = originTile.getColumn();

		Tile[][] moves = new Tile[8][8];

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
