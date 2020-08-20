package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import manageri.KategorijaManager;
import manageri.UredjajManager;
import model.Kategorija;
import model.Uredjaj;
import net.miginfocom.swing.MigLayout;

public class UredjajiPanel extends JPanel {
	
	
	private static final long serialVersionUID = 1L;
	
	private UredjajManager manager;
	private ArrayList<Uredjaj> biraniUredjaji = new ArrayList<Uredjaj>();

	/**
	 * Panel koji se prikazuje prilikom odabira uredjaja
	 */
	public UredjajiPanel() {
		manager = UredjajManager.getInstance();
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[grow]", "[grow][]"));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 0,grow");
		
		DefaultListModel<CheckboxListItem> l1 = new DefaultListModel<>();  
		for (String uredjaj : manager.getNaziviUredjaja()) {
			CheckboxListItem box = new CheckboxListItem(uredjaj);
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
		
		JButton btnNewButton = new JButton("Dodaj uredjaje recepta");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				for (int i = 0; i< list.getModel().getSize();i++) {
					if(list.getModel().getElementAt(i) instanceof CheckboxListItem) {
						CheckboxListItem uredjaj = (CheckboxListItem)list.getModel().getElementAt(i);
						if((uredjaj).isSelected()) {
							Uredjaj birana = manager.getUredjaj((uredjaj).getText());
							biraniUredjaji.add(birana);
					}	
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(btnNewButton, "cell 0 1,growx");

	}

}
