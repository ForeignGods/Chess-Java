package chess;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.Icon;
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
	//private JPanel[][] tiles;
	private BackgroundPanel[][] tiles;
	


	
	private Board board;
	
	public GUI(Board board)
	{
		this.frame = new JFrame();
		this.boardSize = new Dimension(800, 800);
		this.layeredPane = new JLayeredPane();
		chessBoard = new JPanel();
		
		tiles = new BackgroundPanel[8][8];
		
		this.board = board;
		
		frame.getContentPane().add(layeredPane);
		
		layeredPane.setPreferredSize(boardSize);
		layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
		
		chessBoard.setLayout(new GridLayout(8, 8));
		chessBoard.setPreferredSize(boardSize);
	  	chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);
	  	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Chess");
		frame.setVisible(true);
        
		MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
        layeredPane.addMouseListener(myMouseAdapter);
        layeredPane.addMouseMotionListener(myMouseAdapter);
	}
	//source for MyMouseAdapter class
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
        //private Piece targetPiece;
        private Tile originTile;
        //private Tile targetTile;
        private String debugString;

        @Override
	    public void mousePressed(MouseEvent me) 
	    {
        	debugString= null;
        	clickedPanel = (JPanel) chessBoard.getComponentAt(me.getPoint());
	        Component[] components = clickedPanel.getComponents();
	        
	        
			for (int row = 0; row < board.getTiles().length; row++)
			{
				for (int col = 0; col < board.getTiles()[row].length; col++)
			    {
		        	if (getTiles()[row][col] == chessBoard.getComponentAt(me.getPoint())&&board.getTiles()[row][col].getPiece()!=null)
		        	{
	        			//get selected Piece
		        		originPiece = board.getTiles()[row][col].getPiece();
		        		originTile = board.getTiles()[row][col];
				        
		        		switch (originPiece.getType())
				        {
				            case "Pawn":
				            	
				                break;
				            case "Rook":
				            	ArrayList<Tile> possibleRookMoves = originPiece.getMoves(board, originTile);
			        			for(Tile tile : possibleRookMoves)
			        			{
			        				System.out.println("Rook moves:" + tile.getColumn()+" "+tile.getRow());
			        			}
				                break;
				            case "Knight":
				            	ArrayList<Tile> possibleKnightMoves = originPiece.getMoves(board, originTile);
			        			for(Tile tile : possibleKnightMoves)
			        			{
			        				System.out.println("Knight moves:" + tile.getColumn()+" "+tile.getRow());
			        			}
				                break;      
				            case "Bishop":
				            	ArrayList<Tile> possibleBishopMoves = originPiece.getMoves(board, originTile);
			        			for(Tile tile : possibleBishopMoves)
			        			{
			        				System.out.println("Bishop moves:" + tile.getColumn()+" "+tile.getRow());
			        			}
				                break;
				            case "King":
				            	ArrayList<Tile> possibleKingMoves = originPiece.getMoves(board, originTile);
			        			for(Tile tile : possibleKingMoves)
			        			{
			        				System.out.println("King moves:" + tile.getColumn()+" "+tile.getRow());
			        			}
				                break;
				            case "Queen":
				            	ArrayList<Tile> possibleQueenMoves = originPiece.getMoves(board, originTile);
			        			for(Tile tile : possibleQueenMoves)
			        			{
			        				System.out.println("Queen moves:" + tile.getColumn()+" "+tile.getRow());
			        			}
				                break;
				        }
		        		//remove selectedPiece from Origin Tile before moving
		        		board.getTiles()[row][col].setPiece(null);
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
				//System.out.println("mouseReleased Component array empty?");
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
			        		//targetPiece = board.getTiles()[row][col].getPiece();
			        		
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
		        	//Build string to print current state of board
					try
					{
						debugString = debugString + board.getTiles()[row][col].getPiece().getType();
					}
					catch(Exception e)
					{
						debugString = debugString + "leer";
					}
			    }	
				debugString = debugString + '\n';
			} 
        	if (dragLabel == null) 
            {
                return;
            }
            layeredPane.remove(dragLabel); // remove dragLabel for drag layer of JLayeredPane            
            if (droppedPanel == null) 
            {
                // if off the grid, return label to home
            	originTile.setPiece(originPiece);
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
                    originTile.setPiece(originPiece);
                	clickedPanel.add(dragLabel);
                    clickedPanel.revalidate();
                } 
                else 
                {
                	//Here get correct tile and check if move possible then setPiece on destination tile and delete piece on origin tile
                	droppedPanel.add(dragLabel);
                    droppedPanel.revalidate();
                }
            }
            layeredPane.repaint();
            originPiece=null;
            dragLabel = null;
            
            //System.out.println(debugString);//prints Chessboard
        }
    }

	class BackgroundPanel extends JPanel 
	{
	
		private static final long serialVersionUID = 1L;
		Image image;
	
	    public BackgroundPanel(Image image) 
	    {
	        this.image = image;
	    }
	
	    @Override
	    protected void paintComponent(Graphics g) 
	    {
	        super.paintComponent(g);
	        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	        
	    }
	    /*
	    @Override
	    public Dimension getPreferredSize() 
	    {
	        Dimension d = super.getPreferredSize();
	
	        int w = d.width > image.getWidth() ? d.width : image.getWidth();
	        int h = d.height > image.getHeight() ? d.height : image.getHeight();
	
	        return new Dimension(w, h);
	    }
	    */
	}
	public int getRandomNumber(int min, int max) 
	{
	    return (int) ((Math.random() * (max - min)) + min);
	}
	public void drawBoard()
	{
	    ArrayList<String> tileBlack = new ArrayList<String>();
	    tileBlack.add("src\\chess\\imgs\\TileBlack1.gif");
	    tileBlack.add("src\\chess\\imgs\\TileBlack2.gif");
	    tileBlack.add("src\\chess\\imgs\\TileBlack3.gif");
	    tileBlack.add("src\\chess\\imgs\\TileBlack4.gif");
	    tileBlack.add("src\\chess\\imgs\\TileBlack5.gif");
	    tileBlack.add("src\\chess\\imgs\\TileBlack6.gif");
	    tileBlack.add("src\\chess\\imgs\\TileBlack7.gif");
	    tileBlack.add("src\\chess\\imgs\\TileBlack8.gif");
	    
	    ArrayList<String> tileWhite = new ArrayList<String>();
	    tileWhite.add("src\\chess\\imgs\\TileWhite1.gif");
	    tileWhite.add("src\\chess\\imgs\\TileWhite2.gif");
	    tileWhite.add("src\\chess\\imgs\\TileWhite3.gif");
	    tileWhite.add("src\\chess\\imgs\\TileWhite4.gif");
	    tileWhite.add("src\\chess\\imgs\\TileWhite5.gif");
	    tileWhite.add("src\\chess\\imgs\\TileWhite6.gif");
	    tileWhite.add("src\\chess\\imgs\\TileWhite7.gif");
	    tileWhite.add("src\\chess\\imgs\\TileWhite8.gif");
	    
		
	    for (int row = 0; row < board.getTiles().length; row++)
		{
			for (int col = 0; col < board.getTiles()[row].length; col++)
		    {

	            BufferedImage bi1 = null;
	            Image image = null;
	            String path = null;
				if(board.getTiles()[row][col].getColor()=="Black")
				{
					
					path = tileBlack.get(getRandomNumber(0, 7));
				}
				else
				{
					path = tileWhite.get(getRandomNumber(0, 7));
				}

					
				image = Toolkit.getDefaultToolkit().createImage(path);
	            tiles[row][col] = new BackgroundPanel(image);
	            chessBoard.add(tiles[row][col]);
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
				            	path="src\\chess\\imgs\\PawnWhite.gif";
				                break;
				            case "Rook":
				            	path="src\\chess\\imgs\\RookWhite.gif";
				                break;
				            case "Knight":
				            	path="src\\chess\\imgs\\KnightWhite.gif";
				                break;      
				            case "Bishop":
				            	path="src\\chess\\imgs\\BishopWhite.gif";
				                break;
				            case "King":
				            	path="src\\chess\\imgs\\KingWhite.gif";
				                break;
				            case "Queen":
				            	path="src\\chess\\imgs\\QueenWhite.gif";
				                break;
				        }	
					}
					else
					{
				        switch (board.getTiles()[row][col].getPiece().getType())
				        {
				            case "Pawn":
				            	path="src\\chess\\imgs\\PawnBlack.gif";
				                break;
				            case "Rook":
				            	path="src\\chess\\imgs\\RookBlack.gif";
				                break;
				            case "Knight":
				            	path="src\\chess\\imgs\\KnightBlack.gif";
				                break;      
				            case "Bishop":
				            	path="src\\chess\\imgs\\BishopBlack.gif";
				                break;
				            case "King":
				            	path="src\\chess\\imgs\\KingBlack.gif";
				                break;
				            case "Queen":
				            	path="src\\chess\\imgs\\QueenBlack.gif";
				                break;
				        }
					}
					try
					{
					    Icon icon = new ImageIcon(path);
					    JLabel gif = new JLabel(icon);
						tiles[row][col].add(gif);
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
	public JFrame getFrame() 
	{
		return frame;
	}
	public Dimension getBoardSize() 
	{
		return boardSize;
	}

	public JLayeredPane getLayeredPane() 
	{
		return layeredPane;
	}
	public JPanel getChessBoard() 
	{
		return chessBoard;
	}

	public JPanel[][] getTiles() 
	{
		return tiles;
	}
	public Board getBoard() 
	{
		return board;
	}
}
