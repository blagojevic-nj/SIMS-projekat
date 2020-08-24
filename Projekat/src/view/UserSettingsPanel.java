package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import manageri.KategorijaManager;
import manageri.KorisnikManager;
import manageri.ReceptManager;
import model.Kategorija;
import model.Korisnik;
import model.Nalog;
import model.Recept;
import model.RegistrovaniKorisnik;
import net.miginfocom.swing.MigLayout;

import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserSettingsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel main,trenutniPanel,info, receptiKorisnika, korisnikKategorije;
	private JLabel naslov,username,lblNewLabel,lblSauvaniRecepti_1,lblSauvaniRecepti_3,lblSauvaniRecepti_4,lblSauvaniRecepti_5,lblSauvaniRecepti_6,lblSauvaniRecepti_7,lblSauvaniRecepti_8;
	private KorisnikManager km;
	private ReceptManager rm;
	private KategorijaManager kgm;
	private Nalog trenutni;
	private MainWindow mw;
	private Image img;
	private JLabel lblKorisnikoIme;
	private JLabel lblPreyime;
	private JLabel label_2;
	private JLabel lblEmail;
	private JLabel lblTipNaloga;
	private JLabel lblPromenaifre;
	private JSeparator separator_2;
	private JButton btnSifra;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblPrivrez;
	private JLabel lblBrprtrez;
	private JLabel lblBedzrez;
	private JLabel lblOcenarez;
	private JTextField txtPrz;
	private JTextField txtIme;
	private JTextField txtMail;
	private JTextField txtTipnaloga;
	private JTextField txtUsername;
	private JButton btnOdbaci;
	private Korisnik korisnik;
	private DefaultListModel<String> listModel;
	private JList<String> list;
	
	
	public UserSettingsPanel(MainWindow mainWindow,KorisnikManager manager, Nalog trenutniNalog) {
		mw=mainWindow;
		km = manager;
		rm = ReceptManager.getInstance();
		trenutni = trenutniNalog;
		korisnik = MainWindow.km.getKorisnik(trenutni.getKorisnickoIme());
		
		

		this.img = new ImageIcon("data/ikonice/back2.jpg").getImage();
	    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
	    setSize(size);		
		setLayout(null);
		setBounds(0,0,1040,650);
		setBackground(Color.gray);
		

		
		main = new JPanel();
		main.setLayout(null);
		main.setBounds(0, 0, 200, 650);
		add(main);
		main.setBackground(Color.white);
		initializeMaliMeniAndLabels();
		username.setText(trenutniNalog.getKorisnickoIme());

		/*****************************************************************************************************************************************************/
		
		

		
		
		
		
		
	}
	

	private void initializeMaliMeniAndLabels()
	{

		/*
		 * Prva labela.....
		 */
				
				lblNewLabel = new JLabel("Moj Nalog");
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
				lblNewLabel.setBounds(0, 0, 200, 60);
				lblNewLabel.setForeground(Color.black);
				main.add(lblNewLabel);

							
				lblSauvaniRecepti_1 = new JLabel("Omiljene kategorije");
				lblSauvaniRecepti_1.setToolTipText("Uredi svoje omiljene kategorije!");
				lblSauvaniRecepti_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblSauvaniRecepti_1.setHorizontalAlignment(SwingConstants.CENTER);
				lblSauvaniRecepti_1.setForeground(Color.BLACK);
				lblSauvaniRecepti_1.setBounds(0, 230, 200, 60);
				main.add(lblSauvaniRecepti_1);
				lblSauvaniRecepti_1.addMouseListener(new MouseAdapter() 
				{
					
				    public void mouseEntered(MouseEvent evt) {
				    	JLabel label = (JLabel) evt.getSource();
		                if(!label.isEnabled())
		                {
		                    return;
		                }
				    	lblSauvaniRecepti_1.setBackground(Color.LIGHT_GRAY);
				    	lblSauvaniRecepti_1.setOpaque(true);
				    }
				    public void mouseExited(MouseEvent evt) {
				    	JLabel label = (JLabel) evt.getSource();
		                if(!label.isEnabled())
		                {
		                    return;
		                }
				    	lblSauvaniRecepti_1.setBackground(Color.WHITE);
				    }
				    public void mouseClicked(MouseEvent evt) {
				    	JLabel label = (JLabel) evt.getSource();
		                if(!label.isEnabled())
		                {
		                    return;
		                }
				    	naslov.setText("Omiljene kategorije");
				    	prikazKategorijePanel();
				    }
				});

				lblSauvaniRecepti_3 = new JLabel("Moja Kuhinja");
				lblSauvaniRecepti_3.setToolTipText("Unos sastojaka i uređaja kojima raspolažeš!");
				lblSauvaniRecepti_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblSauvaniRecepti_3.setHorizontalAlignment(SwingConstants.CENTER);
				lblSauvaniRecepti_3.setForeground(Color.BLACK);
				lblSauvaniRecepti_3.setBounds(0, 290, 200, 60);
				main.add(lblSauvaniRecepti_3);
				lblSauvaniRecepti_3.addMouseListener(new MouseAdapter() 
				{
				    public void mouseEntered(MouseEvent evt) {
				    	JLabel label = (JLabel) evt.getSource();
		                if(!label.isEnabled())
		                {
		                    return;
		                }
				    	lblSauvaniRecepti_3.setBackground(Color.LIGHT_GRAY);
				    	lblSauvaniRecepti_3.setOpaque(true);
				    }
				    public void mouseExited(MouseEvent evt) {
				    	JLabel label = (JLabel) evt.getSource();
		                if(!label.isEnabled())
		                {
		                    return;
		                }
				    	lblSauvaniRecepti_3.setBackground(Color.WHITE);
				    }
				    public void mouseClicked(MouseEvent evt) {
				    	JLabel label = (JLabel) evt.getSource();
		                if(!label.isEnabled())
		                {
		                    return;
		                }
				    	naslov.setText("Moja Kuhinja");
				    }
				});

				lblSauvaniRecepti_4 = new JLabel("Moji recepti");
				lblSauvaniRecepti_4.setToolTipText("Uredi recepte koje si dodao!");
				lblSauvaniRecepti_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblSauvaniRecepti_4.setHorizontalAlignment(SwingConstants.CENTER);
				lblSauvaniRecepti_4.setForeground(Color.BLACK);
				lblSauvaniRecepti_4.setBounds(0, 350, 200, 60);
				main.add(lblSauvaniRecepti_4);
				lblSauvaniRecepti_4.addMouseListener(new MouseAdapter() 
				{
				    public void mouseEntered(MouseEvent evt) {
				    	JLabel label = (JLabel) evt.getSource();
		                if(!label.isEnabled())
		                {
		                    return;
		                }
				    	lblSauvaniRecepti_4.setBackground(Color.LIGHT_GRAY);
				    	lblSauvaniRecepti_4.setOpaque(true);
				    }
				    public void mouseExited(MouseEvent evt) {
				    	JLabel label = (JLabel) evt.getSource();
		                if(!label.isEnabled())
		                {
		                    return;
		                }
				    	lblSauvaniRecepti_4.setBackground(Color.WHITE);
				    }
				    public void mouseClicked(MouseEvent evt) {
				    	JLabel label = (JLabel) evt.getSource();
		                if(!label.isEnabled())
		                {
		                    return;
		                }
				    	naslov.setText("Moji recepti");
				    	receptiKorisnika = new JPanel(new MigLayout("wrap 1", "[][]20[]", "[]20[]"));
				    	JScrollPane scrollPanela = new JScrollPane(receptiKorisnika);
				    	receptiKorisnika.setSize(1000, 550);
				    	receptiKorisnika.setLocation(200, 100);
				    	receptiKorisnika.setOpaque(false);
				    	ArrayList<Recept> recepti =  rm.getRecepti(((RegistrovaniKorisnik)korisnik).getRecepti());
				    	for (Recept recept : recepti) {
				    		MaliPrikazRecepta mpr = new MaliPrikazRecepta(recept);
				    		receptiKorisnika.add(mpr);
						}
			    		postaviPanel(receptiKorisnika);
			    		main.add(scrollPanela);
				    }
				});

				lblSauvaniRecepti_5 = new JLabel("Pode\u0161avanja naloga");
				lblSauvaniRecepti_5.setToolTipText("Promena korisni\u010Dkih informacija!");
				lblSauvaniRecepti_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblSauvaniRecepti_5.setHorizontalAlignment(SwingConstants.CENTER);
				lblSauvaniRecepti_5.setForeground(Color.BLACK);
				lblSauvaniRecepti_5.setBounds(0, 530, 200, 60);
				main.add(lblSauvaniRecepti_5);
				lblSauvaniRecepti_5.addMouseListener(new MouseAdapter() 
				{
				    public void mouseEntered(MouseEvent evt) {
				    	JLabel label = (JLabel) evt.getSource();
		                if(!label.isEnabled())
		                {
		                    return;
		                }
				    	lblSauvaniRecepti_5.setBackground(Color.LIGHT_GRAY);
				    	lblSauvaniRecepti_5.setOpaque(true);
				    }
				    public void mouseExited(MouseEvent evt) {
				    	JLabel label = (JLabel) evt.getSource();
		                if(!label.isEnabled())
		                {
		                    return;
		                }
				    	lblSauvaniRecepti_5.setBackground(Color.WHITE);
				    }
				    public void mouseClicked(MouseEvent evt) {
				    	JLabel label = (JLabel) evt.getSource();
		                if(!label.isEnabled())
		                {
		                    return;
		                }
				    	naslov.setText("Podešavanja naloga");
				    	informacijPrikazPanel();
				    }
				});

				lblSauvaniRecepti_6 = new JLabel("Dodaj novi recept");
				lblSauvaniRecepti_6.setToolTipText("Dodaj novi recept!");
				lblSauvaniRecepti_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblSauvaniRecepti_6.setHorizontalAlignment(SwingConstants.CENTER);
				lblSauvaniRecepti_6.setForeground(Color.BLACK);
				lblSauvaniRecepti_6.setBounds(0, 470, 200, 60);
				main.add(lblSauvaniRecepti_6);
				lblSauvaniRecepti_6.addMouseListener(new MouseAdapter() 
				{
				    public void mouseEntered(MouseEvent evt) {
				    	JLabel label = (JLabel) evt.getSource();
		                if(!label.isEnabled())
		                {
		                    return;
		                }
				    	lblSauvaniRecepti_6.setBackground(Color.LIGHT_GRAY);
				    	lblSauvaniRecepti_6.setOpaque(true);
				    }
				    public void mouseExited(MouseEvent evt) {
				    	JLabel label = (JLabel) evt.getSource();
		                if(!label.isEnabled())
		                {
		                    return;
		                }
				    	lblSauvaniRecepti_6.setBackground(Color.WHITE);
				    }
				    public void mouseClicked(MouseEvent evt) {
				    	JLabel label = (JLabel) evt.getSource();
		                if(!label.isEnabled())
		                {
		                    return;
		                }
				    	naslov.setText("Unos novog recepta");
						mw.postaviDesniPanel(new DodavanjeRecepta(mw));

				    }
				});

				lblSauvaniRecepti_7 = new JLabel("Praćeni nalozi");
				lblSauvaniRecepti_7.setToolTipText("Praćeni nalozi");
				lblSauvaniRecepti_7.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblSauvaniRecepti_7.setHorizontalAlignment(SwingConstants.CENTER);
				lblSauvaniRecepti_7.setForeground(Color.BLACK);
				lblSauvaniRecepti_7.setBounds(0, 410, 200, 60);
				main.add(lblSauvaniRecepti_7);
				lblSauvaniRecepti_7.addMouseListener(new MouseAdapter() 
				{
				    public void mouseEntered(MouseEvent evt) {
				    	JLabel label = (JLabel) evt.getSource();
		                if(!label.isEnabled())
		                {
		                    return;
		                }
				    	lblSauvaniRecepti_7.setBackground(Color.LIGHT_GRAY);
				    	lblSauvaniRecepti_7.setOpaque(true);
				    }
				    public void mouseExited(MouseEvent evt) {
				    	JLabel label = (JLabel) evt.getSource();
		                if(!label.isEnabled())
		                {
		                    return;
		                }
				    	lblSauvaniRecepti_7.setBackground(Color.WHITE);
				    }
				    public void mouseClicked(MouseEvent evt) {
				    	JLabel label = (JLabel) evt.getSource();
		                if(!label.isEnabled())
		                {
		                    return;
		                }
				    	naslov.setText("Praćeni nalozi");
				    }
				});

				lblSauvaniRecepti_8 = new JLabel("Izve\u0161taj");
				lblSauvaniRecepti_8.setToolTipText("Izve\u0161taj");
				lblSauvaniRecepti_8.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblSauvaniRecepti_8.setHorizontalAlignment(SwingConstants.CENTER);
				lblSauvaniRecepti_8.setForeground(Color.BLACK);
				lblSauvaniRecepti_8.setBounds(0, 590, 200, 60);
				main.add(lblSauvaniRecepti_8);
				lblSauvaniRecepti_8.addMouseListener(new MouseAdapter() 
				{
				    public void mouseEntered(MouseEvent evt) {
				    	JLabel label = (JLabel) evt.getSource();
		                if(!label.isEnabled())
		                {
		                    return;
		                }
				    	lblSauvaniRecepti_8.setBackground(Color.LIGHT_GRAY);
				    	lblSauvaniRecepti_8.setOpaque(true);
				    }
				    public void mouseExited(MouseEvent evt) {
				    	JLabel label = (JLabel) evt.getSource();
		                if(!label.isEnabled())
		                {
		                    return;
		                }
				    	lblSauvaniRecepti_8.setBackground(Color.WHITE);
				    }			    
				    public void mouseClicked(MouseEvent evt) {
				    	JLabel label = (JLabel) evt.getSource();
		                if(!label.isEnabled())
		                {
		                    return;
		                }
				    	naslov.setText("Izveštaj");
				    }
				    
				    
				});
				
				
				
		/*Meni separatori*/	
				JSeparator separator = new JSeparator();
				separator.setBounds(0, 70, 200, 5);
				main.add(separator);
				
				JSeparator separator_1 = new JSeparator();
				separator_1.setBounds(0, 165, 200, 5);
				main.add(separator_1);
				
				
		/**Naslovna labela*/
				naslov = new JLabel("");
				naslov.setFont(new Font("Tahoma", Font.BOLD, 20));
				naslov.setBounds(400, 10, 300, 30);
				naslov.setForeground(Color.white);
				add(naslov);

				/*Uspravni separator meni/ostalo*/
				JSeparator sep = new JSeparator();
				sep.setBounds(200, 0, 5, 650);
				sep.setOrientation(1);
				add(sep);
				sep.setForeground(Color.black);
		
		/*Samo pise...*/		
				JLabel lblKorisnik = new JLabel("Korisnik:");
				lblKorisnik.setFont(new Font("Tahoma", Font.BOLD, 15));
				lblKorisnik.setBounds(10, 90, 75, 20);
				main.add(lblKorisnik);
		/*I ime...*/
				username = new JLabel("Ime");
				username.setFont(new Font("Tahoma", Font.PLAIN, 15));
				username.setBounds(10, 125, 180, 25);
				main.add(username);
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	public void paintComponent(Graphics g) {
		    g.drawImage(img, 0, 0, null);
	 }
	 
	private void postaviPanel(JPanel novi)
		{
			if(trenutniPanel != null)
			{
				remove(trenutniPanel);

			}
			trenutniPanel=novi;
			add(novi);
			revalidate();
			repaint();
		}

	 @Override
	public void setEnabled(boolean enabled)
	 {
			super.setEnabled(enabled);
			blokada(enabled);
	 }
	 
	public void blokada(boolean b) {
			for(Component c: this.getComponents())
			{
				if(c.getClass().equals(JPanel.class))
				{
					System.out.println("Prosao panel");
					blokada((JPanel)c,b);
				}
				c.setEnabled(b);
				System.out.println(c);
			}

		}

	public void blokada(JPanel p, boolean b) {
		for(Component c: p.getComponents()) {
			if(c.getClass().equals(JPanel.class))
			{
				System.out.println("Prosao panel");
				blokada((JPanel)c,b);
			}			
			c.setEnabled(b);
			System.out.println(c);

		}

	}

	public void informacijPrikazPanel()
	{
		
		
		
		
		info = new JPanel();
		info.setSize(840, 650);
		info.setLocation(200, 0);
		info.setLayout(null);
		info.setOpaque(false);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 70, 840, 5);
		info.add(separator);
		
		lblKorisnikoIme = new JLabel("Ime:");
		lblKorisnikoIme.setForeground(Color.WHITE);
		lblKorisnikoIme.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblKorisnikoIme.setBounds(130, 290, 190, 30);
		info.add(lblKorisnikoIme);
		
		lblPreyime = new JLabel("Prezime");
		lblPreyime.setForeground(Color.WHITE);
		lblPreyime.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPreyime.setBounds(130, 240, 190, 30);
		info.add(lblPreyime);
		
		label_2 = new JLabel("Korisničko ime:");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_2.setBounds(130, 125, 190, 30);
		info.add(label_2);
		
		lblEmail = new JLabel("e-Mail:");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(130, 340, 190, 30);
		info.add(lblEmail);
		
		lblTipNaloga = new JLabel("Tip Naloga:");
		lblTipNaloga.setForeground(Color.WHITE);
		lblTipNaloga.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTipNaloga.setBounds(130, 85, 190, 30);
		info.add(lblTipNaloga);
		
		lblPromenaifre = new JLabel("Promena šifre");
		lblPromenaifre.setForeground(Color.WHITE);
		lblPromenaifre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPromenaifre.setBounds(130, 390, 190, 30);
		info.add(lblPromenaifre);
		
		separator_2 = new JSeparator();
		separator_2.setBounds(0, 165, 840, 5);
		info.add(separator_2);
		
		JButton btnPrezime = new JButton("Prezime");
		btnPrezime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtPrz.isEditable()) 
				{
					txtPrz.setEditable(false);
					return;
				}
				txtPrz.setEditable(true);
			}
		});
		btnPrezime.setBounds(70, 241, 30, 30);
		info.add(btnPrezime);
		
		JButton btnIme = new JButton("Ime");
		btnIme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtIme.isEditable()) 
				{
					txtIme.setEditable(false);
					return;
				}
				txtIme.setEditable(true);
			}
		});
		btnIme.setBounds(70, 290, 30, 30);
		info.add(btnIme);
		
		JButton btnMail = new JButton("mail");
		btnMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtMail.isEditable()) 
				{
					txtMail.setEditable(false);
					return;
				}
				txtMail.setEditable(true);
			}
		});
		btnMail.setBounds(70, 340, 30, 30);
		info.add(btnMail);
		
		JLabel lblNewLabel_1 = new JLabel("Privilegija:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(70, 485, 125, 30);
		info.add(lblNewLabel_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 460, 840, 5);
		info.add(separator_1);
		
		btnSifra = new JButton("sifra");
		btnSifra.setBounds(70, 390, 30, 30);
		info.add(btnSifra);
		
		lblNewLabel_2 = new JLabel("Broj Pratilaca:");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(70, 545, 125, 30);
		info.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Bedž:");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(490, 485, 70, 30);
		info.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Ocena:");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(490, 545, 70, 30);
		info.add(lblNewLabel_4);
		
		lblPrivrez = new JLabel("");
		lblPrivrez.setBounds(220, 485, 125, 30);
		info.add(lblPrivrez);
		
		lblBrprtrez = new JLabel("");
		lblBrprtrez.setBounds(220, 545, 125, 30);
		info.add(lblBrprtrez);
		
		lblBedzrez = new JLabel("");
		lblBedzrez.setBounds(640, 485, 125, 30);
		info.add(lblBedzrez);
		
		lblOcenarez = new JLabel("");
		lblOcenarez.setBounds(640, 545, 125, 30);
		info.add(lblOcenarez);
		
		txtPrz = new JTextField();
		txtPrz.setEditable(false);
		txtPrz.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtPrz.setBounds(415, 240, 355, 30);
		info.add(txtPrz);
		txtPrz.setColumns(10);
		
		txtIme = new JTextField();
		txtIme.setEditable(false);
		txtIme.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtIme.setBounds(415, 290, 355, 30);
		info.add(txtIme);
		txtIme.setColumns(10);


		txtMail = new JTextField();
		txtMail.setEditable(false);
		txtMail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtMail.setBounds(415, 340, 355, 30);
		info.add(txtMail);
		txtMail.setColumns(10);

		
		JButton btnSacuvaj = new JButton("Sacuvaj");
		btnSacuvaj.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSacuvaj.setBounds(640, 390, 125, 30);
		info.add(btnSacuvaj);
		
		txtTipnaloga = new JTextField();
		txtTipnaloga.setEditable(false);
		txtTipnaloga.setBounds(415, 85, 355, 30);
		info.add(txtTipnaloga);
		txtTipnaloga.setColumns(10);
		
		txtUsername = new JTextField();
		txtUsername.setEditable(false);
		txtUsername.setBounds(415, 125, 355, 30);
		info.add(txtUsername);
		txtUsername.setColumns(10);
		
		btnOdbaci = new JButton(new ImageIcon("data/ikonice/cancel.png"));
		btnOdbaci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetujInfo();
			}
		});
		btnOdbaci.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnOdbaci.setBounds(585, 390, 45, 30);
		info.add(btnOdbaci);
		
		


		resetujInfo();
		postaviPanel(info);
	}

	private void resetujInfo()
	{
		txtUsername.setText(korisnik.getKorisnickoIme());
		txtTipnaloga.setText(String.valueOf(trenutni.getTip()));
		txtMail.setText(korisnik.getEmail());
		txtIme.setText(korisnik.getIme());
		txtPrz.setText(korisnik.getPrezime());

	}
	
	public void prikazKategorijePanel() {
		korisnikKategorije = new JPanel();
		korisnikKategorije.setSize(840, 550);
		korisnikKategorije.setLocation(200, 100);
		korisnikKategorije.setOpaque(false);
		korisnikKategorije.setLayout(new MigLayout("", "[grow][]", "[][grow]"));
		
		listModel = new DefaultListModel<String>();
		
		//ArrayList<Kategorija> kategorije = new ArrayList<Kategorija>();
		ArrayList<Integer> sifre = ((RegistrovaniKorisnik) korisnik).getKategorije();
		
		if(sifre != null) {
			for (Integer sifra : sifre) {
				Kategorija kategorija = kgm.getKategorija(sifra);
				//kategorije.add(kategorija);
				listModel.addElement(kategorija.getNaziv());
			}
		}
		
		JScrollPane scrollPane = new JScrollPane();
		
		list = new JList<String>(listModel);
		scrollPane.setViewportView(list);
		korisnikKategorije.add(scrollPane, "cell 0 1,grow");
		
		JButton btnDodajNoveKategorije = new JButton("Dodaj nove kategorije");
		btnDodajNoveKategorije.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//TODO nekako povezati sa kategorija panelom i onda dodavati
			}
		});
		korisnikKategorije.add(btnDodajNoveKategorije, "cell 0 0,growx");
		
		JButton btnBrisiOdabranuKategoriju = new JButton("Brisi odabranu kategoriju");
		btnBrisiOdabranuKategoriju.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				for ( String selektovani : list.getSelectedValuesList()) {
					listModel.removeElement(selektovani);
				}
			}
		});
		korisnikKategorije.add(btnBrisiOdabranuKategoriju, "cell 1 1,alignx center");
		
		JButton btnX = new JButton(new ImageIcon("data/ikonice/cancel.png"));
		btnX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				listModel.removeAllElements();
			}
		});
		korisnikKategorije.add(btnX, "flowx,cell 1 2,alignx left");
		
		JButton btnSacuvaj = new JButton("Sacuvaj");
		btnX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//TODO sacuvanje kategorija korisniku
			}
		});
		btnSacuvaj.setFont(new Font("Tahoma", Font.PLAIN, 20));
		korisnikKategorije.add(btnSacuvaj, "cell 1 2,alignx right");
		
		postaviPanel(korisnikKategorije);
	}
	
}
