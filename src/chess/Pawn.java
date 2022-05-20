package chess;

/**
 * 
 * @author Pascal
 *
 */
public class Pawn extends Piece {

	private boolean firstMover;

	public Pawn(String color) {
		super(color);
		super.setType("Pawn");
		firstMover = true;
	}

	/**
	 * @return Tile[][]
	 */
	@Override
	public Tile[][] getMoves(Board board, Tile originTile) {
		int originX = originTile.getRow();
		int originY = originTile.getColumn();

		Tile[][] moves = new Tile[8][8];

		if (originTile.getPiece().getColor() != "Black") {
			try {
				if (board.getTiles()[originX + 1][originY].getPiece() == null) {
					addToMovesIfValid(moves, originX + 1, originY, board);
					if (firstMover == true) {
						addToMovesIfValid(moves, originX + 2, originY, board);
					}
				}
				if (board.getTiles()[originX + 1][originY + 1].getPiece() != null
						&& board.getTiles()[originX + 1][originY + 1].getPiece()
								.getColor() != board.getTiles()[originX][originY].getPiece().getColor()) {
					addToMovesIfValid(moves, originX + 1, originY + 1, board);
				}
				if (board.getTiles()[originX + 1][originY - 1].getPiece() != null
						&& board.getTiles()[originX + 1][originY - 1].getPiece()
								.getColor() != board.getTiles()[originX][originY].getPiece().getColor()) {
					addToMovesIfValid(moves, originX + 1, originY - 1, board);
				}
			} catch (Exception e) {

			}

		} else {
			try {
				if (board.getTiles()[originX - 1][originY].getPiece() == null) {
					addToMovesIfValid(moves, originX - 1, originY, board);
					if (firstMover == true) {
						addToMovesIfValid(moves, originX - 2, originY, board);
					}
				}
				if (board.getTiles()[originX - 1][originY + 1].getPiece() != null
						&& board.getTiles()[originX - 1][originY + 1].getPiece()
								.getColor() != board.getTiles()[originX][originY].getPiece().getColor()) {
					addToMovesIfValid(moves, originX - 1, originY + 1, board);
				}
				if (board.getTiles()[originX - 1][originY - 1].getPiece() != null
						&& board.getTiles()[originX - 1][originY - 1].getPiece()
								.getColor() != board.getTiles()[originX][originY].getPiece().getColor()) {
					addToMovesIfValid(moves, originX - 1, originY - 1, board);
				}
			} catch (Exception e) {

			}
		}
		return moves;
	}

	/**
	 * 
	 * @param firstMover
	 */
	@Override
	public void setFirstMover(boolean firstMover) {
		this.firstMover = firstMover;
	}

}
