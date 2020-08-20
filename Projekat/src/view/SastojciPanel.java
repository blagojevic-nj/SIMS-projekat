package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import manageri.KategorijaManager;
import manageri.ProizvodManager;
import model.Kategorija;
import model.Proizvod;
import model.Sastojak;
import model.TipSastojka;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SastojciPanel extends JPanel {
	
	private ProizvodManager manager;
	private ArrayList<Sastojak> biraniSastojci = new ArrayList<Sastojak>();
	private static final long serialVersionUID = 1L;
	private JTextField txtKolicina;
	private JTextField txtMernaJedinica;
	private DefaultListModel<CheckboxListItem> l1 = new DefaultListModel<>();

	public ArrayList<Sastojak> getBiraniSastojci() {
		return biraniSastojci;
	}
	/**
	 * Panela koja se prikazuje kad biramo sastojke
	 */
	public SastojciPanel() {
		setToolTipText("proizvod");
		manager = ProizvodManager.getInstance();
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[grow][grow][][][][][][][][]", "[][grow][]"));
		
		JComboBox<String> comboBox = new JComboBox<String>();
		for (String proizvod : manager.getNaziviProizvoda()) {
			comboBox.addItem(proizvod);
		}
		add(comboBox, "flowx,cell 0 0,growx");
		
		txtKolicina = new JTextField();
		txtKolicina.setText("kolicina");
		txtKolicina.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				JTextField src = (JTextField)e.getSource();
				src.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				JTextField src = (JTextField)e.getSource();
				if(src.getText().equals(""))
					src.setText("kolicina");
			}
			
		});
		
		JComboBox<TipSastojka> tip = new JComboBox<TipSastojka>();
		tip.addItem(TipSastojka.POZELJAN);
		tip.addItem(TipSastojka.ESENCIJALAN);
		tip.addItem(TipSastojka.PO_IZBORU);
		add(tip, "cell 1 0,growx");
		add(txtKolicina, "cell 2 0,alignx left");
		txtKolicina.setColumns(10);
		
		txtMernaJedinica = new JTextField();
		txtMernaJedinica.setText("Merna jedinica");
		txtMernaJedinica.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				JTextField src = (JTextField)e.getSource();
				src.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				JTextField src = (JTextField)e.getSource();
				if(src.getText().equals(""))
					src.setText("Merna Jedinica");
			}
			
		});
		add(txtMernaJedinica, "cell 4 0");
		txtMernaJedinica.setColumns(10);
		
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "flowx,cell 0 1 5 1,grow");
		
		JList<CheckboxListItem> list = new JList<>(l1);
		list.setCellRenderer(new CheckBoxListCellRenderer());
	    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		
		list.addMouseListener(new MouseAdapter() {
	         public void mouseClicked(MouseEvent event) {
	        	if(list.getModel().getSize() == 0) {
	        		return;
	        	}
	            JList<CheckboxListItem> list =
	               (JList<CheckboxListItem>) event.getSource();
	 
	            // Get index of item clicked
	 
	            int index = list.locationToIndex(event.getPoint());
	            CheckboxListItem item = (CheckboxListItem) list.getModel()
	                  .getElementAt(index);
	 
	            // Toggle selected state
	 
	            item.setSelected(!item.isSelected());
	 
	            // Repaint cell
	 
	            list.repaint(list.getCellBounds(index, index));
	         }
	      });
		
		
		JButton btnDodavanjeListi = new JButton("dodaj sastojcima");
		btnDodavanjeListi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int kolicina = 0;
				try {
					kolicina = Integer.parseInt(txtKolicina.getText());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(new JFrame(), "Kolicina mora da bude broj!" , "Greska", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(txtMernaJedinica.getText().equals("") || txtMernaJedinica.getText().equals("Merna Jedinica")) {
					JOptionPane.showMessageDialog(new JFrame(), "Niste uneli mernu jedinicu!" , "Greska", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String jedinica = txtMernaJedinica.getText();
				
				CheckboxListItem box = new CheckboxListItem((String) comboBox.getSelectedItem()+
						" kolicina: "+Integer.toString(kolicina)+" "+jedinica + " " + tip.getSelectedItem());
				l1.addElement(box);
			}
		});
		add(btnDodavanjeListi, "cell 6 0,alignx right");
		
		JButton btnDodavanjeReceptu = new JButton("Dodaj sastojke receptu");
		btnDodavanjeReceptu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				for (int i = 0; i< list.getModel().getSize();i++) {
					if(list.getModel().getElementAt(i) instanceof CheckboxListItem) {
						CheckboxListItem sastojak = (CheckboxListItem)list.getModel().getElementAt(i);
						String[] tokeni = sastojak.getText().split(" kolicina:");
						String proizvod = tokeni[0];

						String[] tokeni2 = tokeni[1].split(" ");
						
						int kolicina = Integer.parseInt(tokeni2[1]);
						TipSastojka tipSastojka = TipSastojka.valueOf(tokeni2[tokeni2.length - 1]);
						
						String jedinica = tokeni2[2];
						if(tokeni2.length > 4) {
							for(int j = 3; j < tokeni2.length - 1;j++) {
								jedinica = jedinica + " " + tokeni2[j];
							}
						}
						
						Sastojak birana = new Sastojak(kolicina, jedinica, tipSastojka, proizvod);
						biraniSastojci.add(birana);
					}
				}
			}
		});
		
		JButton btnBrisiIzListe = new JButton("brisi iz liste");
		btnBrisiIzListe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				for (int i = 0; i< list.getModel().getSize();i++) {
					if(list.getModel().getElementAt(i) instanceof CheckboxListItem) {
						if(list.getModel().getElementAt(i).isSelected()) {
							l1.removeElementAt(i);
						}	
					}
				}
			}
		});
		add(btnBrisiIzListe, "cell 6 1");
		btnDodavanjeReceptu.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(btnDodavanjeReceptu, "cell 0 2 10 1,growx");

	}

}
