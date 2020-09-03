package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import manageri.ReceptManager;
import model.Korisnik;
import model.Recept;
import model.RegistrovaniKorisnik;
import net.miginfocom.swing.MigLayout;

public class MojiReceptiPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private ReceptManager rM;
	private JScrollPane scrollRecepti;
	private Image img;

	public MojiReceptiPanel(Korisnik k, MainWindow mw) {
		this.img = new ImageIcon("data/ikonice/back2.jpg").getImage();
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);

		rM = ReceptManager.getInstance();

		setLayout(null);
		setBounds(200, 0, 1040, 650);
		
		JLabel logoRecepata = new JLabel(new ImageIcon("data/ikonice/mojiRecepti.png"));
		logoRecepata.setBounds(350, 0, 150, 150);
		add(logoRecepata);

		JPanel receptiPane = new JPanel(new MigLayout("wrap 1", "[][]10[]", "[]10[]"));
		receptiPane.setOpaque(false);
		scrollRecepti = new JScrollPane(receptiPane);
		scrollRecepti.setOpaque(false);
		scrollRecepti.getViewport().setOpaque(false);
		scrollRecepti.setBounds(30, 140, 810, 490);
		scrollRecepti.getVerticalScrollBar().setUnitIncrement(20);
		scrollRecepti.getVerticalScrollBar().setVisible(false);
		scrollRecepti.setBorder(BorderFactory.createEmptyBorder());
		scrollRecepti.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		add(scrollRecepti);
		for (Recept recept : rM.getRecepti(((RegistrovaniKorisnik) k).getRecepti())) {
			MaliPrikazRecepta mpr = new MaliPrikazRecepta(recept, mw, true);
			receptiPane.add(mpr);
		}

	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

	@Override
	public void setEnabled(boolean b) {
		super.setEnabled(b);
		
		scrollRecepti.getVerticalScrollBar().setEnabled(b);
		scrollRecepti.setWheelScrollingEnabled(b);
	}
	
	

}
