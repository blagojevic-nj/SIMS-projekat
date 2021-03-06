package model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import manageri.LocalDateTimeDeserializer;
import manageri.LocalDateTimeSerializer;

public class Recenzija {
	private String komentar;
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime datum;
	private String idKorisnika;

	public Recenzija() {
	}

	public Recenzija(String komentar, LocalDateTime datum, String idKorisnika) {
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

	public String getIdKorisnika() {
		return idKorisnika;
	}

	public void setIdKorisnika(String idKorisnika) {
		this.idKorisnika = idKorisnika;
	}

}
