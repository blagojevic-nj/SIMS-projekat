package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import manageri.KategorijaManager;
import manageri.ProizvodManager;
import manageri.ReceptManager;
import model.Kategorija;
import model.Proizvod;
import model.Recept;

public class PretragaPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JComboBox<String> sastojciBox, nepozeljniBox, kategorijeBox;
	private JButton dodaj1, dodaj2, dodaj3, btnPretrazi, btnOcisti;
	private JList<String> sastojciList, nepozeljniList, kategorijeList;
	private JRadioButton rbtnSvi;
	private JSpinner spinner;
	private Image img;
	private ArrayList<Recept> rezulat;

	private ReceptManager rm = ReceptManager.getInstance();
	private ProizvodManager pm = ProizvodManager.getInstance();
	private KategorijaManager km = KategorijaManager.getInstance();

	public PretragaPanel(MainWindow mw) {
		this.img = new ImageIcon("data/ikonice/whiteBackSmall.jpg").getImage();
		setBackground(Color.LIGHT_GRAY);
		setBounds(0, 0, 300, 700);
		setLayout(null);

		JLabel lblPretraga = new JLabel("Pretraga");
		lblPretraga.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblPretraga.setBounds(50, 30, 200, 50);
		add(lblPretraga);

		JSeparator separator = new JSeparator();
		separator.setBounds(50, 80, 200, 5);
		add(separator);

		JLabel lblSastojci = new JLabel("Sastojci:");
		lblSastojci.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSastojci.setBounds(30, 100, 200, 25);
		add(lblSastojci);

		DefaultComboBoxModel<String> proizvodBoxModel = new DefaultComboBoxModel<String>();
		proizvodBoxModel.addElement("");
		for (String s : pm.getNaziviProizvoda())
			proizvodBoxModel.addElement(s);

		sastojciBox = new JComboBox<String>(proizvodBoxModel);
		sastojciBox.setBounds(30, 120, 200, 25);
		add(sastojciBox);

		dodaj1 = new JButton(new ImageIcon("data/ikonice/add.png"));
		dodaj1.setContentAreaFilled(false);
		dodaj1.setFocusPainted(false);
		dodaj1.setBorderPainted(false);
		dodaj1.setBounds(240, 120, 25, 25);
		add(dodaj1);

		DefaultListModel<String> sastojciListModel = new DefaultListModel<String>();
		sastojciList = new JList<String>(sastojciListModel);
		JScrollPane sastojciSP = new JScrollPane(sastojciList);
		sastojciSP.setBounds(30, 150, 200, 75);
		add(sastojciSP);

		rbtnSvi = new JRadioButton("Svi navedeni sastojci su u receptu");
		rbtnSvi.setBounds(30, 230, 230, 25);
		rbtnSvi.setOpaque(false);
		add(rbtnSvi);

		JSeparator separator1 = new JSeparator();
		separator1.setBounds(30, 270, 230, 5);
		add(separator1);

		JLabel lblNepozeljni = new JLabel("Nepozeljni sastojci:");
		lblNepozeljni.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNepozeljni.setBounds(30, 280, 200, 25);
		add(lblNepozeljni);

		nepozeljniBox = new JComboBox<String>(proizvodBoxModel);
		nepozeljniBox.setBounds(30, 300, 200, 25);
		add(nepozeljniBox);

		dodaj2 = new JButton(new ImageIcon("data/ikonice/add.png"));
		dodaj2.setContentAreaFilled(false);
		dodaj2.setFocusPainted(false);
		dodaj2.setBorderPainted(false);
		dodaj2.setBounds(240, 300, 25, 25);
		add(dodaj2);

		DefaultListModel<String> nepozeljniListModel = new DefaultListModel<String>();
		nepozeljniList = new JList<String>(nepozeljniListModel);
		JScrollPane nepozeljniSP = new JScrollPane(nepozeljniList);
		nepozeljniSP.setBounds(30, 330, 200, 50);
		add(nepozeljniSP);

		JSeparator separator2 = new JSeparator();
		separator2.setBounds(30, 390, 230, 5);
		add(separator2);

		JLabel lblKategorije = new JLabel("Kategorije:");
		lblKategorije.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblKategorije.setBounds(30, 400, 200, 25);
		add(lblKategorije);

		DefaultComboBoxModel<String> kategorijaBoxModel = new DefaultComboBoxModel<String>();
		kategorijaBoxModel.addElement("");
		for (String k : km.getNaziviKategorija())
			kategorijaBoxModel.addElement(k);

		kategorijeBox = new JComboBox<String>(kategorijaBoxModel);
		kategorijeBox.setBounds(30, 420, 200, 25);
		add(kategorijeBox);

		dodaj3 = new JButton(new ImageIcon("data/ikonice/add.png"));
		dodaj3.setContentAreaFilled(false);
		dodaj3.setFocusPainted(false);
		dodaj3.setBorderPainted(false);
		dodaj3.setBounds(240, 420, 25, 25);
		add(dodaj3);

		DefaultListModel<String> kategorijeListModel = new DefaultListModel<String>();
		kategorijeList = new JList<String>(kategorijeListModel);
		JScrollPane kategorijeSP = new JScrollPane(kategorijeList);
		kategorijeSP.setBounds(30, 450, 200, 50);
		add(kategorijeSP);

		JSeparator separator3 = new JSeparator();
		separator3.setBounds(30, 510, 230, 5);
		add(separator3);

		JLabel lblVreme = new JLabel("Vreme pripreme maks.:");
		lblVreme.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVreme.setBounds(30, 520, 200, 25);
		add(lblVreme);

		SpinnerNumberModel sModel = new SpinnerNumberModel(60, 10, 300, 5);
		spinner = new JSpinner(sModel);
		// spinner.setEditor(new JSpinner.DefaultEditor(spinner));
		spinner.setBounds(190, 520, 40, 25);
		add(spinner);

		btnPretrazi = new JButton("Pretrazi", new ImageIcon("data/ikonice/search2.png"));
		btnPretrazi.setBounds(170, 660, 120, 30);
		btnPretrazi.setEnabled(false);
		add(btnPretrazi);

		btnOcisti = new JButton("Ocisti");
		btnOcisti.setBounds(20, 660, 120, 30);
		add(btnOcisti);

		dodaj1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (sastojciBox.getSelectedIndex() != 0) {
					sastojciListModel.addElement((String) sastojciBox.getSelectedItem());
					btnPretrazi.setEnabled(true);
				}
			}
		});

		dodaj2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (nepozeljniBox.getSelectedIndex() != 0) {
					nepozeljniListModel.addElement((String) nepozeljniBox.getSelectedItem());
					btnPretrazi.setEnabled(true);
				}
			}
		});

		dodaj3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (kategorijeBox.getSelectedIndex() != 0) {
					kategorijeListModel.addElement((String) kategorijeBox.getSelectedItem());
					btnPretrazi.setEnabled(true);
				}
			}
		});

		btnPretrazi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				preuzmiArgumenteIPretrazi();
				mw.collapseSmallMenu();
				mw.postaviDesniPanel(new ReceptiPanel(rezulat, mw));
			}
		});

		btnOcisti.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnPretrazi.setEnabled(false);
				sastojciBox.setSelectedIndex(0);
				nepozeljniBox.setSelectedIndex(0);
				kategorijeBox.setSelectedIndex(0);
				sastojciListModel.removeAllElements();
				nepozeljniListModel.removeAllElements();
				kategorijeListModel.removeAllElements();
				rbtnSvi.setSelected(false);
				spinner.setValue(10);
			}
		});
	}

	private void preuzmiArgumenteIPretrazi() {
		ArrayList<Proizvod> sastojci = new ArrayList<Proizvod>();
		ArrayList<Proizvod> nepozeljni = new ArrayList<Proizvod>();
		ArrayList<Kategorija> kategorije = new ArrayList<Kategorija>();

		for (int i = 0; i < sastojciList.getModel().getSize(); i++) {
			sastojci.add(pm.getProizvod(sastojciList.getModel().getElementAt(i)));
		}
		for (int i = 0; i < nepozeljniList.getModel().getSize(); i++) {
			nepozeljni.add(pm.getProizvod(nepozeljniList.getModel().getElementAt(i)));
		}
		for (int i = 0; i < kategorijeList.getModel().getSize(); i++) {
			kategorije.add(km.getKategorija(kategorijeList.getModel().getElementAt(i)));
		}
		int vreme = (int) spinner.getValue();
		if (vreme >= 300)
			vreme = Integer.MAX_VALUE;
		rezulat = rm.pretraziPoKriterijumima(sastojci, rbtnSvi.isSelected(), nepozeljni, kategorije, vreme);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
}
