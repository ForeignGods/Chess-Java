package chess;

public class Move 
{
	Tile originTile;
	Tile destinationTile;
	boolean valid;
	Piece movingPiece;
	Piece capturingPiece;
	Player currentPlayer;
	
	public void changeCurrentPlayer(Player player)
	{
		
	}
	
	public Tile getOriginTile() 
	{
		return originTile;
	}
	
	public Tile getDestinationTile() 
	{
		return destinationTile;
	}
	
	public boolean isValid() 
	{
		return valid;
	}
	
	public Piece getMovingPiece() 
	{
		return movingPiece;
	}
	
	public Piece getCapturingPiece() 
	{
		return capturingPiece;
	}

	public Player getCurrentPlayer() 
	{
		return currentPlayer;
	}
	
}
