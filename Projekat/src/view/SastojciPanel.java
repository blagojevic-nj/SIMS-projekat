package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import manageri.KategorijaManager;
import manageri.ProizvodManager;
import model.Kategorija;
import model.Proizvod;
import model.Sastojak;
import net.miginfocom.swing.MigLayout;

public class SastojciPanel extends JPanel {
	
	private ProizvodManager manager;
	private ArrayList<Sastojak> biraniSastojci;
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public SastojciPanel() {
		manager = ProizvodManager.getInstance();
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[grow]", "[grow][]"));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "flowx,cell 0 0,grow");
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		for (String kategorija : manager.getNaziviProizvoda()) {
			JCheckBox box = new JCheckBox(kategorija);
			list.add(box);
		}
		
		JButton btnNewButton = new JButton("Dodaj sastojke receptu");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				for (JCheckBox proizvod : (JCheckBox[])list.getComponents()) {
					if(proizvod.isSelected()) {
						Proizvod birana = manager.getProizvod(proizvod.getText());
						//biraniSastojci.add(birana);
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(btnNewButton, "cell 0 1,growx");
		
		JButton btnNewButton_1 = new JButton("kreiraj novi");
		add(btnNewButton_1, "cell 0 0,alignx right");

	}

}
