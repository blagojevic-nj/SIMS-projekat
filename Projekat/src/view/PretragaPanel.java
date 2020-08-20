package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import manageri.ProizvodManager;
import manageri.ReceptManager;

public class PretragaPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private ReceptManager rm = ReceptManager.getInstance();
	private ProizvodManager pm = ProizvodManager.getInstance();
	
	public PretragaPanel() {
		setBackground(Color.LIGHT_GRAY);
		setBounds(0, 0, 300, 700);
		setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(50, 100, 200, 5);
		add(separator);
		
		
		JLabel lblPretraga = new JLabel("Pretraga");
		lblPretraga.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblPretraga.setBounds(50, 30, 200, 60);
		add(lblPretraga);
		
		
		DefaultComboBoxModel<String> dml = new DefaultComboBoxModel<String>();
		for (String s : pm.getNaziviProizvoda()) dml.addElement(s);
		JComboBox<String> sastojciBox = new JComboBox<String>(dml);
		sastojciBox.setBounds(50, 140, 200, 25);
		add(sastojciBox);
		
	}

}
