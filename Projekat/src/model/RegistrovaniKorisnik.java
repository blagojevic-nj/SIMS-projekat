package model;

import java.util.ArrayList;

public class RegistrovaniKorisnik extends Korisnik {
	private boolean privilegovani;
	private Bedz bedz;
	private int brojPratilaca;
	private float prosecnaOcena;
	private ArrayList<String> praceni;
	private ArrayList<Integer> uredjaji;
	private ArrayList<Integer> kategorije;
	private ArrayList<Integer> proizvodi;
	private ArrayList<Integer> recepti;
	private ArrayList<Integer> sacuvaniRecepti;

	public RegistrovaniKorisnik() {
	}

	public RegistrovaniKorisnik(int id, String ime, String prezime, String email, String korisnickoIme,
			boolean privilegovani, Bedz bedz, int brojPratilaca, float prosecnaOcena) {
		super(id, ime, prezime, email, korisnickoIme);
		this.privilegovani = privilegovani;
		this.bedz = bedz;
		this.brojPratilaca = brojPratilaca;
		this.prosecnaOcena = prosecnaOcena;
		this.recepti = new ArrayList<Integer>();
		this.praceni = new ArrayList<String>();
		this.kategorije = new ArrayList<Integer>();
		this.proizvodi = new ArrayList<Integer>();
		this.uredjaji = new ArrayList<Integer>();
		this.sacuvaniRecepti = new ArrayList<Integer>();
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

	public ArrayList<String> getPraceni() {
		return praceni;
	}

	public void setPraceni(ArrayList<String> praceni) {
		this.praceni = praceni;
	}

	public ArrayList<Integer> getUredjaji() {
		return uredjaji;
	}

	public void setUredjaji(ArrayList<Integer> uredjaji) {
		this.uredjaji = uredjaji;
	}

	public ArrayList<Integer> getKategorije() {
		return kategorije;
	}

	public void setKategorije(ArrayList<Integer> kategorije) {
		this.kategorije = kategorije;
	}

	public ArrayList<Integer> getProizvodi() {
		return proizvodi;
	}

	public void setProizvodi(ArrayList<Integer> proizvodi) {
		this.proizvodi = proizvodi;
	}

	public ArrayList<Integer> getRecepti() {
		return recepti;
	}

	public void setRecepti(ArrayList<Integer> recepti) {
		this.recepti = recepti;
	}

	public ArrayList<Integer> getSacuvaniRecepti() {
		return sacuvaniRecepti;
	}

	public void setSacuvaniRecepti(ArrayList<Integer> sacuvaniRecepti) {
		this.sacuvaniRecepti = sacuvaniRecepti;
	}

	public void addPracen(String pracen) {
		praceni.add(pracen);
	}

	public boolean deletePracen(String pracen) {
		return praceni.remove(pracen);
	}

	public void addRecept(int recept) {
		recepti.add(recept);
	}

	public boolean deleteRecept(Integer recept) {
		return recepti.remove(recept);
	}

	public void addSacuvaniRecept(int recept) {
		sacuvaniRecepti.add(recept);
	}

	public boolean deleteSacuvaniRecept(Integer recept) {
		return sacuvaniRecepti.remove(recept);
	}

	public void addUredjaj(int uredjaj) {
		uredjaji.add(uredjaj);
	}

	public boolean deleteUredjaj(Integer uredjaj) {
		return uredjaji.remove(uredjaj);
	}

	public void addKategorija(int kategorija) {
		kategorije.add(kategorija);
	}

	public boolean deleteKategorija(Integer kategorija) {
		return kategorije.remove(kategorija);
	}

	public void addProizvod(int proizvod) {
		proizvodi.add(proizvod);
	}

	public boolean deleteProizvod(Integer proizvod) {
		return proizvodi.remove(proizvod);
	}
}
