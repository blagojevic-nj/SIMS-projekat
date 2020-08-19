package view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Login extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JPasswordField passwordField;
	private JTextField textField;

	public Login()
	{
		setBackground(Color.LIGHT_GRAY);
		setBounds(0, 0, 300, 700);
		setLayout(null);

		
		textField = new JTextField();
		textField.setBounds(50, 210, 200, 25);
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
						try
						{
							uloguj();
						}catch(Exception e1)
						{
							JOptionPane.showMessageDialog(null, "Netačan username/password");

						}												
				    }
				}
				});

		passwordField = new JPasswordField();
		passwordField.setLocation(50, 275);
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
						try
						{
							uloguj();
						}catch(Exception e1)
						{
							JOptionPane.showMessageDialog(null, "Netačan username/password");

						}												
				    }
				}
				});
		
		
		
		
		
		JSeparator separator = new JSeparator();
		separator.setBounds(50, 150, 200, 5);
		add(separator);
		
		
		JLabel lblLogin = new JLabel("Log In");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblLogin.setBounds(50, 80, 200, 60);
		add(lblLogin);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsername.setBounds(50, 185, 200, 25);
		add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(50, 250, 200, 25);
		add(lblPassword);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(50, 346, 200, 5);
		add(separator_1);
		
		JButton btnNewButton = new JButton(new ImageIcon("images/login.png"));
		btnNewButton.setBounds(220, 435, 30, 30);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setToolTipText("Prijavi se");
		add(btnNewButton);
	}
	
	private boolean uloguj()
	{
		return true;
	}
}
