package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import model.Korisnik;
import model.Nalog;
import model.RegistrovaniKorisnik;
import javax.swing.SwingConstants;

public class NalogInfoPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField txtTipNaloga;
	private JTextField txtUsername;
	private JTextField txtPrezime;
	private JTextField txtIme;
	private JTextField txtMail;
	private JLabel lblPrivilegijaVal;
	private JLabel lblBrojPratilaca;
	private JLabel lblBedzVal;
	private JLabel lblOcenaVal;
	private RegistrovaniKorisnik korisnik;
	private Nalog nalog;
	private Image img;
	
	public NalogInfoPanel(Nalog nalog, Korisnik korisnik) {
		this.korisnik = (RegistrovaniKorisnik)korisnik;
		this.nalog = nalog;
		this.img = new ImageIcon("data/ikonice/back2.jpg").getImage();
		setLayout(null);
		setBounds(200, 0, 840, 650);
		setOpaque(false);

		JSeparator sep1 = new JSeparator();
		sep1.setBounds(0, 105, 840, 5);
		add(sep1);
		
		JLabel lblTipNaloga = new JLabel("Tip naloga:");
		lblTipNaloga.setForeground(Color.WHITE);
		lblTipNaloga.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTipNaloga.setBounds(130, 120, 190, 30);
		add(lblTipNaloga);

		JLabel lblKorisnicko = new JLabel("Korisnicko ime:");
		lblKorisnicko.setForeground(Color.WHITE);
		lblKorisnicko.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblKorisnicko.setBounds(130, 160, 190, 30);
		add(lblKorisnicko);
		
		txtTipNaloga = new JTextField();
		txtTipNaloga.setEditable(false);
		txtTipNaloga.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTipNaloga.setBounds(415, 120, 355, 30);
		txtTipNaloga.setColumns(10);
		add(txtTipNaloga);

		txtUsername = new JTextField();
		txtUsername.setEditable(false);
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtUsername.setBounds(415, 160, 355, 30);
		txtUsername.setColumns(10);
		add(txtUsername);
		
		JSeparator sep2 = new JSeparator();
		sep2.setBounds(0, 200, 840, 5);
		add(sep2);
		
		JLabel lblPrezime = new JLabel("Prezime:");
		lblPrezime.setForeground(Color.WHITE);
		lblPrezime.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPrezime.setBounds(130, 240, 190, 30);
		add(lblPrezime);

		JLabel lblIme = new JLabel("Ime:");
		lblIme.setForeground(Color.WHITE);
		lblIme.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIme.setBounds(130, 290, 190, 30);
		add(lblIme);

		JLabel lblEmail = new JLabel("e-mail:");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(130, 340, 190, 30);
		add(lblEmail);

		txtPrezime = new JTextField();
		txtPrezime.setEditable(false);
		txtPrezime.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtPrezime.setBounds(415, 240, 355, 30);
		add(txtPrezime);
		txtPrezime.setColumns(10);

		txtIme = new JTextField();
		txtIme.setEditable(false);
		txtIme.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtIme.setBounds(415, 290, 355, 30);
		add(txtIme);
		txtIme.setColumns(10);

		txtMail = new JTextField();
		txtMail.setEditable(false);
		txtMail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtMail.setBounds(415, 340, 355, 30);
		add(txtMail);
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
		add(btnPrezime);

		JButton btnIme = new JButton(new ImageIcon(edit2));
		btnIme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtIme.setEditable(!txtIme.isEditable());
			}
		});
		btnIme.setBounds(70, 290, 30, 30);
		add(btnIme);

		JButton btnMail = new JButton(new ImageIcon(edit3));
		btnMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtMail.setEditable(!txtMail.isEditable());
			}
		});
		btnMail.setBounds(70, 340, 30, 30);
		add(btnMail);
		
		
		ImageIcon psdImg = new ImageIcon("data/ikonice/novaLozinka.png");
		Image lozinka = psdImg.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		
		
		JButton btnLozinka = new JButton(new ImageIcon(lozinka));
		btnLozinka.setHorizontalAlignment(SwingConstants.LEFT);
		btnLozinka.setBounds(40, 370, 160, 65);
		add(btnLozinka);
		btnLozinka.setToolTipText("Promena Lozinke!");
		btnLozinka.setBorderPainted(false);
		btnLozinka.setFocusPainted(false);
		btnLozinka.setContentAreaFilled(false);
		btnLozinka.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PromenaLozinkeDialog pld = new PromenaLozinkeDialog(NalogInfoPanel.this, nalog);
				pld.setVisible(true);
			}
		});
		
		JSeparator sep3 = new JSeparator();
		sep3.setBounds(0, 460, 840, 5);
		add(sep3);

		JLabel lblPrivilegija = new JLabel("Privilegija:");
		lblPrivilegija.setForeground(Color.WHITE);
		lblPrivilegija.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPrivilegija.setBounds(70, 510, 125, 30);
		add(lblPrivilegija);

		JLabel lblPratilaca = new JLabel("Broj pratilaca:");
		lblPratilaca.setForeground(Color.WHITE);
		lblPratilaca.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPratilaca.setBounds(70, 590, 200, 30);
		add(lblPratilaca);

		JLabel lblBedz = new JLabel("Bedz:");
		lblBedz.setForeground(Color.WHITE);
		lblBedz.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBedz.setBounds(490, 510, 70, 30);
		add(lblBedz);

		JLabel lblOcena = new JLabel("Ocena:");
		lblOcena.setForeground(Color.WHITE);
		lblOcena.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblOcena.setBounds(490, 590, 70, 30);
		add(lblOcena);

		lblPrivilegijaVal = new JLabel();
		lblPrivilegijaVal.setForeground(Color.WHITE);
		lblPrivilegijaVal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPrivilegijaVal.setBounds(205, 490, 75, 75);
		add(lblPrivilegijaVal);

		lblBrojPratilaca = new JLabel(((RegistrovaniKorisnik)korisnik).getBrojPratilaca()+"");
		lblBrojPratilaca.setForeground(Color.WHITE);
		lblBrojPratilaca.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBrojPratilaca.setBounds(220, 590, 125, 30);
		add(lblBrojPratilaca);

		lblBedzVal = new JLabel(((RegistrovaniKorisnik)korisnik).getBedz()+"");
		lblBedzVal.setForeground(Color.WHITE);
		lblBedzVal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBedzVal.setBounds(630, 475, 100, 100);
		add(lblBedzVal);

		lblOcenaVal = new JLabel(((RegistrovaniKorisnik)korisnik).getProsecnaOcena()+"");
		lblOcenaVal.setForeground(Color.WHITE);
		lblOcenaVal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblOcenaVal.setBounds(665, 590, 125, 30);
		add(lblOcenaVal);

		ImageIcon saveDugme = new ImageIcon("data/ikonice/save.png");
		Image save = saveDugme.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		
		JButton btnSacuvaj = new JButton(new ImageIcon(save));
		btnSacuvaj.setBounds(710, 380, 50, 45);
		add(btnSacuvaj);
		btnSacuvaj.setToolTipText("Sačuvaj!");
		btnSacuvaj.setBorderPainted(false);
		btnSacuvaj.setFocusPainted(false);
		btnSacuvaj.setContentAreaFilled(false);
		btnSacuvaj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				korisnik.setIme(txtIme.getText());
				korisnik.setPrezime(txtPrezime.getText());
				korisnik.setEmail(txtMail.getText());
				JOptionPane.showMessageDialog(null, "Podaci su sacuvani");
			}
		});

		
		ImageIcon odbaci = new ImageIcon("data/ikonice/dismiss.png");
		Image dismiss = odbaci.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		JButton btnOdbaci = new JButton(new ImageIcon(dismiss));
		btnSacuvaj.setToolTipText("Odbaci izmene!");
		btnOdbaci.setBorderPainted(false);
		btnOdbaci.setFocusPainted(false);
		btnOdbaci.setContentAreaFilled(false);		
		btnOdbaci.setBounds(630, 375, 60, 55);
		add(btnOdbaci);
		btnOdbaci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetujInfo();
			}
		});
		resetujInfo();
		
		
		ImageIcon mainIcon = new ImageIcon("data/ikonice/podesavanja.png");
		Image podesavanja = mainIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(315, 10, 245, 85);
		add(lblNewLabel);
		lblNewLabel.setIcon(mainIcon);
		
		
		
		ImageIcon info = new ImageIcon("data/ikonice/info.png");
		Image infoSl = info.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		
		JButton button = new JButton(new ImageIcon(infoSl));
		button.setToolTipText("Info");
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BedzInfo bi = new BedzInfo();
				bi.setVisible(true);
			}
		});
		button.setBounds(440, 505, 40, 40);
		add(button);
		
		

	}
	
	
	
	private void resetujInfo() {
		
		txtUsername.setText(korisnik.getKorisnickoIme());
		txtTipNaloga.setText(String.valueOf(nalog.getTip()));
		txtMail.setText(korisnik.getEmail());
		txtIme.setText(korisnik.getIme());
		txtPrezime.setText(korisnik.getPrezime());
		postavidonjeInf();
	}
	
	
	
	private void postavidonjeInf()
	{
		ImageIcon priv1 = new ImageIcon("data/ikonice/privilegija.png");
		Image privilegija = priv1.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
		
		ImageIcon priv2 = new ImageIcon("data/ikonice/privilegijeBez.png");
		Image privilegijeBez = priv2.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
		
		ImageIcon bedz1 = new ImageIcon("data/ikonice/bedzevi/nemaBedz.png");
		Image b1 = bedz1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		
		ImageIcon bedz2 = new ImageIcon("data/ikonice/bedzevi/oneStar.png");
		Image b2 = bedz2.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		
		ImageIcon bedz3 = new ImageIcon("data/ikonice/bedzevi/twoStar.png");
		Image b3 = bedz3.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		
		ImageIcon bedz4 = new ImageIcon("data/ikonice/bedzevi/threeStar.png");
		Image b4 = bedz4.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		
		ImageIcon bedz5 = new ImageIcon("data/ikonice/bedzevi/master.png");
		Image b5 = bedz5.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		

		if(korisnik.isPrivilegovani())
		{
			
			lblPrivilegijaVal.setIcon(new ImageIcon(privilegija));
			
		}
		else
		{
			
			lblPrivilegijaVal.setIcon(new ImageIcon(privilegijeBez));

		}
		
		
		
		switch (korisnik.getBedz()) {
		
		
			case NEMA:
				lblBedzVal.setIcon(new ImageIcon(b1));
				break;
				
				
			case JAJE:
				
				lblBedzVal.setIcon(new ImageIcon(b2));
				break;
				
				
			case KUVANO_JAJE:
				lblBedzVal.setIcon(new ImageIcon(b3));
				break;
				
				
			case PRZENO_JAJE:
				lblBedzVal.setIcon(new ImageIcon(b4));
				break;
				
				
			case OMLET:
				lblBedzVal.setIcon(new ImageIcon(b5));
				break;
				
				
				
			default:
				break;
			}
		
		
		
	}
	
	
	
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
}
