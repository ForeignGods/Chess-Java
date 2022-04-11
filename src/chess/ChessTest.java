package chess;

public class ChessTest {

	public static void main(String[] args) 
	{
		Board board = new Board();
		
		
		for (int row = 0; row < board.getBoard().length; row++)
		{
			for (int col = 0; col < board.getBoard()[row].length; col++)
		    {
				try 
				{
					System.out.print(board.getBoard()[row][col].getPiece().getColor());
					System.out.print(board.getBoard()[row][col].getPiece().getType());
				}
				catch(Exception e) 
				{
					System.out.print("leer");
				}

		    }
			
			System.out.println(); 
		}

	}

}
