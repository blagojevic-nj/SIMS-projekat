package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import model.Recept;

public class ReceptiPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Recept> recepti;
	
	private static JPanel gornjiDeo, donjiDeo;
	private static JScrollPane pane;
	public ReceptiPanel(ArrayList<Recept> r) {
		setLayout(null);
		recepti = r;
		setBounds(0, 0, 1040, 650);
		
		donjiDeo = new JPanel(new MigLayout("wrap 2", "[][]20[]", "[]20[]"));
		
		for(Recept rec: recepti) {
			MaliPrikazRecepta mpr = new MaliPrikazRecepta(rec);
			this.add(mpr);
		}
		
		pane = new JScrollPane(donjiDeo);
		pane.setBounds(0,  50, 1040, 600);
		add(pane);
		
		gornjiDeo = new JPanel(null);
		gornjiDeo.setBounds(0, 0, 1040, 50);
		gornjiDeo.setBackground(Color.gray);
		
		JLabel sort = new JLabel("Sortiraj: ");
		sort.setBounds(10, 10, 100, 30);
		gornjiDeo.add(sort);
		
		JComboBox<String> cB = new JComboBox<String>();
		cB.addItem("------");
		cB.addItem("Naziv [A-Z]");
		cB.addItem("Naziv [Z-A]");
		cB.addItem("Od najlaksih");
		cB.addItem("Od najtezih");
		cB.addItem("Oceni");
		cB.addItem("Od najstarijih");
		cB.addItem("Od najnovijih");
		cB.addItem("Po popularnosti");
		
		cB.setBounds(80,  10, 150, 30);
		gornjiDeo.add(cB);
		
		JTextField search = new JTextField("Unesite recept koji trazite");
		search.setBounds(320, 10, 400, 30);
		
		search.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				JTextField src = (JTextField)e.getSource();
				src.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				JTextField src = (JTextField)e.getSource();
				if(src.getText().equals(""))
					src.setText("Unesite recept koji trazite");
			}
			
		});
		
		JButton searchBtn = new JButton("Pretrazi", new ImageIcon("data/images/search.png"));
		searchBtn.setBounds(910, 10, 120, 30);
		gornjiDeo.add(search);
		gornjiDeo.add(searchBtn);
		
		add(gornjiDeo);
		
	}
	
	void blokada(boolean b) {
		for(Component c: gornjiDeo.getComponents()) {
			c.setEnabled(b);
		}
		for(Component c: donjiDeo.getComponents()) {
			c.setEnabled(b);
		}
		
		pane.getVerticalScrollBar().setEnabled(b);
		pane.setWheelScrollingEnabled(b);
		
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		blokada(enabled);
	}
	
	
}
