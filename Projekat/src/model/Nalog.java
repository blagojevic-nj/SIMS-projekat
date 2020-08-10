package model;

public class Nalog {
	private String korisnickoIme, lozinka;
	private TipNaloga tip;
	private Korisnik korisnik;
	
	public Nalog(String korisnickoIme, String lozinka, TipNaloga tip, Korisnik korisnik) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.tip = tip;
		this.korisnik = korisnik;
	}
	
	public Nalog(String korisnickoIme, String lozinka, TipNaloga tip) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.tip = tip;
	}
	
	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
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
}
