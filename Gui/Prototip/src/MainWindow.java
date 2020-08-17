
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


import java.awt.event.ActionListener;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.SystemColor;
import javax.swing.JComboBox;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private CardLayout cardLayout;
	private int xx, xy;
	private JTextField textField_2;
	
	
	
	public MainWindow() {
				
		ImageIcon logo = new ImageIcon("fork.png");
		setIconImage(logo.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 800, 640);
		setResizable(false);
		setBackground(Color.WHITE);
		setUndecorated(true);
		setVisible(true);
		setSize(1200, 700);
		
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Ono tamno sa leve strane, prikaz pretrage
		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(Color.DARK_GRAY);
		panelMenu.setBounds(0, 0, 350, getHeight());
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);
		
		//collapse za panelManu, sa desne strane istog
		JLabel HideMenu = new JLabel("");
		HideMenu.setForeground(Color.BLACK);
		HideMenu.setHorizontalAlignment(SwingConstants.CENTER);
		HideMenu.setBounds(335, 0, 15, 700);
		HideMenu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		//HideMenu.setOpaque(true);
		//HideMenu.setBackground(Color.LIGHT_GRAY);
		panelMenu.add(HideMenu);
		
		
	//Search dugme
		JButton searchBtn = new JButton(new ImageIcon("search.png"));
		searchBtn.setBorderPainted(false);
		searchBtn.setFocusPainted(false);
		searchBtn.setContentAreaFilled(false);
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("search");
			}
		});
		searchBtn.setBounds(10, 25, 55, 45);
		panelMenu.add(searchBtn);
		
	//LoginDugme
		JButton btnLogin = new JButton(new ImageIcon("signIn.png"));
		btnLogin.setBorderPainted(false);
		btnLogin.setFocusPainted(false);
		btnLogin.setContentAreaFilled(false);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnLogin");
			}
		});
		btnLogin.setBounds(105, 27, 55, 45);
		panelMenu.add(btnLogin);
	
		
	//Register dugme
		JButton btnRegister = new JButton(new ImageIcon("signUp.png"));
		btnRegister.setBorderPainted(false);
		btnRegister.setFocusPainted(false);
		btnRegister.setContentAreaFilled(false);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnRegister");
			}
		});
		btnRegister.setBounds(200, 27, 55, 45);
		panelMenu.add(btnRegister);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(SystemColor.control);
		separator.setBounds(0, 80, 350, 2);
		panelMenu.add(separator);
		
		//Panel prikazuje panele za pretragu, login ili registraciju, zato container...
		//koristi card Layout...
		cardLayout = new CardLayout();
		JPanel panel_container_Menu = new JPanel();
		panel_container_Menu.setBounds(10, 90, 330, 605);
		panel_container_Menu.setBackground(Color.DARK_GRAY);
		panelMenu.add(panel_container_Menu);
		panel_container_Menu.setLayout(cardLayout);
		
		//Pretraga... Model samo ne finalno...
		JPanel panel_pretraga = new JPanel();
		panel_pretraga.setLayout(null);
		panel_pretraga.setBounds(0,  0, panel_container_Menu.getWidth(), panel_container_Menu.getHeight());
		panel_pretraga.setBackground(Color.DARK_GRAY);
		panel_container_Menu.add(panel_pretraga,"pretraga");
		
		
		textField_2 = new JTextField();
		textField_2.setBounds(15, 59, 250, 30);
		panel_pretraga.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNaziv = new JLabel("Naziv");
		lblNaziv.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNaziv.setForeground(Color.WHITE);
		lblNaziv.setBounds(15, 33, 46, 13);
		panel_pretraga.add(lblNaziv);
		
		JLabel lblKategorija = new JLabel("Kategorija");
		lblKategorija.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblKategorija.setForeground(Color.WHITE);
		lblKategorija.setBounds(15, 115, 100, 30);
		panel_pretraga.add(lblKategorija);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(15, 155, 250, 30);
		panel_pretraga.add(comboBox);
		
		JButton btnDetaljnaPretraga = new JButton("Detaljna pretraga");
		btnDetaljnaPretraga.setBounds(15, 238, 113, 21);
		panel_pretraga.add(btnDetaljnaPretraga);
		
		JButton btnPretrazi = new JButton("Pretrazi");
		btnPretrazi.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnPretrazi.setBounds(169, 485, 151, 39);
		panel_pretraga.add(btnPretrazi);
		panel_container_Menu.setLayout(null);
		
		
		/*
		 * Labela koja sluzi za pomeranje prozora
		 */
		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				 xx = e.getX();
			     xy = e.getY();
			}
		});
		label.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				
				int x = arg0.getXOnScreen();
	            int y = arg0.getYOnScreen();
	            MainWindow.this.setLocation(x - xx, y - xy);  
			}
		});
		label.setBounds(0, 0,1140,25);
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setBackground(Color.DARK_GRAY);
		label.setOpaque(true);
		contentPane.add(label);
		//*******************************************
		//Dugme za zatvaranje
		JLabel lbl_close = new JLabel("x");
		lbl_close.setVerticalAlignment(SwingConstants.BOTTOM);
		lbl_close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				System.exit(0);
			}
		});
		lbl_close.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_close.setForeground(SystemColor.control);
		lbl_close.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lbl_close.setBounds(1130, 0, 70, 25);
		lbl_close.setBackground(Color.DARK_GRAY);
		lbl_close.setOpaque(true);
		contentPane.add(lbl_close);
		
			
		cardLayout.show(panel_container_Menu, "Pretraga");
	}
}

