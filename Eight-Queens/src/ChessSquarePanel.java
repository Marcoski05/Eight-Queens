import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * @author Marco Cecchi-Rivas
 * object represents each square on the chess board, either having a queen or not
 */
public class ChessSquarePanel extends JPanel {

	private Color backgroundColor;
	private boolean hasQueen;

	/**
	 * @param backgroundColor background color for the tile
	 * @param hasQueen if there is a queen on this spot
	 * instantiates the panel and fills fields, also sets preferred size of (50, 50)
	 */
	public ChessSquarePanel(Color backgroundColor, boolean hasQueen) {
		this.backgroundColor = backgroundColor;
		this.hasQueen = hasQueen;
		setPreferredSize(new Dimension(50, 50));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(backgroundColor);
		g.fillRect(0, 0, getWidth(), getHeight());

		if (hasQueen) {
			g.setColor(Color.BLACK);
			g.setFont(new Font("SansSerif", Font.PLAIN, 40));
			g.drawString("\u2655", 5, 40);
		}
	}
}
