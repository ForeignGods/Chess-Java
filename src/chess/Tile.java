package chess;

public class Tile 
{
	Piece piece;
	int column;
	int row;
	
	public Tile(int row, int column, Piece piece)
	{
		this.row = row;
		this.column = column;
		this.piece = piece;
	}
	
	public Tile(int row, int column)
	{
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
	
	
}
