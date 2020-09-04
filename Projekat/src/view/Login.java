package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import manageri.KorisnikManager;
import model.Nalog;

public class Login extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JPasswordField passwordField;
	private JTextField textField;
	private MainWindow m;
	private KorisnikManager km = KorisnikManager.getInstance();
	private Image img;

	public Login(MainWindow mw)
	{
		this.img = new ImageIcon("data/ikonice/whiteBackSmall.jpg").getImage();
		m = mw;
		setBounds(0, 0, 300, 700);
		setLayout(null);

		textField = new JTextField();
		textField.setBounds(50, 240, 200, 25);
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
		passwordField.setLocation(50, 320);
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
		
		
//		JButton btnNewButton = new JButton();
//		btnNewButton.setText("Prijavi se");
//		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
//		btnNewButton.setBounds(150, 385, 100, 40);
//		//btnNewButton.setBorderPainted(false);
//		//btnNewButton.setFocusPainted(false);
//		//btnNewButton.setContentAreaFilled(false);
//		btnNewButton.setToolTipText("Prijavi se");
//		add(btnNewButton);
//		btnNewButton.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) 
//			{
//				String username= textField.getText();
//				String password = String.valueOf(passwordField.getPassword());
//				if(! uloguj(username,password))
//				{
//					JOptionPane.showMessageDialog(null, "Netačan username/password");
//				}
//			}
//		});
		
		JLabel netacan = new JLabel("Netacan username/pasword");
		netacan.setForeground(Color.red);
		netacan.setBounds(70, 350, 200, 20);
		
		JLabel potvrdi = new JLabel(new ImageIcon("data/ikonice/potvrdi.png"));
		potvrdi.setBounds(150, 385, 120, 50);
		add(potvrdi);
		
		potvrdi.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				String username= textField.getText();
				String password = String.valueOf(passwordField.getPassword());
				if(! uloguj(username,password))
				{
					add(netacan);
					Login.this.repaint();
				}else
					remove(netacan);
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				potvrdi.setBackground(new Color(0,0,0,100));
				potvrdi.setOpaque(true);
				Login.this.repaint();
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				potvrdi.setOpaque(false);
				Login.this.repaint();
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
		});
		
		
//		JSeparator separator = new JSeparator();
//		separator.setBounds(50, 100, 200, 5);
//		add(separator);
		
		
		JLabel lblLogin = new JLabel(new ImageIcon("data/ikonice/loginLogo.png"));
		lblLogin.setBounds(75, 30, 150, 150);
		add(lblLogin);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", 1, 20));
		lblUsername.setBounds(50, 200, 150, 30);
		add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", 1, 20));
		lblPassword.setBounds(50, 280, 150, 30);
		add(lblPassword);
		
//		JSeparator separator_1 = new JSeparator();
//		separator_1.setBounds(50, 295, 200, 5);
//		add(separator_1);

		
		/*****************************************************************************/
		setDefaultLogin();
		
		
	}
	
	
	/**Postavlja trenutnog korisnika u mainWindow*/
	private boolean uloguj(String usr,String psd)
	{
		if(km.postoji(usr, psd))
		{
			Nalog real = km.getNalog(usr);
			if(real.getLozinka().equals(psd))
			{
				MainWindow.trenutniNalog=real;
				m.fireNalogChanged();
				JOptionPane.showMessageDialog(null, "Uspešna prijava!");
				m.collapseSmallMenu();
				return true;
			}	
		}

		return false;
	}

	
	/**Zbog testitanja*/
	private void setDefaultLogin()
	{
		textField.setText("lule");
		passwordField.setText("123456789");
		textField.requestFocus();
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
}
