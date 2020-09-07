package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import manageri.ReceptManager;
import model.Recept;
import model.RegistrovaniKorisnik;
import net.miginfocom.swing.MigLayout;

public class PraceniSacuvaniPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private ReceptManager rm;

	private Image img;
	private RegistrovaniKorisnik rk;
	private ArrayList<MaliPrikazRecepta> paneli;
	private ArrayList<JednaStavkaPanel> praceni;
	private JPanel praceniPane, receptiPane;
	private JScrollPane scrollPraceni, scrollRecepti;

	public PraceniSacuvaniPanel(RegistrovaniKorisnik k, MainWindow mw, KorisnikPanel kP) {
		this.img = new ImageIcon("data/ikonice/back2.jpg").getImage();
		paneli = new ArrayList<MaliPrikazRecepta>();
		praceni = new ArrayList<JednaStavkaPanel>();
		rk = k;
		rm = ReceptManager.getInstance();

		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);

		setLayout(null);
		setBounds(200, 0, 1040, 650);
		
		JLabel logoPraceni = new JLabel(new ImageIcon("data/ikonice/praceniNalozi.png"));
		logoPraceni.setBounds(70, 40, 150, 150);
		add(logoPraceni);
		
		praceniPane = new JPanel(new MigLayout("wrap 1", "[]10[]", "[][]10[]"));
		praceniPane.setOpaque(false);
		
		for (String pracen : k.getPraceni()) {
			JednaStavkaPanel jsp = new JednaStavkaPanel(pracen, PraceniSacuvaniPanel.this, mw, kP);
			praceniPane.add(jsp);
			praceni.add(jsp);
		}
		
		scrollPraceni = new JScrollPane(praceniPane);
		scrollPraceni.setOpaque(false);
		scrollPraceni.getViewport().setOpaque(false);
		scrollPraceni.setBounds(20,200, 350, 400);
		scrollPraceni.getVerticalScrollBar().setUnitIncrement(20);
		scrollPraceni.getVerticalScrollBar().setVisible(false);
		scrollPraceni.setBorder(BorderFactory.createEmptyBorder());
		scrollPraceni.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		add(scrollPraceni);
		
		JSeparator sep = new JSeparator();
		sep.setBounds(310, 50, 7, 570);
		sep.setOrientation(1);
		add(sep);
		sep.setForeground(Color.black);
		
		JLabel logoSacuvani = new JLabel(new ImageIcon("data/ikonice/sacuvani.png"));
		logoSacuvani.setBounds(540, 40, 150, 150);
		add(logoSacuvani);
		
		receptiPane = new JPanel(new MigLayout("wrap 1", "[][]10[]", "[]10[]"));
		receptiPane.setOpaque(false);
		scrollRecepti = new JScrollPane(receptiPane);
		scrollRecepti.setOpaque(false);
		scrollRecepti.getViewport().setOpaque(false);
		scrollRecepti.setBounds(320, 200, 520, 420);
		scrollRecepti.getVerticalScrollBar().setUnitIncrement(20);
		scrollRecepti.getVerticalScrollBar().setVisible(false);
		scrollRecepti.setBorder(BorderFactory.createEmptyBorder());
		scrollRecepti.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		add(scrollRecepti);
		for (Recept recept : rm.getRecepti(k.getSacuvaniRecepti())) {
			MaliPrikazRecepta mpr = new MaliPrikazRecepta(recept, mw, false);
			receptiPane.add(mpr);
			paneli.add(mpr);
		}
		
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

	public void refreshRecept(int sifra) {
		if (rk.getSacuvaniRecepti().contains(sifra)) {
			for (MaliPrikazRecepta mpr : paneli) {
				if (mpr.getRecept().getId() == sifra)
					mpr.refresh();
			}
		} else {
			receptiPane.removeAll();
			for (MaliPrikazRecepta mpr : paneli) {
				if (mpr.getRecept().getId() == sifra) {
					paneli.remove(mpr);
					break;
				}
			}
			for (MaliPrikazRecepta mpr : paneli)
				receptiPane.add(mpr);
			receptiPane.repaint();
			receptiPane.revalidate();
		}
	}

	public void refreshPraceni(String korisnickoIme) {
		if (!rk.getPraceni().contains(korisnickoIme)) {
			praceniPane.removeAll();
			for (JednaStavkaPanel jsp : praceni) {
				if (jsp.username.equals(korisnickoIme)) {
					praceni.remove(jsp);
					break;
				}
			}
			for (JednaStavkaPanel jsp : praceni)
				praceniPane.add(jsp);
			praceniPane.repaint();
			praceniPane.revalidate();
		}
	}
}

class JednaStavkaPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	String username;
	
	public JednaStavkaPanel(String username, PraceniSacuvaniPanel pisp, MainWindow mw, KorisnikPanel kP) {
		this.username = username;
		setLayout(new MigLayout());
		setOpaque(false);
		setPreferredSize(new Dimension(270, 60));
		setBackground(null);
		
		JLabel slicica = new JLabel(new ImageIcon("data/ikonice/coveculjak.png"));
		add(slicica);
		
		JLabel user = new JLabel(username);
		user.setForeground(Color.white);
		user.setFont(new Font("Tahoma", 1, 18));
		add(user);
		
		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				mw.postaviDesniPanel(new PregledKorisnikaPanel(mw, kP,
					(RegistrovaniKorisnik) MainWindow.km.getKorisnik(username)));
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				JednaStavkaPanel.this.setBackground(new Color(189, 177, 151, 100));
				JednaStavkaPanel.this.setOpaque(true);
				pisp.revalidate();
				pisp.repaint();
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				JednaStavkaPanel.this.setOpaque(false);
				repaint();
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
		});
	}
}
