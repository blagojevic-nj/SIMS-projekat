package view;

import javax.swing.JPanel;

import manageri.KorisnikManager;
import model.Nalog;
import javax.swing.JSplitPane;
import javax.swing.UIManager;

import java.awt.Choice;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;

public class UserSettingsPanel extends JPanel {
	private static final long serialVersionUID = -1716355102957653930L;
	JPanel main;
	KorisnikManager km;
	Nalog trenutni;
	
	public UserSettingsPanel(KorisnikManager manager, Nalog trenutniNalog) {
		km = manager;
		trenutni = trenutniNalog;
		setLayout(null);
		setBounds(500, 30, 1040, 650);
		
		main = new JPanel();
		main.setLayout(null);
		main.setBounds(10, 10, 285, 630);
		add(main);
		setBackground(Color.white);
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 472, 285, 60);
		lblNewLabel.setForeground(Color.black);
		main.add(lblNewLabel);
		
		
		lblNewLabel.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	lblNewLabel.setBackground(Color.white);
		    	lblNewLabel.setOpaque(true);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	lblNewLabel.setBackground(UIManager.getColor("control"));
		    }
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
