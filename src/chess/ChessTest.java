package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChessTest
{
	private int count = 0;
	private JLabel label;
	private JFrame frame;
	private JPanel panel;
	private JButton button;
	
    private Color black = Color.decode("#8f0300"); 
    private Color white = Color.decode("#fad481"); 
	
	public ChessTest()
	{
		/*
		frame = new JFrame();
		
		panel = new JPanel();
		
		panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
		panel.setLayout(new GridLayout(8,8));
		panel.add(button);
		panel.add(label);
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Chess GUI");
		frame.pack();
		frame.setVisible(true);
		*/
	}
	
	public static void main(String[] args) 
	{
		ChessTest test = new ChessTest();
		test.testTiles();
		test.testPieceOnTiles();
	}
	public void testTiles()
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
	public void testPieceOnTiles()
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
