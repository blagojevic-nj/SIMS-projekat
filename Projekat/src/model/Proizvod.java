package model;

public class Proizvod {
	private int sifra;
	private String naziv;
	
	public Proizvod(int sifra, String naziv) {
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
