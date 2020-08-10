package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Recept {
	private int id, vremePripreme;
	private String naziv, opis, koraci, youtubeLink;
	private float ocena;
	private Tezina tezina;
	private boolean uredjen;
	private LocalDateTime datum;
	private ArrayList<UredjajUReceptu> uredjaji;
	private ArrayList<Sastojak> sastojci;
	private ArrayList<Kategorija> kategorije;
	private RegistrovaniKorisnik autor;
	
	public void setAutor(RegistrovaniKorisnik autor) {
		this.autor = autor;
	}
	public ArrayList<UredjajUReceptu> getUredjaji() {
		return uredjaji;
	}
	public ArrayList<Sastojak> getSastojci() {
		return sastojci;
	}
	public ArrayList<Kategorija> getKategorije() {
		return kategorije;
	}
	public RegistrovaniKorisnik getAutor() {
		return autor;
	}
	public ArrayList<Ocena> getOcene() {
		return ocene;
	}
	public ArrayList<Recenzija> getRecenzije() {
		return recenzije;
	}
	private ArrayList<Ocena> ocene;
	private ArrayList<Recenzija> recenzije;
	
	public Recept(int id, int vremePripreme, String naziv, String opis, String koraci, String youtubeLink, float ocena,
			Tezina tezina, boolean uredjen, LocalDateTime datum, RegistrovaniKorisnik autor) {
		super();
		this.id = id;
		this.vremePripreme = vremePripreme;
		this.naziv = naziv;
		this.opis = opis;
		this.koraci = koraci;
		this.youtubeLink = youtubeLink;
		this.ocena = ocena;
		this.tezina = tezina;
		this.uredjen = uredjen;
		this.datum = datum;
		this.autor = autor;
	}
	public Recept(int id, int vremePripreme, String naziv, String opis, String koraci, String youtubeLink, float ocena,
			Tezina tezina, boolean uredjen, LocalDateTime datum) {
		super();
		this.id = id;
		this.vremePripreme = vremePripreme;
		this.naziv = naziv;
		this.opis = opis;
		this.koraci = koraci;
		this.youtubeLink = youtubeLink;
		this.ocena = ocena;
		this.tezina = tezina;
		this.uredjen = uredjen;
		this.datum = datum;
	}
	public Recept(int id, int vremePripreme, String naziv, String opis, String koraci, float ocena, Tezina tezina,
			boolean uredjen, LocalDateTime datum) {
		super();
		this.id = id;
		this.vremePripreme = vremePripreme;
		this.naziv = naziv;
		this.opis = opis;
		this.koraci = koraci;
		this.ocena = ocena;
		this.tezina = tezina;
		this.uredjen = uredjen;
		this.datum = datum;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVremePripreme() {
		return vremePripreme;
	}
	public void setVremePripreme(int vremePripreme) {
		this.vremePripreme = vremePripreme;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public String getKoraci() {
		return koraci;
	}
	public void setKoraci(String koraci) {
		this.koraci = koraci;
	}
	public String getYoutubeLink() {
		return youtubeLink;
	}
	public void setYoutubeLink(String youtubeLink) {
		this.youtubeLink = youtubeLink;
	}
	public float getOcena() {
		return ocena;
	}
	public void setOcena(float ocena) {
		this.ocena = ocena;
	}
	public Tezina getTezina() {
		return tezina;
	}
	public void setTezina(Tezina tezina) {
		this.tezina = tezina;
	}
	public boolean isUredjen() {
		return uredjen;
	}
	public void setUredjen(boolean uredjen) {
		this.uredjen = uredjen;
	}
	public LocalDateTime getDatum() {
		return datum;
	}
	public void setDatum(LocalDateTime datum) {
		this.datum = datum;
	}
}
