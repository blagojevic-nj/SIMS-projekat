package view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import manageri.KorisnikManager;
import model.Korisnik;
import model.Nalog;

import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Login extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JPasswordField passwordField;
	private JTextField textField;
	
	private KorisnikManager km = KorisnikManager.getInstance();

	public Login()
	{
		setBackground(Color.LIGHT_GRAY);
		setBounds(0, 0, 300, 700);
		setLayout(null);

		
		textField = new JTextField();
		textField.setBounds(50, 160, 200, 25);
		add(textField);
		textField.setColumns(10);
		textField.addKeyListener(new KeyListener() {
			@Override
				public void keyTyped(KeyEvent e) {
				}
				
				@Override
				public void keyReleased(KeyEvent e) {
				}
				
				@Override
				public void keyPressed(KeyEvent e) {
				 int key = e.getKeyCode();
				 if (key == KeyEvent.VK_ENTER) {
						String username = textField.getText();
						String password =String.valueOf(passwordField.getPassword());
						if(! uloguj(username,password))
						{
							JOptionPane.showMessageDialog(null, "Netačan username/password");
						}											
				    }
				}
				});

		passwordField = new JPasswordField();
		passwordField.setLocation(50, 225);
		passwordField.setSize(200, 25);
		add(passwordField);
		passwordField.addKeyListener(new KeyListener() {
			@Override
				public void keyTyped(KeyEvent e) {
				}
				
				@Override
				public void keyReleased(KeyEvent e) {
				}
				
				@Override
				public void keyPressed(KeyEvent e) {
				 int key = e.getKeyCode();
				 if (key == KeyEvent.VK_ENTER) {
						String username = textField.getText();
						String password =String.valueOf(passwordField.getPassword());
						if(! uloguj(username,password))
						{
							JOptionPane.showMessageDialog(null, "Netačan username/password");
						}												
				    }
				}
				});
		
		
		//JButton btnNewButton = new JButton(new ImageIcon("data/ikonice/login.png"));
		JButton btnNewButton = new JButton();
		btnNewButton.setText("Prijavi se");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(150, 385, 100, 40);
		//btnNewButton.setBorderPainted(false);
		//btnNewButton.setFocusPainted(false);
		//btnNewButton.setContentAreaFilled(false);
		btnNewButton.setToolTipText("Prijavi se");
		add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String username= textField.getText();
				String password = String.valueOf(passwordField.getPassword());
				if(! uloguj(username,password))
				{
					JOptionPane.showMessageDialog(null, "Netačan username/password");
				}
			}
		});
		
		
		
		JSeparator separator = new JSeparator();
		separator.setBounds(50, 100, 200, 5);
		add(separator);
		
		
		JLabel lblLogin = new JLabel("Log In");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblLogin.setBounds(50, 30, 200, 60);
		add(lblLogin);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsername.setBounds(50, 135, 200, 25);
		add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(50, 200, 200, 25);
		add(lblPassword);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(50, 295, 200, 5);
		add(separator_1);

	}
	
	private boolean uloguj(String usr,String psd)
	{
		if(km.postoji(usr, psd))
		{
			Nalog real = km.getNalog(usr);
			if(real.getLozinka().equals(psd))
			{
				postaviTrenutnogKorisnika(real);
				return true;
			}	
		}

		return false;
	}


/**Postavlja trenutnog korisnika u mainWindow arg je korisnicko ime
 * komentar*/
	private void postaviTrenutnogKorisnika(Nalog n)
	{
		MainWindow.trenutniNalog = n;
		System.out.println("Successful login");
	}






}
