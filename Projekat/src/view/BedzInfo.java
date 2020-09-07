package view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class BedzInfo extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image img;
	
	
	
	public BedzInfo()
	{
		super("Bedž Info");
		img = new ImageIcon("data/ikonice/back2.jpg").getImage();
		setContentPane(new ImagePanel(img));
		getContentPane().setLayout(null);
		setBounds(500, 200, 500, 520);
		
		
		
		
		JLabel lblBedevi = new JLabel("Bed\u017Eevi:");
		lblBedevi.setForeground(Color.WHITE);
		lblBedevi.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblBedevi.setBounds(10, 10, 465, 80);
		getContentPane().add(lblBedevi);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setBounds(0, 90, 500, 5);
		getContentPane().add(separator);
		
		JLabel label = new JLabel("1");
		label.setBounds(50, 100, 100, 100);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("2");
		label_1.setBounds(200, 100, 100, 100);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("3");
		label_2.setBounds(350, 100, 100, 100);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("4");
		label_3.setBounds(100, 300, 100, 100);
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("5");
		label_4.setBounds(260, 255, 140, 150);
		getContentPane().add(label_4);


		
		
		
		ImageIcon bedz1 = new ImageIcon("data/ikonice/bedzevi/nemaBedz.png");
		Image b1 = bedz1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		
		ImageIcon bedz2 = new ImageIcon("data/ikonice/bedzevi/oneStar.png");
		Image b2 = bedz2.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		
		ImageIcon bedz3 = new ImageIcon("data/ikonice/bedzevi/twoStar.png");
		Image b3 = bedz3.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		
		ImageIcon bedz4 = new ImageIcon("data/ikonice/bedzevi/threeStar.png");
		Image b4 = bedz4.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		
		ImageIcon bedz5 = new ImageIcon("data/ikonice/bedzevi/master.png");
		Image b5 = bedz5.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH);
		
		label.setIcon(new ImageIcon(b1));
		label_1.setIcon(new ImageIcon(b2));
		label_2.setIcon(new ImageIcon(b3));
		label_3.setIcon(new ImageIcon(b4));
		label_4.setIcon(new ImageIcon(b5));
		
		JLabel lblBezBeda = new JLabel("Bez Bed\u017Ea");
		lblBezBeda.setHorizontalAlignment(SwingConstants.CENTER);
		lblBezBeda.setForeground(Color.WHITE);
		lblBezBeda.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblBezBeda.setBounds(60, 210, 80, 40);
		getContentPane().add(lblBezBeda);
		
		JLabel lblPoetnik = new JLabel("Junior");
		lblPoetnik.setHorizontalAlignment(SwingConstants.CENTER);
		lblPoetnik.setForeground(Color.WHITE);
		lblPoetnik.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPoetnik.setBounds(210, 210, 80, 40);
		getContentPane().add(lblPoetnik);
		
		JLabel lblNapredan = new JLabel("Senior");
		lblNapredan.setHorizontalAlignment(SwingConstants.CENTER);
		lblNapredan.setForeground(Color.WHITE);
		lblNapredan.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNapredan.setBounds(360, 210, 80, 40);
		getContentPane().add(lblNapredan);
		
		JLabel lblMedior = new JLabel("Medior");
		lblMedior.setHorizontalAlignment(SwingConstants.CENTER);
		lblMedior.setForeground(Color.WHITE);
		lblMedior.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMedior.setBounds(110, 407, 80, 40);
		getContentPane().add(lblMedior);
		
		JLabel lblMaster = new JLabel("Master");
		lblMaster.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaster.setForeground(Color.WHITE);
		lblMaster.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaster.setBounds(300, 407, 80, 40);
		getContentPane().add(lblMaster);


	}
	
}



class ImagePanel extends JComponent {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image image;
    public ImagePanel(Image image) {
        this.image = image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}