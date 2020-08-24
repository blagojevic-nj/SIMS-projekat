package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;

public class KategorijaPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private ArrayList<Integer> kategorije;
	private ArrayList<JCheckBox> boksovi;
	public DodavanjeRecepta parent;
	public KategorijaPanel() {
		setBounds(560, 80, 210, 150);
		setLayout(null);
		JPanel lista = new JPanel(new MigLayout());
		kategorije = new ArrayList<Integer>();
		boksovi = new ArrayList<JCheckBox>();
		
		for(String s: MainWindow.katM.getNaziviKategorija()) {
			JCheckBox cb = new JCheckBox(s);
			boksovi.add(cb);
			lista.add(cb, "wrap");
		}
		
		JButton potvrda = new JButton("Potvrda");
		potvrda.setBounds(0, 130, 210, 20);
		add(potvrda);
		
		JScrollPane pane = new JScrollPane(lista);
		pane.setBounds(0,0, 210, 130);
		add(pane);
		
		
		potvrda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				kategorije.clear();
				for(JCheckBox cb: boksovi) {
					if(cb.isSelected()) {
						kategorije.add(MainWindow.katM.getKategorija(cb.getText()).getSifra());
					}
				}
				
				KategorijaPanel.this.setVisible(false);
				if(parent != null) {
					KategorijaPanel.this.parent.blokada(true);
				}		
			}	
		});
	}
	public ArrayList<Integer> getKategorije() {
		return kategorije;
	}
	public void setKategorije(ArrayList<Integer> kategorije) {
		this.kategorije = kategorije;
	}
}
