package chess;

public class Piece 
{
	private String color;
	private String type;
	private boolean alive;
	private Tile tile;
	
	public Piece(String color)
	{
		this.color = color;
	}
	
	public String getColor() 
	{
		return color;
	}
	
	public String getType() 
	{
		return type;
	}
	
	public boolean isAlive() 
	{
		return alive;
	}
	
	public void setAlive(boolean alive) 
	{
		this.alive = alive;
	}

	public void setType(String type) 
	{
		this.type = type;
	}

	public Tile getTile() 
	{
		return tile;
	}

	public void setTile(Tile tile) 
	{
		this.tile = tile;
	}
}
