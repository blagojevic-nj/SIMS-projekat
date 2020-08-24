package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import model.Sastojak;
import model.TipSastojka;

public class SastojciPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private ArrayList<Sastojak> sastojci;
	private ArrayList<Stavka> stavke;
	public DodavanjeRecepta parent;

	public SastojciPanel() {
		sastojci = new ArrayList<Sastojak>();
		stavke = new ArrayList<Stavka>();
		setLayout(null);
		setBounds(540, 120, 500, 180);

		JTextField pretraga = new JTextField("Naziv sastojka");
		pretraga.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				JTextField src = (JTextField) e.getSource();
				src.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				JTextField src = (JTextField) e.getSource();
				if (src.getText().equals(""))
					src.setText("Naziv sastojka");
			}
		});

		pretraga.setBounds(0, 0, 500, 20);
		add(pretraga);

		JPanel lista = new JPanel(new MigLayout());
		for (String s : MainWindow.pM.getNaziviProizvoda()) {
			Stavka stavka = new Stavka(s);
			stavke.add(stavka);
			lista.add(stavka.cb);
			lista.add(stavka.tip);
			lista.add(stavka.kol);
			lista.add(stavka.kolicina);
			lista.add(stavka.m);
			lista.add(stavka.mera, "wrap");
		}

		JScrollPane pane = new JScrollPane(lista);
		pane.setBounds(0, 20, 500, 130);

		add(pane);

		JButton dodaj = new JButton("Novi", new ImageIcon("data/ikonice/add.png"));
		dodaj.setBounds(0, 150, 250, 30);
		add(dodaj);

		dodaj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String naziv = JOptionPane.showInputDialog("Unesite ime novog sastojka:");
				if (naziv != null)
					if (!naziv.trim().equals("")) {
						MainWindow.pM.novProizvod(naziv.trim());
						MainWindow.pM.sacuvajProizvode();
						Stavka s = new Stavka(naziv.trim());
						stavke.add(s);
						revalidate();
						repaint();
					}
			}

		});

		JButton potvrda = new JButton("Potvrda");
		potvrda.setBounds(250, 150, 250, 30);
		add(potvrda);

		potvrda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				sastojci.clear();
				for (Stavka s : stavke) {
					if (s.cb.isSelected()) {
						Sastojak sastojak = s.getSastojakFromStavka();
						if (sastojak != null)
							sastojci.add(sastojak);
						else {
							JOptionPane.showMessageDialog(parent, "Neki od unetih podataka nisu validni, ispravite!");
							return;
						}
					}
				}

				lista.removeAll();
				for (Stavka s : stavke) {
					lista.add(s.cb);
					lista.add(s.tip);
					lista.add(s.kol);
					lista.add(s.kolicina);
					lista.add(s.m);
					lista.add(s.mera, "wrap");
				}

				revalidate();
				repaint();

				SastojciPanel.this.setVisible(false);
				SastojciPanel.this.parent.blokada(true);
			}
		});

		pretraga.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				lista.removeAll();
				for (Stavka s : stavke) {
					if (s.cb.getText().toLowerCase().startsWith(pretraga.getText().toLowerCase())) {
						lista.add(s.cb);
						lista.add(s.tip);
						lista.add(s.kol);
						lista.add(s.kolicina);
						lista.add(s.m);
						lista.add(s.mera, "wrap");
					}
				}

				revalidate();
				repaint();
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				lista.removeAll();
				for (Stavka s : stavke) {
					if (s.cb.getText().toLowerCase().startsWith(pretraga.getText().toLowerCase())) {
						lista.add(s.cb);
						lista.add(s.tip);
						lista.add(s.kol);
						lista.add(s.kolicina);
						lista.add(s.m);
						lista.add(s.mera, "wrap");
					}
				}

				revalidate();
				repaint();

			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				lista.removeAll();
				for (Stavka s : stavke) {
					if (s.cb.getText().toLowerCase().startsWith(pretraga.getText().toLowerCase())) {
						lista.add(s.cb);
						lista.add(s.tip);
						lista.add(s.kol);
						lista.add(s.kolicina);
						lista.add(s.m);
						lista.add(s.mera, "wrap");
					}
				}

				revalidate();
				repaint();
			}

		});
	}

	public ArrayList<Sastojak> getSastojci() {
		return sastojci;
	}

	public void setSastojci(ArrayList<Sastojak> sastojci) {
		this.sastojci = sastojci;
	}

}

class Stavka extends JPanel {

	private static final long serialVersionUID = 1L;
	public JCheckBox cb;
	public JTextField kolicina, mera;
	public JLabel kol, m;
	public JComboBox<TipSastojka> tip;

	public Stavka(String s) {
		setLayout(new MigLayout());
		cb = new JCheckBox(s);
		kol = new JLabel("Kolicina: ");
		kolicina = new JTextField(5);
		m = new JLabel("Mera: ");
		mera = new JTextField(5);
		tip = new JComboBox<TipSastojka>(TipSastojka.values());

		add(cb);
		add(kol);
		add(kolicina);
		add(m);
		add(mera);
		add(tip);
	}

	Sastojak getSastojakFromStavka() {
		boolean validan = true;
		int k = 0;
		try {
			k = Integer.parseInt(kolicina.getText());
		} catch (Exception e) {
			validan = false;
		}
		if (mera.getText().equals(""))
			validan = false;
		if (validan) {
			Sastojak s = new Sastojak(k, mera.getText(), (TipSastojka) tip.getSelectedItem(), cb.getText());
			return s;
		}
		return null;
	}

}
