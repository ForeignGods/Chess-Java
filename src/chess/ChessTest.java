package chess;

public class ChessTest {

	public ChessTest() {

	}

	public static void main(String[] args) {
		GUI gui = new GUI(new Board());

		gui.drawBoard();
		gui.drawPieces();

	}

}
