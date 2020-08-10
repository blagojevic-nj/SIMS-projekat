package model;

public class UredjajUReceptu {
	private boolean pozeljan;
	private Recept recept;
	private Uredjaj uredjaj;
	
	public UredjajUReceptu(boolean pozeljan, Recept recept, Uredjaj uredjaj) {
		super();
		this.pozeljan = pozeljan;
		this.recept = recept;
		this.uredjaj = uredjaj;
	}
	public boolean isPozeljan() {
		return pozeljan;
	}
	public void setPozeljan(boolean pozeljan) {
		this.pozeljan = pozeljan;
	}
	public Recept getRecept() {
		return recept;
	}
	public void setRecept(Recept recept) {
		this.recept = recept;
	}
	public Uredjaj getUredjaj() {
		return uredjaj;
	}
	public void setUredjaj(Uredjaj uredjaj) {
		this.uredjaj = uredjaj;
	}
}
