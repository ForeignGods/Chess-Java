package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	}
	static void drawBoard()
	{
		
		Board board = new Board();
	    
		Color black = Color.decode("#8f0300"); 
	    Color white = Color.decode("#fad481"); 
		  
		JFrame frame = new JFrame();
		Dimension boardSize = new Dimension(800, 800);
	 
		JLayeredPane layeredPane = new JLayeredPane();
		frame.getContentPane().add(layeredPane);
		layeredPane.setPreferredSize(boardSize);

		JPanel chessBoard = new JPanel();
		layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
		chessBoard.setLayout(new GridLayout(8, 8));
		chessBoard.setPreferredSize(boardSize);
	  	chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);
	  	String path="";	 
		
		for (int row = 0; row < board.getTiles().length; row++)
		{
			for (int col = 0; col < board.getTiles()[row].length; col++)
		    {
				JPanel tile = new JPanel(new BorderLayout());
				chessBoard.add(tile);
					if(board.getTiles()[row][col].getColor()=="Black")//checkt ob feld schwarz
					{
						tile.setBackground(black);//schwarzes Feld zeichenen
						
						if(board.getTiles()[row][col].getPiece()!=null)//checkt ob feld figur hat
						{
					        
							if(board.getTiles()[row][col].getPiece().getColor()=="White")//wählt Pfad für weisse Figuren
							{
						        switch (board.getTiles()[row][col].getPiece().getType())
						        {
						            case "Pawn":
						            	path="src\\chess\\imgs\\PawnWhite.png";
						                break;
						            case "Rook":
						            	path="src\\chess\\imgs\\RookWhite.png";
						                break;
						            case "Knight":
						            	path="src\\chess\\imgs\\KnightWhite.png";
						                break;      
						            case "Bishop":
						            	path="src\\chess\\imgs\\BishopWhite.png";
						                break;
						            case "King":
						            	path="src\\chess\\imgs\\KingWhite.png";
						                break;
						            case "Queen":
						            	path="src\\chess\\imgs\\QueenWhite.png";
						                break;
						        }	
							}
							else//wählt Pfad für schwarze Figuren
							{
						        switch (board.getTiles()[row][col].getPiece().getType())
						        {
						            case "Pawn":
						            	path="src\\chess\\imgs\\PawnBlack.png";
						                break;
						            case "Rook":
						            	path="src\\chess\\imgs\\RookBlack.png";
						                break;
						            case "Knight":
						            	path="src\\chess\\imgs\\KnightBlack.png";
						                break;      
						            case "Bishop":
						            	path="src\\chess\\imgs\\BishopBlack.png";
						                break;
						            case "King":
						            	path="src\\chess\\imgs\\KingBlack.png";
						                break;
						            case "Queen":
						            	path="src\\chess\\imgs\\QueenBlack.png";
						                break;
						        }
							}
	
							try
							{
								File imgFile = new File(path);
								BufferedImage img = ImageIO.read(imgFile);
								JLabel pic = new JLabel(new ImageIcon(img));
								tile.add(pic);
							}
							catch(Exception e)
							{
								System.out.print("e1");
							}
						}
						else
						{
							//hat keine Figur auf Feld
						}
					}
					else // alle weissen felder
					{
						tile.setBackground(white);//weisses Feld zeichnen
						
						if(board.getTiles()[row][col].getPiece()!=null)//checkt ob feld figur hat
						{
							if(board.getTiles()[row][col].getPiece().getColor()=="White")//alle weissen Figuren
							{
						        switch (board.getTiles()[row][col].getPiece().getType())
						        {
						            case "Pawn":
						            	path="src\\chess\\imgs\\PawnWhite.png";
						                break;
						            case "Rook":
						            	path="src\\chess\\imgs\\RookWhite.png";
						                break;
						            case "Knight":
						            	path="src\\chess\\imgs\\KnightWhite.png";
						                break;      
						            case "Bishop":
						            	path="src\\chess\\imgs\\BishopWhite.png";
						                break;
						            case "King":
						            	path="src\\chess\\imgs\\KingWhite.png";
						                break;
						            case "Queen":
						            	path="src\\chess\\imgs\\QueenWhite.png";
						                break;
						        }	
							}
							else//alle schwarze Figuren
							{
						        switch (board.getTiles()[row][col].getPiece().getType())
						        {
						            case "Pawn":
						            	path="src\\chess\\imgs\\PawnBlack.png";
						                break;
						            case "Rook":
						            	path="src\\chess\\imgs\\RookBlack.png";
						                break;
						            case "Knight":
						            	path="src\\chess\\imgs\\KnightBlack.png";
						                break;      
						            case "Bishop":
						            	path="src\\chess\\imgs\\BishopBlack.png";
						                break;
						            case "King":
						            	path="src\\chess\\imgs\\KingBlack.png";
						                break;
						            case "Queen":
						            	path="src\\chess\\imgs\\QueenBlack.png";
						                break;
						        }
							}
							try
							{
								File imgFile = new File(path);
								BufferedImage img = ImageIO.read(imgFile);
								JLabel pic = new JLabel(new ImageIcon(img));
								tile.add(pic);
							}
							catch(Exception e)
							{
								System.out.print("e1");
							}
						}
						else
						{
							//hat keine Figur auf diesem Fled
						}
					}
		    }	
		} 
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Chess GUI");
		frame.pack();
		frame.setVisible(true);
	}
}
