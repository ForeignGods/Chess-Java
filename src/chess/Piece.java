package chess;

/**
 * 
 * @author Pascal
 *
 */
public class Piece {
	private String color;
	private String type;
	private boolean alive;

	/**
	 * 
	 * @param color
	 */
	public Piece(String color) {
		this.color = color;
	}

	/**
	 * 
	 * @return String
	 */
	public String getColor() {
		return color;
	}

	/**
	 * 
	 * @return String
	 */
	public String getType() {
		return type;
	}

	/**
	 * 
	 * @return boolean
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * 
	 * @param alive
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	/**
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 
	 * @param board
	 * @param originTile
	 * @return Tile [][]
	 */
	public Tile[][] getMoves(Board board, Tile originTile) {
		return null;
	}

	// Inspiration for valid move check
	// https://github.com/shd101wyy/Chess/blob/master/src/piece/Piece.java
	/**
	 * 
	 * @param coords
	 * @param x
	 * @param y
	 * @param board
	 * @return boolean
	 */
	public boolean addToMovesIfValid(Tile[][] coords, int x, int y, Board board) {
		if (x < 0 || y < 0 || x > 7 || y > 7) {
			System.out.println("invalid coordinate");
			return false;// invalid coordinate
		}
		if (board.getTiles()[x][y].getPiece() == null) {
			// the square is not occupied by any piece
			coords[x][y] = new Tile(x, y, null);
			return false;
		} else if (board.getTiles()[x][y].getPiece().getColor() != this.getColor()) { // meet opponent's piece
			coords[x][y] = new Tile(x, y, null);
			return true;
		} else {
			return true;// meet player's own piece
		}
	}

	public void setFirstMover(boolean b) {

	}

}
