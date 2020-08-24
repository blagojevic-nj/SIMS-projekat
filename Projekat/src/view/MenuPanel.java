package view;

import java.awt.Color;

import javax.swing.JPanel;

public class MenuPanel extends JPanel {


	private static final long serialVersionUID = 1L;

	/**
	 * Staticki dimenzije width*height = 300*700 (za testiranje)
	 * Ali koristiti metode getWidth i getHeight pri inicijalizaciji...
	 * */
	public MenuPanel(int width, int height)
	{
		setBackground(Color.LIGHT_GRAY);
		setBounds(0, 0, width, height);
	}



}
