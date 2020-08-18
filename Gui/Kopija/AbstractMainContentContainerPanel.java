import java.awt.Color;

import javax.swing.JPanel;

public class AbstractMainContentContainerPanel extends JPanel
{

	private static final long serialVersionUID = 1L;

	/**
	 * Staticki dimenzije width*height = 1150*700 (za testiranje)
	 * Ali koristiti metode getWidth i getHeight pri inicijalizaciji...
	 * */
	AbstractMainContentContainerPanel(int width, int height)
	{
		setBackground(Color.WHITE);
		setBounds(50, 0, width, height);
		setLayout(null);
	}
}
