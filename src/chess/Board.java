package chess;

public class Board 
{
	private Tile[][] tiles;
	
	public Board()
	{
		tiles = new Tile[8][8];
	
		for (int row = 0; row < tiles.length; row++)
		{
			for (int col = 0; col < tiles[row].length; col++)
		    {
				tiles[row][col] = new Tile(row, col); 
		    }
		}
		
		tiles[0][0].setPiece(new Rook("White")); 
		tiles[0][1].setPiece(new Knight("White")); 
		tiles[0][2].setPiece(new Bishop("White")); 
		tiles[0][3].setPiece(new King("White")); 
		tiles[0][4].setPiece(new Queen("White")); 
		tiles[0][5].setPiece(new Bishop("White")); 
		tiles[0][6].setPiece(new Knight("White")); 
		tiles[0][7].setPiece(new Rook("White")); 
		
		for (int i = 0; i < tiles.length; i++)
		{
			tiles[1][i].setPiece(new Pawn("White")); 
		}
		
		tiles[7][0].setPiece(new Rook("Black")); 
		tiles[7][1].setPiece(new Knight("Black")); 
		tiles[7][2].setPiece(new Bishop("Black")); 
		tiles[7][3].setPiece(new King("Black")); 
		tiles[7][4].setPiece(new Queen("Black")); 
		tiles[7][5].setPiece(new Bishop("Black")); 
		tiles[7][6].setPiece(new Knight("Black")); 
		tiles[7][7].setPiece(new Rook("Black")); 
		
		for (int i = 0; i < tiles.length; i++)
		{
			tiles[6][i].setPiece(new Pawn("Black")); 
		}
		
	}

	public Tile[][] getBoard() 
	{
		return tiles;
	}
	
}
