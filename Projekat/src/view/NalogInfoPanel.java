package view;

import java.awt.Color;
import java.awt.Font;
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
	private Korisnik korisnik;
	private Nalog nalog;
	
	public NalogInfoPanel(Nalog nalog, Korisnik korisnik) {
		this.korisnik = korisnik;
		this.nalog = nalog;
		setLayout(null);
		setBounds(200, 0, 840, 650);
		setOpaque(false);

		JSeparator sep1 = new JSeparator();
		sep1.setBounds(0, 70, 840, 5);
		add(sep1);
		
		JLabel lblTipNaloga = new JLabel("Tip naloga:");
		lblTipNaloga.setForeground(Color.WHITE);
		lblTipNaloga.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTipNaloga.setBounds(130, 85, 190, 30);
		add(lblTipNaloga);

		JLabel lblKorisnicko = new JLabel("Korisnicko ime:");
		lblKorisnicko.setForeground(Color.WHITE);
		lblKorisnicko.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblKorisnicko.setBounds(130, 125, 190, 30);
		add(lblKorisnicko);
		
		txtTipNaloga = new JTextField();
		txtTipNaloga.setEditable(false);
		txtTipNaloga.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTipNaloga.setBounds(415, 85, 355, 30);
		txtTipNaloga.setColumns(10);
		add(txtTipNaloga);

		txtUsername = new JTextField();
		txtUsername.setEditable(false);
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtUsername.setBounds(415, 125, 355, 30);
		txtUsername.setColumns(10);
		add(txtUsername);
		
		JSeparator sep2 = new JSeparator();
		sep2.setBounds(0, 165, 840, 5);
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
		
		JButton btnLozinka = new JButton("Promena lozinke");
		btnLozinka.setBounds(70, 400, 200, 30);
		add(btnLozinka);
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
		lblPrivilegija.setBounds(70, 485, 125, 30);
		add(lblPrivilegija);

		JLabel lblPratilaca = new JLabel("Broj pratilaca:");
		lblPratilaca.setForeground(Color.WHITE);
		lblPratilaca.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPratilaca.setBounds(70, 545, 200, 30);
		add(lblPratilaca);

		JLabel lblBedz = new JLabel("Bedz:");
		lblBedz.setForeground(Color.WHITE);
		lblBedz.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBedz.setBounds(490, 485, 70, 30);
		add(lblBedz);

		JLabel lblOcena = new JLabel("Ocena:");
		lblOcena.setForeground(Color.WHITE);
		lblOcena.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblOcena.setBounds(490, 545, 70, 30);
		add(lblOcena);

		lblPrivilegijaVal = new JLabel(((RegistrovaniKorisnik)korisnik).isPrivilegovani()+"");
		lblPrivilegijaVal.setForeground(Color.WHITE);
		lblPrivilegijaVal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPrivilegijaVal.setBounds(220, 485, 125, 30);
		add(lblPrivilegijaVal);

		lblBrojPratilaca = new JLabel(((RegistrovaniKorisnik)korisnik).getBrojPratilaca()+"");
		lblBrojPratilaca.setForeground(Color.WHITE);
		lblBrojPratilaca.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBrojPratilaca.setBounds(220, 545, 125, 30);
		add(lblBrojPratilaca);

		lblBedzVal = new JLabel(((RegistrovaniKorisnik)korisnik).getBedz()+"");
		lblBedzVal.setForeground(Color.WHITE);
		lblBedzVal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBedzVal.setBounds(640, 485, 125, 30);
		add(lblBedzVal);

		lblOcenaVal = new JLabel(((RegistrovaniKorisnik)korisnik).getProsecnaOcena()+"");
		lblOcenaVal.setForeground(Color.WHITE);
		lblOcenaVal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblOcenaVal.setBounds(640, 545, 125, 30);
		add(lblOcenaVal);

		JButton btnSacuvaj = new JButton("Sacuvaj");
		btnSacuvaj.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSacuvaj.setBounds(640, 390, 125, 30);
		add(btnSacuvaj);
		btnSacuvaj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				korisnik.setIme(txtIme.getText());
				korisnik.setPrezime(txtPrezime.getText());
				korisnik.setEmail(txtMail.getText());
				JOptionPane.showMessageDialog(null, "Podaci su sacuvani");
			}
		});

		JButton btnOdbaci = new JButton(new ImageIcon("data/ikonice/cancel.png"));
		btnOdbaci.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnOdbaci.setBounds(585, 390, 45, 30);
		add(btnOdbaci);
		btnOdbaci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetujInfo();
			}
		});
		resetujInfo();
	}
	
	private void resetujInfo() {
		txtUsername.setText(korisnik.getKorisnickoIme());
		txtTipNaloga.setText(String.valueOf(nalog.getTip()));
		txtMail.setText(korisnik.getEmail());
		txtIme.setText(korisnik.getIme());
		txtPrezime.setText(korisnik.getPrezime());
	}

}
