package chess;

/**
 * 
 * @author Pascal
 *
 */
public class Tile {
	Piece piece;

	private String color;
	private int column;
	private int row;

	/**
	 * 
	 * @param row
	 * @param column
	 * @param color
	 */
	public Tile(int row, int column, String color) {
		this.color = color;
		this.row = row;
		this.column = column;
		this.piece = null;
	}

	/**
	 * 
	 * @return Piece
	 */
	public Piece getPiece() {
		return piece;
	}

	/**
	 * 
	 * @param piece
	 */
	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	/**
	 * 
	 * @return int
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * 
	 * @return int
	 */
	public int getRow() {
		return row;
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
	 * @param color
	 */
	public void setColor(String color) {
		this.color = color;
	}

}
