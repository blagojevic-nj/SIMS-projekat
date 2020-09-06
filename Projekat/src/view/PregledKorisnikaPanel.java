package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import model.Recept;
import model.RegistrovaniKorisnik;
import model.TipNaloga;
import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;

public class PregledKorisnikaPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private RegistrovaniKorisnik k;
	private JLabel bedzVal ;
	private JButton follow,unfollow;
	private Image img;
	private RegistrovaniKorisnik trenutni = null;
	
	public PregledKorisnikaPanel(MainWindow mw, JPanel prethodni, RegistrovaniKorisnik korisnik) {
		

		k=korisnik;
		this.img = new ImageIcon("data/ikonice/back2.jpg").getImage();
		setBounds(0, 0, 1040, 650);
		setLayout(null);
		setBackground(Color.DARK_GRAY);

		
		ImageIcon back1 = new ImageIcon("data/ikonice/back1.png");
		Image b1 = back1.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);	
		JButton nazad = new JButton(new ImageIcon(b1));
		nazad.setBounds(10, 0, 50, 50);
		nazad.setBorderPainted(false);
		nazad.setFocusPainted(false);
		nazad.setContentAreaFilled(false);
		add(nazad);
		nazad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mw.postaviDesniPanel(prethodni);
			}
		});

		JLabel lblKorisnickoIme = new JLabel(korisnik.getKorisnickoIme());
		lblKorisnickoIme.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblKorisnickoIme.setForeground(Color.WHITE);
		lblKorisnickoIme.setBounds(80, 0, 300, 50);
		add(lblKorisnickoIme);

		JSeparator sep = new JSeparator();
		sep.setBounds(0, 50, 1040, 5);
		add(sep);

		JLabel korisnikImg = new JLabel(new ImageIcon("data/ikonice/noUser.png"));
		korisnikImg.setBounds(20, 80, 200, 200);
		add(korisnikImg);

		JLabel imePrezime = new JLabel(korisnik.getIme() + " " + korisnik.getPrezime());
		imePrezime.setFont(new Font("Tahoma", Font.BOLD, 35));
		imePrezime.setForeground(Color.WHITE);
		imePrezime.setBounds(30, 290, 400, 50);
		add(imePrezime);

		ImageIcon zaprati1 = new ImageIcon("data/ikonice/follow.png");
		Image z1 = zaprati1.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);	
		follow = new JButton(new ImageIcon(z1));
		follow.setBounds(220, 235, 50, 50);
		follow.setToolTipText("Zaprati!");
		follow.setBorderPainted(false);
		follow.setFocusPainted(false);
		follow.setContentAreaFilled(false);
		add(follow);
		follow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				trenutni.zaprati(korisnik);
				MainWindow.km.promenjen(trenutni.getKorisnickoIme());
				MainWindow.km.promenjen(korisnik.getKorisnickoIme());
				MainWindow.km.sacuvajKorisnike();
				unfollow.setVisible(true);
				follow.setVisible(false);
				revalidate();
				repaint();			
			}
		});

		
		ImageIcon otprati1 = new ImageIcon("data/ikonice/unfollow.png");
		Image o1 = otprati1.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		unfollow = new JButton(new ImageIcon(o1));
		unfollow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				trenutni.otprati(korisnik);
				MainWindow.km.promenjen(trenutni.getKorisnickoIme());
				MainWindow.km.promenjen(korisnik.getKorisnickoIme());
				MainWindow.km.sacuvajKorisnike();
				unfollow.setVisible(false);
				follow.setVisible(true);
				revalidate();
				repaint();
			}
		});
		unfollow.setBounds(220, 235, 50, 50);
		unfollow.setToolTipText("Otprati!");
		unfollow.setBorderPainted(false);
		unfollow.setFocusPainted(false);
		unfollow.setContentAreaFilled(false);
		add(unfollow);
		
		
		unfollow.setVisible(false);
		follow.setVisible(false);
		if(MainWindow.trenutniNalog != null)
		{
			trenutni = (RegistrovaniKorisnik) MainWindow.km.getKorisnik(MainWindow.trenutniNalog.getKorisnickoIme());
			
			if (trenutni.getPraceni() == null)
			{
				unfollow.setVisible(false);
				follow.setVisible(true);
			}
			else 
			{
				if (trenutni.getPraceni().contains(korisnik.getKorisnickoIme()))
				{
					unfollow.setVisible(true);
					follow.setVisible(false);
				}
				else
				{
					unfollow.setVisible(false);
					follow.setVisible(true);
				}

			}

		}
		
		JLabel email = new JLabel(korisnik.getEmail());
		email.setFont(new Font("Tahoma", Font.BOLD, 20));
		email.setForeground(Color.WHITE);
		email.setBounds(30, 340, 400, 50);
		add(email);
	/*
	 * Staro****************************************************************************************************	
	 */
		//JLabel lblBedz = new JLabel("Bedz:");
		//lblBedz.setFont(new Font("Tahoma", Font.PLAIN, 20));
		//.setForeground(Color.WHITE);
		//lblBedz.setBounds(30, 530, 200, 50);
		//add(lblBedz);
		
		//bedzVal = new JLabel(korisnik.getBedz()+"");
		//bedzVal.setFont(new Font("Tahoma", Font.PLAIN, 25));
		//bedzVal.setForeground(Color.WHITE);
		//bedzVal.setBounds(225, 520, 100, 100);
		//add(bedzVal);
	/*
	 * *********************************************************************************************************	
	 */
		
		bedzVal = new JLabel();
		bedzVal.setFont(new Font("Tahoma", Font.PLAIN, 25));
		bedzVal.setForeground(Color.WHITE);
		bedzVal.setBounds(330, 480, 150, 150);
		add(bedzVal);
		
		
		
		
	/*
	 * *********************************************************************************************************	
	 */
		
		JLabel lblOcena = new JLabel("Ocena:");
		lblOcena.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblOcena.setForeground(Color.WHITE);
		lblOcena.setBounds(30, 459, 200, 50);
		add(lblOcena);
		
		JLabel ocenaVal = new JLabel(korisnik.getProsecnaOcena()+"");
		ocenaVal.setFont(new Font("Tahoma", Font.PLAIN, 25));
		ocenaVal.setForeground(Color.WHITE);
		ocenaVal.setBounds(187, 459, 87, 50);
		add(ocenaVal);
		
		JLabel lblPratioci = new JLabel("Pratioci:");
		lblPratioci.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPratioci.setForeground(Color.WHITE);
		lblPratioci.setBounds(30, 509, 200, 50);
		add(lblPratioci);
		
		JLabel pratiociVal = new JLabel(korisnik.getBrojPratilaca()+"");
		pratiociVal.setFont(new Font("Tahoma", Font.PLAIN, 25));
		pratiociVal.setForeground(Color.WHITE);
		pratiociVal.setBounds(187, 509, 87, 50);
		add(pratiociVal);
		
		JLabel lblBrRecepata = new JLabel("Broj recepata:");
		lblBrRecepata.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBrRecepata.setForeground(Color.WHITE);
		lblBrRecepata.setBounds(30, 559, 200, 50);
		add(lblBrRecepata);
		
		JLabel receptiVal = new JLabel(korisnik.getRecepti().size()+"");
		receptiVal.setFont(new Font("Tahoma", Font.PLAIN, 25));
		receptiVal.setForeground(Color.WHITE);
		receptiVal.setBounds(187, 559, 87, 50);
		add(receptiVal);

		JPanel receptiPane = new JPanel(new MigLayout("wrap 1", "[][]10[]", "[]10[]"));
		receptiPane.setBackground(Color.DARK_GRAY);
		JScrollPane scrollRecepti = new JScrollPane(receptiPane);
		scrollRecepti.setBounds(500, 50, 540, 600);
		scrollRecepti.getVerticalScrollBar().setUnitIncrement(20);
		scrollRecepti.setOpaque(false);
		scrollRecepti.getViewport().setOpaque(false);
		scrollRecepti.getVerticalScrollBar().setVisible(false);
		scrollRecepti.setBorder(BorderFactory.createEmptyBorder());
		scrollRecepti.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		add(scrollRecepti);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(300, 465, 2, 175);
		add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.WHITE);
		separator_1.setBounds(10, 450, 480, 2);
		add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.WHITE);
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(498, 50, 1, 600);
		add(separator_2);
		
		if (korisnik.getRecepti().size() == 0) {
			JLabel nema = new JLabel("Nema recepata");
			nema.setFont(new Font("Lucida Sans", Font.BOLD, 20));
			receptiPane.add(nema);
		} else
			for (Recept recept : MainWindow.rM.getRecepti(korisnik.getRecepti())) {
				MaliPrikazRecepta mpr = new MaliPrikazRecepta(recept, mw, false);
				receptiPane.add(mpr);
			}
		
		
		postaviBedz();
	}

	
	private void postaviBedz()
	{
		
		ImageIcon bedz1 = new ImageIcon("data/ikonice/bedzevi/nemaBedz.png");
		Image b1 = bedz1.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		
		ImageIcon bedz2 = new ImageIcon("data/ikonice/bedzevi/oneStar.png");
		Image b2 = bedz2.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		
		ImageIcon bedz3 = new ImageIcon("data/ikonice/bedzevi/twoStar.png");
		Image b3 = bedz3.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		
		ImageIcon bedz4 = new ImageIcon("data/ikonice/bedzevi/threeStar.png");
		Image b4 = bedz4.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		
		ImageIcon bedz5 = new ImageIcon("data/ikonice/bedzevi/master.png");
		Image b5 = bedz5.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		
		
		
		
		switch (k.getBedz()) {
		
		
			case NEMA:
				bedzVal.setIcon(new ImageIcon(b1));
				break;
				
				
			case JAJE:
				
				bedzVal.setIcon(new ImageIcon(b2));
				break;
				
				
			case KUVANO_JAJE:
				bedzVal.setIcon(new ImageIcon(b3));
				break;
				
				
			case PRZENO_JAJE:
				bedzVal.setIcon(new ImageIcon(b4));
				break;
				
				
			case OMLET:
				bedzVal.setIcon(new ImageIcon(b5));
				break;
				
				
				
			default:
				break;
			}
		
		
		
	}
	
	
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
	
	
	
}
