package chess;

public class Tile 
{
	Piece piece;
	
	private String color;
	private int column;
	private int row;
	
	
	public Tile(int row, int column, String color)
	{
		this.color = color;
		this.row = row;
		this.column = column;
		this.piece = null;
	}

	public Piece getPiece() 
	{
		return piece;
	}

	public void setPiece(Piece piece) 
	{
		this.piece = piece;
	}

	public int getColumn() 
	{
		return column;
	}

	public int getRow() 
	{
		return row;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	
}
