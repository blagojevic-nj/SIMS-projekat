package model;

public class Ocena {
	private TipOcene tip;
	private Recept recept;
	private RegistrovaniKorisnik korisnik;
	
	public Ocena(TipOcene tip, Recept recept, RegistrovaniKorisnik korisnik) {
		super();
		this.tip = tip;
		this.recept = recept;
		this.korisnik = korisnik;
	}
	public TipOcene getTip() {
		return tip;
	}
	public void setTip(TipOcene tip) {
		this.tip = tip;
	}
	public Recept getRecept() {
		return recept;
	}
	public void setRecept(Recept recept) {
		this.recept = recept;
	}
	public RegistrovaniKorisnik getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(RegistrovaniKorisnik korisnik) {
		this.korisnik = korisnik;
	}
}
