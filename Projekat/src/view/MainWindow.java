package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.Recept;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	
	//flag da li je prosiren skriveni panel
	static boolean expands = false;
	private ReceptiPanel rP;
	private int xx, xy;
	private CardLayout menuCardLayout,contentCardLayout;
	//private ReceptManager rM = ReceptManager.getInstance();
	
	public MainWindow() {
		
		ImageIcon logo = new ImageIcon("data/ikonice/fork.png");
		setIconImage(logo.getImage());
		setUndecorated(true);
		setSize(1200, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
/*
 * 
 * Content pane koji je layered da bi se preklapali paneli kako treba
 * 
 */
		JLayeredPane contentPane = new JLayeredPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
/*
 * 
 *Desni panel koji ce sadrzati sve "Bele (Velike desno) panele", AbstractMainContentContainer clasa...
 * 
 */

		
		JPanel mainContentContainerPanel = new JPanel();
		contentCardLayout = new CardLayout();
		
		mainContentContainerPanel.setBounds(100, 30, 1040, 650);
		mainContentContainerPanel.setLayout(contentCardLayout);
		contentPane.add(mainContentContainerPanel,1);
		
/*
 * 
 *Levi panel koji ce sadrzati sve male sive panele", AbstractMenuPanel clasa...
 * 
 */
		
		JPanel panelMenu = new JPanel();
		menuCardLayout = new CardLayout();
		
		panelMenu.setBackground(Color.LIGHT_GRAY);
		panelMenu.setBounds(0, 0, 300, getHeight());
		panelMenu.setLayout(menuCardLayout);
		panelMenu.setVisible(false);
		contentPane.add(panelMenu, 0);
/*
 * 
 * Traka sa dugmicima...
 * 
 */
		
		JPanel smallPanelMenu = new JPanel();
		smallPanelMenu.setBackground(Color.DARK_GRAY);
		smallPanelMenu.setBounds(0, 0, 50, getHeight());
		contentPane.add(smallPanelMenu,0);
		smallPanelMenu.setLayout(null);
		
/*
 * 
 * Home dugme// Bice collapse verovatno....
 * 
 */
		JButton home = new JButton(new ImageIcon("data/ikonice/home.png"));
		home.setBorderPainted(false);
		home.setFocusPainted(false);
		home.setContentAreaFilled(false);
		home.setBounds(5, 10, 40, 40);
		home.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				smallPanelMenu.setBounds(0, 0, 50, getHeight());
				panelMenu.setVisible(false);
				expands = false;
				rP.blokada(true);
			}
		});
		smallPanelMenu.add(home);
		
		
/*
 * 
 * Logout dugme
 * 
 */		
		JButton logout = new JButton(new ImageIcon("data/ikonice/exit.png"));
		logout.setBorderPainted(false);
		logout.setFocusPainted(false);
		logout.setContentAreaFilled(false);
		logout.setBounds(10, 140, 30, 30);
		
		logout.setVisible(false);
		smallPanelMenu.add(logout);
		

		
/*
 * 
 * Login dugme
 * 
 */
		
		JButton login = new JButton(new ImageIcon("data/ikonice/login.png"));
		login.setBounds(10, 80, 30, 30);
		login.setBorderPainted(false);
		login.setFocusPainted(false);
		login.setContentAreaFilled(false);
		login.setToolTipText("Prijavi se");
		
		smallPanelMenu.add(login);
		
		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				if(!expands) {
					smallPanelMenu.setBounds(300, 0, 50, getHeight());
					panelMenu.setVisible(true);
					expands = true;
					rP.blokada(false);	
				}
			}
			
		});
		
/*
 * 
 * Registracija dugme
 * 
 */
		JButton register = new JButton(new ImageIcon("data/ikonice/register.png"));
		register.setBounds(10, 140, 30, 30);
		register.setBorderPainted(false);
		register.setFocusPainted(false);
		register.setContentAreaFilled(false);
		register.setToolTipText("Registruj se");
		register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!expands) {
					smallPanelMenu.setBounds(300, 0, 50, getHeight());
					panelMenu.setVisible(true);
					expands = true;
					rP.blokada(false);	
				}
				
			}
		});
		smallPanelMenu.add(register);
		
		JButton exit = new JButton(new ImageIcon("data/ikonice/close.png"));
		exit.setBounds(10, getHeight()-50, 30, 30);
		exit.setBorderPainted(false);
		exit.setFocusPainted(false);
		exit.setContentAreaFilled(false);
		
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
			
		});
		smallPanelMenu.add(exit);
		

/*
 * 
 * Labela koja sluzi za pomeranje prozora
 * 
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
		label.setBounds(0, 0,1200,25);
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setOpaque(true);
		contentPane.add(label);
		
		//ovde bi trebao da imam listu recepata ali mi nemamo nista 
		rP = new ReceptiPanel(new ArrayList<Recept>());
		
		
		mainContentContainerPanel.add(rP, "Recepti");
	}
	
}
