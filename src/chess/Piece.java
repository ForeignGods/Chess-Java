package chess;

public class Piece 
{
	private String color;
	private String type;
	private boolean alive;
	
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

}
