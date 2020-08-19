package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import model.Recept;

public class MaliPrikazRecepta extends JPanel{

	private static final long serialVersionUID = 1L;
	
	public MaliPrikazRecepta(Recept r){
		setSize(500, 300);
		this.setMaximumSize(new Dimension(500, 300));
		setBorder(BorderFactory.createRaisedBevelBorder());
		setLayout(new MigLayout("", "[][]10[]", "[]10[]"));		
		
		JPanel zoomPanel = new JPanel();
		zoomPanel.add(new JLabel(new ImageIcon("data/images/zoom.png")));
		JLabel see = new JLabel("Pogledaj recept");
		see.setForeground(Color.white);
		zoomPanel.add(see);
		zoomPanel.setBackground(new Color(0, 0, 0, 123));
		add(zoomPanel, "pos 70 100", 0);
		zoomPanel.setVisible(false);
		
		
		ImageIcon img = new ImageIcon("data/images/"+r.getId()+".jpg");
		Image resize = img.getImage().getScaledInstance(240, 240, Image.SCALE_SMOOTH);
		
		JLabel slika = new JLabel(new ImageIcon(resize));
		add(slika, "spany");
		
		slika.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if(!MainWindow.expands) {
					zoomPanel.setVisible(true);
					revalidate();
				}
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				zoomPanel.setVisible(false);
				revalidate();
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		String kategorije = "DOMACA KUHINJA; CORBE";
		JLabel kat = new JLabel(kategorije);
		kat.setFont(new Font("Lucida Sans", 0, 14));
		add(kat, "center, span");
		
		JLabel naslov = new JLabel(r.getNaziv());
		naslov.setFont(new Font("Lucida Sans", Font.ITALIC, 20));
		naslov.setForeground(Color.red);
		add(naslov, "center, span");
		
		JLabel ocena = new JLabel("Ocena: ");
		ocena.setFont(new Font("Lucida Sans", Font.ITALIC, 14));
		JLabel star = new JLabel(new ImageIcon("data/images/stars/"+(int)r.getOcena() + ".png"));
		
		add(ocena);
		add(star, "wrap");
		
		JLabel tezina = new JLabel("Tezina: ");
		tezina.setFont(new Font("Lucida Sans", 2, 14));
		JLabel tez = new JLabel(new ImageIcon("data/images/tezina/"+ r.getTezina().toString() + ".png"));
		
		add(tezina);
		add(tez, "center, wrap");
		
		JLabel vreme = new JLabel("Vreme pripreme: ");
		vreme.setFont(new Font("Lucida Sans", 2, 14));
		
		JLabel time = new JLabel(r.getVremePripreme() + " min");
		time.setFont(new Font("Lucida Sans", 2, 14));
		add(vreme);
		add(time, "wrap");
		
		JLabel datum = new JLabel(r.getDatum().toString());
		datum.setFont(new Font("Lucida Sans", 0, 10));
		add(datum, "align left");
		JLabel pregledi = new JLabel(r.getPregleda() + " pregleda");
		pregledi.setFont(new Font("Lucida Sans", 0, 10));
		add(pregledi, "align right, wrap");
	}

}
