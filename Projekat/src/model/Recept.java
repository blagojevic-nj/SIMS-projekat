package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Recept {
	private int id;
	private String naziv, opis, koraci, youtubeLink;
	private float ocena;
	private Tezina tezina;
	private int vremePripreme;
	private boolean uredjen;
	private LocalDate datum;
	private ArrayList<UredjajUReceptu> uredjaji;
	private ArrayList<Sastojak> sastojci;
	private ArrayList<Integer> kategorije;
	private String autor;	//korisnicko ime autora
	private HashMap<Integer, TipOcene> ocene; // sifra korisnika i njegova ocena
	private ArrayList<Recenzija> recenzije;
	
	public Recept(int id, int vremePripreme, String naziv, String opis, String koraci, String youtubeLink, float ocena,
			Tezina tezina, boolean uredjen, LocalDate datum, RegistrovaniKorisnik autor) {
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
		this.autor = autor.getKorisnickoIme();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getVremePripreme() {
		return vremePripreme;
	}
	public void setVremePripreme(int vremePripreme) {
		this.vremePripreme = vremePripreme;
	}
	public boolean isUredjen() {
		return uredjen;
	}
	public void setUredjen(boolean uredjen) {
		this.uredjen = uredjen;
	}
	public LocalDate getDatum() {
		return datum;
	}
	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}
	public ArrayList<UredjajUReceptu> getUredjaji() {
		return uredjaji;
	}
	public void setUredjaji(ArrayList<UredjajUReceptu> uredjaji) {
		this.uredjaji = uredjaji;
	}
	public ArrayList<Sastojak> getSastojci() {
		return sastojci;
	}
	public void setSastojci(ArrayList<Sastojak> sastojci) {
		this.sastojci = sastojci;
	}
	public ArrayList<Integer> getKategorije() {
		return kategorije;
	}
	public void setKategorije(ArrayList<Integer> kategorije) {
		this.kategorije = kategorije;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public HashMap<Integer, TipOcene> getOcene() {
		return ocene;
	}
	public void setOcene(HashMap<Integer, TipOcene> ocene) {
		this.ocene = ocene;
	}
	public ArrayList<Recenzija> getRecenzije() {
		return recenzije;
	}
	public void setRecenzije(ArrayList<Recenzija> recenzije) {
		this.recenzije = recenzije;
	}

}
