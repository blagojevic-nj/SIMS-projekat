package model;

import java.util.ArrayList;

public class RegistrovaniKorisnik extends Korisnik {
	private boolean privilegovani;
	private Bedz bedz;
	private int brojPratilaca;
	private float prosecnaOcena;
	private ArrayList<RegistrovaniKorisnik> praceni;
	private ArrayList<Uredjaj> uredjaji;
	private ArrayList<Kategorija> kategorije;
	private ArrayList<Proizvod> proizvodi;
	private ArrayList<Recept> recepti, sacuvani;
	private ArrayList<Ocena> ocene;
	private ArrayList<Recenzija> recenzije;
	
	
	public ArrayList<RegistrovaniKorisnik> getPraceni() {
		return praceni;
	}

	public ArrayList<Uredjaj> getUredjaji() {
		return uredjaji;
	}

	public ArrayList<Kategorija> getKategorije() {
		return kategorije;
	}

	public ArrayList<Proizvod> getProizvodi() {
		return proizvodi;
	}

	public ArrayList<Recept> getRecepti() {
		return recepti;
	}

	public ArrayList<Recept> getSacuvani() {
		return sacuvani;
	}

	public ArrayList<Ocena> getOcene() {
		return ocene;
	}

	public ArrayList<Recenzija> getRecenzije() {
		return recenzije;
	}

	public RegistrovaniKorisnik(int id, String ime, String prezime, String email, Nalog nalog, boolean privilegovani,
			Bedz bedz, int brojPratilaca, float prosecnaOcena) {
		super(id, ime, prezime, email, nalog);
		this.privilegovani = privilegovani;
		this.bedz = bedz;
		this.brojPratilaca = brojPratilaca;
		this.prosecnaOcena = prosecnaOcena;
	}
	
	public boolean isPrivilegovani() {
		return privilegovani;
	}
	public void setPrivilegovani(boolean privilegovani) {
		this.privilegovani = privilegovani;
	}
	public Bedz getBedz() {
		return bedz;
	}
	public void setBedz(Bedz bedz) {
		this.bedz = bedz;
	}
	public int getBrojPratilaca() {
		return brojPratilaca;
	}
	public void setBrojPratilaca(int brojPratilaca) {
		this.brojPratilaca = brojPratilaca;
	}
	public float getProsecnaOcena() {
		return prosecnaOcena;
	}
	public void setProsecnaOcena(float prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}
	
	
}
