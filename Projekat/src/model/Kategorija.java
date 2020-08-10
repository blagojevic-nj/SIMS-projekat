package model;

public class Kategorija {
	private int sifra;
	private String naziv;
	
	public Kategorija(int sifra, String naziv) {
		super();
		this.sifra = sifra;
		this.naziv = naziv;
	}
	public int getSifra() {
		return sifra;
	}
	public void setSifra(int sifra) {
		this.sifra = sifra;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
}
