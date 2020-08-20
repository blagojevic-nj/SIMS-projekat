package view;

import javax.swing.JPanel;

import manageri.KorisnikManager;
import model.Nalog;
import javax.swing.JSplitPane;
import java.awt.Choice;

import javax.swing.JButton;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;

public class UserSettingsPanel extends JPanel {
	
	KorisnikManager km;
	Nalog trenutni;
	
	public UserSettingsPanel(KorisnikManager manager, Nalog trenutniNalog) {
		km = manager;
		trenutni = trenutniNalog;
		setLayout(null);
		setBounds(60, 30, 1040, 650);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 285, 630);
		add(panel);

	}
}
