package view;

import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JList;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import manageri.KategorijaManager;
import model.Kategorija;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KategorijePanel extends JPanel {

	private KategorijaManager manager;
	private ArrayList<Kategorija> biraneKategorije = new ArrayList<Kategorija>();
	private JPanel panel;
	private CardLayout layout;
	private static final long serialVersionUID = 1L;

	public ArrayList<Kategorija> getBiraneKategorije() {
		return biraneKategorije;
	}

	/**
	 * Panela koja se prikazuje kad biramo kategorije
	 */
	public KategorijePanel(JPanel containerPanel, CardLayout contentLayout) {
		panel = containerPanel;
		layout = contentLayout;
		manager = KategorijaManager.getInstance();
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[grow]", "[grow][]"));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 0,grow");
		
		DefaultListModel<CheckboxListItem> l1 = new DefaultListModel<>();  
		for (String kategorija : manager.getNaziviKategorija()) {
			CheckboxListItem box = new CheckboxListItem(kategorija);
			l1.addElement(box);
		}
		
		JList<CheckboxListItem> list = new JList<>(l1);
		list.setCellRenderer(new CheckBoxListCellRenderer());
	    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		
		list.addMouseListener(new MouseAdapter() {
	         public void mouseClicked(MouseEvent event) {
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
		
		JButton btnNewButton = new JButton("Dodaj kategorije recepta");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				for (int i = 0; i< list.getModel().getSize();i++) {
					if(list.getModel().getElementAt(i) instanceof CheckboxListItem) {
						CheckboxListItem kategorija = (CheckboxListItem)list.getModel().getElementAt(i);
						if((kategorija).isSelected()) {
							Kategorija birana = manager.getKategorija((kategorija).getText());
							biraneKategorije.add(birana);
					}	
					}
				}
				layout.show(panel, "addRecept");
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(btnNewButton, "cell 0 1,growx");

	}

}