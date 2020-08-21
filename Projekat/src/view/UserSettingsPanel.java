package view;

import javax.swing.JPanel;
import javax.swing.JSeparator;

import manageri.KorisnikManager;
import model.Nalog;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class UserSettingsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel main,trenutniPanel;
	private JLabel naslov,username,lblNewLabel,lblSauvaniRecepti_1,lblSauvaniRecepti_2,lblSauvaniRecepti_3,lblSauvaniRecepti_4,lblSauvaniRecepti_5,lblSauvaniRecepti_6,lblSauvaniRecepti_7,lblSauvaniRecepti_8;
	private KorisnikManager km;
	private Nalog trenutni;
	private MainWindow mw;
	private Image img;
	
	public UserSettingsPanel(MainWindow mainWindow,KorisnikManager manager, Nalog trenutniNalog) {
		mw=mainWindow;
		km = manager;
		trenutni = trenutniNalog;
		
		
		

		this.img = new ImageIcon("data/ikonice/back2.jpg").getImage();
	    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
	    //setPreferredSize(size);
	    //setMinimumSize(size);
	    //setMaximumSize(size);
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
				lblSauvaniRecepti_1.setBounds(0, 170, 200, 60);
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
				    }
				});

				lblSauvaniRecepti_2 = new JLabel("Ure\u0111aji");
				lblSauvaniRecepti_2.setToolTipText("Unos ure\u0111aja kojim raspola\u017Ee\u0161!");
				lblSauvaniRecepti_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblSauvaniRecepti_2.setHorizontalAlignment(SwingConstants.CENTER);
				lblSauvaniRecepti_2.setForeground(Color.BLACK);
				lblSauvaniRecepti_2.setBounds(0, 230, 200, 60);
				main.add(lblSauvaniRecepti_2);
				lblSauvaniRecepti_2.addMouseListener(new MouseAdapter() 
				{
				    public void mouseEntered(MouseEvent evt) {
				    	JLabel label = (JLabel) evt.getSource();
		                if(!label.isEnabled())
		                {
		                    return;
		                }
				    	lblSauvaniRecepti_2.setBackground(Color.LIGHT_GRAY);
				    	lblSauvaniRecepti_2.setOpaque(true);
				    }
				    public void mouseExited(MouseEvent evt) {
				    	JLabel label = (JLabel) evt.getSource();
		                if(!label.isEnabled())
		                {
		                    return;
		                }
				    	lblSauvaniRecepti_2.setBackground(Color.WHITE);
				    }
				    public void mouseClicked(MouseEvent evt) {
				    	JLabel label = (JLabel) evt.getSource();
		                if(!label.isEnabled())
		                {
		                    return;
		                }
		                naslov.setText("Uredi Uređaje");
				    }
				});

				lblSauvaniRecepti_3 = new JLabel("Moji sastojci");
				lblSauvaniRecepti_3.setToolTipText("Unos sastojaka kojima raspola\u017Ee\u0161!");
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
				    	naslov.setText("Moji sastojci");
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
				    }
				});

				lblSauvaniRecepti_5 = new JLabel("Pode\u0161avanja naloga");
				lblSauvaniRecepti_5.setToolTipText("Promena korisni\u010Dkih informacija!");
				lblSauvaniRecepti_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblSauvaniRecepti_5.setHorizontalAlignment(SwingConstants.CENTER);
				lblSauvaniRecepti_5.setForeground(Color.BLACK);
				lblSauvaniRecepti_5.setBounds(0, 410, 200, 60);
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

				lblSauvaniRecepti_7 = new JLabel("Info");
				lblSauvaniRecepti_7.setToolTipText("Informacije o nalogu");
				lblSauvaniRecepti_7.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblSauvaniRecepti_7.setHorizontalAlignment(SwingConstants.CENTER);
				lblSauvaniRecepti_7.setForeground(Color.BLACK);
				lblSauvaniRecepti_7.setBounds(0, 530, 200, 60);
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
				    	naslov.setText("Informacije o nalogu");
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
				main.remove(trenutniPanel);

			}
			trenutniPanel=novi;
			main.add(novi);
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



}
