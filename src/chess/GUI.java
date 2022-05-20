package chess;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

//In dieser Klasse findet die ganze Spiellogik statt
//Da die Figuren mit Drag and Drop bewegt werden 
//Habe ich mich dafür entschieden alles in dieser Klasse zu implementieren
/**
 * 
 * @author Pascal
 *
 */
public class GUI {
	private JFrame frame;
	private Dimension boardSize;
	private JLayeredPane layeredPane;
	private JPanel chessBoard;
	private BackgroundPanel[][] tiles;
	private Board board;

	/**
	 * 
	 * @param board
	 */
	public GUI(Board board) {
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
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setTitle("Schach - ForeignGods - Version 1.3");
		frame.setVisible(true);
		frame.setResizable(false);
		ImageIcon icon = new ImageIcon("src\\chess\\imgs\\Logo.png");
		frame.setIconImage(icon.getImage());
		MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
		layeredPane.addMouseListener(myMouseAdapter);
		layeredPane.addMouseMotionListener(myMouseAdapter);
	}

	// source for MyMouseAdapter class
	// https://stackoverflow.com/questions/4893265/dragging-a-jlabel-around-the-screen
	/**
	 * 
	 * @author Pascal
	 *
	 */
	private class MyMouseAdapter extends MouseAdapter {
		private JLabel dragLabel = null;
		private JLabel droppedLabel = null;
		private int dragLabelWidthDiv2;
		private int dragLabelHeightDiv2;
		private JPanel clickedPanel = null;
		private JPanel droppedPanel = null;
		private Piece originPiece;
		private Tile originTile;
		@SuppressWarnings("unused")
		private Piece targetPiece;
		@SuppressWarnings("unused")
		private Tile targetTile;
		private Tile[][] possibleMoves;
		private boolean toOrigin;
		private int turnCounter = 1;
		private String turnColor;

		/**
		 * event when mouse is pressed
		 */
		@Override
		public void mousePressed(MouseEvent me) {
			Component[] components = null;

			// alternates what color pieces can be moved
			if (turnCounter % 2 == 0) {
				turnColor = "Black";
			} else {
				turnColor = "White";
			}

			possibleMoves = new Tile[8][8];

			// iterate over the 2d array of tiles
			for (int row = 0; row < board.getTiles().length; row++) {
				for (int col = 0; col < board.getTiles()[row].length; col++) {
					if (getTiles()[row][col] == chessBoard.getComponentAt(me.getPoint())
							&& board.getTiles()[row][col].getPiece() != null) {

						// get clicked Piece and Tile
						originPiece = board.getTiles()[row][col].getPiece();
						originTile = board.getTiles()[row][col];

						// calculates possible moves
						possibleMoves = originPiece.getMoves(board, originTile);

						// only one pieces with one color are clickable per turn
						if (originPiece.getColor() != turnColor) {
							clickedPanel = (JPanel) chessBoard.getComponentAt(me.getPoint());

							components = clickedPanel.getComponents();
						} else {
							originPiece = null;
							originTile = null;
						}
					}
				}
			}
			if (components != null) {

				if (components.length == 0) {
					return;
				}
				// if we click on jpanel that holds a jlabel
				if (components[0] instanceof JLabel) {

					// remove label from panel
					dragLabel = (JLabel) components[0];
					clickedPanel.remove(dragLabel);
					clickedPanel.revalidate();
					clickedPanel.repaint();

					dragLabelWidthDiv2 = dragLabel.getWidth() / 2;
					dragLabelHeightDiv2 = dragLabel.getHeight() / 2;

					// get position of mouse when click event occured
					int x = me.getPoint().x - dragLabelWidthDiv2;
					int y = me.getPoint().y - dragLabelHeightDiv2;
					dragLabel.setLocation(x, y);
					layeredPane.add(dragLabel, JLayeredPane.DRAG_LAYER);
					layeredPane.repaint();
				}
			}

		}

		/**
		 * event when mouse is dragged
		 */
		@Override
		public void mouseDragged(MouseEvent me) {
			if (dragLabel == null) {
				return;
			}
			int x = me.getPoint().x - dragLabelWidthDiv2;
			int y = me.getPoint().y - dragLabelHeightDiv2;
			// repositions selected label while mouse is being dragged
			dragLabel.setLocation(x, y);
			layeredPane.repaint();
		}

		/**
		 * event when mouse is released
		 */
		@Override
		public void mouseReleased(MouseEvent me) {

			for (int row = 0; row < board.getTiles().length; row++) {
				for (int col = 0; col < board.getTiles()[row].length; col++) {
					try {
						// gets tile on position where mouse was released
						if (getTiles()[row][col] == chessBoard.getComponentAt(me.getPoint())) {
							toOrigin = true;
							if (possibleMoves[row][col].getRow() == board.getTiles()[row][col].getRow()
									&& possibleMoves[row][col].getColumn() == board.getTiles()[row][col].getColumn()) {
								if (board.getTiles()[row][col].getPiece() != null) {
									// moves piece to target
									originTile.setPiece(null);
									targetTile = board.getTiles()[row][col];
									targetPiece = board.getTiles()[row][col].getPiece();
									board.getTiles()[row][col].setPiece(originPiece);
									toOrigin = false;

								} else {
									originTile.setPiece(null);
									board.getTiles()[row][col].setPiece(originPiece);
									targetTile = board.getTiles()[row][col];
									toOrigin = false;
								}
							} else {
								toOrigin = true;
							}
						}
					} catch (Exception e) {

					}
				}
			}
			try {
				droppedPanel = (JPanel) chessBoard.getComponentAt(me.getPoint());
				Component[] components = droppedPanel.getComponents();
				if (components[0] instanceof JLabel && !toOrigin) {
					// remove label from panel
					droppedLabel = (JLabel) components[0];
					droppedPanel.remove(droppedLabel);
					droppedPanel.revalidate();
					droppedPanel.repaint();
				}
			} catch (Exception e) {

			}
			if (dragLabel == null) {
				return;
			}
			// remove dragLabel for drag layer of JLayeredPane
			layeredPane.remove(dragLabel);
			if (droppedPanel == null) {
				// if off the grid, return label and piece to origin
				originTile.setPiece(originPiece);
				clickedPanel.add(dragLabel);
				clickedPanel.revalidate();
			} else {
				int r = -1;
				int c = -1;
				searchPanelGrid: for (int row = 0; row < tiles.length; row++) {
					for (int col = 0; col < tiles[row].length; col++) {
						if (tiles[row][col] == droppedPanel) {
							r = row;
							c = col;
							break searchPanelGrid;
						}
					}
				}
				if (r == -1 || c == -1 || toOrigin) {
					// if off the grid, return label to origin
					clickedPanel.add(dragLabel);
					clickedPanel.revalidate();
				} else {
					// Here get correct tile and check if move possible then setPiece on destination
					// tile and delete piece on origin tile
					turnCounter++;
					if (originPiece.getType() == "Pawn") {
						originPiece.setFirstMover(false);
					}
					droppedPanel.add(dragLabel);
					droppedPanel.revalidate();
				}
			}
			layeredPane.repaint();
			originPiece = null;
			dragLabel = null;
		}
	}

	/**
	 * 
	 * @author Pascal extends JPanel so it can hold animated GIF
	 *
	 */
	class BackgroundPanel extends JPanel {

		private static final long serialVersionUID = 1L;
		Image image;

		public BackgroundPanel(Image image) {
			this.image = image;
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);

		}
	}

	/**
	 * 
	 * @param min
	 * @param max
	 * @return int
	 */
	public int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

	/**
	 * draws ui board based on Board class data graphics of tiles I made in blender
	 */
	public void drawBoard() {
		ArrayList<String> tileBlack = new ArrayList<>();
		tileBlack.add("src\\chess\\imgs\\TileBlack1.gif");
		tileBlack.add("src\\chess\\imgs\\TileBlack2.gif");
		tileBlack.add("src\\chess\\imgs\\TileBlack3.gif");
		tileBlack.add("src\\chess\\imgs\\TileBlack4.gif");
		tileBlack.add("src\\chess\\imgs\\TileBlack5.gif");
		tileBlack.add("src\\chess\\imgs\\TileBlack6.gif");
		tileBlack.add("src\\chess\\imgs\\TileBlack7.gif");
		tileBlack.add("src\\chess\\imgs\\TileBlack8.gif");

		ArrayList<String> tileWhite = new ArrayList<>();
		tileWhite.add("src\\chess\\imgs\\TileWhite1.gif");
		tileWhite.add("src\\chess\\imgs\\TileWhite2.gif");
		tileWhite.add("src\\chess\\imgs\\TileWhite3.gif");
		tileWhite.add("src\\chess\\imgs\\TileWhite4.gif");
		tileWhite.add("src\\chess\\imgs\\TileWhite5.gif");
		tileWhite.add("src\\chess\\imgs\\TileWhite6.gif");
		tileWhite.add("src\\chess\\imgs\\TileWhite7.gif");
		tileWhite.add("src\\chess\\imgs\\TileWhite8.gif");

		for (int row = 0; row < board.getTiles().length; row++) {
			for (int col = 0; col < board.getTiles()[row].length; col++) {
				Image image = null;
				String path = null;
				if (board.getTiles()[row][col].getColor() == "Black") {

					path = tileBlack.get(getRandomNumber(0, 7));
				} else {
					path = tileWhite.get(getRandomNumber(0, 7));
				}

				image = Toolkit.getDefaultToolkit().createImage(path);
				tiles[row][col] = new BackgroundPanel(image);
				chessBoard.add(tiles[row][col]);
			}
		}
	}

	/**
	 * draws ui pieces based on Board class data graphics of pieces I made in
	 * blender
	 */
	public void drawPieces() {
		String path = "";
		for (int row = 0; row < board.getTiles().length; row++) {
			for (int col = 0; col < board.getTiles()[row].length; col++) {
				if (board.getTiles()[row][col].getPiece() != null) {
					if (board.getTiles()[row][col].getPiece().getColor() == "White") {
						switch (board.getTiles()[row][col].getPiece().getType()) {
						case "Pawn":
							path = "src\\chess\\imgs\\PawnWhite.gif";
							break;
						case "Rook":
							path = "src\\chess\\imgs\\RookWhite.gif";
							break;
						case "Knight":
							path = "src\\chess\\imgs\\KnightWhite.gif";
							break;
						case "Bishop":
							path = "src\\chess\\imgs\\BishopWhite.gif";
							break;
						case "King":
							path = "src\\chess\\imgs\\KingWhite.gif";
							break;
						case "Queen":
							path = "src\\chess\\imgs\\QueenWhite.gif";
							break;
						}
					} else {
						switch (board.getTiles()[row][col].getPiece().getType()) {
						case "Pawn":
							path = "src\\chess\\imgs\\PawnBlack.gif";
							break;
						case "Rook":
							path = "src\\chess\\imgs\\RookBlack.gif";
							break;
						case "Knight":
							path = "src\\chess\\imgs\\KnightBlack.gif";
							break;
						case "Bishop":
							path = "src\\chess\\imgs\\BishopBlack.gif";
							break;
						case "King":
							path = "src\\chess\\imgs\\KingBlack.gif";
							break;
						case "Queen":
							path = "src\\chess\\imgs\\QueenBlack.gif";
							break;
						}
					}
					try {
						Icon icon = new ImageIcon(path);
						JLabel gif = new JLabel(icon);
						tiles[row][col].add(gif);
					} catch (Exception e) {
						System.out.print("e1");
					}
					frame.pack();
				}
			}
		}
	}

	/**
	 * 
	 * @return JFrame
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * 
	 * @return Dimension
	 */
	public Dimension getBoardSize() {
		return boardSize;
	}

	/**
	 * 
	 * @return JLayeredPane
	 */
	public JLayeredPane getLayeredPane() {
		return layeredPane;
	}

	/**
	 * 
	 * @return JPanel
	 */
	public JPanel getChessBoard() {
		return chessBoard;
	}

	/**
	 * 
	 * @return JPanel[][]
	 */
	public JPanel[][] getTiles() {
		return tiles;
	}

	/**
	 * 
	 * @return Board
	 */
	public Board getBoard() {
		return board;
	}
}
