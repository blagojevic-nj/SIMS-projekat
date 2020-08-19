package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import org.w3c.dom.Text;

import manageri.KorisnikManager;

import javax.swing.JButton;

public class Register extends JPanel{
	private JPasswordField passwordField,passwordField_1;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField;
	
	private KorisnikManager km = KorisnikManager.getInstance();
	
		public Register()
		{
			setBackground(Color.LIGHT_GRAY);
			setBounds(0, 0, 300, 700);
			setLayout(null);
			
			
			JSeparator separator = new JSeparator();
			separator.setBounds(50, 150, 200, 5);
			add(separator);
			
			
			JLabel lblLogin = new JLabel("Registracija");
			lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 35));
			lblLogin.setBounds(50, 80, 200, 60);
			add(lblLogin);
			
			JLabel lblUsername = new JLabel("Username");
			lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblUsername.setBounds(50, 377, 200, 25);
			add(lblUsername);
			
			JLabel lblPassword = new JLabel("Password");
			lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblPassword.setBounds(50, 442, 200, 25);
			add(lblPassword);
			
			textField = new JTextField();
			textField.setBounds(50, 402, 200, 25);
			add(textField);
			textField.setColumns(10);
			
			passwordField = new JPasswordField();
			passwordField.setLocation(50, 467);
			passwordField.setSize(200, 25);
			add(passwordField);
			
			JLabel lblPotvrdiPassword = new JLabel("Potvrdi Password");
			lblPotvrdiPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblPotvrdiPassword.setBounds(50, 507, 200, 25);
			add(lblPotvrdiPassword);
			
			passwordField_1 = new JPasswordField();
			passwordField_1.setBounds(50, 532, 200, 25);
			add(passwordField_1);
			
			JLabel lblEmail = new JLabel("E-mail");
			lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblEmail.setBounds(50, 295, 200, 25);
			add(lblEmail);
			
			JLabel lblPrezime = new JLabel("Prezime");
			lblPrezime.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblPrezime.setBounds(50, 230, 200, 25);
			add(lblPrezime);
			
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(50, 190, 200, 25);
			add(textField_1);
			
			JLabel lblIme = new JLabel("Ime");
			lblIme.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblIme.setBounds(50, 165, 200, 25);
			add(lblIme);
			
			JSeparator separator_1 = new JSeparator();
			separator_1.setBounds(50, 360, 200, 5);
			add(separator_1);
			
			JSeparator separator_2 = new JSeparator();
			separator_2.setBounds(50, 567, 200, 16);
			add(separator_2);
			
			textField_2 = new JTextField();
			textField_2.setColumns(10);
			textField_2.setBounds(50, 260, 200, 25);
			add(textField_2);
			
			textField_3 = new JTextField();
			textField_3.setColumns(10);
			textField_3.setBounds(50, 325, 200, 25);
			add(textField_3);
			
			JButton btnNewButton = new JButton("Registracija");
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnNewButton.setBounds(125, 595, 125, 40);
			add(btnNewButton);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					String ime = textField_1.getText();
					String prz = textField_2.getText();
					String mail = textField_3.getText();
					String usr = textField.getText();
					String psd1 = String.valueOf(passwordField.getPassword());
					String psd2 = String.valueOf(passwordField_1.getPassword());
					if(!Registracija(ime, prz, mail, usr, psd1, psd2))
					{
						JOptionPane.showMessageDialog(null, "Invalid username/password");
					}
				}
			});

			
			
		}
		
		private boolean Registracija(String ime, String prz, String mail, String usr,String psd1,String psd2) 
		{
			if(!proveriPassword(psd1, psd2))
			{
				JOptionPane.showMessageDialog(null, "Šifra 1 i šifra 2 nisu podudarne!");
				return false;
			}
			if(km.getKorisnik(usr) != null)
			{
				JOptionPane.showMessageDialog(null, "Korisničko ime zauzeto");
				return false;
			}
			if(finalnaProvera(ime, prz, mail, usr, psd1, psd2))
			{
				km.registracijaKorisnika(ime, prz, mail, usr, psd1);
			}
			return true;

		}
		
		private boolean proveriPassword(String p1, String p2)
		{
			return(p1.equals(p2));
		}
		
		private boolean finalnaProvera(String ime, String prz, String mail, String usr,String psd1,String psd2)
		{
			//Ako ime ili prz sadrza brojeve...
			if(ime.matches(".*\\d.*") || prz.matches(".*\\d.*")  )
			{
				JOptionPane.showMessageDialog(null, "Ime i prezime ne seju sadržati broj!");
				return false;
			}
			if(psd1.length()<8)
			{
				JOptionPane.showMessageDialog(null, "Šifra mora biti duga najmanje 8 karaktera!");
				return false;
			}
			
			
			return true;
		}
		
		/**Brise sve podatke iz svih boxova...*/
		public void fireClearSignal()
		{
			textField.setText("");
			textField_1.setText("");
			textField_2.setText("");
			textField_3.setText("");

			passwordField.setText("");
			passwordField_1.setText("");

		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

}
