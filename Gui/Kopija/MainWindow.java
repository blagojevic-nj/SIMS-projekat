import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private int xx, xy;
	
	public MainWindow() {
		
		ImageIcon logo = new ImageIcon("images/fork.png");
		
		setIconImage(logo.getImage());
		setUndecorated(true);
		setSize(1200, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setBackground(Color.white);
		setContentPane(contentPane);
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(Color.LIGHT_GRAY);
		panelMenu.setBounds(0, 0, 300, getHeight());
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);
		panelMenu.setVisible(false);
		
		JPanel smallPanelMenu = new JPanel();
		smallPanelMenu.setBackground(Color.DARK_GRAY);
		smallPanelMenu.setBounds(0, 0, 50, getHeight());
		contentPane.add(smallPanelMenu);
		smallPanelMenu.setLayout(null);
		
		JLabel reduce = new JLabel(new ImageIcon("images/reduce.png"));
		reduce.setBounds(0, getHeight()/2-25, 50, 50);
		
		reduce.setVisible(false);
		
		JLabel expand = new JLabel(new ImageIcon("images/expand.png"));
		expand.setBounds(0, getHeight()/2-25, 50, 50);
		
		reduce.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				panelMenu.setVisible(false);
				reduce.setVisible(false);
				expand.setVisible(true);
				smallPanelMenu.setBounds(0, 0, 50, getHeight());
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
		});
		smallPanelMenu.add(reduce);
		
		
		expand.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				smallPanelMenu.setBounds(300, 0, 50, getHeight());
				panelMenu.setVisible(true);
				expand.setVisible(false);
				reduce.setVisible(true);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
		});
		
		smallPanelMenu.add(expand);
		
		
		JButton home = new JButton(new ImageIcon("images/home.png"));
		home.setBorderPainted(false);
		home.setFocusPainted(false);
		home.setContentAreaFilled(false);
		home.setBounds(5, 10, 40, 40);
	
		smallPanelMenu.add(home);
		
		
		
		JButton logout = new JButton(new ImageIcon("images/exit.png"));
		logout.setBorderPainted(false);
		logout.setFocusPainted(false);
		logout.setContentAreaFilled(false);
		logout.setBounds(10, 140, 30, 30);
		
		logout.setVisible(false);
		
		smallPanelMenu.add(logout);
		
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
		label.setBounds(0, 0,1200,25);
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setBackground(Color.white);
		label.setOpaque(true);
		contentPane.add(label);

		
		JButton login = new JButton(new ImageIcon("images/login.png"));
		login.setBounds(10, 80, 30, 30);
		login.setBorderPainted(false);
		login.setFocusPainted(false);
		login.setContentAreaFilled(false);
		login.setToolTipText("Prijavi se");
		
		smallPanelMenu.add(login);
		
		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
			
		});
		
		
		JButton register = new JButton(new ImageIcon("images/register.png"));
		register.setBounds(10, 140, 30, 30);
		register.setBorderPainted(false);
		register.setFocusPainted(false);
		register.setContentAreaFilled(false);
		register.setToolTipText("Registruj se");
		
		smallPanelMenu.add(register);
		
		JButton exit = new JButton(new ImageIcon("images/close.png"));
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
		

		
	}
	
}
