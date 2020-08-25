package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import manageri.KorisnikManager;
import manageri.ReceptManager;
import model.Korisnik;
import model.Nalog;
import model.Recept;
import model.RegistrovaniKorisnik;
import net.miginfocom.swing.MigLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class KorisnikPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private NalogInfoPanel pnlInfo;
	private MojaKuhinjaPanel pnlKuhinja;
	private JPanel mainMenu, trenutniPanel, pnlRecepti;
	private JLabel naslov, username, lblNalog, lblKuhinja, lblRecepti,
			lblPodesavanje, lblDodajRecept, lblPraceni, lblIzvestaj;
	private KorisnikManager km;
	private ReceptManager rm;
	private Nalog trenutniNalog;
	private Korisnik korisnik;
	private MainWindow mw;

	public KorisnikPanel(MainWindow mainWindow, Nalog trenutniNalog) {
		mw = mainWindow;
		km = KorisnikManager.getInstance();
		rm = ReceptManager.getInstance();
		this.trenutniNalog = trenutniNalog;
		korisnik = km.getKorisnik(trenutniNalog.getKorisnickoIme());
		km.promenjen(korisnik);

		setLayout(null);
		setBounds(0, 0, 1040, 650);
		setBackground(Color.DARK_GRAY);

		mainMenu = new JPanel(null);
		mainMenu.setBounds(0, 0, 200, 650);
		mainMenu.setBackground(Color.white);
		add(mainMenu);
		initMeni();
		username.setText(trenutniNalog.getKorisnickoIme());
		
		naslov.setText("Moji recepti");
		prikaziMojiReceptiPanel();
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
				naslov.setText("Pode�avanja naloga");
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
			pnlRecepti.setOpaque(false);
			pnlRecepti.setBounds(200, 50, 840, 600);
			JPanel receptiPane = new JPanel(new MigLayout("wrap 1", "[][]10[]", "[]10[]"));
			receptiPane.setBackground(Color.DARK_GRAY);
			JScrollPane scrollRecepti = new JScrollPane(receptiPane);
			scrollRecepti.setBounds(30, 0, 810, 600);
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
		if (pnlInfo == null)
			pnlInfo = new NalogInfoPanel(trenutniNalog, korisnik);
		postaviPanel(pnlInfo);
	}

	public void prikaziKuhinjaPanel() {
		if (pnlKuhinja == null)
			pnlKuhinja = new MojaKuhinjaPanel((RegistrovaniKorisnik) korisnik);
		postaviPanel(pnlKuhinja);
	}

}
