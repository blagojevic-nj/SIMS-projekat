package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import manageri.ReceptManager;
import model.Recept;
import model.RegistrovaniKorisnik;
import model.Tezina;
import net.miginfocom.swing.MigLayout;

public class DodavanjeRecepta extends JPanel{

	private static final long serialVersionUID = 1L;
	private KategorijaPanel kP;
	private SastojciPanel sP;
	private UredjajiPanel uP;
	private JScrollPane opisPane, koraciPane;
	private JTextArea opisArea, koraciArea;
	private JLabel slika;
	private Image img;
	private TezinaPanel tP;
	String path;
	
	public DodavanjeRecepta(MainWindow mW) {
		this.img = new ImageIcon("data/ikonice/back2.jpg").getImage();
	    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    
		setLayout(null);
		setBounds(0,0,1040,650);
		setBackground(Color.gray);
		
		JLabel naslov = new JLabel("Unosenje novog recepta");
		naslov.setFont(new Font("Tahoma", Font.BOLD, 20));
		naslov.setBounds(400, 10, 300, 30);
		naslov.setForeground(Color.white);
		add(naslov);
		
		JSeparator sep = new JSeparator();
		sep.setBounds(520, 50, 7, 570);
		sep.setOrientation(1);
		add(sep);
		sep.setForeground(Color.black);
		
		JPanel panelSlike = new JPanel();
		panelSlike.setBackground(Color.DARK_GRAY);
		panelSlike.setBorder(BorderFactory.createRaisedBevelBorder());
		panelSlike.setBounds(160, 50, 200, 200);
		add(panelSlike, 1);
		
		JButton addImg = new JButton(new ImageIcon("data/ikonice/add-image.png"));
		addImg.setFocusPainted(false);
		addImg.setContentAreaFilled(false);
		addImg.setBorderPainted(false);
		
		addImg.setBounds(112, 48, 50, 50);
		
		path = null;
		
		JFileChooser fc  = new JFileChooser();
		
		addImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				fc.setFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "svg"));
				int returnVal = fc.showOpenDialog(fc);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					path = fc.getSelectedFile().getAbsolutePath();
					ImageIcon img = new ImageIcon(path);
					Image imgScale = img.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
					slika = new JLabel(new ImageIcon(imgScale));
					slika.setBounds(160, 50, 200, 200);
					slika.setBorder(BorderFactory.createRaisedBevelBorder());
					panelSlike.setVisible(false);
					add(slika, 0);
					revalidate();
					repaint();
				}
			}
		});
		add(addImg);
		
		JLabel naziv = new JLabel("Naziv: ");
		naziv.setFont(new Font("Tahoma", 0, 18));
		naziv.setBounds(20, 270, 80, 30);
		naziv.setForeground(Color.white);
		JTextField naz = new JTextField();
		naz.setBounds(20, 300, 480, 30);
		add(naziv);
		add(naz);
		
		JSeparator sep1 = new JSeparator(0);
		sep1.setBounds(40, 340, 440, 7);
		add(sep1);
		
		
		JLabel opis = new JLabel("Opis: ");
		opis.setForeground(Color.white);
		opis.setFont(new Font("Tahoma", 0, 18));
		opis.setBounds(20, 340, 80, 30);
		
		opisArea = new JTextArea();
		opisPane = new JScrollPane(opisArea);
		opisPane.setBounds(20, 370, 480, 150);
		
		add(opis); add(opisPane);
		
		JSeparator sep2 = new JSeparator(0);
		sep2.setBounds(40, 530, 440, 7);
		add(sep2);
	
		
		JLabel time = new JLabel("Unesite vreme [min]: ");
		time.setForeground(Color.white);
		time.setFont(new Font("Tahoma", 0, 18));
		time.setBounds(20, 540, 200, 30);
		JTextField vreme = new JTextField();
		vreme.setBounds(200, 540, 50, 30);
		
		add(time); add(vreme);
		
		JButton kat = new JButton("Dodaj kategoriju");
		kat.setBounds(540, 50, 200, 30);
		
		add(kat);
		
		kP = new KategorijaPanel();
		kP.setVisible(false);
		add(kP);
		kP.parent = this;
		kat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				kP.setVisible(true);
				blokada(kP, false);
				revalidate();
			}
			
		});
		
		JSeparator sep3 = new JSeparator(0);
		sep3.setBounds(40, 590, 440, 7);
		add(sep3);
		
		JLabel tezina = new JLabel("Tezina: ");
		tezina.setForeground(Color.white);
		tezina.setFont(new Font("Tahoma", 0, 18));
		tezina.setBounds(20, 600, 70, 30);
		add(tezina);
		
		
		tP = new TezinaPanel();
		tP.setBounds(100, 590, 470, 40);
		add(tP);
		
		JButton sas = new JButton("Dodaj sastojke");
		sas.setBounds(540, 90, 200, 30);
		
		add(sas);
		
		sP = new SastojciPanel();
		sP.setVisible(false);
		add(sP);
		sP.parent = this;
		sas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sP.setVisible(true);
				blokada(sP, false);
				revalidate();
			}
			
		});
		
		JButton ure = new JButton("Dodaj uredjaje");
		ure.setBounds(540, 130, 200, 30);
		
		add(ure);
		
		uP = new UredjajiPanel();
		uP.setVisible(false);
		add(uP);
		uP.parent = this;
		ure.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				uP.setVisible(true);
				blokada(uP, false);
				revalidate();
			}
			
		});
		
		JSeparator sep4 = new JSeparator(0);
		sep4.setBounds(560, 170, 440, 7);
		add(sep4);
		
		JLabel koraci = new JLabel("Unesite postupak pravljenja: ");
		koraci.setForeground(Color.white);
		koraci.setFont(new Font("Tahoma", 0, 18));
		koraci.setBounds(540, 180, 250, 30);
		
		koraciArea = new JTextArea();
		koraciPane = new JScrollPane(koraciArea);
		koraciPane.setBounds(540, 220, 480, 300);
		add(koraci); add(koraciPane);
		
		JSeparator sep5 = new JSeparator(0);
		sep5.setBounds(560, 530, 440, 7);
		add(sep5);
		
		
		JLabel ytLink = new JLabel("YouTube link: ");
		ytLink.setForeground(Color.white);
		ytLink.setBounds(540, 540, 100, 30);
		add(ytLink);
		
		JLabel logo = new JLabel(new ImageIcon("data/ikonice/youtube.png"));
		logo.setBounds(608, 535, 54, 40);
		add(logo);
		JTextField yt = new JTextField();
		yt.setBounds(660, 540, 360, 30);
		add(yt);
		
		JSeparator sep6 = new JSeparator(0);
		sep6.setBounds(560, 590, 440, 7);
		add(sep6);
		
		JButton potvrda = new JButton("Potvrda", new ImageIcon("data/ikonice/ok.png"));
		potvrda.setBounds(870, 600, 150, 30);
		add(potvrda);
		
		potvrda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean validan = true;
				
				if(naz.getText().trim().equals("") 
						|| opisArea.getText().trim().equals("") 
							|| sP.getSastojci().size() == 0 
								|| koraciArea.getText().trim().equals(""))
					validan = false;
				
				try {
					Integer.parseInt(vreme.getText().trim());
				}catch(Exception e) {
					validan = false;
				}
				if(validan) {
					Recept r = MainWindow.rM.noviRecept(naz.getText().trim(), opisArea.getText().trim(), koraciArea.getText().trim(), tP.getTezinu(), 
							Integer.parseInt(vreme.getText().trim()), uP.getUredjaji(), sP.getSastojci(), kP.getKategorije(), 
							(RegistrovaniKorisnik)MainWindow.km.getKorisnik(MainWindow.trenutniNalog.getKorisnickoIme()), yt.getText().trim());
					if(path != null) {
						ReceptManager.dodajSliku(r.getId(), path);
					}
					
					MainWindow.rM.sacuvajRecepte();
					MainWindow.rM.sacuvajTabelu();
					JOptionPane.showMessageDialog(null, "Uspesno ste kreirali recept!");
					mW.rP = new ReceptiPanel(new ArrayList<Recept>());
					mW.trenutniDesni = mW.rP;
					mW.mainContentContainerPanel.remove(DodavanjeRecepta.this);
					mW.mainContentContainerPanel.add(mW.trenutniDesni);
					revalidate();
					mW.mainContentContainerPanel.repaint();
					
				}
				else {
					JOptionPane.showMessageDialog(DodavanjeRecepta.this, "Nisu validni svi podaci, molimo vas ispravite!");
				}
			}
			
		});
		
		JButton otkazi = new JButton("Otkazi", new ImageIcon("data/ikonice/cancel.png"));
		otkazi.setBounds(710, 600, 150, 30);
		add(otkazi);
		
		otkazi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int result = JOptionPane.showConfirmDialog(mW.mainContentContainerPanel, "Podaci se nece sacuvati, da li ste sigurni?");
				if(result == JOptionPane.YES_OPTION) {
					mW.rP = new ReceptiPanel(new ArrayList<Recept>());
					mW.mainContentContainerPanel.remove(DodavanjeRecepta.this);
					mW.trenutniDesni = mW.rP;
					mW.mainContentContainerPanel.add(mW.trenutniDesni);
					revalidate();
					mW.mainContentContainerPanel.repaint();
				}
			}
			
		});
		
		JLabel chef = new JLabel(new ImageIcon("data/ikonice/logo.png"));
		chef.setBounds(830, 15, 142, 150);
		add(chef);
	}
	
	public void blokada(Component c, boolean b) {
		for(Component kom: this.getComponents()) {
			if(!kom.equals(c))
				kom.setEnabled(b);
		}
		
		opisArea.setEnabled(b);
		koraciArea.setEnabled(b);
		opisPane.getVerticalScrollBar().setEnabled(b);
		opisPane.setWheelScrollingEnabled(b);
		koraciPane.getVerticalScrollBar().setEnabled(b);
		koraciPane.setWheelScrollingEnabled(b);
	}
	
	public void blokada(boolean b) {
		for(Component c: this.getComponents())
			c.setEnabled(b);
		
		opisArea.setEnabled(b);
		koraciArea.setEnabled(b);
		opisPane.getVerticalScrollBar().setEnabled(b);
		opisPane.setWheelScrollingEnabled(b);
		koraciPane.getVerticalScrollBar().setEnabled(b);
		koraciPane.setWheelScrollingEnabled(b);
	}
	
	 public void paintComponent(Graphics g) {
		    g.drawImage(img, 0, 0, null);
	 }
	 
		@Override
		public void setEnabled(boolean enabled) {
			super.setEnabled(enabled);
			blokada(enabled);
		}
}

class TezinaPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	public JRadioButton lako, srednje, tesko;
	public TezinaPanel() {
		setOpaque(false);
		lako = new JRadioButton();
		lako.setSelected(true);
		lako.setOpaque(false);
		srednje = new JRadioButton();
		srednje.setOpaque(false);
		tesko = new JRadioButton();
		tesko.setOpaque(false);
		setLayout(new MigLayout());
		
		ButtonGroup bG = new ButtonGroup();
		bG.add(lako);
		bG.add(srednje);
		bG.add(tesko);
		add(lako);
		add(new JLabel(new ImageIcon("data/ikonice/tezina/malo_lako.png")));
		add(new JLabel("    "));
		add(srednje);
		add(new JLabel(new ImageIcon("data/ikonice/tezina/malo_srednje.png")));
		add(new JLabel("    "));
		add(tesko);
		add(new JLabel(new ImageIcon("data/ikonice/tezina/malo_tesko.png")));	
	}
	
	Tezina getTezinu() {
		if(lako.isSelected())
			return Tezina.LAKO;
		else if(srednje.isSelected())
			return Tezina.SREDNJE;
		return Tezina.TESKO;
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		lako.setEnabled(enabled);
		srednje.setEnabled(enabled);
		tesko.setEnabled(enabled);
	}
	
}
