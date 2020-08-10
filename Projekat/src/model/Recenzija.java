package model;

import java.time.LocalDateTime;

public class Recenzija {
	private String komentar;
	private LocalDateTime datum;
	private RegistrovaniKorisnik korisnik;
	private Recept recept;
	
	public Recenzija(String komentar, LocalDateTime datum, RegistrovaniKorisnik korisnik, Recept recept) {
		super();
		this.komentar = komentar;
		this.datum = datum;
		this.korisnik = korisnik;
		this.recept = recept;
	}
	public String getKomentar() {
		return komentar;
	}
	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}
	public LocalDateTime getDatum() {
		return datum;
	}
	public void setDatum(LocalDateTime datum) {
		this.datum = datum;
	}
	public RegistrovaniKorisnik getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(RegistrovaniKorisnik korisnik) {
		this.korisnik = korisnik;
	}
	public Recept getRecept() {
		return recept;
	}
	public void setRecept(Recept recept) {
		this.recept = recept;
	}
	
	
}
