package model;

public class Korisnik {
	private int id;
	private String ime, prezime, email;
	private Nalog nalog;
	
	public Korisnik(int id, String ime, String prezime, String email, Nalog nalog) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.nalog = nalog;
	}
	
	public Korisnik(int id, String ime, String prezime, String email) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Nalog getNalog() {
		return nalog;
	}
	public void setNalog(Nalog nalog) {
		this.nalog = nalog;
	}
}
