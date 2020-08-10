package model;

public class Mesto {
	private int ppt;
	private String naziv;
	
	public Mesto(int ppt, String naziv) {
		super();
		this.ppt = ppt;
		this.naziv = naziv;
	}
	public int getPpt() {
		return ppt;
	}
	public void setPpt(int ppt) {
		this.ppt = ppt;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
}
