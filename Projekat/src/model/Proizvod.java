package model;

import java.util.ArrayList;

public class Proizvod {
	private int sifra;
	private String naziv;
	private ArrayList<Integer> recepti;
	
	public Proizvod() {
		this.recepti = new ArrayList<Integer>();
	}
	public Proizvod(int sifra, String naziv) {
		super();
		this.sifra = sifra;
		this.naziv = naziv;
		this.recepti = new ArrayList<Integer>();
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
	public ArrayList<Integer> getRecepti() {
		return recepti;
	}
	public void setRecepti(ArrayList<Integer> recepti) {
		this.recepti = recepti;
	}
	
	public void addRecept(int recept) {
		recepti.add(recept);
	}
	
	public boolean deleteRecept(Integer recept) {
		return recepti.remove(recept);
	}
}
