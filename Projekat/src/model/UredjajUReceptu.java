package model;

public class UredjajUReceptu {
	private boolean pozeljan;
	private Uredjaj uredjaj;

	public UredjajUReceptu() {
	}

	public UredjajUReceptu(boolean pozeljan, Uredjaj uredjaj) {
		super();
		this.pozeljan = pozeljan;
		this.uredjaj = uredjaj;
	}

	public boolean isPozeljan() {
		return pozeljan;
	}

	public void setPozeljan(boolean pozeljan) {
		this.pozeljan = pozeljan;
	}

	public Uredjaj getUredjaj() {
		return uredjaj;
	}

	public void setUredjaj(Uredjaj uredjaj) {
		this.uredjaj = uredjaj;
	}
}
