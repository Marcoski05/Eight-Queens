import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JFrame; 

/**
 * @author Marco Cecchi-Rivas
 * graphically displays the solution Marco found
 */
public class ChessBoard extends JFrame {

	/**
	 * creates the chess board graphic with the queen spots that Marco found
	 */
	public ChessBoard() {
		setTitle("Chessboard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(8, 8));

		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				boolean hasQueen = ((row == 0 && col == 1) || (row == 1 && col == 6) || (row == 2 && col == 2) || (row == 3 && col == 5) || 
						(row == 4 && col == 7) || (row == 5 && col == 4) || (row == 6 && col == 0) || (row == 7 && col == 3)); 
				Color backgroundColor = (row + col) % 2 == 0 ? Color.WHITE : Color.GRAY;
				ChessSquarePanel panel = new ChessSquarePanel(backgroundColor, hasQueen);
				add(panel);
			}
		}

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * @param args unused
	 * creates the ChessBoard
	 */
	public static void main(String[] args) {
		ChessBoard cb = new ChessBoard();
	}
}
