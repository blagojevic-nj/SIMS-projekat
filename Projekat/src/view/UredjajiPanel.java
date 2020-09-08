package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;
import model.UredjajUReceptu;

public class UredjajiPanel extends JPanel {
	public DodavanjeRecepta parent;
	private static final long serialVersionUID = 1L;
	private ArrayList<UredjajUReceptu> uredjaji;
	private ArrayList<StavkaUredjaja> stavke;

	public UredjajiPanel(MainWindow mw) {
		uredjaji = new ArrayList<UredjajUReceptu>();
		stavke = new ArrayList<StavkaUredjaja>();
		setLayout(null);

		setBounds(560, 160, 250, 200);

		JPanel lista = new JPanel(new MigLayout());
		for (String s : mw.uM.getNaziviUredjaja()) {
			StavkaUredjaja stavka = new StavkaUredjaja(s);
			stavke.add(stavka);
			lista.add(stavka.cb);
			lista.add(stavka.neophodan, "wrap");
		}

		JScrollPane pane = new JScrollPane(lista);
		pane.setBounds(0, 0, 250, 170);
		add(pane);

		JButton potvrda = new JButton("Potvrda");
		potvrda.setBounds(0, 170, 250, 30);
		add(potvrda);

		potvrda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				uredjaji.clear();
				for (StavkaUredjaja s : stavke) {
					if (s.cb.isSelected()) {
						UredjajUReceptu uur = new UredjajUReceptu(s.neophodan.isSelected(),
								mw.uM.getUredjaj(s.cb.getText()));
						uredjaji.add(uur);
					}
				}

				UredjajiPanel.this.setVisible(false);
				UredjajiPanel.this.parent.blokada(true);
			}

		});
	}

	public ArrayList<UredjajUReceptu> getUredjaji() {
		return uredjaji;
	}

	public void setUredjaji(ArrayList<UredjajUReceptu> uredjaji) {
		this.uredjaji = uredjaji;
	}
}

class StavkaUredjaja {

	public JCheckBox cb;
	public JCheckBox neophodan;

	public StavkaUredjaja(String s) {
		cb = new JCheckBox(s);
		neophodan = new JCheckBox("Neophodan");
	}
}
