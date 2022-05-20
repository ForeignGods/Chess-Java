package chess;

/**
 * 
 * @author Pascal
 *
 */
public class Knight extends Piece {
	public Knight(String color) {
		super(color);
		super.setType("Knight");
	}

	// Inspiration for possible moves Knight
	// https://github.com/shd101wyy/Chess/blob/master/src/piece/Knight.java
	/**
	 * @return Tile [][]
	 */
	@Override
	public Tile[][] getMoves(Board board, Tile originTile) {
		int originX = originTile.getRow();
		int originY = originTile.getColumn();

		Tile[][] moves = new Tile[8][8];
		int x, y;
		x = originX - 2;
		y = originY + 1;
		if (x >= 0 && y <= 7) {
			addToMovesIfValid(moves, x, y, board);
		}
		x = originX - 1;
		y = originY + 2;
		if (x >= 0 && y <= 7) {
			addToMovesIfValid(moves, x, y, board);
		}
		x = originX + 1;
		y = originY + 2;
		if (x <= 7 && y <= 7) {
			addToMovesIfValid(moves, x, y, board);
		}
		x = originX + 2;
		y = originY + 1;
		if (x <= 7 && y <= 7) {
			addToMovesIfValid(moves, x, y, board);
		}
		x = originX - 2;
		y = originY - 1;
		if (x >= 0 && y >= 0) {
			addToMovesIfValid(moves, x, y, board);
		}
		x = originX - 1;
		y = originY - 2;
		if (x >= 0 && y >= 0) {
			addToMovesIfValid(moves, x, y, board);
		}
		x = originX + 1;
		y = originY - 2;
		if (x <= 7 && y >= 0) {
			addToMovesIfValid(moves, x, y, board);
		}
		x = originX + 2;
		y = originY - 1;
		if (x <= 7 && y >= 0) {
			addToMovesIfValid(moves, x, y, board);
		}
		return moves;
	}
}
