package model;

import java.util.ArrayList;

public class Aplikacija {
	private String naziv;
	ArrayList<Nalog> nalozi;
	ArrayList<Uredjaj> uredjaji;
	ArrayList<Proizvod> proizvodi;
	ArrayList<Recept> recepti;
	ArrayList<Kategorija> kategorije;
	
	public Aplikacija(String naziv) {
		super();
		this.naziv = naziv;
	}

	public String getNaziv() {
		return naziv;
	}

	public ArrayList<Nalog> getNalozi() {
		return nalozi;
	}

	public ArrayList<Uredjaj> getUredjaji() {
		return uredjaji;
	}

	public ArrayList<Proizvod> getProizvodi() {
		return proizvodi;
	}

	public ArrayList<Recept> getRecepti() {
		return recepti;
	}

	public ArrayList<Kategorija> getKategorije() {
		return kategorije;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
}
