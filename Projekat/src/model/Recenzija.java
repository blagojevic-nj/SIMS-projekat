package model;

import java.time.LocalDateTime;

public class Recenzija {
	private String komentar;
	private LocalDateTime datum;
	private int idKorisnika;
	
	public Recenzija(String komentar, LocalDateTime datum, int idKorisnika) {
		super();
		this.komentar = komentar;
		this.datum = datum;
		this.idKorisnika = idKorisnika;
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
	public int getIdKorisnika() {
		return idKorisnika;
	}
	public void setIdKorisnika(int idKorisnika) {
		this.idKorisnika = idKorisnika;
	}

	
}
