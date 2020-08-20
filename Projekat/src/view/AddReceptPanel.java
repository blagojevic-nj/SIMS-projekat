package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import manageri.KorisnikManager;
import model.Kategorija;
import model.Recept;
import model.RegistrovaniKorisnik;
import model.Sastojak;
import model.Tezina;
import model.Uredjaj;
import model.UredjajUReceptu;
import net.miginfocom.swing.MigLayout;



//Za testiranje
		//AddReceptPanel recept = new AddReceptPanel(this);
		//recept.setSize(getWidth(), getHeight());
		//mainContentContainerPanel.add(recept, "recept");
		//contentCardLayout.show(mainContentContainerPanel, "recept");
		
public class AddReceptPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainWindow parent;
	private JTextField txtNazivRecepta;
	private JTextField txtVremePripreme;
	private JTextArea txtrUnesiteKorake;
	private JTextField txtYoutubeLink;
	private JButton btnPutanjaDoSlike;
	private JButton btnKreirajRecept;
	private File slika;
	private JButton btnKategorije;
	private JButton btnUredjaji;
	private JButton btnSastojci;
	private JPanel panel;
	private CardLayout layout;
	//private ArrayList<Kategorija> kategorije;
	//private ArrayList<Uredjaj> uredjaji;
	//private ArrayList<Sastojak> sastojci;

	/**
	 * Create the panel.
	 */
	public AddReceptPanel(MainWindow frame, JPanel containerPanel, CardLayout contentLayout) {
		layout = contentLayout;
		panel = containerPanel;
		parent = frame;
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[][grow][][][][][][][grow][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow][][][][][grow]", "[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow][][][][][grow][][][grow][grow][][grow]"));
		
		JLabel lblInformacije = new JLabel("Unesite informacije o receptu");
		lblInformacije.setFont(new Font("Tahoma", Font.BOLD, 22));
		add(lblInformacije, "cell 1 0 48 1,alignx center");
		
		txtNazivRecepta = new JTextField();
		txtNazivRecepta.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				JTextField src = (JTextField)e.getSource();
				src.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				JTextField src = (JTextField)e.getSource();
				if(src.getText().equals(""))
					src.setText("Naziv recepta");
			}
			
		});
		txtNazivRecepta.setToolTipText("Naziv recepta");
		txtNazivRecepta.setText("Naziv Recepta");
		add(txtNazivRecepta, "cell 1 7 16 1,growx");
		txtNazivRecepta.setColumns(10);
		
		txtrUnesiteKorake = new JTextArea();
		txtrUnesiteKorake.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				JTextArea src = (JTextArea)e.getSource();
				src.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				JTextArea src = (JTextArea)e.getSource();
				if(src.getText().equals(""))
					src.setText("Unesite korake");
			}
			
		});
		txtrUnesiteKorake.setLineWrap(true);
		txtrUnesiteKorake.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		txtrUnesiteKorake.setBackground(Color.WHITE);
		txtrUnesiteKorake.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtrUnesiteKorake.setToolTipText("Koraci");
		txtrUnesiteKorake.setText("Unesite korake");
		add(txtrUnesiteKorake, "cell 24 5 23 30,grow");
		
		txtVremePripreme = new JTextField();
		txtVremePripreme.setToolTipText("Vreme pripreme");
		txtVremePripreme.setText("Vreme pripreme");
		
		add(txtVremePripreme, "cell 1 10 16 1,growx");
		txtVremePripreme.setColumns(10);
		txtVremePripreme.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				JTextField src = (JTextField)e.getSource();
				src.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				JTextField src = (JTextField)e.getSource();
				if(src.getText().equals(""))
					src.setText("Vreme pripreme");
			}
			
		});
		
		txtYoutubeLink = new JTextField();
		txtYoutubeLink.setToolTipText("Youtube link");
		txtYoutubeLink.setText("Youtube link");
		add(txtYoutubeLink, "cell 1 13 16 1,growx");
		txtYoutubeLink.setColumns(10);
		txtYoutubeLink.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				JTextField src = (JTextField)e.getSource();
				src.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				JTextField src = (JTextField)e.getSource();
				if(src.getText().equals(""))
					src.setText("Youtube link");
			}
			
		});
		
		JFileChooser fc  = new JFileChooser();
		
		btnPutanjaDoSlike = new JButton();
		btnPutanjaDoSlike.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				fc.addChoosableFileFilter(new ImageFilter());
				fc.setAcceptAllFileFilterUsed(false);
				int returnVal =fc.showDialog(parent, "Birajte sliku");
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					File slika = fc.getSelectedFile();
				}
			}
		});
		
		JComboBox<Tezina> tezina = new JComboBox<Tezina>();
		tezina.addItem(Tezina.LAKO);
		tezina.addItem(Tezina.SREDNJE);
		tezina.addItem(Tezina.TESKO);
		tezina.setToolTipText("Tezina recepta");
		add(tezina, "cell 1 17 16 1,growx");
		btnPutanjaDoSlike.setToolTipText("Putanja slike");
		btnPutanjaDoSlike.setText("Putanja do slike ");
		add(btnPutanjaDoSlike, "cell 1 21 16 1,growx");
		
		btnKreirajRecept = new JButton("Kreiraj Recept");
		btnKreirajRecept.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnKreirajRecept.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				boolean validan = true;
				if(txtNazivRecepta.getText().equals("") || txtNazivRecepta.getText().equals("Naziv Recepta")) {
					validan = false;
				}
				
				if(txtVremePripreme.getText().equals("") || txtNazivRecepta.getText().equals("Vreme pripreme")) {
					validan = false;
				}
				
				if(txtrUnesiteKorake.getText().equals("") || txtNazivRecepta.getText().equals("Unesite korake")) {
					validan = false;
				}
				
				int vreme = 0;
				try {
					vreme = Integer.parseInt(txtVremePripreme.getText());
				}catch (Exception e) {
					validan = false;
				}
				
				if(!validan) {
					JOptionPane.showMessageDialog(new JFrame(), "Nisu sve informacije unete ili "
							+ "su neke informacije nekorektne!" , "Greska", JOptionPane.ERROR_MESSAGE);
				}
				
				String youtube;
				if(txtYoutubeLink.getText().equals("Youtube link")) {
					youtube = null;
				}else {
					youtube = txtYoutubeLink.getText();
				}
				
				String naziv = txtNazivRecepta.getText();
				String koraci = txtrUnesiteKorake.getText();
				Tezina tesko = (Tezina) tezina.getSelectedItem();
				
				
/*********************************************************************************************/

/*Proslediti panele kao argumente pa onda pozivati metode, ne static!*/
				
				//ArrayList<Kategorija> kategorije = MainWindow.kPanel.getBiraneKategorije();
				//ArrayList<Sastojak> sastojci = MainWindow.sPanel.getBiraniSastojci();
				//ArrayList<Uredjaj> uredjaji = MainWindow.uPanel.getBiraniUredjaji();

				
	//			ArrayList<UredjajUReceptu> uredj = new ArrayList<UredjajUReceptu>();
	//			for(Uredjaj u : uredjaji)
	//				uredj.add(new UredjajUReceptu(true, u));
	//			ArrayList<Integer> kat = new ArrayList<Integer>();
	//			for(Kategorija k: kategorije)
	//				kat.add(k.getSifra());
				
	//			MainWindow.rM.noviRecept(naziv, "", koraci, tesko, vreme, uredj, sastojci, kat, (RegistrovaniKorisnik) KorisnikManager.getInstance().getKorisnik(parent.trenutniNalog.getKorisnickoIme()), "");
	//			MainWindow.rM.sacuvajRecepte();
	//			MainWindow.rM.sacuvajTabelu();
				
				
/*********************************************************************************************/

			}
		});
		add(btnKreirajRecept, "cell 2 40 46 5,growx");
		
		
		
		
		btnKategorije = new JButton("Kategorije");
		btnKategorije.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				layout.show(panel, "kategorije");
			}
		});
		add(btnKategorije, "cell 1 25 16 1,growx");
		
		btnUredjaji = new JButton("Uredjaji");
		btnUredjaji.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				layout.show(panel, "uredjaji");
			}
		});
		add(btnUredjaji, "cell 1 29 16 1,growx");
		
		btnSastojci = new JButton("Sastojci");
		btnSastojci.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				layout.show(panel, "sastojci");
			}
		});
		add(btnSastojci, "cell 1 33 16 1,growx");
		
		//panel = new JPanel();
		//panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		//add(panel, "cell 1 35 16 9,grow");
		//btnKreirajRecept.setFont(new Font("Tahoma", Font.BOLD, 16));
		//add(btnKreirajRecept, "cell 9 44 37 2,growx");

	}

}
