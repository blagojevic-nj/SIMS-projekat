package model;

public class Nalog {
	private String korisnickoIme, lozinka;
	private TipNaloga tip;
	private int idKorisnika;
	
	public Nalog(String korisnickoIme, String lozinka, TipNaloga tip, int idKorisnika) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.tip = tip;
		this.idKorisnika = idKorisnika;
	}
	
	public Nalog(String korisnickoIme, String lozinka, TipNaloga tip) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.tip = tip;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	public TipNaloga getTip() {
		return tip;
	}
	public void setTip(TipNaloga tip) {
		this.tip = tip;
	}
	public int getIdKorisnika() {
		return idKorisnika;
	}
	public void setIdKorisnika(int idKorisnika) {
		this.idKorisnika = idKorisnika;
	}
}
