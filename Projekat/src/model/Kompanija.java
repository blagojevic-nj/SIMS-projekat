package model;

import java.util.ArrayList;

public class Kompanija {
	private String naziv, telefon, email;
	private Mesto mesto;
	private ArrayList<Proizvod> proizvodi;
	
	public ArrayList<Proizvod> getProizvodi() {
		return proizvodi;
	}
	public Kompanija(String naziv, String telefon, String email) {
		super();
		this.naziv = naziv;
		this.telefon = telefon;
		this.email = email;
	}
	public Kompanija(String naziv, String telefon, String email, Mesto mesto) {
		super();
		this.naziv = naziv;
		this.telefon = telefon;
		this.email = email;
		this.mesto = mesto;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Mesto getMesto() {
		return mesto;
	}
	public void setMesto(Mesto mesto) {
		this.mesto = mesto;
	}
	
	public void addProizvod(Proizvod proizvod) {
		proizvodi.add(proizvod);
	}
	
	public boolean deleteProizvod(Proizvod proizvod) {
		return proizvodi.remove(proizvod);
	}
}
