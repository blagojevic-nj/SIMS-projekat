package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import manageri.KategorijaManager;
import manageri.ProizvodManager;
import manageri.UredjajManager;
import model.RegistrovaniKorisnik;

public class MojaKuhinjaPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private ProizvodManager pm;
	private KategorijaManager kgm;
	private UredjajManager um;
	private Image img;
	private JList<String> sastojciList, kategorijeList, uredjajiList;
	private JScrollPane sastojciSP, kategorijeSP, uredjajiSP;

	public MojaKuhinjaPanel(RegistrovaniKorisnik korisnik, MainWindow mw) {
		this.img = new ImageIcon("data/ikonice/back2.jpg").getImage();
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);

		um = mw.uM;
		pm = mw.pM;
		kgm = mw.katM;

		setLayout(null);
		setBounds(200, 0, 1040, 650);
	
		JLabel logoKuhinje = new JLabel(new ImageIcon("data/ikonice/myKitchen.png"));
		logoKuhinje.setBounds(300, 10, 200, 100);
		add(logoKuhinje);
		

		JLabel lblSastojci = new JLabel("Moji sastojci:");
		lblSastojci.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSastojci.setForeground(Color.WHITE);
		lblSastojci.setBounds(30, 100, 250, 25);
		add(lblSastojci);

		DefaultComboBoxModel<String> proizvodBoxModel = new DefaultComboBoxModel<String>();
		proizvodBoxModel.addElement("");
		for (String s : pm.getNaziviProizvoda())
			proizvodBoxModel.addElement(s);

		JComboBox<String> sastojciBox = new JComboBox<String>(proizvodBoxModel);
		sastojciBox.setBounds(30, 130, 250, 25);
		add(sastojciBox);

		DefaultListModel<String> sastojciListModel = new DefaultListModel<String>();
		sastojciList = new JList<String>(sastojciListModel);
		sastojciList.setFixedCellHeight(40);
		sastojciList.setFont(new Font("Tahoma", Font.BOLD, 18));
		sastojciSP = new JScrollPane(sastojciList);
		sastojciSP.setBounds(30, 170, 250, 250);
		add(sastojciSP);

		JButton dodaj1 = new JButton(new ImageIcon("data/ikonice/add.png"));
		dodaj1.setBounds(30, 430, 30, 30);
		dodaj1.setToolTipText("Dodaj sastojak");
		dodaj1.setContentAreaFilled(false);
		dodaj1.setFocusPainted(false);
		dodaj1.setBorderPainted(false);
		add(dodaj1);
		JButton obrisi1 = new JButton(new ImageIcon("data/ikonice/cancel.png"));
		obrisi1.setBounds(60, 430, 30, 30);
		obrisi1.setToolTipText("Obrisi sastojak");
		obrisi1.setContentAreaFilled(false);
		obrisi1.setFocusPainted(false);
		obrisi1.setBorderPainted(false);
		add(obrisi1);

		JLabel lblKategorije = new JLabel("Omiljene kategorije:");
		lblKategorije.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblKategorije.setForeground(Color.WHITE);
		lblKategorije.setBounds(300, 200, 250, 25);
		add(lblKategorije);

		DefaultComboBoxModel<String> kategorijaBoxModel = new DefaultComboBoxModel<String>();
		kategorijaBoxModel.addElement("");
		for (String k : kgm.getNaziviKategorija())
			kategorijaBoxModel.addElement(k);

		JComboBox<String> kategorijeBox = new JComboBox<String>(kategorijaBoxModel);
		kategorijeBox.setBounds(300, 230, 250, 25);
		add(kategorijeBox);

		DefaultListModel<String> kategorijeListModel = new DefaultListModel<String>();
		kategorijeList = new JList<String>(kategorijeListModel);
		kategorijeList.setFixedCellHeight(40);
		kategorijeList.setFont(new Font("Tahoma", Font.BOLD, 18));
		kategorijeSP = new JScrollPane(kategorijeList);
		kategorijeSP.setBounds(300, 270, 250, 250);
		add(kategorijeSP);

		JButton dodaj2 = new JButton(new ImageIcon("data/ikonice/add.png"));
		dodaj2.setBounds(300, 530, 30, 30);
		dodaj2.setToolTipText("Dodaj kategoriju");
		dodaj2.setContentAreaFilled(false);
		dodaj2.setFocusPainted(false);
		dodaj2.setBorderPainted(false);
		add(dodaj2);
		JButton obrisi2 = new JButton(new ImageIcon("data/ikonice/cancel.png"));
		obrisi2.setBounds(330, 530, 30, 30);
		obrisi2.setToolTipText("Obrisi kategoriju");
		obrisi2.setContentAreaFilled(false);
		obrisi2.setFocusPainted(false);
		obrisi2.setBorderPainted(false);
		add(obrisi2);

		JLabel lblUredjaji = new JLabel("Moji uredjaji:");
		lblUredjaji.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUredjaji.setForeground(Color.WHITE);
		lblUredjaji.setBounds(570, 280, 250, 25);
		add(lblUredjaji);

		DefaultComboBoxModel<String> uredjajiBoxModel = new DefaultComboBoxModel<String>();
		uredjajiBoxModel.addElement("");
		for (String u : um.getNaziviUredjaja())
			uredjajiBoxModel.addElement(u);

		JComboBox<String> uredjajiBox = new JComboBox<String>(uredjajiBoxModel);
		uredjajiBox.setBounds(570, 310, 250, 25);
		add(uredjajiBox);

		DefaultListModel<String> uredjajiListModel = new DefaultListModel<String>();
		uredjajiList = new JList<String>(uredjajiListModel);
		uredjajiList.setFixedCellHeight(40);
		uredjajiList.setFont(new Font("Tahoma", Font.BOLD, 18));
		uredjajiSP = new JScrollPane(uredjajiList);
		uredjajiSP.setBounds(570, 350, 250, 250);
		add(uredjajiSP);

		JButton dodaj3 = new JButton(new ImageIcon("data/ikonice/add.png"));
		dodaj3.setBounds(570, 610, 30, 30);
		dodaj3.setToolTipText("Dodaj uredjaj");
		dodaj3.setContentAreaFilled(false);
		dodaj3.setFocusPainted(false);
		dodaj3.setBorderPainted(false);
		add(dodaj3);
		JButton obrisi3 = new JButton(new ImageIcon("data/ikonice/cancel.png"));
		obrisi3.setBounds(600, 610, 30, 30);
		obrisi3.setToolTipText("Obrisi uredjaj");
		obrisi3.setContentAreaFilled(false);
		obrisi3.setFocusPainted(false);
		obrisi3.setBorderPainted(false);
		add(obrisi3);

		for (int sifra : korisnik.getProizvodi())
			sastojciListModel.addElement(pm.getProizvod(sifra).getNaziv());
		for (int sifra : korisnik.getKategorije())
			kategorijeListModel.addElement(kgm.getKategorija(sifra).getNaziv());
		for (int sifra : korisnik.getUredjaji())
			uredjajiListModel.addElement(um.getUredjaj(sifra).getNaziv());

		dodaj1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int sifra = sastojciBox.getSelectedIndex() - 1;
				if (sifra != -1) {
					if (!korisnik.getProizvodi().contains(sifra)) {
						sastojciListModel.addElement((String) sastojciBox.getSelectedItem());
						korisnik.addProizvod(sifra);
					}
				}
			}
		});

		dodaj2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int sifra = kategorijeBox.getSelectedIndex() - 1;
				if (sifra != -1) {
					if (!korisnik.getKategorije().contains(sifra)) {
						kategorijeListModel.addElement((String) kategorijeBox.getSelectedItem());
						korisnik.addKategorija(sifra);
					}
				}
			}
		});

		dodaj3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int sifra = uredjajiBox.getSelectedIndex() - 1;
				if (sifra != -1) {
					if (!korisnik.getUredjaji().contains(sifra)) {
						uredjajiListModel.addElement((String) uredjajiBox.getSelectedItem());
						korisnik.addUredjaj(sifra);
					}
				}
			}
		});

		obrisi1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (sastojciList.getSelectedValue() != null) {
					korisnik.deleteProizvod(pm.getProizvod(sastojciList.getSelectedValue()).getSifra());
					sastojciListModel.removeElement(sastojciList.getSelectedValue());
				}
			}
		});

		obrisi2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (kategorijeList.getSelectedValue() != null) {
					korisnik.deleteKategorija(kgm.getKategorija(kategorijeList.getSelectedValue()).getSifra());
					kategorijeListModel.removeElement(kategorijeList.getSelectedValue());
				}
			}
		});

		obrisi3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (uredjajiList.getSelectedValue() != null) {
					korisnik.deleteUredjaj(um.getUredjaj(uredjajiList.getSelectedValue()).getSifra());
					uredjajiListModel.removeElement(uredjajiList.getSelectedValue());
				}
			}
		});
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

	@Override
	public void setEnabled(boolean b) {
		super.setEnabled(b);
		for (Component c : this.getComponents())
			c.setEnabled(b);
		sastojciSP.getVerticalScrollBar().setEnabled(b);
		sastojciSP.setWheelScrollingEnabled(b);
		kategorijeSP.getVerticalScrollBar().setEnabled(b);
		kategorijeSP.setWheelScrollingEnabled(b);
		uredjajiSP.getVerticalScrollBar().setEnabled(b);
		uredjajiSP.setWheelScrollingEnabled(b);
		
		sastojciList.setEnabled(b);
		kategorijeList.setEnabled(b);
		uredjajiList.setEnabled(b);
	}

}
