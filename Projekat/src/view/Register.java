package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import manageri.KorisnikManager;
import model.Korisnik;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Register extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPasswordField passwordField, passwordField_1;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField;
	private MainWindow m;
	private Image img;

	private KorisnikManager km;

	public Register(MainWindow mw) {
		this.img = new ImageIcon("data/ikonice/whiteBackSmall.jpg").getImage();
		m = mw;
		km = m.km;
		setBackground(Color.LIGHT_GRAY);
		setBounds(0, 0, 300, 700);
		setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setBounds(50, 100, 200, 5);
		add(separator);

		JLabel lblLogin = new JLabel("Registracija");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblLogin.setBounds(50, 30, 200, 60);
		add(lblLogin);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsername.setBounds(50, 330, 200, 25);
		add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(50, 395, 200, 25);
		add(lblPassword);

		textField = new JTextField();
		textField.setBounds(50, 360, 200, 25);
		add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setLocation(50, 425);
		passwordField.setSize(200, 25);
		add(passwordField);

		JLabel lblPotvrdiPassword = new JLabel("Potvrdi Password");
		lblPotvrdiPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPotvrdiPassword.setBounds(50, 460, 200, 25);
		add(lblPotvrdiPassword);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(50, 490, 200, 25);
		add(passwordField_1);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(50, 245, 200, 25);
		add(lblEmail);

		JLabel lblPrezime = new JLabel("Prezime");
		lblPrezime.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrezime.setBounds(50, 180, 200, 25);
		add(lblPrezime);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(50, 140, 200, 25);
		add(textField_1);

		JLabel lblIme = new JLabel("Ime");
		lblIme.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIme.setBounds(50, 115, 200, 25);
		add(lblIme);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(50, 310, 200, 5);
		add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(50, 525, 200, 16);
		add(separator_2);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(50, 210, 200, 25);
		add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(50, 275, 200, 25);
		add(textField_3);

		JButton btnNewButton = new JButton(new ImageIcon("data/ikonice/potvrdi.png"));
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBounds(125, 595, 125, 50);
		add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ime = textField_1.getText();
				String prz = textField_2.getText();
				String mail = textField_3.getText();
				String usr = textField.getText();
				String psd1 = String.valueOf(passwordField.getPassword());
				String psd2 = String.valueOf(passwordField_1.getPassword());
				if (!Registracija(ime, prz, mail, usr, psd1, psd2)) {
					System.out.println("FAil");
				}
			}
		});

	}

	private boolean Registracija(String ime, String prz, String mail, String usr, String psd1, String psd2) {
		if (!checkPraznaPolja()) {
			JOptionPane.showMessageDialog(null, "Sva polja moraju biti popunjena!");
			return false;
		}
		if (km.getKorisnik(usr) != null) {
			JOptionPane.showMessageDialog(null, "Korisničko ime zauzeto");
			return false;
		}
		if (!proveriPassword(psd1, psd2)) {
			JOptionPane.showMessageDialog(null, "Šifra 1 i šifra 2 nisu podudarne!");
			return false;
		}
		if (finalnaProvera(ime, prz, mail, usr, psd1, psd2)) {
			Korisnik newKorisnik = km.registracijaKorisnika(ime, prz, mail, usr, psd1);
			System.out.println("Success Registrovan!!!");
			fireClearSignal();
			km.sacuvajKorisnike();
			km.sacuvajNaloge();
			MainWindow.trenutniNalog = km.getNalog(newKorisnik.getKorisnickoIme());
			m.fireNalogChanged();
			m.collapseSmallMenu();
			JOptionPane.showMessageDialog(null, "Registracija uspešna!");
			return true;
		}
		return false;

	}

	private boolean proveriPassword(String p1, String p2) {
		return (p1.equals(p2));
	}

	private boolean finalnaProvera(String ime, String prz, String mail, String usr, String psd1, String psd2) {
		// Ako ime ili prz sadrza brojeve...
		if (ime.matches(".*\\d.*") || prz.matches(".*\\d.*")) {
			JOptionPane.showMessageDialog(null, "Ime i prezime ne smeju sadržati broj!");
			return false;
		}
		if (psd1.length() < 8) {
			JOptionPane.showMessageDialog(null, "Šifra mora biti duga najmanje 8 karaktera!");
			return false;
		}
		if (!km.validnoKorisnickoIme(usr)) {
			JOptionPane.showMessageDialog(null, "Korisničko ime nije validno!");
			return false;
		}

		return true;
	}

	private boolean checkPraznaPolja() {
		String str1 = textField.getText();
		String str2 = textField_1.getText();
		String str3 = textField_2.getText();
		String str4 = textField_3.getText();

		String ps1 = String.valueOf(passwordField.getPassword());
		String ps2 = String.valueOf(passwordField_1.getPassword());

		if (str1.trim().length() == 0 || str2.trim().length() == 0 || str3.trim().length() == 0
				|| str4.trim().length() == 0 || ps1.trim().length() == 0 || ps2.trim().length() == 0) {
			return false;
		}
		return true;

	}

	/** Brise sve podatke iz svih boxova... */
	public void fireClearSignal() {
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");

		passwordField.setText("");
		passwordField_1.setText("");

	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

}
