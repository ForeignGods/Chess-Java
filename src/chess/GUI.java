package chess;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class GUI 
{
	private JFrame frame;
	private Dimension boardSize;
	private JLayeredPane layeredPane;
	private JPanel chessBoard;
	private JPanel[][] tiles;
	
	private Board board;
	
	public GUI(Board board)
	{
		this.frame = new JFrame();
		this.boardSize = new Dimension(800, 800);
		this.layeredPane = new JLayeredPane();
		chessBoard = new JPanel();
		
		tiles = new JPanel[8][8];
		
		this.board = board;
		
		frame.getContentPane().add(layeredPane);
		
		layeredPane.setPreferredSize(boardSize);
		layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
		
		chessBoard.setLayout(new GridLayout(8, 8));
		chessBoard.setPreferredSize(boardSize);
	  	chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);
	  	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Chess GUI");
		frame.setVisible(true);
        
		MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
        layeredPane.addMouseListener(myMouseAdapter);
        layeredPane.addMouseMotionListener(myMouseAdapter);
	}
	
	//Source for MyMouseAdapter Class
	//https://stackoverflow.com/questions/4893265/dragging-a-jlabel-around-the-screen
	private class MyMouseAdapter extends MouseAdapter
	{
		private JLabel dragLabel = null;
		private JLabel droppedLabel = null;
        private int dragLabelWidthDiv2;
        private int dragLabelHeightDiv2;
        private JPanel clickedPanel = null;
        private JPanel droppedPanel = null;
        private Piece originPiece;
        private Piece targetPiece;

        @Override
	    public void mousePressed(MouseEvent me) 
	    {
	        clickedPanel = (JPanel) chessBoard.getComponentAt(me.getPoint());
	        Component[] components = clickedPanel.getComponents();
	        
	        
			for (int row = 0; row < board.getTiles().length; row++)
			{
				for (int col = 0; col < board.getTiles()[row].length; col++)
			    {
		        	if (getTiles()[row][col] == chessBoard.getComponentAt(me.getPoint()))
		            {
			        	//if zusammenfügen
		        		if(board.getTiles()[row][col].getPiece()!=null)
			        	{
		        			//get selected Piece
			        		originPiece = board.getTiles()[row][col].getPiece();
			        		//remove selectedPiece from Origin Tile before moving
			        		board.getTiles()[row][col].setPiece(null);
			        		//display possible moves   
			        	}
		            }
			    }	
			} 

	        
	        if (components.length == 0) 
	        {
	            return;
	        }
	        // if we click on jpanel that holds a jlabel
	        if (components[0] instanceof JLabel) 
	        {
	
	            // remove label from panel
	            dragLabel = (JLabel) components[0];
	            clickedPanel.remove(dragLabel);
	            clickedPanel.revalidate();
	            clickedPanel.repaint();
	
	            dragLabelWidthDiv2 = dragLabel.getWidth() / 2;
	            dragLabelHeightDiv2 = dragLabel.getHeight() / 2;
	
	            int x = me.getPoint().x - dragLabelWidthDiv2;
	            int y = me.getPoint().y - dragLabelHeightDiv2;
	            dragLabel.setLocation(x, y);
	            layeredPane.add(dragLabel, JLayeredPane.DRAG_LAYER);
	            layeredPane.repaint();
	            System.out.println("mousePressed: "+x+" "+y);
	        }
	    }
        @Override
        public void mouseDragged(MouseEvent me) 
        {
            if (dragLabel == null) 
            {
                return;
            }
            int x = me.getPoint().x - dragLabelWidthDiv2;
            int y = me.getPoint().y - dragLabelHeightDiv2;
            dragLabel.setLocation(x, y);
            layeredPane.repaint();
        }

        @Override
        public void mouseReleased(MouseEvent me) 
        {
            
	        //if einbauen um fehlermeldung zuvermeiden statt try catch
	        try
	        {
	            droppedPanel = (JPanel) chessBoard.getComponentAt(me.getPoint());
	            Component[] components = droppedPanel.getComponents();
	        	if (components[0] instanceof JLabel) 
		        {
		
		            // remove label from panel
		            droppedLabel = (JLabel) components[0];
		            droppedPanel.remove(droppedLabel);
		            droppedPanel.revalidate();
		            droppedPanel.repaint();
		

		        }
	        }
			catch(Exception e)
			{
				System.out.println(e);
			}
	        

	        
			for (int row = 0; row < board.getTiles().length; row++)
			{
				for (int col = 0; col < board.getTiles()[row].length; col++)
			    {
		        	if (getTiles()[row][col] == chessBoard.getComponentAt(me.getPoint()))
		            {
			        	if(board.getTiles()[row][col].getPiece()!=null)
			        	{
		        			//get target Piece
			        		targetPiece = board.getTiles()[row][col].getPiece();
			        		
			        		//set Piece of origin tile to null
			        		board.getTiles()[row][col].setPiece(null);
			        		
			        		//set origin Piece to target tile
			        		board.getTiles()[row][col].setPiece(originPiece);

			        	}
			        	else
			        	{
			        		board.getTiles()[row][col].setPiece(originPiece);
			        	}
		            }

		        	//Print all Types
					try
					{
						System.out.print(board.getTiles()[row][col].getPiece().getType());
					}
					catch(Exception e)
					{
						System.out.print("leer");
					}
					
			    }	
				System.out.println("");
			} 
        	if (dragLabel == null) 
            {
                return;
            }
        	
        	
        	
            layeredPane.remove(dragLabel); // remove dragLabel for drag layer of JLayeredPane

            
            //int x = me.getPoint().x - dragLabelWidthDiv2;
            //int y = me.getPoint().y - dragLabelHeightDiv2;
            
            if (droppedPanel == null) 
            {
                // if off the grid, return label to home
                clickedPanel.add(dragLabel);
                clickedPanel.revalidate();
            } 
            else 
            {
                int r = -1;
                int c = -1;
                searchPanelGrid: for (int row = 0; row < tiles.length; row++) 
                {
                    for (int col = 0; col < tiles[row].length; col++) 
                    {
                        if (tiles[row][col] == droppedPanel) 
                        {
                            r = row;
                            c = col;
                            break searchPanelGrid;
                        }
                    }
                }

                if (r == -1 || c == -1) 
                {
                    // if off the grid, return label to home
                    clickedPanel.add(dragLabel);
                    clickedPanel.revalidate();
                } 
                else 
                {
                	//Write general method for checking tile
                	//Here get correct tile and check if move possible then setPiece on destination tile and delete piece on origin tile
                    //JLabel targetLabel = (JLabel) chessBoard.getComponentAt(me.getPoint());
                	//droppedPanel.remove(targetLabel);
                	droppedPanel.add(dragLabel);
                    droppedPanel.revalidate();
                }
            }

            layeredPane.repaint();
            dragLabel = null;
        }
    }
	    
	public void drawBoard()
	{
		Color black = Color.decode("#8f0300"); 
	    Color white = Color.decode("#fad481"); 
		for (int row = 0; row < board.getTiles().length; row++)
		{
			for (int col = 0; col < board.getTiles()[row].length; col++)
		    {
				tiles[row][col] = new JPanel(new GridBagLayout());//!PanelGrid
				chessBoard.add(tiles[row][col]);//!backingPanel
				if(board.getTiles()[row][col].getColor()=="Black")
				{
					tiles[row][col].setBackground(black);
				}
				else
				{
					tiles[row][col].setBackground(white);
				}
		    }	
		} 
	}
	public void drawPieces()
	{
	  	String path="";	 
		for (int row = 0; row < board.getTiles().length; row++)
		{
		  	for (int col = 0; col < board.getTiles()[row].length; col++)
		    {
				if(board.getTiles()[row][col].getPiece()!=null)
				{
					if(board.getTiles()[row][col].getPiece().getColor()=="White")
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
					else
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
						tiles[row][col].add(pic);
					}
					catch(Exception e)
					{
						System.out.print("e1");
					}
					frame.pack();
				}
			}	
		}
	}

	public JFrame getFrame() {
		return frame;
	}

	public Dimension getBoardSize() {
		return boardSize;
	}

	public JLayeredPane getLayeredPane() {
		return layeredPane;
	}

	public JPanel getChessBoard() {
		return chessBoard;
	}

	public JPanel[][] getTiles() {
		return tiles;
	}

	public Board getBoard() {
		return board;
	}
}
