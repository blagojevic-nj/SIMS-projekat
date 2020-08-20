package view;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Tezina;

import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import java.awt.Font;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;



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
	private JFrame parent;
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

	/**
	 * Create the panel.
	 */
	public AddReceptPanel(JFrame frame) {
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
				
				int vreme;
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
				
				//TODO napraviti Restoran
			}
		});
		
		
		//TODO povezati sa ostalim panelama
		btnKategorije = new JButton("Kategorije");
		add(btnKategorije, "cell 1 25 16 1,growx");
		
		btnUredjaji = new JButton("Uredjaji");
		add(btnUredjaji, "cell 1 29 16 1,growx");
		
		btnSastojci = new JButton("Sastojci");
		add(btnSastojci, "cell 1 33 16 1,growx");
		
		//panel = new JPanel();
		//panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		//add(panel, "cell 1 35 16 9,grow");
		//btnKreirajRecept.setFont(new Font("Tahoma", Font.BOLD, 16));
		//add(btnKreirajRecept, "cell 9 44 37 2,growx");

	}

}
