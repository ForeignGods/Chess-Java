package chess;

/**
 * 
 * @author Pascal
 *
 */
public class King extends Piece {
	public King(String color) {
		super(color);
		super.setType("King");
	}

	// Inspiration for possible moves King
	// https://github.com/shd101wyy/Chess/blob/master/src/piece/King.java
	/**
	 * @return Tile[][]
	 */
	@Override
	public Tile[][] getMoves(Board board, Tile originTile) {
		int originX = originTile.getRow();
		int originY = originTile.getColumn();

		Tile[][] moves = new Tile[8][8];

		// check left top
		addToMovesIfValid(moves, originX - 1, originY + 1, board);

		// check top
		addToMovesIfValid(moves, originX, originY + 1, board);

		// check right top
		addToMovesIfValid(moves, originX + 1, originY + 1, board);

		// check left
		addToMovesIfValid(moves, originX - 1, originY, board);

		// check right
		addToMovesIfValid(moves, originX + 1, originY, board);

		// check left bottom
		addToMovesIfValid(moves, originX - 1, originY - 1, board);

		// check bottom
		addToMovesIfValid(moves, originX, originY - 1, board);

		// check right bottom
		addToMovesIfValid(moves, originX + 1, originY - 1, board);

		return moves;
	}
}
