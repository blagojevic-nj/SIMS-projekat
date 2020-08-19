package view;

import java.awt.Component;

class CheckboxListItem extends Component{
	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String label;
	   private boolean isSelected = false;
	 
	   public CheckboxListItem(String label) {
	      this.label = label;
	   }
	 
	   public boolean isSelected() {
	      return isSelected;
	   }
	 
	   public void setSelected(boolean isSelected) {
	      this.isSelected = isSelected;
	   }
	 
	   public String toString() {
	      return label;
	   }

	public String getText() {
		return label;
	}
	}
	 