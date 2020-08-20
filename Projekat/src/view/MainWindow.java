package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import manageri.KorisnikManager;
import manageri.ReceptManager;
import model.Nalog;
import model.TipNaloga;


public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	
	//flag da li je prosiren skriveni panel
	static boolean expands = false;
	private ReceptiPanel rP;
	private JButton home,login,logout,register,exit,pretraga,settings;
	private JPanel panelMenu,smallPanelMenu,mainContentContainerPanel;
	private JLayeredPane contentPane;
	private JLabel label;
	private int xx, xy;
	private CardLayout menuCardLayout,contentCardLayout;
	static ReceptManager rM = ReceptManager.getInstance();
	static KorisnikManager km = KorisnikManager.getInstance();
	Nalog trenutniNalog;
	
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
		contentPane = new JLayeredPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
/*
 * 
 *Desni panel koji ce sadrzati sve "Bele (Velike desno) panele", AbstractMainContentContainer clasa...
 * 
 */
		mainContentContainerPanel = new JPanel();
		contentCardLayout = new CardLayout();
		mainContentContainerPanel.setBounds(100, 30, 1040, 650);
		mainContentContainerPanel.setLayout(contentCardLayout);
		contentPane.add(mainContentContainerPanel,1);
		
	

		//Za testiranje
		AddReceptPanel recept = new AddReceptPanel(this, mainContentContainerPanel, contentCardLayout);
		recept.setSize(getWidth(), getHeight());
		mainContentContainerPanel.add(recept, "addRecept");
		KategorijePanel kPanel = new KategorijePanel(mainContentContainerPanel, contentCardLayout);
		mainContentContainerPanel.add(kPanel, "kategorije");
		SastojciPanel sPanel = new SastojciPanel(mainContentContainerPanel, contentCardLayout);
		mainContentContainerPanel.add(sPanel, "sastojci");
		UredjajiPanel uPanel = new UredjajiPanel(mainContentContainerPanel, contentCardLayout);
		mainContentContainerPanel.add(uPanel, "uredjaji");
		contentCardLayout.show(mainContentContainerPanel, "addRecept");
		
/*
 * 
 *Levi panel koji ce sadrzati sve male sive panele"
 * 
 */
		
		panelMenu = new JPanel();
		menuCardLayout = new CardLayout();
		
		panelMenu.setBackground(Color.LIGHT_GRAY);
		panelMenu.setBounds(0, 0, 300, getHeight());
		panelMenu.setLayout(menuCardLayout);
		panelMenu.setVisible(false);
		contentPane.add(panelMenu, 0);
		

/*************************************ZARAD CITLJIVIJEG KODA DUGMICI ZA MENI IDU DOLE************************************************************/
		initializeMenuButtons();
		initializeMoveLabel();
/************************************************************************************************************************************************/

 
/*
 * 
 * Login Panel
 * 
 */
		Login panelLogIn = new Login(this);
		panelMenu.add(panelLogIn,"panelLogIn");	
/*
 * 
 * Register Panel
 * 
 */
		Register panelRegister = new Register();
		panelMenu.add(panelRegister,"panelRegister");
/*
 * 
 * Pretraga Panel
 * 
 */
		PretragaPanel panelPretraga = new PretragaPanel();
		panelMenu.add(panelPretraga, "panelPretraga");
/*
 * 
 * Recepti Panel
 * 
 */
		//ovde bi trebao da imam listu recepata ali mi nemamo nista 
		rP = new ReceptiPanel(rM.getNajpopularnijih10());
		mainContentContainerPanel.add(rP, "Recepti");
		contentCardLayout.show(mainContentContainerPanel, "Recepti");
/*
 * 
 * Podesavanja Panel
 * 
 */
		UserSettingsPanel userPanel = new UserSettingsPanel(km, trenutniNalog);
		mainContentContainerPanel.add(userPanel,"userPanel");
	
	}	
	
	
	public  void fireNalogChanged()
	{
		if(trenutniNalog != null)
		{
			switch (trenutniNalog.getTip()) {
			case ADMIN:
				break;
			case MODERATOR:
				break;
			case REG_KORISNIK:
				login.setVisible(false);
				logout.setVisible(true);
				register.setVisible(false);
				settings.setVisible(true);
				break;
			//Logout
			default:
				break;
			}
		}
		else
		{
			login.setVisible(true);
			logout.setVisible(false);
			register.setVisible(true);
			settings.setVisible(false);
		}

	}
	
	/**Koristite ovu metodu za smanjivanje menija, da bude citljiviji kod*/
	public void collapseSmallMenu()
	{
		smallPanelMenu.setBounds(0, 0, 50, getHeight());
		panelMenu.setVisible(false);
		expands = false;
		rP.blokada(true);
	}
	
	/**Koristite ovu metodu za expand menija, da bude citljiviji kod*/
	public void expandSmallMenu()
	{
		smallPanelMenu.setBounds(300, 0, 50, getHeight());
		panelMenu.setVisible(true);
		expands = true;
		rP.blokada(false);	
	}
	
	/**Ovde se nalaye svi dugmici menija*/
	private void initializeMenuButtons()
	{
		/*
		 * 
		 * Traka sa dugmicima...
		 * 
		 */
						
						smallPanelMenu = new JPanel();
						smallPanelMenu.setBackground(Color.DARK_GRAY);
						smallPanelMenu.setBounds(0, 0, 50, getHeight());
						contentPane.add(smallPanelMenu,0);
						smallPanelMenu.setLayout(null);

		/*
		 * 
		 * Home dugme// Bice collapse verovatno....
		 * 
		 */
				home = new JButton(new ImageIcon("data/ikonice/home.png"));
				home.setBorderPainted(false);
				home.setFocusPainted(false);
				home.setContentAreaFilled(false);
				home.setBounds(5, 10, 40, 40);
				home.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e)
					{
						contentCardLayout.show(mainContentContainerPanel, "Recepti");
						collapseSmallMenu();
					}
				});
				smallPanelMenu.add(home);
				
				
		/*
		 * 
		 * Logout dugme
		 * 
		 */		
				logout = new JButton(new ImageIcon("data/ikonice/exit.png"));
				logout.setBorderPainted(false);
				logout.setFocusPainted(false);
				logout.setContentAreaFilled(false);
				logout.setBounds(10, 80, 30, 30);
				logout.setVisible(false);
				smallPanelMenu.add(logout);
				logout.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						trenutniNalog=null;	
						fireNalogChanged();
						JOptionPane.showMessageDialog(null, "Uspešna odjava!");
						collapseSmallMenu();

					}
				});
				

				
		/*
		 * 
		 * Login dugme
		 * 
		 */
				
				login = new JButton(new ImageIcon("data/ikonice/login.png"));
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
							expandSmallMenu();
						}
						menuCardLayout.show(panelMenu, "panelLogIn");
						
					}
					
				});
				
		/*
		 * 
		 * Registracija dugme
		 * 
		 */
				register = new JButton(new ImageIcon("data/ikonice/register.png"));
				register.setBounds(10, 140, 30, 30);
				register.setBorderPainted(false);
				register.setFocusPainted(false);
				register.setContentAreaFilled(false);
				register.setToolTipText("Registruj se");
				register.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(!expands) {
							expandSmallMenu();
						}
						menuCardLayout.show(panelMenu, "panelRegister");
						
					}
				});
				smallPanelMenu.add(register);
		/*
		 * 
		 * Podesavanja dugme
		 * 
		 */
						settings = new JButton(new ImageIcon("data/ikonice/gear.png"));
						settings.setBounds(10, 140, 30, 30);
						settings.setBorderPainted(false);
						settings.setFocusPainted(false);
						settings.setContentAreaFilled(false);
						settings.setToolTipText("Registruj se");
						settings.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								if(!expands) {
									System.out.println("Podesavanja");
									collapseSmallMenu();
									
								}
								contentCardLayout.show(mainContentContainerPanel, "userPanel");
								
							}
						});
						smallPanelMenu.add(settings);
						settings.setVisible(false);
				
				
		/*
		 * Pretraga dugme
		 */
				
				pretraga = new JButton(new ImageIcon("data/ikonice/search.png"));
				pretraga.setBounds(10, 200, 30, 30);
				pretraga.setBorderPainted(false);
				pretraga.setFocusPainted(false);
				pretraga.setContentAreaFilled(false);
				pretraga.setToolTipText("Pretrazi");
				
				smallPanelMenu.add(pretraga);
				
				pretraga.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						if(!expands) {
							expandSmallMenu();
						}
						menuCardLayout.show(panelMenu, "panelPretraga");
						
					}
				});
				smallPanelMenu.add(pretraga);

				
		/*
		 * 
		 * Exit dugme
		 * 
		 */
				
				exit = new JButton(new ImageIcon("data/ikonice/close.png"));
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
	
	
	/**Labela pomocu koje se pomera ekran*/
	private void initializeMoveLabel()
	{
		/*
		 * 
		 * Labela koja sluzi za pomeranje prozora
		 * 
		 */
				label = new JLabel("");
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

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
