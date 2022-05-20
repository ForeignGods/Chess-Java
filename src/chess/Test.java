package chess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * 
 * @author Pascal
 *
 */
class Test {

	@org.junit.jupiter.api.Test
	void testTiles() {
		Board board = new Board();
		for (int row = 0; row < board.getTiles().length; row++) {
			for (int col = 0; col < board.getTiles()[row].length; col++) {
				assertNotNull(board.getTiles()[row][col], "tile was null");
			}
		}

	}

	@org.junit.jupiter.api.Test
	void testRowValues() {
		Board board = new Board();
		assertEquals(board.getTiles()[0][0].getRow(), 0, "wrong row value");

	}

	@org.junit.jupiter.api.Test
	void testTileColors() {
		Board board = new Board();
		String color;
		for (int row = 0; row < board.getTiles().length; row++) {
			for (int col = 0; col < board.getTiles()[row].length; col++) {
				if (col % 2 == 0) {
					if (row % 2 == 0) {
						color = "White";
						assertEquals(board.getTiles()[row][col].getColor(), color, "no matching colors");
					} else {
						color = "Black";
						assertEquals(board.getTiles()[row][col].getColor(), color, "no matching colors");
					}
				} else {
					if (row % 2 == 0) {

						color = "Black";
						assertEquals(board.getTiles()[row][col].getColor(), color, "no matching colors");
					}

					else {
						color = "White";
						assertEquals(board.getTiles()[row][col].getColor(), color, "no matching colors");
					}
				}

			}

		}

	}
}
