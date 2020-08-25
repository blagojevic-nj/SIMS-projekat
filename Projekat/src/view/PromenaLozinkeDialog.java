package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;

import model.Nalog;
import net.miginfocom.swing.MigLayout;

public class PromenaLozinkeDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	public PromenaLozinkeDialog(NalogInfoPanel parent, Nalog nalog) {
		setTitle("Promena lozinke");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(400, 250);
		setResizable(false);
		setModal(true);
		setLayout(new MigLayout("wrap 2", "[]10[]", "[]20[]20[]"));
		
		add(new JLabel("Aktuelna lozinka:"));
		JPasswordField aktuelna = new JPasswordField(20);
		add(aktuelna);
		
		add(new JLabel("Nova lozinka:"));
		JPasswordField nova = new JPasswordField(20);
		add(nova);
		
		add(new JLabel("Nova lozinka (ponovo):"));
		JPasswordField nova1 = new JPasswordField(20);
		add(nova1);
		
		JLabel napomena = new JLabel("");
		napomena.setForeground(Color.RED);
		add(napomena);
		add(new JSeparator());
		
		JButton sacuvajBtn = new JButton("Sacuvaj");
		add(sacuvajBtn);
		sacuvajBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!nalog.getLozinka().equals(String.valueOf(aktuelna.getPassword()))) {
					napomena.setText("Pogresna lozinka!");
					return;
				}
				if (String.valueOf(nova.getPassword()).length() < 8) {
					napomena.setText("Prekratka lozinka!");
					return;
				}
				if (!String.valueOf(nova.getPassword()).equals(String.valueOf(nova1.getPassword()))) {
					napomena.setText("nova1 i nova2 nisu podudarne!");
					return;
				}
				nalog.setLozinka(String.valueOf(nova.getPassword()));
				JOptionPane.showMessageDialog(null, "Nova lozinka je sacuvana.");
				PromenaLozinkeDialog.this.dispose();
			}
		});
		setLocationRelativeTo(parent);
	}
}
