package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class ChessTest
{
	
	public ChessTest()
	{
		
	}
	public static void main(String[] args) 
	{
		drawBoard();
		testTiles();
		testPieceOnTiles();
	}
	static void drawBoard()
	{
		Board board = new Board();
	    
		Color black = Color.decode("#8f0300"); 
	    Color white = Color.decode("#fad481"); 
		  
		JFrame frame = new JFrame();
		Dimension boardSize = new Dimension(600, 600);
	 
		JLayeredPane layeredPane = new JLayeredPane();
		frame.getContentPane().add(layeredPane);
		layeredPane.setPreferredSize(boardSize);

		JPanel chessBoard = new JPanel();
		layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
		chessBoard.setLayout(new GridLayout(8, 8));
		chessBoard.setPreferredSize(boardSize);
	  	chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);
		 	 
		
		for (int row = 0; row < board.getTiles().length; row++)
		{
			for (int col = 0; col < board.getTiles()[row].length; col++)
		    {
				JPanel tile = new JPanel(new BorderLayout());
				chessBoard.add(tile);
				if(board.getTiles()[row][col].getColor()=="Black")
				{
					tile.setBackground(black);
				}
				else
				{
					tile.setBackground(white);
				}
		    }	
		}   
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Chess GUI");
		frame.pack();
		frame.setVisible(true);
	}
	static void testTiles()
	{
		Board board = new Board();
		
		for (int row = 0; row < board.getTiles().length; row++)
		{
			for (int col = 0; col < board.getTiles()[row].length; col++)
		    {
				try 
				{
					System.out.print(board.getTiles()[row][col].getColor());
					System.out.print(board.getTiles()[row][col].getColumn());
					System.out.print(board.getTiles()[row][col].getRow());
				}
				catch(Exception e) 
				{
					System.out.print("leer");
				}

		    }			
			System.out.println(); 
		}
	}
	static void testPieceOnTiles()
	{
		Board board = new Board();
		
		for (int row = 0; row < board.getTiles().length; row++)
		{
			for (int col = 0; col < board.getTiles()[row].length; col++)
		    {
				try 
				{
					System.out.print(board.getTiles()[row][col].getPiece().getColor());
					System.out.print(board.getTiles()[row][col].getPiece().getType());
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
