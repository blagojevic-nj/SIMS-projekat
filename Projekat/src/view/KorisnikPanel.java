package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import manageri.KategorijaManager;
import manageri.KorisnikManager;
import manageri.ProizvodManager;
import manageri.ReceptManager;
import manageri.UredjajManager;
import model.Kategorija;
import model.Korisnik;
import model.Nalog;
import model.Recept;
import model.RegistrovaniKorisnik;
import net.miginfocom.swing.MigLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class KorisnikPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel mainMenu, trenutniPanel, pnlInfo, pnlRecepti, pnlKuhinja;
	private JLabel naslov, username, lblNalog, lblKuhinja, lblRecepti,
			lblPodesavanje, lblDodajRecept, lblPraceni, lblIzvestaj;
	private KorisnikManager km;
	private ReceptManager rm;
	private KategorijaManager kgm;
	private ProizvodManager pm;
	private UredjajManager um;
	private Nalog trenutniNalog;
	private Korisnik korisnik;
	private MainWindow mw;
	private JLabel lblPrivilegijaVal, lblBrojPratilaca, lblBedzVal, lblOcenaVal;
	private JTextField txtTipNaloga, txtUsername, txtPrezime, txtIme, txtMail;
	private DefaultListModel<String> listModel;
	private JList<String> list;
	private JScrollPane scrollRecepti;

	public KorisnikPanel(MainWindow mainWindow, Nalog trenutniNalog) {
		mw = mainWindow;
		km = KorisnikManager.getInstance();
		rm = ReceptManager.getInstance();
		pm = ProizvodManager.getInstance();
		kgm = KategorijaManager.getInstance();
		um = UredjajManager.getInstance();
		this.trenutniNalog = trenutniNalog;
		korisnik = km.getKorisnik(trenutniNalog.getKorisnickoIme());

		setLayout(null);
		setBounds(0, 0, 1040, 650);
		setBackground(Color.DARK_GRAY);

		mainMenu = new JPanel(null);
		mainMenu.setBounds(0, 0, 200, 650);
		mainMenu.setBackground(Color.white);
		add(mainMenu);
		initMeni();
		username.setText(trenutniNalog.getKorisnickoIme());
	}

	private void initMeni() {
		lblNalog = new JLabel("Moj Nalog");
		lblNalog.setHorizontalAlignment(SwingConstants.CENTER);
		lblNalog.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNalog.setBounds(0, 0, 200, 60);
		lblNalog.setForeground(Color.black);
		mainMenu.add(lblNalog);
		
		JSeparator sep1 = new JSeparator();
		sep1.setBounds(0, 70, 200, 5);
		mainMenu.add(sep1);
		
		JLabel lblKorisnik = new JLabel("Korisnik:");
		lblKorisnik.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblKorisnik.setBounds(10, 90, 75, 20);
		mainMenu.add(lblKorisnik);
		
		username = new JLabel();
		username.setFont(new Font("Tahoma", Font.PLAIN, 20));
		username.setBounds(10, 125, 180, 25);
		mainMenu.add(username);
		
		JSeparator sep2 = new JSeparator();
		sep2.setBounds(0, 165, 200, 5);
		mainMenu.add(sep2);

		lblKuhinja = new JLabel("Moja Kuhinja");
		lblKuhinja.setToolTipText("Unos sastojaka i uredjaja kojima raspolazes!");
		lblKuhinja.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblKuhinja.setHorizontalAlignment(SwingConstants.CENTER);
		lblKuhinja.setForeground(Color.BLACK);
		lblKuhinja.setBounds(0, 230, 200, 60);
		mainMenu.add(lblKuhinja);
		lblKuhinja.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				if (((Component) evt.getSource()).isEnabled()) {
					lblKuhinja.setBackground(Color.LIGHT_GRAY);
					lblKuhinja.setOpaque(true);
				}
			}

			public void mouseExited(MouseEvent evt) {
				if (((Component) evt.getSource()).isEnabled()) {
					lblKuhinja.setBackground(Color.WHITE);
				}
			}
			public void mouseClicked(MouseEvent evt) {
				JLabel label = (JLabel) evt.getSource();
				if (!((Component) evt.getSource()).isEnabled()) {
					return;
				}
				naslov.setText("Moja Kuhinja");
				prikaziKuhinjaPanel();
			}
		});

		lblRecepti = new JLabel("Moji recepti");
		lblRecepti.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRecepti.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecepti.setForeground(Color.BLACK);
		lblRecepti.setBounds(0, 290, 200, 60);
		mainMenu.add(lblRecepti);
		lblRecepti.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				if (((Component) evt.getSource()).isEnabled()) {
					lblRecepti.setBackground(Color.LIGHT_GRAY);
					lblRecepti.setOpaque(true);
				}
			}

			public void mouseExited(MouseEvent evt) {
				if (((Component) evt.getSource()).isEnabled()) {
					lblRecepti.setBackground(Color.WHITE);
				}
			}

			public void mouseClicked(MouseEvent evt) {
				if (!((Component) evt.getSource()).isEnabled()) {
					return;
				}
				naslov.setText("Moji recepti");
				prikaziMojiReceptiPanel();
			}
		});

		lblPodesavanje = new JLabel("Pode\u0161avanja naloga");
		lblPodesavanje.setToolTipText("Promena korisni\u010Dkih informacija!");
		lblPodesavanje.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPodesavanje.setHorizontalAlignment(SwingConstants.CENTER);
		lblPodesavanje.setForeground(Color.BLACK);
		lblPodesavanje.setBounds(0, 350, 200, 60);
		mainMenu.add(lblPodesavanje);
		lblPodesavanje.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				if (((Component) evt.getSource()).isEnabled()) {
					lblPodesavanje.setBackground(Color.LIGHT_GRAY);
					lblPodesavanje.setOpaque(true);
				}
			}

			public void mouseExited(MouseEvent evt) {
				if (((Component) evt.getSource()).isEnabled()) {
					lblPodesavanje.setBackground(Color.WHITE);
				}
			}

			public void mouseClicked(MouseEvent evt) {
				if (!((Component) evt.getSource()).isEnabled()) {
					return;
				}
				naslov.setText("Podešavanja naloga");
				prikaziInfoPanel();
			}
		});

		lblDodajRecept = new JLabel("Dodaj novi recept");
		lblDodajRecept.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDodajRecept.setHorizontalAlignment(SwingConstants.CENTER);
		lblDodajRecept.setForeground(Color.BLACK);
		lblDodajRecept.setBounds(0, 410, 200, 60);
		mainMenu.add(lblDodajRecept);
		lblDodajRecept.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				if (((Component) evt.getSource()).isEnabled()) {
					lblDodajRecept.setBackground(Color.LIGHT_GRAY);
					lblDodajRecept.setOpaque(true);
				}
			}

			public void mouseExited(MouseEvent evt) {
				if (((Component) evt.getSource()).isEnabled()) {
					lblDodajRecept.setBackground(Color.WHITE);
				}
			}

			public void mouseClicked(MouseEvent evt) {
				if (!((Component) evt.getSource()).isEnabled()) {
					return;
				}
				naslov.setText("Unos novog recepta");
				mw.postaviDesniPanel(new DodavanjeRecepta(mw));
			}
		});

		lblPraceni = new JLabel("Praceni nalozi");
		lblPraceni.setToolTipText("Praceni nalozi");
		lblPraceni.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPraceni.setHorizontalAlignment(SwingConstants.CENTER);
		lblPraceni.setForeground(Color.BLACK);
		lblPraceni.setBounds(0, 470, 200, 60);
		mainMenu.add(lblPraceni);
		lblPraceni.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				if (((Component) evt.getSource()).isEnabled()) {
					lblPraceni.setBackground(Color.LIGHT_GRAY);
					lblPraceni.setOpaque(true);
				}
			}

			public void mouseExited(MouseEvent evt) {
				if (((Component) evt.getSource()).isEnabled()) {
					lblPraceni.setBackground(Color.WHITE);
				}
			}

			public void mouseClicked(MouseEvent evt) {
				if (!((Component) evt.getSource()).isEnabled()) {
					return;
				}
				naslov.setText("Praceni nalozi");
				// TODO praceni
			}
		});

		lblIzvestaj = new JLabel("Izve\u0161taj");
		lblIzvestaj.setToolTipText("Izve\u0161taj");
		lblIzvestaj.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblIzvestaj.setHorizontalAlignment(SwingConstants.CENTER);
		lblIzvestaj.setForeground(Color.BLACK);
		lblIzvestaj.setBounds(0, 590, 200, 60);
		mainMenu.add(lblIzvestaj);
		lblIzvestaj.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				if (((Component) evt.getSource()).isEnabled()) {
					lblIzvestaj.setBackground(Color.LIGHT_GRAY);
					lblIzvestaj.setOpaque(true);
				}
			}

			public void mouseExited(MouseEvent evt) {
				if (((Component) evt.getSource()).isEnabled()) {
					lblIzvestaj.setBackground(Color.WHITE);
				}
			}

			public void mouseClicked(MouseEvent evt) {
				if (!((Component) evt.getSource()).isEnabled()) {
					return;
				}
				naslov.setText("Izve\u0161taj");
				// TODO izvestaji
			}
		});

		/* Naslovna labela */
		naslov = new JLabel("");
		naslov.setFont(new Font("Tahoma", Font.BOLD, 20));
		naslov.setBounds(500, 10, 300, 30);
		naslov.setForeground(Color.WHITE);
		add(naslov);
	}

	private void postaviPanel(JPanel novi) {
		if (trenutniPanel != null)
			remove(trenutniPanel);
		trenutniPanel = novi;
		add(novi);
		revalidate();
		repaint();
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		blokada(enabled);
	}

	public void blokada(boolean b) {
		for (Component c : this.getComponents()) {
			if (c.getClass().equals(JPanel.class)) {
				System.out.println("Prosao panel");
				blokada((JPanel) c, b);
			}
			c.setEnabled(b);
			System.out.println(c);
		}
	}

	public void blokada(JPanel p, boolean b) {
		for (Component c : p.getComponents()) {
			if (c.getClass().equals(JPanel.class)) {
				System.out.println("Prosao panel");
				blokada((JPanel) c, b);
			}
			c.setEnabled(b);
			System.out.println(c);
		}
	}
	
	public void prikaziMojiReceptiPanel() {
		if (pnlRecepti == null) {
			pnlRecepti = new JPanel(null);
			pnlRecepti.setBounds(200, 50, 840, 600);
			JPanel receptiPane = new JPanel(new MigLayout("wrap 1", "[][]10[]", "[]10[]"));
			receptiPane.setBackground(Color.DARK_GRAY);
			scrollRecepti = new JScrollPane(receptiPane);
			scrollRecepti.setBounds(0, 0, 840, 600);
			scrollRecepti.getVerticalScrollBar().setUnitIncrement(20);
			pnlRecepti.add(scrollRecepti);
			for (Recept recept : rm.getRecepti(((RegistrovaniKorisnik) korisnik).getRecepti())) {
				MaliPrikazRecepta mpr = new MaliPrikazRecepta(recept);
				receptiPane.add(mpr);
			}
		}
		postaviPanel(pnlRecepti);
	}

	public void prikaziInfoPanel() {
		if (pnlInfo != null) {
			postaviPanel(pnlInfo);
			return;
		}
		pnlInfo = new JPanel(null);
		pnlInfo.setBounds(200, 0, 840, 650);
		pnlInfo.setOpaque(false);

		JSeparator sep1 = new JSeparator();
		sep1.setBounds(0, 70, 840, 5);
		pnlInfo.add(sep1);
		
		JLabel lblTipNaloga = new JLabel("Tip naloga:");
		lblTipNaloga.setForeground(Color.WHITE);
		lblTipNaloga.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTipNaloga.setBounds(130, 85, 190, 30);
		pnlInfo.add(lblTipNaloga);

		JLabel lblKorisnicko = new JLabel("Korisnicko ime:");
		lblKorisnicko.setForeground(Color.WHITE);
		lblKorisnicko.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblKorisnicko.setBounds(130, 125, 190, 30);
		pnlInfo.add(lblKorisnicko);
		
		txtTipNaloga = new JTextField();
		txtTipNaloga.setEditable(false);
		txtTipNaloga.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTipNaloga.setBounds(415, 85, 355, 30);
		txtTipNaloga.setColumns(10);
		pnlInfo.add(txtTipNaloga);

		txtUsername = new JTextField();
		txtUsername.setEditable(false);
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtUsername.setBounds(415, 125, 355, 30);
		txtUsername.setColumns(10);
		pnlInfo.add(txtUsername);
		
		JSeparator sep2 = new JSeparator();
		sep2.setBounds(0, 165, 840, 5);
		pnlInfo.add(sep2);
		
		JLabel lblPrezime = new JLabel("Prezime:");
		lblPrezime.setForeground(Color.WHITE);
		lblPrezime.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPrezime.setBounds(130, 240, 190, 30);
		pnlInfo.add(lblPrezime);

		JLabel lblIme = new JLabel("Ime:");
		lblIme.setForeground(Color.WHITE);
		lblIme.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIme.setBounds(130, 290, 190, 30);
		pnlInfo.add(lblIme);

		JLabel lblEmail = new JLabel("e-mail:");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(130, 340, 190, 30);
		pnlInfo.add(lblEmail);

		JLabel lblPromenaLozinke = new JLabel("Promena lozinke");
		lblPromenaLozinke.setForeground(Color.WHITE);
		lblPromenaLozinke.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPromenaLozinke.setBounds(130, 390, 190, 30);
		pnlInfo.add(lblPromenaLozinke);

		txtPrezime = new JTextField();
		txtPrezime.setEditable(false);
		txtPrezime.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtPrezime.setBounds(415, 240, 355, 30);
		pnlInfo.add(txtPrezime);
		txtPrezime.setColumns(10);

		txtIme = new JTextField();
		txtIme.setEditable(false);
		txtIme.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtIme.setBounds(415, 290, 355, 30);
		pnlInfo.add(txtIme);
		txtIme.setColumns(10);

		txtMail = new JTextField();
		txtMail.setEditable(false);
		txtMail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtMail.setBounds(415, 340, 355, 30);
		pnlInfo.add(txtMail);
		txtMail.setColumns(10);
		
		ImageIcon img = new ImageIcon("data/ikonice/edit.png");
		Image edit1 = img.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		Image edit2 = img.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		Image edit3 = img.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);

		JButton btnPrezime = new JButton(new ImageIcon(edit1));
		btnPrezime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPrezime.setEditable(!txtPrezime.isEditable());
			}
		});
		btnPrezime.setBounds(70, 240, 30, 30);
		pnlInfo.add(btnPrezime);

		JButton btnIme = new JButton(new ImageIcon(edit2));
		btnIme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtIme.setEditable(!txtIme.isEditable());
			}
		});
		btnIme.setBounds(70, 290, 30, 30);
		pnlInfo.add(btnIme);

		JButton btnMail = new JButton(new ImageIcon(edit3));
		btnMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtMail.setEditable(!txtMail.isEditable());
			}
		});
		btnMail.setBounds(70, 340, 30, 30);
		pnlInfo.add(btnMail);
		
		JButton btnLozinka = new JButton();
		// TODO add action
		btnLozinka.setBounds(70, 390, 30, 30);
		pnlInfo.add(btnLozinka);
		
		JSeparator sep3 = new JSeparator();
		sep3.setBounds(0, 460, 840, 5);
		pnlInfo.add(sep3);

		JLabel lblPrivilegija = new JLabel("Privilegija:");
		lblPrivilegija.setForeground(Color.WHITE);
		lblPrivilegija.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPrivilegija.setBounds(70, 485, 125, 30);
		pnlInfo.add(lblPrivilegija);

		JLabel lblPratilaca = new JLabel("Broj pratilaca:");
		lblPratilaca.setForeground(Color.WHITE);
		lblPratilaca.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPratilaca.setBounds(70, 545, 125, 30);
		pnlInfo.add(lblPratilaca);

		JLabel lblBedz = new JLabel("Bedz:");
		lblBedz.setForeground(Color.WHITE);
		lblBedz.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBedz.setBounds(490, 485, 70, 30);
		pnlInfo.add(lblBedz);

		JLabel lblOcena = new JLabel("Ocena:");
		lblOcena.setForeground(Color.WHITE);
		lblOcena.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblOcena.setBounds(490, 545, 70, 30);
		pnlInfo.add(lblOcena);

		lblPrivilegijaVal = new JLabel("");
		lblPrivilegijaVal.setBounds(220, 485, 125, 30);
		pnlInfo.add(lblPrivilegijaVal);

		lblBrojPratilaca = new JLabel("");
		lblBrojPratilaca.setBounds(220, 545, 125, 30);
		pnlInfo.add(lblBrojPratilaca);

		lblBedzVal = new JLabel("");
		lblBedzVal.setBounds(640, 485, 125, 30);
		pnlInfo.add(lblBedzVal);

		lblOcenaVal = new JLabel("");
		lblOcenaVal.setBounds(640, 545, 125, 30);
		pnlInfo.add(lblOcenaVal);

		JButton btnSacuvaj = new JButton("Sacuvaj");
		btnSacuvaj.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSacuvaj.setBounds(640, 390, 125, 30);
		pnlInfo.add(btnSacuvaj);
		// TODO add action

		JButton btnOdbaci = new JButton(new ImageIcon("data/ikonice/cancel.png"));
		btnOdbaci.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnOdbaci.setBounds(585, 390, 45, 30);
		pnlInfo.add(btnOdbaci);
		btnOdbaci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetujInfo();
			}
		});

		resetujInfo();
		postaviPanel(pnlInfo);
	}

	private void resetujInfo() {
		txtUsername.setText(korisnik.getKorisnickoIme());
		txtTipNaloga.setText(String.valueOf(trenutniNalog.getTip()));
		txtMail.setText(korisnik.getEmail());
		txtIme.setText(korisnik.getIme());
		txtPrezime.setText(korisnik.getPrezime());
	}

	public void prikaziKuhinjaPanel() {
		if (pnlKuhinja != null) {
			postaviPanel(pnlKuhinja);
			return;
		}
		pnlKuhinja = new JPanel(null);
		pnlKuhinja.setBounds(200, 50, 840, 600);
		pnlKuhinja.setOpaque(false);
		
		JLabel lblSastojci = new JLabel("Moji sastojci:");
		lblSastojci.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSastojci.setForeground(Color.WHITE);
		lblSastojci.setBounds(30, 100, 250, 25);
		pnlKuhinja.add(lblSastojci);
		
		DefaultComboBoxModel<String> proizvodBoxModel = new DefaultComboBoxModel<String>();
		proizvodBoxModel.addElement("");
		for (String s : pm.getNaziviProizvoda()) proizvodBoxModel.addElement(s);
		
		JComboBox<String> sastojciBox = new JComboBox<String>(proizvodBoxModel);
		sastojciBox.setBounds(30, 130, 250, 25);
		pnlKuhinja.add(sastojciBox);
				
		DefaultListModel<String> sastojciListModel = new DefaultListModel<String>();
		JList<String> sastojciList = new JList<String>(sastojciListModel);
		JScrollPane sastojciSP = new JScrollPane(sastojciList);
		sastojciSP.setBounds(30, 170, 250, 250);
		pnlKuhinja.add(sastojciSP);
		
		JButton dodaj1 = new JButton(new ImageIcon("data/ikonice/add.png"));
		dodaj1.setBounds(30, 430, 30, 30);
		pnlKuhinja.add(dodaj1);
		JButton obrisi1 = new JButton(new ImageIcon("data/ikonice/cancel.png"));
		obrisi1.setBounds(80, 430, 30, 30);
		pnlKuhinja.add(obrisi1);
		
		JLabel lblKategorije = new JLabel("Omiljene kategorije:");
		lblKategorije.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblKategorije.setForeground(Color.WHITE);
		lblKategorije.setBounds(300, 100, 250, 25);
		pnlKuhinja.add(lblKategorije);
		
		DefaultComboBoxModel<String> kategorijaBoxModel = new DefaultComboBoxModel<String>();
		kategorijaBoxModel.addElement("");
		for (String k : kgm.getNaziviKategorija()) kategorijaBoxModel.addElement(k);
		
		JComboBox<String> kategorijeBox = new JComboBox<String>(kategorijaBoxModel);
		kategorijeBox.setBounds(300, 130, 250, 25);
		pnlKuhinja.add(kategorijeBox);
		
		DefaultListModel<String> kategorijeListModel = new DefaultListModel<String>();
		JList<String> kategorijeList = new JList<String>(kategorijeListModel);
		JScrollPane kategorijeSP = new JScrollPane(kategorijeList);
		kategorijeSP.setBounds(300, 170, 250, 250);
		pnlKuhinja.add(kategorijeSP);
		
		JButton dodaj2 = new JButton(new ImageIcon("data/ikonice/add.png"));
		dodaj2.setBounds(300, 430, 30, 30);
		pnlKuhinja.add(dodaj2);
		JButton obrisi2 = new JButton(new ImageIcon("data/ikonice/cancel.png"));
		obrisi2.setBounds(350, 430, 30, 30);
		pnlKuhinja.add(obrisi2);

		JLabel lblUredjaji = new JLabel("Moji uredjaji:");
		lblUredjaji.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUredjaji.setForeground(Color.WHITE);
		lblUredjaji.setBounds(570, 100, 250, 25);
		pnlKuhinja.add(lblUredjaji);
		
		DefaultComboBoxModel<String> uredjajiBoxModel = new DefaultComboBoxModel<String>();
		uredjajiBoxModel.addElement("");
		for (String u : um.getNaziviUredjaja()) uredjajiBoxModel.addElement(u);
		
		JComboBox<String> uredjajiBox = new JComboBox<String>(uredjajiBoxModel);
		uredjajiBox.setBounds(570, 130, 250, 25);
		pnlKuhinja.add(uredjajiBox);
		
		DefaultListModel<String> uredjajiListModel = new DefaultListModel<String>();
		JList<String> uredjajiList = new JList<String>(uredjajiListModel);
		JScrollPane uredjajiSP = new JScrollPane(uredjajiList);
		uredjajiSP.setBounds(570, 170, 250, 250);
		pnlKuhinja.add(uredjajiSP);
		
		JButton dodaj3 = new JButton(new ImageIcon("data/ikonice/add.png"));
		dodaj3.setBounds(570, 430, 30, 30);
		pnlKuhinja.add(dodaj3);
		JButton obrisi3 = new JButton(new ImageIcon("data/ikonice/cancel.png"));
		obrisi3.setBounds(620, 430, 30, 30);
		pnlKuhinja.add(obrisi3);
		
		// TODO: dodati akcije
		
		postaviPanel(pnlKuhinja);
	}

}
