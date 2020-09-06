package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import manageri.ReceptSorter;
import manageri.ReceptSorter.Kriterijum;
import model.Recept;
import net.miginfocom.swing.MigLayout;

public class ReceptiPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private ArrayList<MaliPrikazRecepta> paneli;
	private JPanel gornjiDeo, donjiDeo;
	private JScrollPane pane;
	private Image img;
	private static final String UPIT_TEKST = "Unesite recept koji trazite";

	public ReceptiPanel(ArrayList<Recept> r, MainWindow mW) {
		this.img = new ImageIcon("data/ikonice/back2.jpg").getImage();
		paneli = new ArrayList<MaliPrikazRecepta>();
		setLayout(null);
		setBounds(0, 0, 1040, 650);
		setBorder(BorderFactory.createRaisedBevelBorder());
		initGornjiDeo(mW);
		initDonjiDeo(r, mW);
	}

	void initGornjiDeo(MainWindow mW) {
		gornjiDeo = new JPanel(null);
		gornjiDeo.setBounds(0, 0, 1040, 50);
		gornjiDeo.setOpaque(false);

		JLabel sort = new JLabel("Sortiraj: ");
		sort.setForeground(Color.white);
		sort.setBounds(10, 10, 100, 30);
		gornjiDeo.add(sort);

		JComboBox<String> cB = new JComboBox<String>();
		cB.addItem("------");
		cB.addItem("Naziv [A-Z]");
		cB.addItem("Naziv [Z-A]");
		cB.addItem("Od najlaksih");
		cB.addItem("Od najtezih");
		cB.addItem("Oceni");
		cB.addItem("Od najstarijih");
		cB.addItem("Od najnovijih");
		cB.addItem("Po popularnosti");

		cB.setBounds(80, 10, 150, 30);
		gornjiDeo.add(cB);

		cB.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					switch (cB.getSelectedIndex()) {
					case 1:
						sortiraj(Kriterijum.NAZIV, 1);
						break;
					case 2:
						sortiraj(Kriterijum.NAZIV, -1);
						break;
					case 3:
						sortiraj(Kriterijum.TEZINA, 1);
						break;
					case 4:
						sortiraj(Kriterijum.TEZINA, -1);
						break;
					case 5:
						sortiraj(Kriterijum.OCENA, -1);
						break;
					case 6:
						sortiraj(Kriterijum.DATUM, 1);
						break;
					case 7:
						sortiraj(Kriterijum.DATUM, -1);
						break;
					case 8:
						sortiraj(Kriterijum.POPULARNOST, -1);
						break;
					default:
						break;
					}
				}
			}
		});

		JButton searchBtn = new JButton("Pretrazi", new ImageIcon("data/ikonice/search2.png"));
		searchBtn.setBounds(730, 10, 120, 30);
		searchBtn.setEnabled(false);

		gornjiDeo.add(searchBtn);

		JTextField search = new JTextField(UPIT_TEKST);
		search.setBounds(320, 10, 400, 30);

		search.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				JTextField src = (JTextField) e.getSource();
				src.setText("");
				searchBtn.setEnabled(true);
			}

			@Override
			public void focusLost(FocusEvent e) {
				JTextField src = (JTextField) e.getSource();
				if (src.getText().equals(""))
					src.setText(UPIT_TEKST);
			}
		});

		gornjiDeo.add(search);

		add(gornjiDeo);

		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String upit = search.getText();
				if (!upit.equals(UPIT_TEKST) && !upit.equals("")) {
					initDonjiDeo(MainWindow.rM.pretraziPoNazivu(upit), mW);
					System.out.println(upit);
					searchBtn.setEnabled(false);
				}
			}
		});
	}

	void initDonjiDeo(ArrayList<Recept> r, MainWindow mW) {
		if (donjiDeo == null) {
			donjiDeo = new JPanel(new MigLayout("wrap 2", "[][]10[]", "[]10[]"));
			donjiDeo.setOpaque(false);
			pane = new JScrollPane(donjiDeo);
			pane.setOpaque(false);
			pane.getViewport().setOpaque(false);
			pane.setBounds(30, 50, 1040, 600);
			pane.getVerticalScrollBar().setUnitIncrement(20);
			pane.getVerticalScrollBar().setVisible(false);
			pane.setBorder(BorderFactory.createEmptyBorder());
			pane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
			add(pane);
		} else {
			donjiDeo.removeAll();
			paneli.clear();
		}

		if (r == null) { // prikazuju se najnoviji i najpopularniji recepti
			JLabel lbl1 = new JLabel("Najnoviji");
			lbl1.setFont(new Font("Lucida Sans", Font.BOLD, 20));
			lbl1.setForeground(Color.white);
			donjiDeo.add(lbl1);
			JSeparator sep1 = new JSeparator();
			donjiDeo.add(sep1);
			for (Recept rec : MainWindow.rM.getNajnovijih10()) {
				MaliPrikazRecepta mpr = new MaliPrikazRecepta(rec, mW, false);
				donjiDeo.add(mpr);
				paneli.add(mpr);
			}
			if (paneli.size() % 2 != 0) {
				donjiDeo.add(new JLabel());
			}
			JLabel lbl2 = new JLabel("Najpopularniji");
			lbl2.setFont(new Font("Lucida Sans", Font.BOLD, 20));
			lbl2.setForeground(Color.white);
			donjiDeo.add(lbl2);
			JSeparator sep2 = new JSeparator();
			donjiDeo.add(sep2);
			for (Recept rec : MainWindow.rM.getNajpopularnijih10()) {
				MaliPrikazRecepta mpr = new MaliPrikazRecepta(rec, mW, false);
				donjiDeo.add(mpr);
				paneli.add(mpr);
			}
		} else {
			if (r.size() == 0) {
				JLabel nema = new JLabel("Nema rezultata");
				nema.setFont(new Font("Lucida Sans", Font.BOLD, 20));
				donjiDeo.add(nema);
			} else
				for (Recept rec : r) {
					MaliPrikazRecepta mpr = new MaliPrikazRecepta(rec, mW, false);
					donjiDeo.add(mpr);
					paneli.add(mpr);
				}
		}
		donjiDeo.repaint();
		donjiDeo.revalidate();
	}

	void sortiraj(Kriterijum krit, int smer) {
		ReceptSorter rs = new ReceptSorter(krit, smer);
		Collections.sort(paneli, new Comparator<MaliPrikazRecepta>() {
			@Override
			public int compare(MaliPrikazRecepta o1, MaliPrikazRecepta o2) {
				return rs.compare(o1.getRecept(), o2.getRecept());
			}
		});
		donjiDeo.removeAll();
		for (MaliPrikazRecepta mpr : paneli) {
			donjiDeo.add(mpr);
		}
		donjiDeo.repaint();
		donjiDeo.revalidate();
	}

	void blokada(boolean b) {
		for (Component c : gornjiDeo.getComponents()) {
			c.setEnabled(b);
		}
		for (Component c : donjiDeo.getComponents()) {
			c.setEnabled(b);
		}
		pane.getVerticalScrollBar().setEnabled(b);
		pane.setWheelScrollingEnabled(b);
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		blokada(enabled);
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

}
