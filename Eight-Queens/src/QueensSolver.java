import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
/**
 * @author Marco Cecchi-Rivas
 * Finds all solutions to the Eight Queens problem. Also graphically shows the method for finding one.
 */
public class QueensSolver extends JFrame {
	private static final int BOARD_SIZE = 8;
	private ArrayList<ChessSquarePanel> components = new ArrayList<ChessSquarePanel>();
	
	/**
	 * sets up graphics window
	 */
	public QueensSolver() {
		setTitle("Chessboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 8));
    }
	

	/**
	 * @param queens list to be filled with valid queen spots
	 * @return if a solution was found
	 * @throws InterruptedException thread sleeps for 250 ms
	 * finds an 8 queens solution and shows the process step by step graphically
	 */
	public boolean solveQueens(List<int[]> queens) throws InterruptedException {
        if (queens.size() == BOARD_SIZE) {
            displaySolution(queens);
            return true;
        }

        int row = queens.size();
        for (int col = 0; col < BOARD_SIZE; col++) {
            if (isSafe(queens, row, col)) {
                queens.add(new int[]{row, col});
                displaySolution(queens);
                if(solveQueens(queens)) {
                	return true;
                }
                queens.remove(queens.size() - 1);
            }
        }
        return false;
    }
	
	/**
	 * @param queens list to be filled with valid queen spots
	 * prints all possible solutions in the console
	 */
	public static void solveAllQueens(List<int[]> queens) {
        if (queens.size() == BOARD_SIZE) {
            displaySolutions(queens);
            return;
        }

        int row = queens.size();
        for (int col = 0; col < BOARD_SIZE; col++) {
            if (isSafe(queens, row, col)) {
                queens.add(new int[]{row, col});
                solveAllQueens(queens);
                queens.remove(queens.size() - 1);
            }
        }
    }

    private static boolean isSafe(List<int[]> queens, int row, int col) {
        for (int[] queen : queens) {
            int queenRow = queen[0];
            int queenCol = queen[1];

            if (queenCol == col || (queenRow - queenCol == row - col) || (queenRow + queenCol == row + col))
                return false;
        }
        return true;
    }

    private void displaySolution(List<int[]> queens) throws InterruptedException {
    	for (int i = 0; i < components.size(); i++)
    		this.remove(components.get(i));
    	components.clear();
    	for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
            	boolean hasQueen = false;
            	if (row < queens.size())
                	hasQueen = (queens.get(row)[1] == col);
                Color backgroundColor = (row + col) % 2 == 0 ? Color.WHITE : Color.GRAY;
                ChessSquarePanel panel = new ChessSquarePanel(backgroundColor, hasQueen);
                components.add(panel);
                add(panel);
            }
        }
    	pack();
        setLocationRelativeTo(null);
        setVisible(true);
        Thread.sleep(250);
    }
    
    private static void displaySolutions(List<int[]> queens) {
        char[][] board = new char[BOARD_SIZE][BOARD_SIZE];

        for (int r = 0; r < BOARD_SIZE; r++) {
            for (int c = 0; c < BOARD_SIZE; c++) {
                board[r][c] = '☐';
            }
        }

        for (int[] queen : queens) {
            int row = queen[0];
            int col = queen[1];
            board[row][col] = '♕';
        }

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    /**
     * @param args unused
     * @throws InterruptedException Thread sleeps for 250 ms
     * runs both solve queen methods
     */
    public static void main(String[] args) throws InterruptedException {
        QueensSolver qs = new QueensSolver();
    	qs.solveQueens(new ArrayList<>());
    	solveAllQueens(new ArrayList<>());
    }
}
