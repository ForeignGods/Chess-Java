package chess;

import java.util.ArrayList;

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
	public ArrayList<Tile> getMoves(Board board, Tile originTile) 
	{
		return null;
	}
	//source for valid move check
	//https://github.com/shd101wyy/Chess/blob/master/src/piece/Piece.java
	public boolean addToMovesIfValid(ArrayList<Tile> coords, int x, int y, Board board)
	{
        if (x < 0 || y < 0 || x > 7 || y > 7)
        {
            return false;// invalid coordinate
        }
        if(board.getTiles()[x][y].getPiece() == null)
        {     // the square is not occupied by any piece
            coords.add(new Tile(x, y, null));
            return false;
        }
        else if(board.getTiles()[x][y].getPiece().getColor()!= this.getColor()) 
        {  // meet opponent's piece
            coords.add(new Tile(x, y, null));
            return true;
        }
        else
        {
        	return true;// meet player's own piece
        }   
    }
}
